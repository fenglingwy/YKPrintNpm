package com.jd.ykposprint.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jd.ykposprint.R;
import com.jd.ykposprint.entity.OmsOrder;
import com.jd.ykposprint.utils.BluetoothPrintFormatUtil;
import com.jd.ykposprint.utils.PrintTool;
import com.jd.ykposprint.utils.PrinterWriter;
import com.jd.ykposprint.utils.PrinterWriter58mm;
import com.jd.ykposprint.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class OrderPrintDataMaker1  {


    public List<byte[]> getPrintData(OmsOrder bean, Context mContext) {
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

            String tml = "";
            if (bean.getChannel_num_id().equals("98")  || bean.getChannel_num_id() .equals("99") ) {
                tml = bean.getTml_num_id();
            } else {
                tml = bean.getSo_no();
            }
            printer.printBitmap(PrintTool.createBarCode(""+tml));
            printer.setAlignCenter();
            printer.setFontSize(0);
            printer.print(""+tml);
            printer.printLineFeed(2);

            printer.setAlignLeft();
            printer.setEmphasizedOff();
            printer.setFontSize(0);
            printer.print("门店序号：");
            printer.print("#"+bean.getSub_unit_num_id() );
            printer.printLineFeed();
            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.print("预计送达时间:"+bean.getOrder_item_detail_for_onlines().get(0).getShip_time_end() );
            printer.printLineFeed();

            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("订单号：" + bean.getTml_num_id());
            printer.printLineFeed();

            printer.print(BluetoothPrintFormatUtil.getSpanLine());
            printer.setAlignLeft();
            printer.setEmphasizedOn();
            printer.setFontSize(0);

            printer.print(bean.getConsumer_name());
            printer.print(Utils.getFormatMobile(bean.getConsumer_telephone()));
            printer.printLineFeed(1);

            printer.print(bean.getConsumer_adr());
            printer.printLineFeed(1);

            printer.setFontSize(0);
            printer.print("备注:");
            printer.print(""+bean.getBuyer_meno()+"\n");

            printer.setEmphasizedOff();
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            printer.setAlignLeft();
            printer.setEmphasizedOff();
//            printer.write(result);
            printer.setFontSize(0);
            printer.print("商品规格            单价    数量\n");
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            List<OmsOrder.OrderItemDetailForOnlinesBean> lists = bean.getOrder_item_detail_for_onlines();

            for(int i = 0;i<lists.size();i++){
                OmsOrder.OrderItemDetailForOnlinesBean good = lists.get(i);

                printer.print("条码："+good.getBarcode()+"\n");
                printer.print("["+good.getStyle_num_id()+"]"+good.getItem_name()+"\n");

                StringBuffer sb0 = new StringBuffer();
                sb0.append("                    "+good.getStandard_price());
                String count0 = ""+good.getQty()+"件";
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
//            printer.printInOneLine("包装费",Utils.formatPrice(bean.getIst_amount()),0);
            printer.printInOneLine("买卖实付",Utils.formatPrice(bean.getReal_pay_amount()),0);
            printer.printInOneLine("下单时间",""+bean.getOrder_date(),0);
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            printer.printLineFeed();
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("拣货员签字");
            printer.printLineFeed(3);
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("送货员签字");
            printer.printLineFeed(3);
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("送货员签字");
            printer.printLineFeed(3);
            printer.setFontSize(0);
            printer.setEmphasizedOn();
            printer.printLineFeed();
            printer.printLineFeed(5);
            printer.setAlignCenter();
            printer.setEmphasizedOn();


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
            printer.printBitmap(PrintTool.createBarCode(""+tml));
            printer.setAlignCenter();
            printer.setFontSize(0);
            printer.print(""+tml);
            printer.printLineFeed(2);
            printer.setEmphasizedOff();

            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.print("门店序号：");
            printer.print("#"+bean.getSub_unit_num_id() );
            printer.printLineFeed();

            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.print("预计送达时间:"+bean.getOrder_item_detail_for_onlines().get(0).getShip_time_end() );
            printer.printLineFeed();
            printer.print("商家服务电话:"+bean.getSub_unit_tel());
            printer.printLineFeed();
            printer.print("订单号：" + bean.getTml_num_id());
            printer.printLineFeed();

            printer.print(BluetoothPrintFormatUtil.getSpanLine());
            printer.setAlignLeft();
            printer.setEmphasizedOn();
            printer.setFontSize(0);

            printer.print(bean.getConsumer_name());
            printer.print(Utils.getFormatMobile(bean.getConsumer_telephone()));
            printer.printLineFeed(1);

            printer.print(bean.getConsumer_adr());
            printer.printLineFeed(1);

            printer.setFontSize(0);
            printer.print("备注:");
            printer.print(""+bean.getBuyer_meno()+"\n");

            printer.setEmphasizedOff();

            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            printer.setAlignLeft();
            printer.setEmphasizedOff();
            printer.setFontSize(0);
            printer.print("商品规格            单价    数量\n");
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            for(int i = 0;i<lists.size();i++){
                OmsOrder.OrderItemDetailForOnlinesBean good = lists.get(i);
                printer.print("条码："+good.getBarcode()+"\n");
                printer.print("["+good.getStyle_num_id()+"]"+good.getItem_name()+"\n");

                StringBuffer sb1 = new StringBuffer();
                sb1.append("                    "+good.getStandard_price());
                String count1 = ""+good.getQty()+"件";
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
//            printer.printInOneLine("包装费",Utils.formatPrice(bean.getIst_amount()),0);
            printer.printInOneLine("买卖实付",Utils.formatPrice(bean.getReal_pay_amount()),0);
            printer.printInOneLine("下单时间",""+bean.getOrder_date(),0);
            printer.print(BluetoothPrintFormatUtil.getSpanLine());

            data.add(printer.getDataAndReset());
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.wechat);

            printer.setAlignCenter();
            printer.printBitmapV2(bitmap,true);
            printer.setAlignCenter();

            printer.print("温馨提示:请核对好商品,谢谢惠顾!\n");
            printer.print("欢迎再次光临！：)\n");

            printer.printLineFeed(6);
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
