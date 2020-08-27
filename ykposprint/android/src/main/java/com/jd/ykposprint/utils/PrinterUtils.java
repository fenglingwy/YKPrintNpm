package com.jd.ykposprint.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import com.baidu.mapapi.UIMsg.m_AppUI;
//import org.android.agoo.message.MessageService;
//import u.aly.cv;

public class PrinterUtils {
    public static final byte CAN = 24;
    private static final String CHARSET = "GBK";
    public static final byte CR = 13;
    public static final byte DLE = 16;
    public static final byte ENQ = 5;
    public static final byte EOT = 4;
    public static final byte ESC = 27;
    public static final byte FF = 12;
    public static final byte FS = 28;
    public static final byte GS = 29;
    public static final byte HT = 9;
    public static final byte LF = 10;
    public static final byte SP = 32;
    private static String[] binaryArray = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    private static String hexStr = "0123456789ABCDEF";

    public static class BarCode {
        public static final byte CODE128 = 73;
        public static final byte CODE39 = 4;
        public static final byte CODE93 = 72;
        public static final byte EAN13 = 2;
        public static final byte EAN8 = 3;
        public static final byte ITF = 5;
        public static final byte NW7 = 6;
        public static final byte UPC_A = 0;
        public static final byte UPC_E = 1;
    }

    public static class CodePage {
        public static final byte KATAKANA = 1;
        public static final byte PC437 = 0;
        public static final byte PC850 = 2;
        public static final byte PC852 = 18;
        public static final byte PC858 = 19;
        public static final byte PC860 = 3;
        public static final byte PC863 = 4;
        public static final byte PC865 = 5;
        public static final byte PC866 = 17;
        public static final byte WPC1252 = 16;
    }

    public static byte[] initPrinter() {
        return new byte[]{ESC, 64};
    }

    public static byte[] printLineFeed() {
        return printLineFeed(1);
    }

    public static byte[] printLineFeed(int lineNum) {
        byte[] result = new byte[lineNum];
        for (int i = 0; i < lineNum; i++) {
            result[i] = 10;
        }
        return result;
    }

    public static byte[] underLine(boolean cn, int dot) {
        byte[] result = new byte[3];
        result[0] = cn ? FS : ESC;
        result[1] = 45;
        switch (dot) {
            case 1:
                result[2] = 1;
                break;
            case 2:
                result[2] = 2;
                break;
            default:
                result[2] = 0;
                break;
        }
        return result;
    }

    public static byte[] emphasizedOn() {
//        return new byte[]{ESC, 69, cv.m};
        return new byte[]{ESC, 69, 15};
    }

    public static byte[] emphasizedOff() {
        return new byte[]{ESC, 69, 0};
    }

    public static byte[] overlappingOn() {
        return new byte[]{ESC, 47, 15};
//        return new byte[]{ESC, 47, cv.m};
    }

    public static byte[] overlappingOff() {
        return new byte[]{ESC, 47, 0};
    }

    public static byte[] doubleStrikeOn() {
        return new byte[]{ESC, 71, 15};
//        return new byte[]{ESC, 71, cv.m};
    }

    public static byte[] doubleStrikeOff() {
        return new byte[]{ESC, 71, 0};
    }

    public static byte[] selectFontA() {
        return new byte[]{ESC, 77, 0};
    }

    public static byte[] selectFontB() {
        return new byte[]{ESC, 77, 1};
    }

    public static byte[] selectFontC() {
        return new byte[]{ESC, 77, 2};
    }

    public static byte[] selectCNFontA() {
        return new byte[]{FS, 33, 0};
    }

    public static byte[] selectCNFontB() {
        return new byte[]{FS, 33, 1};
    }

    public static byte[] doubleHeightWidthOff() {
        return new byte[]{ESC, 33, 0};
    }

    public static byte[] doubleHeightOn() {
        return new byte[]{ESC, 33, 16};
    }

    public static byte[] doubleHeightWidthOn() {
        return new byte[]{ESC, 33, 56};
    }

    public static byte[] alignLeft() {
        return new byte[]{ESC, 97, 0};
    }

