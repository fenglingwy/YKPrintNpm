package com.jd.ykposprint.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;

public abstract class PrinterWriter {
    private static final String CHARSET = "GBK";
    public static final int HEIGHT_PARTING_DEFAULT = 255;
    private ByteArrayOutputStream bos;
    private int heightParting;

    public abstract int getDrawableMaxWidth();

    public abstract int getLineStringWidth(int i);

    public abstract int getLineWidth();

    public PrinterWriter() throws IOException {
        this(255);
    }

    public PrinterWriter(int parting) throws IOException {
        if (parting <= 0 || parting > 255) {
            this.heightParting = 255;
        } else {
            this.heightParting = parting;
        }
        init();
    }

    @Deprecated
    public void reset() throws IOException {
        init();
    }

    public void init() throws IOException {
        this.bos = new ByteArrayOutputStream();
        write(PrinterUtils.initPrinter());
    }

    @Deprecated
    public byte[] getData() throws IOException {
        return getDataAndClose();
    }

    public byte[] getDataAndReset() throws IOException {
        this.bos.flush();
        byte[] data = this.bos.toByteArray();
        this.bos.reset();
        return data;
    }

    public byte[] getDataAndClose() throws IOException {
        this.bos.flush();
        byte[] data = this.bos.toByteArray();
        this.bos.close();
        this.bos = null;
        return data;
    }

    public void write(byte[] data) throws IOException {
        if (this.bos == null) {
            reset();
        }
        this.bos.write(data);
    }

    public void setAlignCenter() throws IOException {
        write(PrinterUtils.alignCenter());
    }

    public void setAlignLeft() throws IOException {
        write(PrinterUtils.alignLeft());
    }

    public void setAlignRight() throws IOException {
        write(PrinterUtils.alignRight());
    }

    public void setEmphasizedOn() throws IOException {
        write(PrinterUtils.emphasizedOn());
    }

    public void setEmphasizedOff() throws IOException {
        write(PrinterUtils.emphasizedOff());
    }

    public void setFontSize(int size) throws IOException {
        write(PrinterUtils.fontSizeSetBig(size));
    }

    public void setLineHeight(int height) throws IOException {
        if (height >= 0 && height <= 255) {
            write(PrinterUtils.printLineHeight((byte) height));
        }
    }

    public void print(String string) throws IOException {
        print(string, CHARSET);
    }

    public void print(String string, String charsetName) throws IOException {
        if (string != null) {
            write(string.getBytes(charsetName));
        }
    }

    public void printLine() throws IOException {
        String line = "";
        for (int length = getLineWidth(); length > 0; length--) {
            line = line + "─";
        }
        print(line);
    }

    public void printDottedLine() throws IOException {
        printInOneLine("-----------------------------------------------", 0);
    }

    public void setFontHRI(byte n) throws IOException {
        write(PrinterUtils.select_font_hri(n));
    }

    public void setPositionHRI(byte n) throws IOException {
        write(PrinterUtils.select_position_hri(n));
    }

    public void setBarCodeWidth(byte dots) throws IOException {
        write(PrinterUtils.barcode_width(dots));
    }

    public void setBarCodeHeight(byte dots) throws IOException {
        write(PrinterUtils.barcode_height(dots));
    }

    public void printBarCode(String barcode2print, int height, int width, int textposition) throws IOException {
        write(PrinterUtils.printBarCode(barcode2print, 8, height, width, textposition));
    }

    public void printBarCodeV2(String barcode2print) throws IOException {
        write(PrinterUtils.print_bar_code_V2(barcode2print, 73));
    }

    public void setCusorPosition(int position) throws IOException {
        write(PrinterUtils.setCusorPosition(position));
    }

    public void printInOneLine(String str1, String str2, int textSize) throws IOException {
        printInOneLine(str1, str2, textSize, CHARSET);
    }

    public void printInOneLine(String str1, String str2, int textSize, String charsetName) throws IOException {
        int lineLength = getLineStringWidth(textSize);
        String empty = "";
        for (int needEmpty = lineLength - ((getStringWidth(str1) + getStringWidth(str2)) % lineLength); needEmpty > 0; needEmpty--) {
            empty = empty + " ";
        }
        print(str1 + empty + str2, charsetName);
    }

    public void printInOneLine(String str, int textSize) throws IOException {
        String s;
        int lineLength = getLineStringWidth(textSize);
        String str2 = "";
        if (lineLength - getStringWidth(str) < 0) {
            s = str.substring(0, lineLength);
        } else {
            s = str;
        }
        print(s, CHARSET);
    }

    public void underLine(boolean cn, int dot) throws IOException {
        write(PrinterUtils.underLine(cn, dot));
    }

    public int getStringWidth(String str) {
        int width = 0;
        for (char c : str.toCharArray()) {
            width += isChinese(c) ? 2 : 1;
        }
        return width;
    }

    @Deprecated
    public void printDrawable(Resources res, int id) throws IOException {
        Bitmap image = scalingBitmap(res, id, getDrawableMaxWidth());
        if (image != null) {
            byte[] command = PrinterUtils.decodeBitmap(image, this.heightParting);
            image.recycle();
            if (command != null) {
                try {
                    write(command);
                } catch (IOException e) {
                    throw new IOException(e.getMessage());
                }
            }
        }
    }

