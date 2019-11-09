package com.jd.print;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jd.print.constant.PrintConstant;
import com.jd.print.entity.OrderEntity;
import com.jd.print.impl.PrintImpl;
import com.jd.print.inter.IConnectCallback;
import com.jd.print.template.Temp1;
import com.jd.print.utils.ToastUtil;
import com.jd.print.utils.Utils;

/**
 * Created by limingguang on 2018/8/25.
 */

public class BTPrinterModule extends ReactContextBaseJavaModule {

    private ReactApplicationContext reactContext;
    private PrintImpl sPrinterImpl;

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
        if (sPrinterImpl == null) sPrinterImpl = new PrintImpl();


        if (json == null) {
            Toast.makeText(reactContext, "打印内容为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (json.getInt("code") != 0) {
            Toast.makeText(reactContext, "打印报文错误!", Toast.LENGTH_SHORT).show();
            return;
        }


        try {
            OrderEntity orderEntity = Utils.serializeOrder(json);

            sPrinterImpl.connectPrinter(new IConnectCallback() {
                @Override
                public void onPrinterConnectSuccess() {
                    Temp1.printNote(getReactApplicationContext().getResources(),sPrinterImpl,orderEntity);
                }

                @Override
                public void onPrinterConnectFailed(int errorCode) {
                    switch (errorCode) {
                        case PrintConstant.CONNECT_CLOSED:
                            ToastUtil.showShort(getReactApplicationContext(), getReactApplicationContext().getString(R.string.toast_close));
                            break;
                        default:
                            ToastUtil.showShort(getReactApplicationContext(), getReactApplicationContext().getString(R.string.toast_fail));
                            break;
                    }
                }
            });

        } catch (Exception ex) {
            Toast.makeText(reactContext, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
