//package com.jd.ykposprint.template;
//
//import android.text.TextUtils;
//
//import com.google.zxing.WriterException;
//import com.jd.ykposprint.entity.CouponInfo;
//import com.jd.ykposprint.entity.Goods;
//import com.jd.ykposprint.entity.OrderFund;
//import com.jd.ykposprint.entity.OrderInfo;
//import com.jd.ykposprint.utils.BluetoothPrintFormatUtil;
//import com.jd.ykposprint.utils.PrintBean;
//import com.jd.ykposprint.utils.PrintDataMaker;
//import com.jd.ykposprint.utils.PrintHelper;
//import com.jd.ykposprint.utils.PrintTool;
//import com.jd.ykposprint.utils.PrinterUtils;
//import com.jd.ykposprint.utils.PrinterWriter;
//import com.jd.ykposprint.utils.PrinterWriter58mm;
//import com.jd.ykposprint.utils.PrinterWriter80mm;
//import com.jd.ykposprint.utils.QRCodeUtil;
//import com.jd.ykposprint.utils.Utils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//public class OrderPrintDataMaker implements PrintDataMaker {
//    public static final String MSG_COUPON_BAR = "实体门店优惠券";
//    public static final String MSG_COUPON_DATE_58MM_END = "       %s";
//    public static final String MSG_COUPON_DATE_58MM_START = "有效期 %s 至";
//    public static final String MSG_COUPON_DATE_80MM = "有效期 %s 至 %s";
//    public static final String MSG_COUPON_INTRO = "使用说明:";
//    public static final String MSG_COUPON_NAME = "券名称 %s";
//    public static final String MSG_COUPON_QR = "微信扫码领优惠券";
//    private static final List<String> judgePrintOrderCondition = Arrays.asList(new String[]{"19", "72", "52", "56", "83", "77", "78"});
//    private Comparator<Goods> goodsComparator = new Comparator<Goods>() {
//        public int compare(Goods o1, Goods o2) {
//            long r = o1.getCatCode() - o2.getCatCode();
//            if (r > 0) {
//                return 1;
//            }
//            return r < 0 ? -1 : 0;
//        }
//    };
//
//    public List<byte[]> getPrintData(PrintBean bean) {
//        PrinterWriter printer;
//        int yiyouhuiNumber = 0;
//        int itemTotalPrice = 0;
//        String sourceName;
//        String buyerNick;
//        String sourceName2;
//        String buyerNick2;
//        ArrayList<byte[]> data = new ArrayList<>();
//        if (bean == null) {
//            return data;
//        }
//        try {
//            if (bean.data == null || !(bean.data instanceof OrderInfo)) {
//                return data;
//            }
//            OrderInfo order = (OrderInfo) bean.data;
//            if (bean.type == 58) {
//                printer = new PrinterWriter58mm();
//            } else {
//                printer = new PrinterWriter80mm();
//            }
//            printer.setAlignCenter();
//            printer.setEmphasizedOn();
//            printer.setFontSize(1);
//            parseSubOrderInfo(order);
//            boolean hasPackage = order.hasPackageFee();
//            int itemTotalPrice2 = 0;
//            int yiyouhuiNumber2 = 0;
//            if (order.getReceiptOrderFunds() != null) {
//                for (int i = 0; i < order.getReceiptOrderFunds().size(); i++) {
//                    if (((OrderFund) order.getReceiptOrderFunds().get(i)).getId() == 1) {
//                        itemTotalPrice2 = ((OrderFund) order.getReceiptOrderFunds().get(i)).getPriceX100();
//                    } else if (((OrderFund) order.getReceiptOrderFunds().get(i)).getId() == 2) {
//                        yiyouhuiNumber2 = ((OrderFund) order.getReceiptOrderFunds().get(i)).getPriceX100();
//                    }
//                }
//            }
//            List<String> leftMsg = new ArrayList<>();
//            List<String> rightMsg = new ArrayList<>();
//            String mobile = order.getContactTel();
//            int pkbFund = PrintHelper.getPackingExpense(order.getReceiptOrderFunds());
//            leftMsg.add("优惠金额");
//            leftMsg.add("配送费");
//            if (pkbFund != -1) {
//                leftMsg.add("包装费");
//            }
//            leftMsg.add("买家实付");
//            leftMsg.add("下单时间");
//            rightMsg.add("" + Utils.scaleFloat(((float) yiyouhuiNumber) / 100.0f));
//            rightMsg.add("" + Utils.scaleFloat(((float) order.getPsf()) / 100.0f));
//            if (pkbFund != -1) {
//                rightMsg.add("" + Utils.scaleFloat(((float) pkbFund) / 100.0f));
//            }
//            rightMsg.add("" + Utils.scaleFloat(((float) order.getTotalPrice()) / 100.0f));
//            rightMsg.add(Utils.formatDate2String(order.getBuyer2uspayTime()));
//            if (!TextUtils.isEmpty(mobile)) {
//                rightMsg.add(mobile);
//            }
//            byte[] result = {PrinterUtils.GS, 33, 1};
//            printer.setAlignCenter();
//            printer.setEmphasizedOn();
//            printer.setFontSize(1);
//            if (TextUtils.isEmpty(order.getSourceName())) {
//                sourceName = "";
//            } else {
//                sourceName = order.getSourceName();
//            }
//            printer.print(sourceName);
//            if (!TextUtils.isEmpty(order.getIspDaySeq())) {
//                printer.setEmphasizedOn();
//                printer.setFontSize(1);
//                printer.print(" #" + order.getIspDaySeq());
//            }
//            printer.printLineFeed(2);
//            if (!TextUtils.isEmpty(order.getShopName())) {
//                printer.setAlignCenter();
//                printer.setFontSize(0);
//                printer.setEmphasizedOff();
//                printer.print(order.getShopName());
//                printer.printLineFeed(2);
//            }
//            printer.setAlignCenter();
//            printer.setFontSize(0);
//            printer.print("商家留存");
//            printer.printLineFeed(2);
//            if (!TextUtils.isEmpty(order.getOutWayId())) {
//                printer.setAlignCenter();
//                data.add(printer.getDataAndReset());
//                printer.printBitmap(PrintTool.createBarCode(order.getOutWayId()));
//                printer.printLineFeed();
//                printer.setAlignCenter();
//                printer.setEmphasizedOff();
//                printer.setFontSize(0);
//                printer.print(order.getOutWayId());
//                printer.printLineFeed(2);
//            }
//            printer.setAlignLeft();
//            printer.setEmphasizedOff();
//            printer.setFontSize(0);
//            printer.print("门店序号 ");
//            printer.setEmphasizedOn();
//            printer.setFontSize(0);
//            printer.print("#" + order.getDailySequence());
//            printer.printLineFeed();
//            if (order.getServiceTime() > 0) {
//                printer.setAlignLeft();
//                printer.setFontSize(0);
//                printer.setEmphasizedOff();
//                printer.print("预计送达时间：" + PrintHelper.getYuJiTime(order.getServiceTime()));
//                printer.printLineFeed();
//            }
//            if (isPrintOrderId(order)) {
//                printer.setAlignLeft();
//                printer.setFontSize(0);
//                printer.setEmphasizedOff();
//                printer.print("订单号：" + order.getBizOrderId());
//                printer.printLineFeed();
//            }
//            printer.print(BluetoothPrintFormatUtil.getSpanLine());
//            printer.setAlignLeft();
//            printer.setEmphasizedOn();
//            printer.setFontSize(1);
//            if (TextUtils.isEmpty(order.getBuyerNick())) {
//                buyerNick = "匿名";
//            } else {
//                buyerNick = order.getBuyerNick();
//            }
//            printer.print(buyerNick);
//            printer.printLineFeed();
//            printer.print(Utils.getFormatMobile(order.getMobile()));
//            printer.printLineFeed(2);
//            printer.print(order.getCommunity() + " " + order.getAddressSnapshot());
//            printer.printLineFeed(2);
//            if (PrintHelper.checkSpecailProperity(order.getSpecialCatTagList())) {
//                printer.setFontSize(0);
//                printer.setEmphasizedOn();
//                printer.printLine();
//                printer.printLineFeed();
//                printer.setAlignCenter();
//                printer.print(PrintHelper.getSpecialString(order.getSpecialCatTagList()));
//                printer.printLineFeed();
//                printer.printLine();
//                printer.printLineFeed(2);
//            }
//            if (!TextUtils.isEmpty(order.getComment())) {
//                printer.setFontSize(0);
//                printer.print(BluetoothPrintFormatUtil.getSpanLine());
//                printer.setFontSize(1);
//                if (hasPackage) {
//                    printer.print("备注:" + order.getComment() + "\n");
//                } else {
//                    printer.print("备注: 无包装袋\n");
//                    printer.print(order.getComment() + "\n");
//                }
//                printer.setFontSize(0);
//                printer.print(BluetoothPrintFormatUtil.getSpanLine());
//            }
//            if (!TextUtils.isEmpty(order.getInvoiceInfo())) {
//                printer.setEmphasizedOff();
//                printer.setFontSize(0);
//                printer.print(order.getInvoiceInfo());
//            }
//            printer.setAlignLeft();
//            printer.setEmphasizedOff();
//            printer.write(result);
//            printer.setFontSize(0);
//            Collections.sort(order.getSubOrders(), this.goodsComparator);
//            printer.print(BluetoothPrintFormatUtil.printSubOrderMSGV4(order.getSubOrders()));
//            printer.printLineFeed();
//            printer.print(BluetoothPrintFormatUtil.printHejiMSG(order.getBuyAmount() + "件", "   ", "" + Utils.scaleFloat(((float) itemTotalPrice) / 100.0f)));
//            printer.print(BluetoothPrintFormatUtil.printLeftRightMSG(leftMsg, rightMsg));
//            printer.setFontSize(0);
//            printer.print(BluetoothPrintFormatUtil.getSpanLine());
//            printer.printLineFeed();
//            printer.setFontSize(0);
//            printer.setEmphasizedOff();
//            printer.print("拣货员签字");
//            printer.printLineFeed(4);
//            printer.setFontSize(0);
//            printer.setEmphasizedOff();
//            printer.print("送货员签字");
//            printer.printLineFeed(4);
//            printer.setFontSize(0);
//            printer.setEmphasizedOff();
//            printer.print("送货员签字");
//            printer.printLineFeed(4);
//            printer.setFontSize(0);
//            printer.setEmphasizedOn();
//            printer.printLineFeed();
//            printer.printLineFeed(7);
//            printer.setAlignCenter();
//            printer.setEmphasizedOn();
//            printer.setFontSize(1);
//            if (TextUtils.isEmpty(order.getSourceName())) {
//                sourceName2 = "";
//            } else {
//                sourceName2 = order.getSourceName();
//            }
//            printer.print(sourceName2);
//            if (!TextUtils.isEmpty(order.getIspDaySeq())) {
//                printer.setEmphasizedOn();
//                printer.setFontSize(1);
//                printer.print(" #" + order.getIspDaySeq());
//            }
//            printer.printLineFeed(2);
//            if (!TextUtils.isEmpty(order.getShopName())) {
//                printer.setAlignCenter();
//                printer.setFontSize(0);
//                printer.setEmphasizedOff();
//                printer.print(order.getShopName());
//                printer.printLineFeed(2);
//            }
//            if (!TextUtils.isEmpty(order.getOutWayId())) {
//                printer.setAlignCenter();
//                data.add(printer.getDataAndReset());
//                printer.printBitmap(PrintTool.createBarCode(order.getOutWayId()));
//                printer.printLineFeed();
//                printer.setAlignCenter();
//                printer.setEmphasizedOff();
//                printer.setFontSize(0);
//                printer.print(order.getOutWayId());
//                printer.printLineFeed(2);
//            }
//            printer.setAlignLeft();
//            printer.setEmphasizedOff();
//            printer.setFontSize(0);
//            printer.print("门店序号 ");
//            printer.setEmphasizedOn();
//            printer.setFontSize(0);
//            printer.print("#" + order.getDailySequence());
//            printer.printLineFeed();
//            if (order.getServiceTime() > 0) {
//                printer.setAlignLeft();
//                printer.setFontSize(0);
//                printer.setEmphasizedOff();
//                printer.print("预计送达时间：" + PrintHelper.getYuJiTime(order.getServiceTime()));
//                printer.printLineFeed();
//            }
//            if (!TextUtils.isEmpty(mobile)) {
//                printer.print(BluetoothPrintFormatUtil.printLeftRightMSG(Arrays.asList(new String[]{"客服电话"}), Arrays.asList(new String[]{mobile})));
//                printer.printLineFeed();
//            }
//            if (isPrintOrderId(order)) {
//                printer.setAlignLeft();
//                printer.setFontSize(0);
//                printer.setEmphasizedOff();
//                printer.print("订单号：" + order.getBizOrderId());
//                printer.printLineFeed();
//            }
//            printer.print(BluetoothPrintFormatUtil.getSpanLine());
//            printer.setAlignLeft();
//            printer.setEmphasizedOn();
//            printer.setFontSize(1);
//            if (TextUtils.isEmpty(order.getBuyerNick())) {
//                buyerNick2 = "匿名";
//            } else {
//                buyerNick2 = order.getBuyerNick();
//            }
//            printer.print(buyerNick2);
//            printer.printLineFeed();
//            printer.print(Utils.getFormatMobile(order.getMobile()));
//            printer.printLineFeed(2);
//            printer.print(order.getCommunity() + " " + order.getAddressSnapshot());
//            printer.printLineFeed(2);
//            if (PrintHelper.checkSpecailProperity(order.getSpecialCatTagList())) {
//                printer.setFontSize(0);
//                printer.setEmphasizedOn();
//                printer.printLine();
//                printer.printLineFeed();
//                printer.setAlignCenter();
//                printer.print(PrintHelper.getSpecialString(order.getSpecialCatTagList()));
//                printer.printLineFeed();
//                printer.printLine();
//                printer.printLineFeed(2);
//            }
//            if (!TextUtils.isEmpty(order.getComment())) {
//                printer.setFontSize(0);
//                printer.print(BluetoothPrintFormatUtil.getSpanLine());
//                printer.setFontSize(1);
//                if (hasPackage) {
//                    printer.print("备注:" + order.getComment() + "\n");
//                } else {
//                    printer.print("备注: 无包装袋\n");
//                    printer.print(order.getComment() + "\n");
//                }
//                printer.setFontSize(0);
//                printer.print(BluetoothPrintFormatUtil.getSpanLine());
//            }
//            if (!TextUtils.isEmpty(order.getInvoiceInfo())) {
//                printer.setEmphasizedOff();
//                printer.setFontSize(0);
//                printer.print(order.getInvoiceInfo());
//            }
//            printer.setAlignLeft();
//            printer.setEmphasizedOff();
//            printer.write(result);
//            printer.setFontSize(0);
//            printer.print(BluetoothPrintFormatUtil.printSubOrderMSGV4(order.getSubOrders()));
//            printer.printLineFeed();
//            printer.print(BluetoothPrintFormatUtil.printHejiMSG(order.getBuyAmount() + "件", "   ", "" + Utils.scaleFloat(((float) itemTotalPrice) / 100.0f)));
//            printer.print(BluetoothPrintFormatUtil.printLeftRightMSG(leftMsg, rightMsg));
//            printer.printLineFeed(2);
//            printer.setAlignCenter();
//            printer.print("温馨提示：请仔细核对商品\n");
//            printer.print("谢谢惠顾，欢迎再次光临:)\n");
//            printer.printLineFeed();
////            if (PreferenceProvider.isLvDiOid()) {
////                data.add(printer.getDataAndReset());
////                printer.printBitmapV2(PrintTool.LV_LOGO, false);
////                printer.setAlignCenter();
////                printer.print("描上方二维码，根据指引步骤开具增值税电子普通发票");
////                printer.printLineFeed(2);
////            }
//            if (!TextUtils.isEmpty(PrintTool.MINI_APP_QR_CODE_URL)) {
//                data.add(printer.getDataAndReset());
//                printer.printBitmapV2(PrintTool.createMinAppCodeBitmap(), false);
//                printer.setAlignCenter();
//                printer.print("扫二维码，优惠享不停");
//            } else if (!TextUtils.isEmpty(order.getDownLoadUrl())) {
//                data.add(printer.getDataAndReset());
//                printer.printBitmapV2(PrintTool.createLHQRCodeBitmap(order.getDownLoadUrl()));
//                printer.setAlignCenter();
//                printer.print("扫二维码，优惠享不停");
//            }
//            if (order.hasCouponInfo()) {
//                printCoupon(order.getCouponInfoList(), printer);
//            }
//            printer.printLineFeed(7);
//            printer.feedPaperCutPartial();
//            data.add(printer.getDataAndClose());
//            return data;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
//
//    private void printCoupon(List<CouponInfo> couponInfoList, PrinterWriter printer) throws IOException, WriterException {
//        printer.printLineFeed(2);
//        for (CouponInfo coupon : couponInfoList) {
//            printer.printDottedLine();
//            printer.printLineFeed(2);
//            String content = coupon.getCouponNo();
//            printer.setFontSize(1);
//            printer.setAlignCenter();
//            if (Utils.isNumeric(content)) {
//                printer.print(MSG_COUPON_BAR);
//                printer.printLineFeed(2);
//                printer.printBitmapV2(PrintTool.createBarCode(content, 400, 100));
//                printer.setAlignCenter();
//                printer.setFontSize(0);
//                printer.print(content);
//                printer.printLineFeed(2);
//            } else {
//                printer.print(MSG_COUPON_QR);
//                printer.printLineFeed();
//                printer.printBitmapV2(QRCodeUtil.createCode(content, 300, 300));
//                printer.setFontSize(0);
//                printer.printLineFeed();
//            }
//            printer.setAlignLeft();
//            printer.print(String.format(MSG_COUPON_NAME, new Object[]{coupon.getCouponName()}));
//            printer.printLineFeed();
//            if (printer instanceof PrinterWriter58mm) {
//                printer.print(String.format(MSG_COUPON_DATE_58MM_START, new Object[]{coupon.getStartTime()}));
//                printer.printLineFeed();
//                printer.print(String.format(MSG_COUPON_DATE_58MM_END, new Object[]{coupon.getEndTime()}));
//            } else if (printer instanceof PrinterWriter80mm) {
//                printer.print(String.format(MSG_COUPON_DATE_80MM, new Object[]{coupon.getStartTime(), coupon.getEndTime()}));
//            }
//            printer.printLineFeed();
//            printer.setAlignLeft();
//            printer.print(MSG_COUPON_INTRO);
//            printer.printLineFeed();
//            printer.print(coupon.getIntroduce());
//            printer.printLineFeed(2);
//        }
//    }
//
//    private void parseSubOrderInfo(OrderInfo order) {
//        String itemBarCode;
//        String sku;
//        String property;
//        for (int i = 0; i < order.getSubOrders().size(); i++) {
//            Goods goods = (Goods) order.getSubOrders().get(i);
//            String titleString = goods.getItemName();
//            String packageIndex = goods.getItemPackageIndex() > 0 ? String.format("【口袋%s】 ", new Object[]{Integer.valueOf(goods.getItemPackageIndex())}) : "";
//            if (TextUtils.isEmpty(goods.getItemBarCode())) {
//                itemBarCode = "";
//            } else {
//                itemBarCode = goods.getItemBarCode();
//            }
//            String complimentary = "";
//            if (goods.getType() == 2) {
//                complimentary = "[赠品]";
//            }
//            if (TextUtils.isEmpty(goods.getSkuCode())) {
//                sku = "";
//            } else {
//                sku = "[" + goods.getSkuCode() + "]";
//            }
//            String price = Utils.scaleFloat(((float) goods.getItemPrice()) / 100.0f);
//            boolean equals = price.equals(Utils.scaleFloat(((float) goods.getItemOriginPrice()) / 100.0f));
//            if (TextUtils.isEmpty(goods.getProperty())) {
//                property = "";
//            } else {
//                property = goods.getProperty();
//            }
//            String exchangeItem = goods.isExchangedItem() ? "换货商品  " : "";
//            StringBuilder tabBuilder = new StringBuilder("");
//            if (!TextUtils.isEmpty(goods.getItemTag())) {
//                exchangeItem = exchangeItem + goods.getItemTag() + "  ";
//            } else if (TextUtils.isEmpty(exchangeItem)) {
//                exchangeItem = "";
//            }
//            tabBuilder.append(exchangeItem);
//            if (goods.getItemProperties() != null && goods.getItemProperties().size() > 0) {
//                for (String proStr : goods.getItemProperties()) {
//                    tabBuilder.append(proStr + "  ");
//                }
//            }
//            goods.setShowMenu(complimentary + sku + titleString + property + "$    $" + price + "$" + goods.getBuyAmount() + "件$" + packageIndex + "条形码:" + itemBarCode + "$" + tabBuilder.toString());
//        }
//    }
//
//    private boolean isPrintOrderId(OrderInfo order) {
////        PreferenceProvider provider = PreferenceProvider.instance();
////        if (provider == null) {
////            return false;
////        }
////        String oid = provider.getOid();
////        for (String s : judgePrintOrderCondition) {
////            if (s.equals(oid)) {
////                return true;
////            }
////        }
//        return false;
//    }
//}
