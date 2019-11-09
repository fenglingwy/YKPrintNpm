package com.jd.ykposprint.template;

import android.text.TextUtils;

import com.jd.ykposprint.entity.OmsOrder;
import com.jd.ykposprint.utils.BluetoothPrintFormatUtil;
import com.jd.ykposprint.utils.PrintBean;
import com.jd.ykposprint.utils.PrintDataMaker;
import com.jd.ykposprint.utils.PrintTool;
import com.jd.ykposprint.utils.PrinterWriter;
import com.jd.ykposprint.utils.PrinterWriter58mm;
import com.jd.ykposprint.utils.QRCodeUtil;
import com.jd.ykposprint.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class OrderPrintDataMaker1  {


    public List<byte[]> getPrintData(OmsOrder bean) {
        PrinterWriter printer;

        ArrayList<byte[]> data = new ArrayList<>();
//        if (bean == null) {
//            return data;
//        }
        try {
            printer = new PrinterWriter58mm();

            data.add(printer.getDataAndClose());

            printer.setAlignCenter();
            printer.setEmphasizedOn();
            printer.setFontSize(1);
            printer.print(bean.getChannel_name()+"  #"+bean.getShop_total_num());
            printer.printLineFeed(2);

            printer.setFontSize(0);
            printer.print("绿地G-super("+bean.getSub_unit_name()+")");
            printer.printLineFeed(2);

            printer.setAlignCenter();
            printer.setFontSize(0);
            printer.print("商家留存");
            printer.printLineFeed(2);

            printer.setAlignCenter();
            data.add(printer.getDataAndReset());
            printer.printBitmap(PrintTool.createBarCode(""+bean.getRefund_tml_num_id()));
            printer.setAlignCenter();
            printer.setEmphasizedOff();
            printer.setFontSize(0);
            printer.print(""+bean.getRefund_tml_num_id());
            printer.printLineFeed(2);

            printer.setAlignLeft();
            printer.setEmphasizedOff();
            printer.setFontSize(0);
            printer.print("门店序号：");
            printer.setEmphasizedOn();
            printer.setFontSize(0);
            printer.print("#"+bean.getSub_unit_num_id() );
            printer.printLineFeed();
            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("预计送达时间:"+bean.getOrder_item_detail_for_onlines().get(0).getShip_time_end() );
            printer.printLineFeed();

            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("订单号：" + bean.getRefund_tml_num_id());
            printer.printLineFeed();

            printer.print(BluetoothPrintFormatUtil.getSpanLine());
            printer.setAlignLeft();
            printer.setEmphasizedOn();
            printer.setFontSize(1);

            printer.print(bean.getConsumer_name());
            printer.printLineFeed();
            printer.print(Utils.getFormatMobile(bean.getConsumer_telephone()));
            printer.printLineFeed(1);

            printer.print(bean.getConsumer_adr());
            printer.printLineFeed(1);

            printer.setFontSize(0);
            printer.print(BluetoothPrintFormatUtil.getSpanLine());


            printer.setFontSize(1);
            printer.print("备注:\n");
            printer.print(""+bean.getBuyer_meno()+"\n");

            printer.setFontSize(0);
            printer.setEmphasizedOff();


            printer.setAlignLeft();
            printer.setEmphasizedOff();
//            printer.write(result);
            printer.setFontSize(0);
            printer.print("商品规格            单价    数量\n");
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            List<OmsOrder.OrderItemDetailForOnlinesBean> lists = bean.getOrder_item_detail_for_onlines();

            for(int i = 0;i<lists.size();i++){
                OmsOrder.OrderItemDetailForOnlinesBean good = lists.get(i);
                printer.print("["+good.getStyle_num_id()+"]"+good.getItem_name()+"\n");
                printer.print("条形码："+good.getBarcode()+"\n");

                StringBuffer sb0 = new StringBuffer();
                sb0.append("                    "+good.getStandard_price());
                String count0 = ""+good.getQty()+good.getUnit_name();
                printer.printInOneLine(sb0.toString(),count0,0);
                printer.print(BluetoothPrintFormatUtil.getSpanLine());
            }

            StringBuffer sb0 = new StringBuffer();
            sb0.append("商品总计            "+bean.getP_amount());
            String count0 = ""+bean.getItem_qty()+"件";
            printer.printInOneLine(sb0.toString(),count0,0);
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            printer.printInOneLine("优惠金额",Utils.formatPrice(bean.getD_amount()),0);
            printer.printInOneLine("配送费",Utils.formatPrice(bean.getEf_amount()),0);
            printer.printInOneLine("包装费",Utils.formatPrice(bean.getIst_amount()),0);
            printer.printInOneLine("买卖实付",Utils.formatPrice(bean.getReal_pay_amount()),0);
            printer.printInOneLine("下单时间",""+bean.getOrder_date(),0);
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            printer.printLineFeed();
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("拣货员签字");
            printer.printLineFeed(4);
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("送货员签字");
            printer.printLineFeed(4);
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("送货员签字");
            printer.printLineFeed(4);
            printer.setFontSize(0);
            printer.setEmphasizedOn();
            printer.printLineFeed();
            printer.printLineFeed(7);
            printer.setAlignCenter();
            printer.setEmphasizedOn();
            printer.setFontSize(1);


            /************************************************/

            data.add(printer.getDataAndClose());
            printer.setAlignCenter();
            printer.setEmphasizedOn();
            printer.setFontSize(1);
            printer.print(bean.getChannel_name()+"  #"+bean.getShop_total_num());
            printer.printLineFeed(2);

            printer.setFontSize(0);
            printer.print("绿地G-super("+bean.getSub_unit_name()+")");
            printer.printLineFeed(2);


            printer.setAlignCenter();
            data.add(printer.getDataAndReset());
            printer.printBitmap(PrintTool.createBarCode(""+bean.getRefund_tml_num_id()));
            printer.setAlignCenter();
            printer.setEmphasizedOff();
            printer.setFontSize(0);
            printer.print(""+bean.getRefund_tml_num_id());
            printer.printLineFeed(2);

            printer.setAlignLeft();
            printer.setEmphasizedOff();
            printer.setFontSize(0);
            printer.print("门店序号：");
            printer.setEmphasizedOn();
            printer.setFontSize(0);
            printer.print("#"+bean.getSub_unit_num_id() );
            printer.printLineFeed();


//            printer.setAlignLeft();
//            printer.setFontSize(0);
//            printer.setEmphasizedOff();
//            printer.printInOneLine("客服电话","021-12345678",0 );
//            printer.printLineFeed();

            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("预计送达时间:"+bean.getOrder_item_detail_for_onlines().get(0).getShip_time_end() );
            printer.printLineFeed();

            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("订单号：" + bean.getRefund_tml_num_id());
            printer.printLineFeed();

            printer.print(BluetoothPrintFormatUtil.getSpanLine());
            printer.setAlignLeft();
            printer.setEmphasizedOn();
            printer.setFontSize(1);

            printer.print(bean.getConsumer_name());
            printer.printLineFeed();
            printer.print(Utils.getFormatMobile(bean.getConsumer_telephone()));
            printer.printLineFeed(1);

            printer.print(bean.getConsumer_adr());
            printer.printLineFeed(1);

            printer.setFontSize(0);
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            printer.setFontSize(1);
            printer.print("备注:\n");
            printer.print(""+bean.getBuyer_meno()+"\n");

            printer.setFontSize(0);
            printer.setEmphasizedOff();

            printer.setAlignLeft();
            printer.setEmphasizedOff();
            printer.setFontSize(0);
            printer.print("商品规格            单价    数量\n");
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            for(int i = 0;i<lists.size();i++){
                OmsOrder.OrderItemDetailForOnlinesBean good = lists.get(i);
                printer.print("["+good.getStyle_num_id()+"]"+good.getItem_name()+"\n");
                printer.print("条形码："+good.getBarcode()+"\n");

                StringBuffer sb1 = new StringBuffer();
                sb1.append("                    "+good.getStandard_price());
                String count1 = ""+good.getQty()+good.getUnit_name();
                printer.printInOneLine(sb1.toString(),count1,0);
                printer.print(BluetoothPrintFormatUtil.getSpanLine());
            }

            StringBuffer sb1 = new StringBuffer();
            sb1.append("商品总计            "+bean.getP_amount());
            String count1 = ""+bean.getItem_qty()+"件";
            printer.printInOneLine(sb1.toString(),count1,0);
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            printer.printInOneLine("优惠金额",Utils.formatPrice(bean.getD_amount()),0);
            printer.printInOneLine("配送费",Utils.formatPrice(bean.getEf_amount()),0);
            printer.printInOneLine("包装费",Utils.formatPrice(bean.getIst_amount()),0);
            printer.printInOneLine("买卖实付",Utils.formatPrice(bean.getReal_pay_amount()),0);
            printer.printInOneLine("下单时间",""+bean.getOrder_date(),0);
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            printer.printLineFeed(1);
            printer.setAlignCenter();
            printer.print("温馨提示：请仔细核对商品\n");
            printer.print("谢谢惠顾，欢迎再次光临:)\n");

//            data.add(printer.getDataAndReset());
//            printer.printBitmapV2(QRCodeUtil.createCode(PrintTool.LVDI_LOGO_URL,200,200));
//            printer.setAlignCenter();
//            printer.print("描上方二维码，根据指引步骤开具增值税电子普通发票");
//            printer.printLineFeed(2);

//            if (PreferenceProvider.isLvDiOid()) {
//                data.add(printer.getDataAndReset());
//                printer.printBitmapV2(PrintTool.LV_LOGO, false);
//                printer.setAlignCenter();
//                printer.print("描上方二维码，根据指引步骤开具增值税电子普通发票");
//                printer.printLineFeed(2);
//            }

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

            printer.printLineFeed(7);
            printer.setAlignCenter();
            printer.setEmphasizedOn();
            printer.setFontSize(1);


            printer.feedPaperCutPartial();
            data.add(printer.getDataAndClose());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