    public static byte[] alignCenter() {
        return new byte[]{ESC, 97, 1};
    }

    public static byte[] alignRight() {
        return new byte[]{ESC, 97, 2};
    }

    public static byte[] printAndFeedLines(byte n) {
        return new byte[]{ESC, 100, n};
    }

    public static byte[] printAndReverseFeedLines(byte n) {
        return new byte[]{ESC, 101, n};
    }

    public static byte[] printHorizontalTab() {
        return new byte[]{ESC, 44, 20, FS, 0};
    }

    public static byte[] printHTNext() {
        return new byte[]{9};
    }

    public static byte[] printLineNormalHeight() {
        return new byte[]{ESC, 50};
    }

    public static byte[] printLineHeight(byte height) {
        return new byte[]{ESC, 51, height};
    }

    public static byte[] selectCodeTab(byte cp) {
        return new byte[]{ESC, 116, cp};
    }

    public static byte[] drawerKick() {
        return new byte[]{ESC, 112, 0, 60, 120};
    }

    public static byte[] selectColor1() {
        return new byte[]{ESC, 114, 0};
    }

    public static byte[] selectColor2() {
        return new byte[]{ESC, 114, 1};
    }

    public static byte[] whitePrintingOn() {
        return new byte[]{GS, 66, Byte.MIN_VALUE};
    }

    public static byte[] whitePrintingOff() {
        return new byte[]{GS, 66, 0};
    }

    public static byte[] barcode_height(byte dots) {
        return new byte[]{GS, 104, dots};
    }

    public static byte[] barcode_width(byte dots) {
        return new byte[]{GS, 119, dots};
    }

    public static byte[] select_font_hri(byte n) {
        return new byte[]{GS, 102, n};
    }

    public static byte[] select_position_hri(byte n) {
        return new byte[]{GS, BarCode.CODE93, n};
    }

    public static byte[] print_bar_code(byte barcode_typ, String barcode2print) throws UnsupportedEncodingException {
        byte[] barcodeBytes = barcode2print.getBytes(CHARSET);
        byte[] result = new byte[(barcodeBytes.length + 3 + 1)];
        result[0] = GS;
        result[1] = 107;
        result[2] = barcode_typ;
        int idx = 3;
        for (byte b : barcodeBytes) {
            result[idx] = b;
            idx++;
        }
        result[idx] = 0;
        return result;
    }

    public static byte[] print_bar_code_V2(String stBarcode, int code) {
        byte[] returnText = new byte[(stBarcode.length() + 4)];
        returnText[0] = GS;
        returnText[1] = 107;
        returnText[2] = (byte) code;
        returnText[3] = (byte) stBarcode.length();
        System.arraycopy(stBarcode.getBytes(), 0, returnText, 4, stBarcode.getBytes().length);
        return returnText;
    }

