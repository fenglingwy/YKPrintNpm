package com.jd.ykposprint.utils;//package com.jd.ykposprint.utils;
//
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothClass;
//import android.bluetooth.BluetoothDevice;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.hardware.usb.UsbDevice;
//import android.widget.Toast;
//
//
//import com.example.bluetoothprintermodule.R;
//import com.goodbaby.bluetoothprintermodule.entity.CashEntity;
//import com.goodbaby.bluetoothprintermodule.entity.GoodEntity;
//import com.goodbaby.bluetoothprintermodule.entity.OrderEntity;
//import com.zebra.sdk.comm.BluetoothConnection;
//import com.zebra.sdk.comm.Connection;
//import com.zebra.sdk.comm.ConnectionException;
//import com.zebra.sdk.graphics.internal.ZebraImageAndroid;
//import com.zebra.sdk.printer.PrinterLanguage;
//import com.zebra.sdk.printer.ZebraPrinter;
//import com.zebra.sdk.printer.ZebraPrinterFactory;
//import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;
//
//import java.io.ByteArrayOutputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//import java.util.Set;
//
///**
// * Created by limingguang on 2018/8/23.
// */
//
//public class PrintUtil {
//
//    private static ZebraPrinter printer = null;
//    private static Connection printerConnection;
//
//    /**
//     * 1.销售小票
//     * @param orderEntity
//     */
//    public static void printTicket(Context context, OrderEntity orderEntity) throws Exception {
//        String noticeStr = "***特卖商品一经售出，恕不退换***";
//        String welcomeStr = "***谢谢惠顾***";
//        String rePrintStr = "***重印***";
//        int lines = orderEntity.getItemList().size();
//        if(printer == null){
//            printer = connect();
//        }
//        if (printer != null) {
//            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] bitmapdata = stream.toByteArray();
//
//            StringBuffer sb = new StringBuffer();
//            sb.append("! U1 JOURNAL\r\n");
//            sb.append("! U1 PAGE-WIDTH 600\r\n");
//            sb.append("! U1 SPEED 2\r\n");
//            sb.append("! U1 ENCODING UTF-8\r\n");
//            sb.append("! U1 LMARGIN 0\r\n");
//            sb.append("! U1 SETLP GBUNSG24.CPF 0 24\r\n");
//            sb.append("! U1 PCX 0 0 !<LOGO.PCX\r\n");
//            sb.append("! U1 SETBOLD 1\r\n");
//            sb.append("! U1 SETLF 10\r\n");
//            sb.append("! U1 LMARGIN 220 \r\n");
//            sb.append("! U1 SETLF 30\r\n");
//            sb.append("GB好孩子\r\n");
//            sb.append("! U1 SETBOLD 0\r\n");
//            sb.append("! U1 LMARGIN 0\r\n");
//            sb.append("! U1 BT OFF\r\n");
//            sb.append("! U1 B 128 1 1 50 0 0 "+ orderEntity.getNumId()+"\r\n");
//            sb.append("! U1 SETLF 55\r\n");
//            sb.append(" \r\n");
//            sb.append("! U1 SETLF 10\r\n");
//            if(orderEntity.isRePrint()){
//                sb.append("! U1 LMARGIN 200 "+rePrintStr+"\r\n");
//                sb.append("! U1 LMARGIN 0\r\n");
//            }
//            sb.append("! U1 LMARGIN 100 "+orderEntity.getUnitName()+"\r\n");
//            sb.append("! U1 LMARGIN 220 "+orderEntity.getOrderType()+"\r\n");
//            sb.append("! U1 LMARGIN 0 交易日期:"+orderEntity.getPayDateTime()+"\r\n");
//            sb.append("单号:"+orderEntity.getNumId()+"\r\n");
//            sb.append("POS机号:"+orderEntity.getClientId()+"("+orderEntity.getClientName()+")\r\n");
//            sb.append("收银员:"+orderEntity.getEmpeName()+"\r\n");
//            sb.append("货号         数量  价格  品名           金额\r\n");
//            sb.append("----------------------------------------------\r\n");
//            for (int i =0;i< lines; i++){
//                GoodEntity good = orderEntity.getItemList().get(i);
//                sb.append(padRight(good.getItemId(), 15));
//                sb.append(padRight(good.getQty()+"", 4));
//                sb.append(padRight(good.getTradePrice()+"", 6));
//                sb.append(padRight(good.getItemName(), 12));
//                sb.append(good.getTradeAmount()+" \r\n");
//            }
//            sb.append("----------------------------------------------\r\n");
//            sb.append("数据合计："+lines+"\r\n");
//            sb.append("金额合计："+orderEntity.getFamount()+"\r\n");
//            sb.append("付款金额："+orderEntity.getPamount()+"\r\n");
//            sb.append("找零："+orderEntity.getRamount()+"\r\n");
//            sb.append("免收："+orderEntity.getMalingAmount()+"\r\n");
//            sb.append("----------------------------------------------\r\n");
//            // 付款方式
//            for (int i=0;i<orderEntity.getCashList().size(); i++){
//                CashEntity cashEntity = orderEntity.getCashList().get(i);
//                if(cashEntity.isReturnCashSign()){
//                    continue;
//                }
//                sb.append(cashEntity.getPayType()+":"+cashEntity.getPayAmount()+"\r\n");
//            }
//            sb.append("----------------------------------------------\r\n");
//            sb.append("! U1 SETLF 20\r\n");
//            sb.append("! U1 LMARGIN 200 "+welcomeStr+"\r\n");
//            sb.append("! U1 LMARGIN 100 "+noticeStr+"\r\n");
//            // 走纸
//            sb.append("! U1 SETLF 15\r\n");
//            sb.append(" \r\n");
//            try{
//                printerConnection.write(sb.toString().getBytes());
//            }catch (ConnectionException ex){
//                System.out.println(ex.getMessage());
//            }
//        } else {
//            disconnect();
//        }
//    }
//
//    /**
//     * 2.打印优惠信息
//     * @param orderEntity
//     */
//    public static void printDeduct(OrderEntity orderEntity) throws Exception {
//        if(orderEntity.getDeductList().size() == 0){
//            return;
//        }
//        if(printer == null){
//            printer = connect();
//        }
//        if(printer != null){
//            StringBuffer sb = new StringBuffer();
//            int totalQty = 0;
//            int lines = orderEntity.getDeductList().size();
//            double totalAmount = 0d;
//            sb.append("! U1 JOURNAL\r\n");
//            sb.append("! U1 PAGE-WIDTH 600\r\n");
//            sb.append("! U1 SPEED 1\r\n");
//            sb.append("! U1 ENCODING UTF-8\r\n");
//            sb.append("! U1 SETLP GBUNSG24.CPF 0 24\r\n");
//            sb.append("! U1 SETBOLD 1\r\n");
//            sb.append("! U1 SETLF 25\r\n");
//            sb.append("! U1 LMARGIN 200\r\n");
//            sb.append("促销优惠信息\r\n");
//            sb.append("! U1 SETBOLD 0\r\n");
//            sb.append("! U1 SETLF 10\r\n");
//            sb.append("! U1 LMARGIN 0\r\n");
//            sb.append("货号                       数量      优惠金额\r\n");
////            sb.append("----------------------------------------------")
//            for (int i=0;i<orderEntity.getDeductList().size(); i++){
//                GoodEntity goodBean = orderEntity.getDeductList().get(i);
//                totalQty += goodBean.getQty();
//                totalAmount += goodBean.getDeductAmount();
//                sb.append(padRight(goodBean.getItemId(), 28));
//                sb.append(padRight(goodBean.getQty()+"", 10));
//                sb.append(goodBean.getDeductAmount()+"\r\n");
//            }
//            sb.append("合计：                      "+padRight(totalQty+"", 10) + totalAmount+"\r\n");
//            // 走纸
//            sb.append("! U1 SETLF 15\r\n");
//            sb.append(" \r\n");
//
//            try{
//                printerConnection.write(sb.toString().getBytes());
//            }catch (ConnectionException ex){
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
//
//    /**
//     * 3.打印提货存根联
//     * @param orderEntity
//     */
//    public static void printPickUpStub(OrderEntity orderEntity) throws Exception {
//        if (orderEntity.getDeliveryItemList().size() == 0) {
//            return;
//        }
//        if(printer == null){
//            printer = connect();
//        }
//        if(printer != null){
//            StringBuffer sb = new StringBuffer();
//            int lines = orderEntity.getDeliveryItemList().size();
//            sb.append("! U1 JOURNAL\r\n");
//            sb.append("! U1 PAGE-WIDTH 600\r\n");
//            sb.append("! U1 SPEED 1\r\n");
//            sb.append("! U1 ENCODING UTF-8\r\n");
//            sb.append("! U1 SETLP GBUNSG24.CPF 0 24\r\n");
//            sb.append("! U1 SETBOLD 1\r\n");
//            sb.append("! U1 SETLF 30\r\n");
//            sb.append("! U1 LMARGIN 200\r\n");
//            sb.append("提货存根联\r\n");
//            sb.append("! U1 SETBOLD 0\r\n");
//            sb.append("! U1 SETLF 10\r\n");
//            sb.append("! U1 LMARGIN 0\r\n");
//            sb.append("POS机号:"+orderEntity.getClientId()+"\r\n");
//            sb.append("生成时间:"+orderEntity.getOrderCreateDate()+"\r\n");
//            sb.append("小票号:"+orderEntity.getNumId()+"\r\n");
//            // 表格
//            sb.append("货号         数量   品名             条码\r\n");
//            sb.append("----------------------------------------------\r\n");
//            for (int i = 0; i < lines; i++){
//                GoodEntity goodEntity = orderEntity.getDeliveryItemList().get(i);
//                sb.append(padRight(goodEntity.getItemId(), 15));
//                sb.append(padRight(goodEntity.getQty()+"", 4));
//                sb.append(padRight(goodEntity.getItemName(), 13));
//                sb.append(goodEntity.getBarcode()+" \r\n");
//            }
//            sb.append("----------------------------------------------\r\n");
//            // 走纸
//            sb.append("! U1 SETLF 15\r\n");
//            sb.append(" \r\n");
//
//            try{
//                printerConnection.write(sb.toString().getBytes());
//            }catch (ConnectionException ex){
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
//
//    /**
//     * 4.打印提货单
//     * @param orderEntity
//     * @param goodEntity
//     */
//    public static void printPickUpBill(OrderEntity orderEntity, GoodEntity goodEntity) throws Exception {
//        String noticeStr = "***凭此单提货，请妥善保管***";
//        if(printer == null){
//            printer = connect();
//        }
//        if(printer != null){
//            StringBuffer sb = new StringBuffer();
//            sb.append("! U1 JOURNAL\r\n");
//            sb.append("! U1 PAGE-WIDTH 600\r\n");
//            sb.append("! U1 SPEED 1\r\n");
//            sb.append("! U1 ENCODING UTF-8\r\n");
//            sb.append("! U1 SETLP GBUNSG24.CPF 0 24\r\n");
//            sb.append("! U1 LMARGIN 100 "+noticeStr+"\r\n");
//            sb.append("! U1 SETBOLD 1\r\n");
//            sb.append("! U1 SETLF 30\r\n");
//            sb.append("! U1 LMARGIN 220\r\n");
//            sb.append("提货单\r\n");
//            sb.append("! U1 SETBOLD 0\r\n");
//            sb.append("! U1 SETLF 10\r\n");
//            sb.append("! U1 LMARGIN 0\r\n");
//            // 条形码
//            sb.append("! U1 SETBOLD 0\r\n");
//            sb.append("! U1 LMARGIN 0\r\n");
//            sb.append("! U1 BT OFF\r\n");
//            sb.append("! U1 B 128 2 1 50 0 0 "+ goodEntity.getSerialNo() +"\r\n");
//            sb.append("! U1 SETLF 40\r\n");
//            sb.append(" \r\n");
//            // 取货单号
//            sb.append("! U1 SETBOLD 1\r\n");
//            sb.append("! U1 LMARGIN 0\r\n");
//            sb.append("! U1 SETLF 15\r\n");
//            sb.append(goodEntity.getSerialNo()+"\r\n");
//            // 收银信息
//            sb.append("! U1 SETBOLD 0\r\n");
//            sb.append("! U1 SETLF 10\r\n");
//            sb.append("POS机号:"+orderEntity.getClientId()+"\r\n");
//            sb.append("生成时间:"+orderEntity.getOrderCreateDate()+"\r\n");
//            sb.append("小票号:"+orderEntity.getNumId()+"\r\n");
//            sb.append("库位:"+goodEntity.getLocName()+"\r\n");
//            // 表格
//            sb.append("货号         数量   品名             条码\r\n");
//            sb.append("----------------------------------------------\r\n");
//            sb.append(padRight(goodEntity.getItemId(), 15));
//            sb.append(padRight(goodEntity.getQty()+"", 4));
//            sb.append(padRight(goodEntity.getItemName(), 13));
//            sb.append(goodEntity.getBarcode()+" \r\n");
//            // 走纸
//            sb.append("! U1 SETLF 15\r\n");
//            sb.append(" \r\n");
//            // 提醒信息
//            sb.append("! U1 SETBOLD 0\r\n");
//            sb.append("! U1 SETLF 10\r\n");
//            sb.append("! U1 LMARGIN 100 "+noticeStr+"\r\n");
//            sb.append(" \r\n");
//
//            try{
//                printerConnection.write(sb.toString().getBytes());
//            }catch (ConnectionException ex){
//                System.out.println(ex.getMessage());
//            }
//        }
//        try {
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // --------------------- MS lmg end ---------------------
//
//
//    /**
//     * 连接打印机
//     * @return
//     */
//    private static ZebraPrinter connect() throws Exception {
//        String macAddress = findPrinterMac();
//        if (macAddress == null){
//            throw new Exception("未找到蓝牙打印机");
//        }
//        printerConnection = null;
//        printerConnection = new BluetoothConnection(macAddress);
//
//        try {
//            printerConnection.open();
//        } catch (ConnectionException e) {
//            disconnect();
//            System.out.println(e.getMessage());
//            throw new Exception("打印机连接失败");
//        }
//
//        ZebraPrinter printer = null;
//
//        if (printerConnection.isConnected()) {
//            try {
//                printer = ZebraPrinterFactory.getInstance(printerConnection);
////                PrinterLanguage pl = printer.getPrinterControlLanguage();
//            } catch (ConnectionException e) {
//                printer = null;
//                System.out.println("打印机连接失败:"+e.getMessage());
//                disconnect();
//                throw e;
//            } catch (ZebraPrinterLanguageUnknownException e) {
//                printer = null;
//                System.out.println("获取打印机语言失败："+e.getMessage());
//                disconnect();
//                throw e;
//            }
//        }
//
//        return printer;
//    }
//
//    /**
//     * 断开连接
//     */
//    public static void disconnect() {
//        try {
//            if (printerConnection != null) {
//                printerConnection.close();
//            }
//        } catch (ConnectionException e) {
//        } finally {
//            printer = null;
//        }
//    }
//
//
//    private static String findPrinterMac() throws Exception {
//        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (defaultAdapter == null){
//            throw new Exception("本机不支持蓝牙功能");
//        }
//        if(!defaultAdapter.isEnabled()){
//            throw new Exception("请检查蓝牙功能是否打开");
//        }
//        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
//        for (BluetoothDevice bond:bondedDevices) {
//            // 如果设置是打印机设备
//            if (bond.getBluetoothClass().getMajorDeviceClass() == BluetoothClass.Device.Major.IMAGING){
//                // 返回设备的Mac地址
//                return bond.getAddress();
//            }
//        }
//        return null;
//    }
//
//    private static String padRight(String str, int width){
//        if (str.length() < width) {
//            for(int i = str.length(); i < width; i++){
//                str += " ";
//            }
//        }
//        return str;
//    }
//}
