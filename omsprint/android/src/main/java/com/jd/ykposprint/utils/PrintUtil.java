//package com.jd.ykposprint.utils;
//
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.content.Context;
//
//import com.jd.ykposprint.entity.CashEntity;
//import com.jd.ykposprint.entity.GoodEntity;
//import com.jd.ykposprint.entity.OrderEntity;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Set;
//
//import print.Print;
//
///**
// * Created by limingguang on 2018/8/23.
// */
//
//public class PrintUtil {
//
//    private static int ALIGN_LEFT = 0;
//    private static int ALIGN_CENTER = 1;
//    private static int ALIGN_RIGHT = 2;
//    //    private static Connection printerConnection;
//    public static Context mContext;
//
//    /**
//     * 1.销售小票
//     *
//     * @param orderEntity
//     */
//    public static void printTicket(OrderEntity orderEntity) throws Exception {
//        String noticeStr = "***特卖商品一经售出，恕不退换***";
//        String welcomeStr = "***谢谢惠顾***";
//        String rePrintStr = "***重印***";
////        int lines = orderEntity.getItemList().size();
//        if (!Print.IsOpened()) {
//            connect();
//        }
//        if (Print.IsOpened()) {
////            iAttribute=(chkDoubleHeight.isChecked()?16:0)
////                    | (chkDoubleWidth.isChecked()?32:0)
////                    | (chkUnderline.isChecked()?4:0)
////                    | (chkBold.isChecked()?2:0)
////                    | (chkMiniFont.isChecked()?1:0)
////                    | (chkTurnWhite.isChecked()?8:0);
//
////            Print.PrintText("129江桥万达店",ALIGN_CENTER,2,1+0x10);
////            Print.PrintText("销售单",ALIGN_CENTER,0,2+2*0x10);
//
//            if(orderEntity.isRePrint()){
//                Print.SetJustification(ALIGN_LEFT);
//                Print.SetCharacterSize(0x0);
//                Print.PrintText("重印");
//                Print.PrintAndLineFeed();
//            }
//
//            Print.SetJustification(ALIGN_CENTER);
//            Print.SetCharacterSize(0x11);
//            Print.PrintText(orderEntity.getUnitName());
//            Print.PrintAndLineFeed();
//
//            Print.SetCharacterSize(0x0);
//            Print.PrintText("销售单");
//            Print.PrintAndLineFeed();
//
//            Print.SetJustification(ALIGN_LEFT);
//            Print.SetTextLineSpace((byte) 25);
//            Print.PrintText("NO：" + orderEntity.getNumId());
//            Print.PrintAndLineFeed();
//
//
//            Print.PrintText("货号/品名                单价      数量  金额  ");
//            Print.PrintAndLineFeed();
//            Print.PrintText("------------------------------------------------");
//            Print.PrintAndLineFeed();
//
//            List<GoodEntity> goods = orderEntity.getItemList();
//
//            int countNum = 0;
//
//            for (int i = 0; i < goods.size(); i++) {
//                GoodEntity good = goods.get(i);
//                countNum += good.getQty();
//                StringBuilder sb = new StringBuilder();
//                String name = good.getItemId() + "/" + good.getItemName();
//                if (getStringWidth(name) > 25) {
//                    sb.append(name + "\r\n");
//                    sb.append(padRight("", 25));
//                } else {
//                    sb.append(padRight(name, 25));
//                }
//
//                sb.append(padRight(good.getTradePrice(), 11));
//                sb.append(padRight("" + good.getQty(), 5));
//                sb.append(good.getTradeAmount());
//                Print.PrintText(sb.toString());
//                Print.PrintAndLineFeed();
//                Print.PrintText("                      原价："+ good.getStandardPrice());
//                Print.PrintAndLineFeed();
//            }
//
//            Print.PrintText("------------------------------------------------");
//            Print.PrintAndLineFeed();
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(padRight("合计:" + countNum, 41));
//            sb.append(orderEntity.getFamount() + "");
//            Print.PrintText(sb.toString());
//            Print.PrintAndLineFeed();
//
//            Print.PrintText("含折扣：" + orderEntity.getMalingAmount());
//            Print.PrintAndLineFeed();
////            Print.PrintText("付款金额：" + orderEntity.getPamount());
////            Print.PrintAndLineFeed();
//            Print.PrintText("找零：" + orderEntity.getRamount());
//            Print.PrintAndLineFeed();
//            Print.PrintText("会员号码：" + orderEntity.getVipNo());
//            Print.PrintAndLineFeed();
//            Print.PrintText("收银员:" + orderEntity.getEmpeName());
//            Print.PrintAndLineFeed();
//            Print.PrintText("" + orderEntity.getPayDateTime());
//            Print.PrintAndLineFeed();
//
//            Print.PrintText("------------------------------------------------");
//            Print.PrintAndLineFeed();
////            Print.PrintText("付款：线上充值：  100");
////            Print.PrintAndLineFeed();
//            // 付款方式
//            for (int i = 0; i < orderEntity.getCashList().size(); i++) {
//                CashEntity cashEntity = orderEntity.getCashList().get(i);
//                if (cashEntity.isReturnCashSign()) {
//                    continue;
//                }
//                Print.PrintText(cashEntity.getPayType() + ":" + cashEntity.getPayAmount() );
//                Print.PrintAndLineFeed();
//            }
//
//            Print.PrintText("------------------------------------------------");
//            Print.PrintAndLineFeed();
//
//            Print.SetJustification(ALIGN_CENTER);
//            Print.PrintText("特价商品恕不接受抵用券支付\r\n " +
//                    "欢迎下次光临！\r\n" +
//                    " 如质量问题请凭小票七日内退换\r\n ");
//            Print.PrintAndLineFeed();
//
//            Print.PrintBarCode(Print.BC_CODE93, orderEntity.getNumId(), 2, 100, 2, 1);
//            Print.PrintText("  \n   \n   \n   \n");
//
////            Print.PrintQRCode("123456", 6, 48, 0);
////            Print.PrintAndLineFeed();
//
////
////
////            StringBuffer sb = new StringBuffer();
////            sb.append("! U1 JOURNAL\r\n");
////            sb.append("! U1 PAGE-WIDTH 600\r\n");
////            sb.append("! U1 SPEED 2\r\n");
////            sb.append("! U1 ENCODING UTF-8\r\n");
////            sb.append("! U1 LMARGIN 0\r\n");
////            sb.append("! U1 SETLP GBUNSG24.CPF 0 24\r\n");
////            sb.append("! U1 PCX 0 0 !<LOGO.PCX\r\n");
////            sb.append("! U1 SETBOLD 1\r\n");
////            sb.append("! U1 SETLF 10\r\n");
////            sb.append("! U1 LMARGIN 220 \r\n");
////            sb.append("! U1 SETLF 30\r\n");
////            sb.append("GB好孩子\r\n");
////            sb.append("! U1 SETBOLD 0\r\n");
////            sb.append("! U1 LMARGIN 0\r\n");
////            sb.append("! U1 BT OFF\r\n");
////            sb.append("! U1 B 128 1 1 50 0 0 "+ orderEntity.getNumId()+"\r\n");
////            sb.append("! U1 SETLF 55\r\n");
////            sb.append(" \r\n");
////            sb.append("! U1 SETLF 10\r\n");
////            if(orderEntity.isRePrint()){
////                sb.append("! U1 LMARGIN 200 "+rePrintStr+"\r\n");
////                sb.append("! U1 LMARGIN 0\r\n");
////            }
////            sb.append("! U1 LMARGIN 100 "+orderEntity.getUnitName()+"\r\n");
////            sb.append("! U1 LMARGIN 220 "+orderEntity.getOrderType()+"\r\n");
////            sb.append("! U1 LMARGIN 0 交易日期:"+orderEntity.getPayDateTime()+"\r\n");
////            sb.append("单号:"+orderEntity.getNumId()+"\r\n");
////            sb.append("POS机号:"+orderEntity.getClientId()+"("+orderEntity.getClientName()+")\r\n");
////            sb.append("收银员:"+orderEntity.getEmpeName()+"\r\n");
////            sb.append("货号         数量  价格  品名           金额\r\n");
////            sb.append("----------------------------------------------\r\n");
////            for (int i =0;i< lines; i++){
////                GoodEntity good = orderEntity.getItemList().get(i);
////                sb.append(padRight(good.getItemId(), 15));
////                sb.append(padRight(good.getQty()+"", 4));
////                sb.append(padRight(good.getTradePrice()+"", 6));
////                sb.append(padRight(good.getItemName(), 12));
////                sb.append(good.getTradeAmount()+" \r\n");
////            }
////            sb.append("----------------------------------------------\r\n");
////            sb.append("数据合计："+lines+"\r\n");
////            sb.append("金额合计："+orderEntity.getFamount()+"\r\n");
////            sb.append("付款金额："+orderEntity.getPamount()+"\r\n");
////            sb.append("找零："+orderEntity.getRamount()+"\r\n");
////            sb.append("免收："+orderEntity.getMalingAmount()+"\r\n");
////            sb.append("----------------------------------------------\r\n");
////            // 付款方式
////            for (int i=0;i<orderEntity.getCashList().size(); i++){
////                CashEntity cashEntity = orderEntity.getCashList().get(i);
////                if(cashEntity.isReturnCashSign()){
////                    continue;
////                }
////                sb.append(cashEntity.getPayType()+":"+cashEntity.getPayAmount()+"\r\n");
////            }
////            sb.append("----------------------------------------------\r\n");
////            sb.append("! U1 SETLF 20\r\n");
////            sb.append("! U1 LMARGIN 200 "+welcomeStr+"\r\n");
////            sb.append("! U1 LMARGIN 100 "+noticeStr+"\r\n");
////            // 走纸
////            sb.append("! U1 SETLF 15\r\n");
////            sb.append(" \r\n");
////            try{
////                printerConnection.write(sb.toString().getBytes());
////            }catch (ConnectionException ex){
////                System.out.println(ex.getMessage());
////            }
//        } else {
//            disconnect();
//        }
//    }
//
//    /**
//     * 2.打印优惠信息
//     * @param orderEntity
//     */
////    public static void printDeduct(OrderEntity orderEntity) throws Exception {
////        if(orderEntity.getDeductList().size() == 0){
////            return;
////        }
////        if(printer == null){
////            printer = connect();
////        }
////        if(printer != null){
////            StringBuffer sb = new StringBuffer();
////            int totalQty = 0;
////            int lines = orderEntity.getDeductList().size();
////            double totalAmount = 0d;
////            sb.append("! U1 JOURNAL\r\n");
////            sb.append("! U1 PAGE-WIDTH 600\r\n");
////            sb.append("! U1 SPEED 1\r\n");
////            sb.append("! U1 ENCODING UTF-8\r\n");
////            sb.append("! U1 SETLP GBUNSG24.CPF 0 24\r\n");
////            sb.append("! U1 SETBOLD 1\r\n");
////            sb.append("! U1 SETLF 25\r\n");
////            sb.append("! U1 LMARGIN 200\r\n");
////            sb.append("促销优惠信息\r\n");
////            sb.append("! U1 SETBOLD 0\r\n");
////            sb.append("! U1 SETLF 10\r\n");
////            sb.append("! U1 LMARGIN 0\r\n");
////            sb.append("货号                       数量      优惠金额\r\n");
//////            sb.append("----------------------------------------------")
////            for (int i=0;i<orderEntity.getDeductList().size(); i++){
////                GoodEntity goodBean = orderEntity.getDeductList().get(i);
////                totalQty += goodBean.getQty();
////                totalAmount += goodBean.getDeductAmount();
////                sb.append(padRight(goodBean.getItemId(), 28));
////                sb.append(padRight(goodBean.getQty()+"", 10));
////                sb.append(goodBean.getDeductAmount()+"\r\n");
////            }
////            sb.append("合计：                      "+padRight(totalQty+"", 10) + totalAmount+"\r\n");
////            // 走纸
////            sb.append("! U1 SETLF 15\r\n");
////            sb.append(" \r\n");
////
////            try{
////                printerConnection.write(sb.toString().getBytes());
////            }catch (ConnectionException ex){
////                System.out.println(ex.getMessage());
////            }
////        }
////    }
//
//    /**
//     * 3.打印提货存根联
//     * @param orderEntity
//     */
////    public static void printPickUpStub(OrderEntity orderEntity) throws Exception {
////        if (orderEntity.getDeliveryItemList().size() == 0) {
////            return;
////        }
////        if(printer == null){
////            printer = connect();
////        }
////        if(printer != null){
////            StringBuffer sb = new StringBuffer();
////            int lines = orderEntity.getDeliveryItemList().size();
////            sb.append("! U1 JOURNAL\r\n");
////            sb.append("! U1 PAGE-WIDTH 600\r\n");
////            sb.append("! U1 SPEED 1\r\n");
////            sb.append("! U1 ENCODING UTF-8\r\n");
////            sb.append("! U1 SETLP GBUNSG24.CPF 0 24\r\n");
////            sb.append("! U1 SETBOLD 1\r\n");
////            sb.append("! U1 SETLF 30\r\n");
////            sb.append("! U1 LMARGIN 200\r\n");
////            sb.append("提货存根联\r\n");
////            sb.append("! U1 SETBOLD 0\r\n");
////            sb.append("! U1 SETLF 10\r\n");
////            sb.append("! U1 LMARGIN 0\r\n");
////            sb.append("POS机号:"+orderEntity.getClientId()+"\r\n");
////            sb.append("生成时间:"+orderEntity.getOrderCreateDate()+"\r\n");
////            sb.append("小票号:"+orderEntity.getNumId()+"\r\n");
////            // 表格
////            sb.append("货号         数量   品名             条码\r\n");
////            sb.append("----------------------------------------------\r\n");
////            for (int i = 0; i < lines; i++){
////                GoodEntity goodEntity = orderEntity.getDeliveryItemList().get(i);
////                sb.append(padRight(goodEntity.getItemId(), 15));
////                sb.append(padRight(goodEntity.getQty()+"", 4));
////                sb.append(padRight(goodEntity.getItemName(), 13));
////                sb.append(goodEntity.getBarcode()+" \r\n");
////            }
////            sb.append("----------------------------------------------\r\n");
////            // 走纸
////            sb.append("! U1 SETLF 15\r\n");
////            sb.append(" \r\n");
////
////            try{
////                printerConnection.write(sb.toString().getBytes());
////            }catch (ConnectionException ex){
////                System.out.println(ex.getMessage());
////            }
////        }
////    }
//
//    /**
//     * 4.打印提货单
//     * @param orderEntity
//     * @param goodEntity
//     */
////    public static void printPickUpBill(OrderEntity orderEntity, GoodEntity goodEntity) throws Exception {
////        String noticeStr = "***凭此单提货，请妥善保管***";
////        if(printer == null){
////            printer = connect();
////        }
////        if(printer != null){
////            StringBuffer sb = new StringBuffer();
////            sb.append("! U1 JOURNAL\r\n");
////            sb.append("! U1 PAGE-WIDTH 600\r\n");
////            sb.append("! U1 SPEED 1\r\n");
////            sb.append("! U1 ENCODING UTF-8\r\n");
////            sb.append("! U1 SETLP GBUNSG24.CPF 0 24\r\n");
////            sb.append("! U1 LMARGIN 100 "+noticeStr+"\r\n");
////            sb.append("! U1 SETBOLD 1\r\n");
////            sb.append("! U1 SETLF 30\r\n");
////            sb.append("! U1 LMARGIN 220\r\n");
////            sb.append("提货单\r\n");
////            sb.append("! U1 SETBOLD 0\r\n");
////            sb.append("! U1 SETLF 10\r\n");
////            sb.append("! U1 LMARGIN 0\r\n");
////            // 条形码
////            sb.append("! U1 SETBOLD 0\r\n");
////            sb.append("! U1 LMARGIN 0\r\n");
////            sb.append("! U1 BT OFF\r\n");
////            sb.append("! U1 B 128 2 1 50 0 0 "+ goodEntity.getSerialNo() +"\r\n");
////            sb.append("! U1 SETLF 40\r\n");
////            sb.append(" \r\n");
////            // 取货单号
////            sb.append("! U1 SETBOLD 1\r\n");
////            sb.append("! U1 LMARGIN 0\r\n");
////            sb.append("! U1 SETLF 15\r\n");
////            sb.append(goodEntity.getSerialNo()+"\r\n");
////            // 收银信息
////            sb.append("! U1 SETBOLD 0\r\n");
////            sb.append("! U1 SETLF 10\r\n");
////            sb.append("POS机号:"+orderEntity.getClientId()+"\r\n");
////            sb.append("生成时间:"+orderEntity.getOrderCreateDate()+"\r\n");
////            sb.append("小票号:"+orderEntity.getNumId()+"\r\n");
////            sb.append("库位:"+goodEntity.getLocName()+"\r\n");
////            // 表格
////            sb.append("货号         数量   品名             条码\r\n");
////            sb.append("----------------------------------------------\r\n");
////            sb.append(padRight(goodEntity.getItemId(), 15));
////            sb.append(padRight(goodEntity.getQty()+"", 4));
////            sb.append(padRight(goodEntity.getItemName(), 13));
////            sb.append(goodEntity.getBarcode()+" \r\n");
////            // 走纸
////            sb.append("! U1 SETLF 15\r\n");
////            sb.append(" \r\n");
////            // 提醒信息
////            sb.append("! U1 SETBOLD 0\r\n");
////            sb.append("! U1 SETLF 10\r\n");
////            sb.append("! U1 LMARGIN 100 "+noticeStr+"\r\n");
////            sb.append(" \r\n");
////
////            try{
////                printerConnection.write(sb.toString().getBytes());
////            }catch (ConnectionException ex){
////                System.out.println(ex.getMessage());
////            }
////        }
////        try {
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////        }
////    }
//
//    // --------------------- MS lmg end ---------------------
//
//
//    /**
//     * 连接打印机
//     *
//     * @return
//     */
//    private static boolean connect() throws Exception {
//        String macAddress = findPrinterMac();
//        if (macAddress == null) {
//            throw new Exception("未找到蓝牙打印机");
//        }
//        int portOpen = Print.PortOpen(mContext, "Bluetooth," + macAddress);
//
//        if (portOpen != 0) {
//            throw new Exception("打印机连接失败");
//        }
//
//        return true;
//    }
//
//    /**
//     * 断开连接
//     */
//    public static void disconnect() {
//        try {
//            Print.PortClose();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private static String findPrinterMac() throws Exception {
//        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (defaultAdapter == null) {
//            throw new Exception("本机不支持蓝牙功能");
//        }
//        if (!defaultAdapter.isEnabled()) {
//            throw new Exception("请检查蓝牙功能是否打开");
//        }
//        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
//        for (BluetoothDevice bond : bondedDevices) {
//            // 如果设置是打印机设备
////            Log.d("1111",bond.getName());
//            return bond.getAddress();
////            if (bond.getBluetoothClass().getMajorDeviceClass() == BluetoothClass.Device.Major.IMAGING) {
////                // 返回设备的Mac地址
////                return bond.getAddress();
////            }
//        }
//        return null;
//    }
//
//    private static String padRight(String str, int width) {
//        int stringWidth = getStringWidth(str);
//        if (stringWidth < width) {
//            for (int i = stringWidth; i < width; i++) {
//                str += " ";
//            }
//        }
//        return str;
//    }
//
//    private static int getStringWidth(String str) {
//        int width = 0;
//        for (char c : str.toCharArray()) {
//            width += isChinese(c) ? 2 : 1;
//        }
//        return width;
//    }
//
//    /**
//     * 判断是否中文
//     * GENERAL_PUNCTUATION 判断中文的“号
//     * CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
//     * HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
//     *
//     * @param c 字符
//     * @return 是否中文
//     */
//    public static boolean isChinese(char c) {
//        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
//        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
//                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
//                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
//                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
//                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
//                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
//                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
//    }
//
//    /**
//     * 一行输出
//     *
//     * @param str1 字符串
//     * @param str2 字符串
//     * @throws IOException 异常
//     */
////    @SuppressWarnings("unused")
////    public void printInOneLineFeed(String str1, String str2, String str3, int textSize) throws IOException {
////        int lineLength = 34;
////        if((getStringWidth(str1)<=lineLength)){
////            if ((getStringWidth(str1) + 15) <= lineLength) {
////                int needEmpty = lineLength - (getStringWidth(str1) + 15);
////                String empty = "";
////                while (needEmpty > 0) {
////                    empty += " ";
////                    needEmpty--;
////                }
////                print(str1 + empty , CHARSET);
////                printInOneLine(str2, str3,15, textSize);
////            }else{
////                printLeft(str1);
////                printLineFeed();
////                printInOneLine(str2, str3,15, textSize);
////            }
////        }        else {
////            ArrayList<String> strings = splitWhith(str1, lineLength);
////            for (int idx = 0; idx < strings.size() - 1; idx++) {
////                printLeft(strings.get(idx));
////                printLineFeed();
////            }
////
////            printInOneLineFeed(strings.get(strings.size()-1),str2,str3,0);
////
////        }
////
////    }
//}
