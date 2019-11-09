package com.jd.ykposprint.utils;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;
import java.util.UUID;

public class PrintSocketHolder {
    public static final int ERROR_0 = 0;
    public static final int ERROR_1 = -1;
    public static final int ERROR_100 = -100;
    public static final int ERROR_2 = -2;
    public static final int ERROR_3 = -3;
    public static final int ERROR_4 = -4;
    public static final int ERROR_5 = -5;
    public static final int ERROR_6 = -6;
    public static final int STATE_0 = 0;
    public static final int STATE_1 = 1;
    public static final int STATE_2 = 2;
    public static final int STATE_3 = 3;
    public static final int STATE_4 = 4;
    private static final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothSocket bluetoothSocket;
    private String ip;
    private BluetoothDevice mDevice;
    private WeakReference<OnStateChangedListener> mListener;
    private OutputStream out;
    private int port = 9100;
    private Socket socket;

    public interface OnStateChangedListener {
        void onStateChanged(int i);
    }

    public PrintSocketHolder(BluetoothDevice device) {
        setDevice(device);
    }

    public PrintSocketHolder(String ip2, int port2) {
        setIp(ip2, port2);
    }

    public int createSocket() {
        onPrinterStateChanged(1);
        if (this.mDevice == null && this.ip == null) {
            return -5;
        }
        try {
            if (this.mDevice != null) {
                this.bluetoothSocket = this.mDevice.createRfcommSocketToServiceRecord(uuid);
                this.bluetoothSocket.connect();
            } else {
                this.socket = new Socket(this.ip, this.port);
            }
        } catch (Exception e) {
            try {
                if (this.mDevice != null) {
                    this.bluetoothSocket = (BluetoothSocket) this.mDevice.getClass().getMethod("createRfcommSocket", new Class[]{Integer.TYPE}).invoke(this.mDevice, new Object[]{Integer.valueOf(1)});
                    this.bluetoothSocket.connect();
                } else {
                    closeSocket();
                    return -2;
                }
            } catch (Exception e2) {
                closeSocket();
                return -2;
            }
        }
        return 0;
    }

    public int getOutputStream() {
        onPrinterStateChanged(2);
        try {
            if (this.mDevice == null || this.bluetoothSocket == null) {
                this.out = this.socket.getOutputStream();
            } else {
                this.out = this.bluetoothSocket.getOutputStream();
            }
            return 0;
        } catch (IOException e) {
            closeSocket();
            return -3;
        }
    }

    public boolean isSocketPrepared() {
        return ((this.bluetoothSocket == null && this.socket == null) || this.out == null) ? false : true;
    }

    public int sendData(List<byte[]> data) {
        onPrinterStateChanged(3);
        if (data == null || data.size() <= 0) {
            return 0;
        }
        for (byte[] item : data) {
            try {
                this.out.write(item);
                this.out.flush();
            } catch (IOException e) {
                closeSocket();
                return -4;
            }
        }
        return 0;
    }

    public int sendData(byte[] data) {
        onPrinterStateChanged(3);
        try {
            this.out.write(data);
            this.out.flush();
            return 0;
        } catch (IOException e) {
            closeSocket();
            return -4;
        }
    }

    public int sendData(byte[]... data) {
        onPrinterStateChanged(3);
        int length = data.length;
        int i = 0;
        while (i < length) {
            try {
                this.out.write(data[i]);
                this.out.flush();
                i++;
            } catch (IOException e) {
                closeSocket();
                return -4;
            }
        }
        return 0;
    }

    public int prepareSocket() {
        int create = createSocket();
        return create != 0 ? create : getOutputStream();
    }

    public int closeSocket() {
        onPrinterStateChanged(4);
        boolean error = false;
        try {
            if (this.out != null) {
                this.out.close();
                this.out = null;
            }
        } catch (IOException e) {
            this.out = null;
            error = true;
        }
        try {
            if (this.socket != null) {
                this.socket.close();
                this.socket = null;
            }
        } catch (IOException e2) {
            this.socket = null;
            error = true;
        }
        try {
            if (this.bluetoothSocket != null) {
                this.bluetoothSocket.close();
                this.bluetoothSocket = null;
            }
        } catch (IOException e3) {
            this.bluetoothSocket = null;
            error = true;
        }
        if (error) {
            return -6;
        }
        return 0;
    }

    public void onPrinterStateChanged(int state) {
        try {
            if (this.mListener != null) {
                ((OnStateChangedListener) this.mListener.get()).onStateChanged(state);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIp(String ip2, int port2) {
        this.ip = ip2;
        this.port = port2;
    }

    public void setDevice(BluetoothDevice device) {
        this.mDevice = device;
    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        this.mListener = new WeakReference<>(listener);
    }
}
