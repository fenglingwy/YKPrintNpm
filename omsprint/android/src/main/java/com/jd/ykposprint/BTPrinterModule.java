package com.jd.ykposprint;

import android.content.Intent;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.jd.ykposprint.bt.BluetoothSearchActivity;
import com.jd.ykposprint.bt.BluetoothUtils;
import com.jd.ykposprint.entity.OmsOrder;
import com.jd.ykposprint.template.OrderPrintDataMaker1;

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

//    @ReactMethod
//    public void print(ReadableMap json) {
//        print(1, "1");

//
//        PrintUtil.mContext = getReactApplicationContext();
////        try {
////            PrintUtil.printTicket(new OrderEntity());
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        if(json == null){
//            Toast.makeText(reactContext,"打印内容为空!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
////        OrderEntity orderEntity = Utils.serializeOrder(json);
//        if(json.getInt("code") != 0){
//            Toast.makeText(reactContext,"打印报文错误!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//
//        try{
//            OrderEntity orderEntity = Utils.serializeOrder(json);
////             1.打印销售小票
//            PrintUtil.printTicket( orderEntity);
////            // 2.打印优惠信息
////            PrintUtil.printDeduct(orderEntity);
////            // 3.打印提货存根联
////            PrintUtil.printPickUpStub(orderEntity);
////            // 4.第四步打印提货单
////            for (int i =0; i< orderEntity.getContainerItemList().size(); i++){
////                PrintUtil.printPickUpBill(orderEntity, orderEntity.getContainerItemList().get(i));
////            }
//            // 断开连接
//            PrintUtil.disconnect();
//        } catch (Exception ex){
//            Toast.makeText(reactContext, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }



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

            new Thread(){
                @Override
                public void run() {
                    try {
                        switch (templateType) {
                            case 1:
                                OmsOrder omsOrder = new Gson().fromJson(data, OmsOrder.class);
                                OrderPrintDataMaker1 maker = new OrderPrintDataMaker1();
                                List<byte[]> printData = maker.getPrintData(omsOrder);
                                for (byte[] d:printData) {
                                    BluetoothUtils.bluetoothSocket.getOutputStream().write(d);
                                    BluetoothUtils.bluetoothSocket.getOutputStream().flush();
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }


    @ReactMethod
    public void selectBt() {
        Intent intent = new Intent(getReactApplicationContext(), BluetoothSearchActivity.class);
        getCurrentActivity().startActivity(intent);
    }
}
