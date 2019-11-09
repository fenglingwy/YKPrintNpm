package com.jd.print.template;

import android.content.res.Resources;

import com.jd.print.R;
import com.jd.print.entity.CashEntity;
import com.jd.print.entity.GoodEntity;
import com.jd.print.entity.OrderEntity;
import com.jd.print.impl.PrintImpl;
import com.printer.sdk.Barcode;
import com.printer.sdk.PrinterConstants;
import com.printer.sdk.PrinterConstants.Command;
import com.printer.sdk.PrinterInstance;
import com.printer.sdk.Table;

import java.util.List;

public class Temp1 {

    /**
     * 打印小票示例
     *
     * @param resources -
     * @param mPrinter  -
     */
    public static void printNote(Resources resources, PrintImpl mPrinter, OrderEntity orderEntity) {
        mPrinter.initPrinter();

        mPrinter.setFont(0, 0, 0, 0, 0);
        mPrinter.setPrinter(Command.ALIGN, Command.ALIGN_LEFT);

        if (orderEntity.isRePrint()) {
            mPrinter.printText("重印");
            mPrinter.setPrinter(Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        }

        mPrinter.setPrinter(Command.ALIGN, Command.ALIGN_CENTER);
        mPrinter.setFont(0, 1, 1, 0, 0);
        mPrinter.printText(orderEntity.getUnitName() + "\n");
        mPrinter.printText("销售单\n");

        mPrinter.setPrinter(Command.ALIGN, Command.ALIGN_LEFT);
        mPrinter.setFont(0, 0, 0, 0, 0);

        mPrinter.printText("NO：" + orderEntity.getNumId() + "\n");


        Table table = new Table("货号/品名;单价;数量;金额", ";", new int[]{14, 6, 6, 6});
        List<GoodEntity> goods = orderEntity.getItemList();
        int countNum = 0;
        for (int i = 0; i < goods.size(); i++) {
            GoodEntity good = goods.get(i);
            countNum += good.getQty();
            StringBuilder sb = new StringBuilder();
            String name = good.getItemId() + "/" + good.getItemName();

            table.addRow("" + name + ";" + good.getTradePrice() + ";" + good.getQty() + ";" + good.getTradeAmount());
            table.addRow(";原价:;" + good.getStandardPrice() + ";");
        }
        mPrinter.printTable(table);
        mPrinter.printText("------------------------------\n");

        Table table1 = new Table("合计:" + countNum + ";" + orderEntity.getFamount(), ";", new int[]{26, 6});
        mPrinter.printTable(table1);

        mPrinter.printText("含折扣：" + orderEntity.getMalingAmount() + "\n");
        mPrinter.printText("找零：" + orderEntity.getRamount() + "\n");
        mPrinter.printText("会员号码：" + orderEntity.getVipNo() + "\n");
        mPrinter.printText("收银员:" + orderEntity.getEmpeName() + "\n");
        mPrinter.printText("" + orderEntity.getPayDateTime() + "\n");
        mPrinter.printText("------------------------------\n");

        // 付款方式
        for (int i = 0; i < orderEntity.getCashList().size(); i++) {
            CashEntity cashEntity = orderEntity.getCashList().get(i);
            if (cashEntity.isReturnCashSign()) {
                continue;
            }
            mPrinter.printText(cashEntity.getPayType() + ":" + cashEntity.getPayAmount() + "\n");
        }

        mPrinter.printText("------------------------------\n");

        mPrinter.setPrinter(Command.ALIGN, Command.ALIGN_CENTER);
        mPrinter.setFont(0, 0, 0, 0, 0);
        mPrinter.printText("特价商品恕不接受抵用券支付\r\n " +
                "欢迎下次光临！\r\n" +
                " 如质量问题请凭小票七日内退换\r\n ");

        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_CENTER);
        Barcode barcode1 = new Barcode(PrinterConstants.BarcodeType.CODE128, 2, 100, 2, orderEntity.getNumId());
        mPrinter.setPrinter(Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        mPrinter.printBarCode(barcode1);
        mPrinter.setPrinter(Command.PRINT_AND_WAKE_PAPER_BY_LINE, 5);
    }

}
