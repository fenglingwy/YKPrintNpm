package com.jd.print.inter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;


import com.jd.print.constant.DensityConstant;
import com.jd.print.constant.PaperConstant;
import com.printer.sdk.Barcode;
import com.printer.sdk.PrinterConstants;
import com.printer.sdk.PrinterInstance;
import com.printer.sdk.Table;

import java.io.File;
import java.io.InputStream;

/**
 * @author :Reginer in  2019/8/16 10:23.
 * 联系方式:QQ:282921012
 * 功能描述:打印机接口
 */
public interface IPrint {
    /**
     * 连接打印机
     *
     * @param callback 连接回调{@link IConnectCallback}
     * @return 打印机操作类, 需要可获取，不需要也可以如下使用:
     * <p>
     * PrintImpl impl = new PrintImpl();
     * impl.connectPrinter(callback);
     * impl.getPrinter().***
     */
    PrinterInstance connectPrinter(@NonNull IConnectCallback callback);

    /**
     * 连接打印机，动态指定各个参数，如参数确定，用上一个连接方法
     *
     * @param device   路径
     * @param baudrate 波特率
     * @param flags    flag
     * @param callback 连接回调{@link IConnectCallback}
     * @return 打印机操作类
     */
    PrinterInstance connectPrinter(File device, int baudrate, int flags, @NonNull IConnectCallback callback);

    /**
     * 关闭连接
     */
    void closeConnect();

    /**
     * 向打印机发送数据
     *
     * @param srcData 要发送的byte数组
     * @return <p>
     * >0 成功发送到打印机的字节数
     * -1 未初始化打印
     * -2 srcData 为空或者srcData 里没有数据。
     * </p>
     */
    int sendBytesData(byte[] srcData);

    /**
     * 读取打印机返回的数据
     *
     * @param buffer 用于接收读到字节的数组
     * @return <p>
     * >0 成功读到的字节数
     * -1 未初始化打印
     * -2 srcData 为空或者 srcData 里没有数据。
     * </p>
     */
    int read(byte[] buffer);

    /**
     * <p>
     * 初始化打印机
     * 可以清除缓存
     * </p>
     */
    void initPrinter();

    /**
     * 设置打印机字体
     *
     * @param mCharacterType 0 表示 12*24 字体大小，1 表示 9*16 字体大小，此设置临时有效
     * @param mWidth         倍宽，范围 0~7
     * @param mHeight        倍高，范围 0~7
     * @param mBold          0 不加粗，1 加粗
     * @param mUnderline     0 无 下划线，1 下划线
     */
    void setFont(int mCharacterType, int mWidth, int mHeight, int mBold, int mUnderline);

    /**
     * 设置打印机打印
     *
     * @param command <p>
     *                PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LNCH 打印并走纸 value 点行
     *                PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE 打印并走纸 value 字符行
     *                PrinterConstants.Command.ALIGN 设置打印内容位置，Value 值可指定设置的具体位置
     *                </p>
     * @param value   <p>
     *                Value 值可指定设置的具体位置
     *                PrinterConstants.Command.ALIGN_LEFT;
     *                PrinterConstants.Command.ALIGN_CENTER;
     *                PrinterConstants.Command.ALIGN_RIGHT
     *                </p>
     */
    void setPrinter(int command, int value);

    /**
     * 设置纸类型
     *
     * @return 返回值应加以说明，我还没看到这返回值是什么意思，你了解的话需要加上
     * <p>
     * >0 成功发送到打印机的字节数
     * -1 未初始化打印
     * -2 srcData 为空或者srcData 里没有数据。
     * </p>
     */
    int setPaperType(@PaperConstant.PrintPaperType int paperType);

    /**
     * 设置浓度
     *
     * @param density 浓度值
     * @return <p>  >0 成功发送到打印机的字节数
     * -1 未初始化打印
     * -2 srcData 为空或者srcData 里没有数据。</p>
     */
    int setDensity(@DensityConstant.PrintDensity int density);

    /**
     * 设置走纸
     *
     * @param line 走纸行数 mm
     */
    void setPaperFeed(int line);

    /**
     * 设置退纸
     *
     * @param line 退纸行数 mm
     */
    void setPaperBack(int line);

    /**
     * 打印自检页
     */
    void printSelfCheck();


    //设置中的一些其他的设置参照setPaperType加上去

    /**
     * 打印文本
     *
     * @param text 待打印内容
     */
    void printText(String text);

    /**
     * 打印条码
     *
     * @param barcode 条码内容
     */
    int printBarCode(Barcode barcode);

    /**
     * 打印图片
     *
     * @param bitmap       单色位图
     * @param alignType    -
     * @param left         -
     * @param isCompressed 是否压缩
     */
    void printImage(Bitmap bitmap, PrinterConstants.PAlign alignType, int left, boolean isCompressed);

    /**
     * 数据分包打印 适合大图片打印
     *
     * @param bitmap       单色位图
     * @param alignType    对齐方式
     * @param left         偏移 alignType为NONE时有效
     * @param isCompressed 是否压缩
     */
    void printBigImage(Bitmap bitmap, PrinterConstants.PAlign alignType, int left, boolean isCompressed);

    /**
     * 打印表格
     *
     * @param table 表格
     */
    void printTable(Table table);

    /**
     * 升级sdk
     *
     * @param inputStream 升级文件
     * @param hexFileLength 文件大小
     * @return -2 成功
     */
    int update(InputStream inputStream, String hexFileLength);

}