    public ArrayList<byte[]> getImageByte(Resources res, int id) {
        Bitmap image = scalingBitmap(res, id, getDrawableMaxWidth());
        if (image == null) {
            return null;
        }
        ArrayList<byte[]> decodeBitmapToDataList = PrinterUtils.decodeBitmapToDataList(image, this.heightParting);
        image.recycle();
        return decodeBitmapToDataList;
    }

    private Bitmap scalingBitmap(Resources res, int id, int maxWidth) {
        if (res == null) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, options);
        options.inJustDecodeBounds = false;
        if (maxWidth > 0 && options.outWidth > maxWidth) {
            int sampleSize = (int) Math.floor(((double) options.outWidth) / ((double) maxWidth));
            if (sampleSize > 1) {
                options.inSampleSize = sampleSize;
            }
        }
        try {
            Bitmap image = BitmapFactory.decodeResource(res, id, options);
            int width = image.getWidth();
            int height = image.getHeight();
            if (maxWidth <= 0 || width <= maxWidth) {
                return image;
            }
            float scale = ((float) maxWidth) / ((float) width);
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap createBitmap = Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
            image.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    @Deprecated
    public void printDrawable(Drawable drawable) throws IOException {
        Bitmap image = scalingDrawable(drawable, getDrawableMaxWidth());
        if (image != null) {
            byte[] command = PrinterUtils.decodeBitmap(image, this.heightParting);
            image.recycle();
            if (command != null) {
                try {
                    write(command);
                } catch (IOException e) {
                    throw new IOException(e.getMessage());
                }
            }
        }
    }

    public ArrayList<byte[]> getImageByte(Drawable drawable) {
        Bitmap image = scalingDrawable(drawable, getDrawableMaxWidth());
        if (image == null) {
            return null;
        }
        ArrayList<byte[]> decodeBitmapToDataList = PrinterUtils.decodeBitmapToDataList(image, this.heightParting);
        image.recycle();
        return decodeBitmapToDataList;
    }

    private Bitmap scalingDrawable(Drawable drawable, int maxWidth) {
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            return null;
        }
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        try {
            Bitmap image = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
            Canvas canvas = new Canvas(image);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
            if (maxWidth <= 0 || width <= maxWidth) {
                return image;
            }
            float scale = ((float) maxWidth) / ((float) width);
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap createBitmap = Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
            image.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    public void printBitmap(Bitmap image) throws IOException {
        Bitmap scalingImage = scalingBitmap(image, getDrawableMaxWidth());
        if (scalingImage != null) {
            byte[] command = PrinterUtils.decodeBitmap(scalingImage, this.heightParting);
            scalingImage.recycle();
            if (command != null) {
                try {
                    write(command);
                } catch (IOException e) {
                    throw new IOException(e.getMessage());
                }
            }
        }
    }

    public void printBitmapV2(Bitmap image, boolean isRecycle) throws IOException {
        byte[] command = PrinterUtils.decodeBitmap(image, this.heightParting);
        if (isRecycle) {
            image.recycle();
        }
        if (command != null) {
            try {
                write(command);
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public void printBitmapV2(Bitmap image) throws IOException {
        printBitmapV2(image, true);
    }

    public ArrayList<byte[]> getImageByte(Bitmap image) {
        if (scalingBitmap(image, getDrawableMaxWidth()) == null) {
            return null;
        }
        ArrayList<byte[]> decodeBitmapToDataList = PrinterUtils.decodeBitmapToDataList(image, this.heightParting);
        image.recycle();
        return decodeBitmapToDataList;
    }

    private Bitmap scalingBitmap(Bitmap image, int maxWidth) {
        if (image == null || image.getWidth() <= 0 || image.getHeight() <= 0) {
            return null;
        }
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            float scale = 1.0f;
            if (maxWidth <= 0 || width <= maxWidth) {
                scale = ((float) maxWidth) / ((float) width);
            }
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            return Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    @Deprecated
    public void printImageFile(String filePath) throws IOException {
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            int width = options.outWidth;
            int height = options.outHeight;
            if (width > 0 && height > 0) {
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Config.ARGB_8888;
                printBitmap(BitmapFactory.decodeFile(filePath, options));
            }
        } catch (Exception | OutOfMemoryError e) {
        }
    }

    public ArrayList<byte[]> getImageByte(String filePath) {
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            int width = options.outWidth;
            int height = options.outHeight;
            if (width <= 0 || height <= 0) {
                return null;
            }
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Config.ARGB_8888;
            return getImageByte(BitmapFactory.decodeFile(filePath, options));
        } catch (Exception | OutOfMemoryError e) {
            return null;
        }
    }

    public void printLineFeed() throws IOException {
        printLineFeed(1);
    }

    public void printLineFeed(int num) throws IOException {
        write(PrinterUtils.printLineFeed(num));
    }

    public void feedPaperCut() throws IOException {
        write(PrinterUtils.feedPaperCut());
    }

    public void feedPaperCutPartial() throws IOException {
        write(PrinterUtils.feedPaperCutPartial());
    }

    public void setHeightParting(int parting) {
        if (parting > 0 && parting <= 255) {
            this.heightParting = parting;
        }
    }

    public int getHeightParting() {
        return this.heightParting;
    }

    public static boolean isChinese(char c) {
        UnicodeBlock ub = UnicodeBlock.of(c);
        return ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == UnicodeBlock.GENERAL_PUNCTUATION;
    }
}
