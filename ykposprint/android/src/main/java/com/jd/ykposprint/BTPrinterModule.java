package com.jd.ykposprint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.jd.ykposprint.bt.BluetoothSearchActivity;
import com.jd.ykposprint.bt.BluetoothUtils;
import com.jd.ykposprint.entity.OmsOrder;
import com.jd.ykposprint.entity.OrderEntity;
import com.jd.ykposprint.template.OrderPrintDataMaker1;
import com.jd.ykposprint.template.PosOrderPrintDataMaker;
import com.jd.ykposprint.utils.Utils;

import java.io.IOException;
import java.util.List;

/**
 * Created by limingguang on 2018/8/25.
 */

public class BTPrinterModule extends ReactContextBaseJavaModule {
    public static final String SP_DEVICE_NAME = "SP_DEVICE_NAME";  //设备名
    public static final String SP_DEVICE_ADDRESS = "SP_DEVICE_ADDRESS"; //设备地址


    private ReactApplicationContext reactContext;

    public BTPrinterModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "BTPrinterModule";
    }

    @ReactMethod
    public void posPrint(ReadableMap json) {
        if (json == null) {
            Toast.makeText(reactContext, "打印内容为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (json.getInt("code") != 0) {
            Toast.makeText(reactContext, "打印报文错误!", Toast.LENGTH_SHORT).show();
            return;
        }

        String devicesAddress = SPUtils.getInstance().getString(SP_DEVICE_ADDRESS);
        if (TextUtils.isEmpty(devicesAddress)) {
            ToastUtils.showShort("请选择打印机！");
            selectBt();
            return;
        }

        if (BluetoothUtils.bluetoothSocket == null) {
            BluetoothUtils.bluetoothSocket = new BluetoothUtils(getCurrentActivity()).connectRemoteDevice(devicesAddress);
        }

        if (BluetoothUtils.bluetoothSocket == null) {
            ToastUtils.showShort("连接失败,请检查打印机！");
            selectBt();
        } else {
            try {
                OrderEntity orderEntity = Utils.serializeOrder(json);
                printPosOrder(orderEntity);
            } catch (JsonParseException e) {
                e.printStackTrace();
                ToastUtils.showShort("打印数据解析异常!");
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showShort("打印异常!");
            }
        }
    }

    @ReactMethod
    public void print(final int templateType, final String data) {

        if (TextUtils.isEmpty(data)) {
            ToastUtils.showShort("打印数据不能为空!");
            return;
        }
        String devicesAddress = SPUtils.getInstance().getString(SP_DEVICE_ADDRESS);
        if (TextUtils.isEmpty(devicesAddress)) {
            ToastUtils.showShort("请选择打印机！");
            selectBt();
            return;
        }

        if (BluetoothUtils.bluetoothSocket == null) {
            BluetoothUtils.bluetoothSocket = new BluetoothUtils(getCurrentActivity()).connectRemoteDevice(devicesAddress);
        }

        if (BluetoothUtils.bluetoothSocket == null) {
            ToastUtils.showShort("连接失败,请检查打印机！");
            selectBt();
        } else {
            try {
                switch (templateType) {
                    case 1:
                        OmsOrder omsOrder = new Gson().fromJson(data, OmsOrder.class);

                        if (!TextUtils.isEmpty(omsOrder.getApplet_code())) {
                            Handler mainHandler = new Handler(Looper.getMainLooper());

                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Glide.with(getReactApplicationContext())
                                            .asBitmap()
                                            .load(omsOrder.getApplet_code())
                                            .into(new SimpleTarget<Bitmap>(140, 140) {
                                                @Override
                                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                    omsOrder.bitmap = resource;
                                                    printOmsOrder(omsOrder);
                                                }

                                                @Override
                                                public void onLoadFailed(Drawable errorDrawable) {
                                                    // 下载失败回调
                                                    super.onLoadFailed(errorDrawable);
                                                    printOmsOrder(omsOrder);
                                                }
                                            });
                                }
                            });
                        } else {
                            printOmsOrder(omsOrder);
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        ToastUtils.showShort("打印数据解析异常!");
                        break;
                }
            } catch (JsonParseException e) {
                e.printStackTrace();
                ToastUtils.showShort("打印数据解析异常!");
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showShort("打印异常!");
            }

        }

    }

    /**
     * OMS打印
     */
    private void printOmsOrder(OmsOrder omsOrder) {
        new Thread() {
            @Override
            public void run() {
                try {
                    OrderPrintDataMaker1 maker = new OrderPrintDataMaker1();
                    List<byte[]> printData = maker.getPrintData(omsOrder, getReactApplicationContext());
                    for (byte[] d : printData) {
                        BluetoothUtils.bluetoothSocket.getOutputStream().write(d);
                        BluetoothUtils.bluetoothSocket.getOutputStream().flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    ToastUtils.showShort("打印异常!");
                }
            }
        }.start();
    }

    /**
     * POS打印
     */
    private void printPosOrder(OrderEntity orderEntity) {
        new Thread() {
            @Override
            public void run() {
                try {
                    PosOrderPrintDataMaker maker = new PosOrderPrintDataMaker();
                    List<byte[]> printData = maker.getPrintData(orderEntity, getReactApplicationContext());
                    for (byte[] d : printData) {
                        BluetoothUtils.bluetoothSocket.getOutputStream().write(d);
                        BluetoothUtils.bluetoothSocket.getOutputStream().flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    ToastUtils.showShort("打印异常!");
                }
            }
        }.start();
    }


    @ReactMethod
    public void selectBt() {
        Intent intent = new Intent(getReactApplicationContext(), BluetoothSearchActivity.class);
        getCurrentActivity().startActivity(intent);
    }
}
