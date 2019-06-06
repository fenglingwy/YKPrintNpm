package com.jd.ykposprint;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jd.ykposprint.entity.OrderEntity;
import com.jd.ykposprint.utils.PrintUtil;
import com.jd.ykposprint.utils.Utils;

/**
 * Created by limingguang on 2018/8/25.
 */

public class BTPrinterModule extends ReactContextBaseJavaModule {

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
    public void print(ReadableMap json) {

        PrintUtil.mContext = getReactApplicationContext();
//        try {
//            PrintUtil.printTicket(new OrderEntity());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if(json == null){
            Toast.makeText(reactContext,"打印内容为空!", Toast.LENGTH_SHORT).show();
            return;
        }

//        OrderEntity orderEntity = Utils.serializeOrder(json);
        if(json.getInt("code") != 0){
            Toast.makeText(reactContext,"打印报文错误!", Toast.LENGTH_SHORT).show();
            return;
        }


        try{
            OrderEntity orderEntity = Utils.serializeOrder(json);
//             1.打印销售小票
            PrintUtil.printTicket( orderEntity);
//            // 2.打印优惠信息
//            PrintUtil.printDeduct(orderEntity);
//            // 3.打印提货存根联
//            PrintUtil.printPickUpStub(orderEntity);
//            // 4.第四步打印提货单
//            for (int i =0; i< orderEntity.getContainerItemList().size(); i++){
//                PrintUtil.printPickUpBill(orderEntity, orderEntity.getContainerItemList().get(i));
//            }
            // 断开连接
//            PrintUtil.disconnect();
        } catch (Exception ex){
            Toast.makeText(reactContext, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