    public static byte[] printBarCode(String data, int symbology, int height, int width, int textposition) {
        if (symbology < 0 || symbology > 8) {
            return new byte[]{10};
        }
        if (textposition < 0 || textposition > 3) {
            textposition = 0;
        }
        if (height < 1 || height > 255) {
            height = 162;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(new byte[]{GS, 102, 0, GS, BarCode.CODE93, (byte) textposition, GS, 119, (byte) width, GS, 104, (byte) height, 10});
            byte[] barcode = data.getBytes();
            if (symbology == 8) {
                buffer.write(new byte[]{GS, 107, BarCode.CODE128, (byte) barcode.length, 123, 65});
            } else {
                buffer.write(new byte[]{GS, 107, (byte) (symbology + 65), (byte) barcode.length});
            }
            buffer.write(barcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteMerger(buffer.toByteArray(), printLineFeed());
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[(byte_1.length + byte_2.length)];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    public static byte[] byteMerger(byte[][] byteList) {
        int length = 0;
        for (byte[] length2 : byteList) {
            length += length2.length;
        }
        byte[] result = new byte[length];
        int index = 0;
        for (int i = 0; i < byteList.length; i++) {
            byte[] nowByte = byteList[i];
            for (int k = 0; k < byteList[i].length; k++) {
                result[index] = nowByte[k];
                index++;
            }
        }
        for (int i2 = 0; i2 < index; i2++) {
        }
        return result;
    }

    public static byte[] setCusorPosition(int position) {
        return new byte[]{ESC, 36, (byte) position, (byte) (position >> 8)};
    }

    public static byte[] set_HT_position(byte col) {
        return new byte[]{ESC, 68, col, 0};
    }

    public static byte[] fontSizeSetBig(int num) {
        byte realSize = 0;
        switch (num) {
            case 0:
                realSize = 0;
                break;
            case 1:
                realSize = CodePage.PC866;
                break;
            case 2:
                realSize = 34;
                break;
            case 3:
                realSize = 51;
                break;
            case 4:
                realSize = 68;
                break;
            case 5:
                realSize = 85;
                break;
            case 6:
                realSize = 102;
                break;
            case 7:
                realSize = 119;
                break;
        }
        return new byte[]{GS, 33, realSize};
    }

    public static byte[] feedPaperCut() {
        return new byte[]{GS, 86, 65, 0};
    }

    public static byte[] feedPaperCutPartial() {
        return new byte[]{GS, 86, 66, 0};
    }

    public static ArrayList<byte[]> decodeBitmapToDataList(Bitmap image, int parting) {
        int partHeight;
        int red;
        int green;
        int blue;
        if (parting <= 0 || parting > 255) {
            parting = 255;
        }
        if (image == null) {
            return null;
        }
        int width = image.getWidth();
        int height = image.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        if (width > 2040) {
            float scale = 2040.0f / ((float) width);
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            try {
                Bitmap resizeImage = Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
                ArrayList<byte[]> decodeBitmapToDataList = decodeBitmapToDataList(resizeImage, parting);
                resizeImage.recycle();
                return decodeBitmapToDataList;
            } catch (OutOfMemoryError e) {
                return null;
            }
        } else {
            String widthHexString = Integer.toHexString(width % 8 == 0 ? width / 8 : (width / 8) + 1);
            if (widthHexString.length() > 2) {
                return null;
            }
            if (widthHexString.length() == 1) {
                widthHexString = "0" + widthHexString;
//                widthHexString = MessageService.MSG_DB_READY_REPORT + widthHexString;
            }
            String widthHexString2 = widthHexString + "00";
            String zeroStr = "";
            int zeroCount = width % 8;
            if (zeroCount > 0) {
                for (int i = 0; i < 8 - zeroCount; i++) {
//                    zeroStr = zeroStr + MessageService.MSG_DB_READY_REPORT;
                    zeroStr = zeroStr + "0";
                }
            }
            ArrayList<String> commandList = new ArrayList<>();
            int time = height % parting == 0 ? height / parting : (height / parting) + 1;
            for (int t = 0; t < time; t++) {
                if (t == time - 1) {
                    partHeight = height % parting;
                } else {
                    partHeight = parting;
                }
                String heightHexString = Integer.toHexString(partHeight);
                if (heightHexString.length() > 2) {
                    return null;
                }
                if (heightHexString.length() == 1) {
//                    heightHexString = MessageService.MSG_DB_READY_REPORT + heightHexString;
                    heightHexString = "0" + heightHexString;
                }
                commandList.add("1D763000" + widthHexString2 + (heightHexString + "00"));
                ArrayList<String> list = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                for (int i2 = 0; i2 < partHeight; i2++) {
                    sb.delete(0, sb.length());
                    for (int j = 0; j < width; j++) {
                        int color = image.getPixel(j, (t * parting) + i2);
                        if (image.hasAlpha()) {
                            int alpha = Color.alpha(color);
                            float offset = ((float) alpha) / 255.0f;
                            red = ((int) Math.ceil((double) (((float) (Color.red(color)  -255)) * offset))) + 255;
                            green = ((int) Math.ceil((double) (((float) (Color.green(color) -255)) * offset))) + 255;
                            blue = ((int) Math.ceil((double) (((float) (Color.blue(color) -255)) * offset))) + 255;
//                            red = ((int) Math.ceil((double) (((float) (Color.red(color) + m_AppUI.V_WM_ADDLISTUPDATE)) * offset))) + 255;
//                            green = ((int) Math.ceil((double) (((float) (Color.green(color) + m_AppUI.V_WM_ADDLISTUPDATE)) * offset))) + 255;
//                            blue = ((int) Math.ceil((double) (((float) (Color.blue(color) + m_AppUI.V_WM_ADDLISTUPDATE)) * offset))) + 255;
                        } else {
                            red = Color.red(color);
                            green = Color.green(color);
                            blue = Color.blue(color);
                        }
                        if (red <= 160 || green <= 160 || blue <= 160) {
                            sb.append("1");
                        } else {
//                            sb.append(MessageService.MSG_DB_READY_REPORT);
                            sb.append("0");
                        }
                    }
                    if (zeroCount > 0) {
                        sb.append(zeroStr);
                    }
                    list.add(sb.toString());
                }
                ArrayList<String> bmpHexList = new ArrayList<>();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    String binaryStr = (String) it.next();
                    sb.delete(0, sb.length());
                    for (int i3 = 0; i3 < binaryStr.length(); i3 += 8) {
                        sb.append(binaryStrToHexString(binaryStr.substring(i3, i3 + 8)));
                    }
                    bmpHexList.add(sb.toString());
                }
                commandList.addAll(bmpHexList);
            }
            ArrayList<byte[]> data = new ArrayList<>();
            Iterator it2 = commandList.iterator();
            while (it2.hasNext()) {
                data.add(hexStringToBytes((String) it2.next()));
            }
            return data;
        }
    }

    public static byte[] decodeBitmap(Bitmap image, int parting) {
        ArrayList<byte[]> data = decodeBitmapToDataList(image, parting);
        int len = 0;
        Iterator it = data.iterator();
        while (it.hasNext()) {
            len += ((byte[]) it.next()).length;
        }
        byte[] destArray = new byte[len];
        int destLen = 0;
        Iterator it2 = data.iterator();
        while (it2.hasNext()) {
            byte[] srcArray = (byte[]) it2.next();
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
            destLen += srcArray.length;
        }
        return destArray;
    }

    public static byte[] decodeBitmap(Bitmap image) {
        return decodeBitmap(image, 255);
    }

//    public static byte[] mergerByteArray(byte[]... byteArray) {
//        int length = 0;
//        for (byte[] item : byteArray) {
//            length += item.length;
//        }
//        byte[] result = new byte[length];
//        int index = 0;
//        for (byte[] item2 : byteArray) {
//            for (byte b : byteArray[r7]) {
//                result[index] = b;
//                index++;
//            }
//        }
//        return result;
//    }

    public static String binaryStrToHexString(String binaryStr) {
        String hex = "";
        String f4 = binaryStr.substring(0, 4);
        String b4 = binaryStr.substring(4, 8);
        for (int i = 0; i < binaryArray.length; i++) {
            if (f4.equals(binaryArray[i])) {
                hex = hex + hexStr.substring(i, i + 1);
            }
        }
        for (int i2 = 0; i2 < binaryArray.length; i2++) {
            if (b4.equals(binaryArray[i2])) {
                hex = hex + hexStr.substring(i2, i2 + 1);
            }
        }
        return hex;
    }

    public static byte[] hexListToByte(List<String> list) {
        ArrayList<byte[]> commandList = new ArrayList<>();
        for (String hexStr2 : list) {
            commandList.add(hexStringToBytes(hexStr2));
        }
        int len = 0;
        Iterator it = commandList.iterator();
        while (it.hasNext()) {
            len += ((byte[]) it.next()).length;
        }
        byte[] destArray = new byte[len];
        int destLen = 0;
        Iterator it2 = commandList.iterator();
        while (it2.hasNext()) {
            byte[] srcArray = (byte[]) it2.next();
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
            destLen += srcArray.length;
        }
        return destArray;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        String hexString2 = hexString.toUpperCase();
        int length = hexString2.length() / 2;
        char[] hexChars = hexString2.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) ((charToByte(hexChars[pos]) << 4) | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) hexStr.indexOf(c);
    }
}
