package com.jd.ykposprint.template;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.jd.ykposprint.entity.CashEntity;
import com.jd.ykposprint.entity.GoodEntity;
import com.jd.ykposprint.entity.OrderEntity;
import com.jd.ykposprint.utils.PrintTool;
import com.jd.ykposprint.utils.PrinterWriter;
import com.jd.ykposprint.utils.PrinterWriter58mm;

import java.util.ArrayList;
import java.util.List;

public class PosOrderPrintDataMaker {

    public List<byte[]> getPrintData(OrderEntity orderEntity, Context mContext) {
        PrinterWriter printer;

        ArrayList<byte[]> data = new ArrayList<>();

        try {
            printer = new PrinterWriter58mm();

            data.add(printer.getDataAndClose());

            printer.setAlignLeft();
            printer.setEmphasizedOff();
            printer.setFontSize(0);
            if (orderEntity.isRePrint()) {
                printer.print("重印");
            }
            printer.printLineFeed(1);

            printer.setAlignCenter();
            printer.setFontSize(1);
            printer.setEmphasizedOn();
            printer.print("销售单\n");

            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("NO：" + orderEntity.getNumId() + "\n");

            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.setEmphasizedOff();
            printer.print("货号/品名           单价    数量\n");

            List<GoodEntity> goods = orderEntity.getItemList();
            int countNum = 0;

            for (int i = 0; i < goods.size(); i++) {
                GoodEntity good = goods.get(i);
                countNum += good.getQty();
                String name = good.getItemId() + "/" + good.getItemName();
                printer.print(name + "\n");
                printer.printInOneLine("                    " + good.getTradePrice(), "" + good.getQty(), 0);
                printer.print("               原价:" + good.getStandardPrice()+"\n");
                printer.print("------------------------------\n");
            }

            printer.printInOneLine("合计:               " +  orderEntity.getFamount(), "" + countNum, 0);
            printer.print("含折扣：" + orderEntity.getMalingAmount() + "\n");
            printer.print("找零：" + orderEntity.getRamount() + "\n");
            printer.print("会员号码：" + orderEntity.getVipNo() + "\n");
            printer.print("收银员:" + orderEntity.getEmpeName() + "\n");
            printer.print("" + orderEntity.getPayDateTime() + "\n");
            printer.print("------------------------------\n");

            // 付款方式
            for (int i = 0; i < orderEntity.getCashList().size(); i++) {
                CashEntity cashEntity = orderEntity.getCashList().get(i);
                if (cashEntity.isReturnCashSign()) {
                    continue;
                }
                printer.print(cashEntity.getPayType() + ":" + cashEntity.getPayAmount() + "\n");
            }

            printer.print("------------------------------\n");
            printer.setAlignCenter();
            printer.print("特价商品恕不接受抵用券支付\r\n" +
                    "欢迎下次光临！\r\n" +
                    "如质量问题请凭小票七日内退换\r\n");

            data.add(printer.getDataAndReset());
            printer.printBitmap(PrintTool.createBarCode("" + orderEntity.getNumId()));
            printer.print(""+orderEntity.getNumId());
            printer.printLineFeed(6);

            printer.feedPaperCutPartial();
            data.add(printer.getDataAndClose());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort("打印异常" + e.getMessage());
            return new ArrayList<>();
        }
    }
}

