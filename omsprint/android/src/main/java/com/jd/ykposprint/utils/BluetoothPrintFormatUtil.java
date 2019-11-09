package com.jd.ykposprint.utils;

import android.text.TextUtils;
import com.google.zxing.common.StringUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class BluetoothPrintFormatUtil {
    private static final int LEFT_GOOD_NAME_SIZE = 18;
    private static final int LEFT_LINE_BYTE_SIZE = 20;
    private static final int LINE_BYTE_SIZE = 32;
    private static final String SEPARATOR = "$";
    private static StringBuffer sb = new StringBuffer();

    public static String printTitle(String title) {
        sb.delete(0, sb.length());
        for (int i = 0; i < (32 - getBytesLength(title)) / 2; i++) {
            sb.append(" ");
        }
        sb.append(title);
        return sb.toString();
    }

    public static String printMiddleMsg(LinkedHashMap<String, String> middleMsgMap) {
        sb.delete(0, sb.length());
        int leftLength = (32 - getBytesLength(":")) / 2;
        for (Entry<String, String> middleEntry : middleMsgMap.entrySet()) {
            for (int i = 0; i < leftLength - getBytesLength((String) middleEntry.getKey()); i++) {
                sb.append(" ");
            }
            sb.append(((String) middleEntry.getKey()) + "��" + ((String) middleEntry.getValue()));
        }
        return sb.toString();
    }

    public static String printSymmetryMSG(LinkedHashMap<String, String> leftMsgMap, LinkedHashMap<String, String> rightMsgMap) {
        sb.delete(0, sb.length());
        int leftPrefixLength = getMaxLength(leftMsgMap.keySet().toArray());
        int rightValueLength = getMaxLength(rightMsgMap.values().toArray());
        Object[] rightMsgKeys = rightMsgMap.keySet().toArray();
        int position = 0;
        for (Entry<String, String> leftEntry : leftMsgMap.entrySet()) {
            String leftMsgPrefix = (String) leftEntry.getKey();
            String leftMsgValue = (String) leftEntry.getValue();
            for (int leftI = 0; leftI < leftPrefixLength - getBytesLength(leftMsgPrefix); leftI++) {
                sb.append(" ");
            }
            sb.append(leftMsgPrefix + "��" + leftMsgValue);
            if (position <= rightMsgKeys.length - 1) {
                int leftLength = leftPrefixLength + getBytesLength("��" + leftMsgValue);
                String rightMsgPrefix = rightMsgKeys[position] + "��";
                int rightLength = getBytesLength(rightMsgPrefix) + rightValueLength;
                String rightMsgValue = (String) rightMsgMap.get(rightMsgKeys[position]);
                for (int middle = 0; middle < (32 - leftLength) - rightLength; middle++) {
                    sb.append(" ");
                }
                sb.append(rightMsgPrefix + rightMsgValue);
                position++;
            }
        }
        return sb.toString();
    }

    public static String printMenuMSG(LinkedHashMap<String, LinkedList<String>> menuMsgMap) {
        sb.delete(0, sb.length());
        List<String> menuNames = new ArrayList<>();
        List<String> menuPrices = new ArrayList<>();
        for (List<String> strList : menuMsgMap.values()) {
            for (String str : strList) {
                if (str.contains(SEPARATOR)) {
                    String[] menus = str.split("[$]");
                    if (!(menus == null || menus.length == 0)) {
                        menuNames.add(menus[0]);
                        menuPrices.add(menus[2]);
                    }
                }
            }
        }
        String menuNameTxt = "商品规格";
        String numTxt = "数量";
        String priceTxt = "单价\n";
        int leftPrefixLength = getMaxLength(menuNames.toArray());
        int rightPrefixLength = getMaxLength(menuPrices.toArray());
        if (rightPrefixLength < getBytesLength(priceTxt)) {
            rightPrefixLength = getBytesLength(priceTxt);
        }
        int leftMiddleNameLength = (leftPrefixLength - getBytesLength(menuNameTxt)) / 2;
        for (int i = 0; i < leftMiddleNameLength; i++) {
            sb.append(" ");
        }
        sb.append(menuNameTxt);
        int middleNumTxtLength = (((32 - leftPrefixLength) - rightPrefixLength) - getBytesLength(numTxt)) / 2;
        for (int i2 = 0; i2 < middleNumTxtLength + leftMiddleNameLength; i2++) {
            sb.append(" ");
        }
        sb.append(numTxt);
        int middlePriceTxtLength = (rightPrefixLength - getBytesLength(priceTxt)) / 2;
        for (int i3 = 0; i3 < middleNumTxtLength + middlePriceTxtLength; i3++) {
            sb.append(" ");
        }
        sb.append(priceTxt);
        for (Entry<String, LinkedList<String>> entry : menuMsgMap.entrySet()) {
            if (!"".equals(entry.getKey())) {
                sb.append(((String) entry.getKey()) + "\n");
            }
            Iterator it = ((LinkedList) entry.getValue()).iterator();
            while (it.hasNext()) {
                String menu = (String) it.next();
                if (menu.contains(SEPARATOR)) {
                    String[] menus2 = menu.split("[$]");
                    if (!(menus2 == null || menus2.length == 0)) {
                        sb.append(menus2[0]);
                        for (int i4 = 0; i4 < (((leftPrefixLength - getBytesLength(menus2[0])) + middleNumTxtLength) + (getBytesLength(numTxt) / 2)) - 1; i4++) {
                            sb.append(" ");
                        }
                        sb.append(menus2[1]);
                        for (int i5 = 0; i5 < ((((getBytesLength(numTxt) / 2) + middleNumTxtLength) + 1) - getBytesLength(menus2[1])) + middlePriceTxtLength; i5++) {
                            sb.append(" ");
                        }
                        sb.append(menus2[2] + "\n");
                    }
                } else {
                    for (int i6 = 0; i6 < (32 / getBytesLength(menu)) - getBytesLength("\n"); i6++) {
                        sb.append(menu);
                    }
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    public static String printSubOrderMSG(List<String> menuList) {
        sb.delete(0, sb.length());
        String menuNameTxt = "商品规格";
        String numTxt = "数量";
        String priceTxt = "总价";
        sb.append(menuNameTxt);
        int middleNumTxtLength = 20 - getBytesLength(menuNameTxt);
        for (int i = 0; i < middleNumTxtLength; i++) {
            sb.append(" ");
        }
        sb.append(numTxt);
        int middlePriceTxtLength = (12 - getBytesLength(numTxt)) - getBytesLength(priceTxt);
        for (int i2 = 0; i2 < middlePriceTxtLength; i2++) {
            sb.append(" ");
        }
        sb.append(priceTxt + "\n");
        sb.append(getSpanLine());
        for (String menu : menuList) {
            String[] menus = menu.split("[$]");
            if (!(menus == null || menus.length == 0)) {
                int goodLength = getBytesLength(menus[0] + menus[1]);
                if (goodLength <= 18) {
                    sb.append(menus[0] + menus[1]);
                    for (int i3 = 0; i3 < 20 - goodLength; i3++) {
                        sb.append(" ");
                    }
                    int offsetL = 3 - getBytesLength(menus[2]);
                    if (offsetL == 1) {
                        sb.append(" ");
                    }
                    if (offsetL <= 0) {
                        offsetL = 0;
                    }
                    sb.append(menus[2]);
                    for (int i4 = 0; i4 < ((12 - getBytesLength(menus[2])) - getBytesLength(menus[3])) - offsetL; i4++) {
                        sb.append(" ");
                    }
                    sb.append(menus[3] + "\n\n");
                } else if (getBytesLength(menus[0]) > 18) {
                    List<String> subList = getDivLines(menus[0], 18);
                    int size = subList.size();
                    String subLeft = (String) subList.get(0);
                    sb.append(subLeft);
                    for (int i5 = 0; i5 < 20 - getBytesLength(subLeft); i5++) {
                        sb.append(" ");
                    }
                    int offsetL2 = 3 - getBytesLength(menus[2]);
                    if (offsetL2 == 1) {
                        sb.append(" ");
                    }
                    if (offsetL2 <= 0) {
                        offsetL2 = 0;
                    }
                    sb.append(menus[2]);
                    for (int i6 = 0; i6 < ((12 - getBytesLength(menus[2])) - getBytesLength(menus[3])) - offsetL2; i6++) {
                        sb.append(" ");
                    }
                    sb.append(menus[3] + "\n");
                    if (size == 1) {
                        sb.append("\n");
                    }
                    subList.addAll(getDivLines(menus[1], 18));
                    for (int i7 = 1; i7 < subList.size(); i7++) {
                        if (i7 == subList.size() - 1) {
                            sb.append(((String) subList.get(i7)) + "\n\n");
                        } else {
                            sb.append(((String) subList.get(i7)) + "\n");
                        }
                    }
                } else {
                    sb.append(menus[0]);
                    for (int i8 = 0; i8 < 20 - getBytesLength(menus[0]); i8++) {
                        sb.append(" ");
                    }
                    int offsetL3 = 3 - getBytesLength(menus[2]);
                    if (offsetL3 == 1) {
                        sb.append(" ");
                    }
                    if (offsetL3 <= 0) {
                        offsetL3 = 0;
                    }
                    sb.append(menus[2]);
                    for (int i9 = 0; i9 < ((12 - getBytesLength(menus[2])) - getBytesLength(menus[3])) - offsetL3; i9++) {
                        sb.append(" ");
                    }
                    sb.append(menus[3] + "\n");
                    List<String> subList2 = getDivLines(menus[1], 18);
                    for (int i10 = 0; i10 < subList2.size(); i10++) {
                        if (i10 == subList2.size() - 1) {
                            sb.append(((String) subList2.get(i10)) + "\n\n");
                        } else {
                            sb.append(((String) subList2.get(i10)) + "\n");
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    public static String printSubOrderMSGV2_1(List<String> menuList) {
        sb.delete(0, sb.length());
        String menuNameTxt = "商品规格";
        String originPriceText = "    ";
        String priceTxt = "单价";
        String numTxt = "数量";
        sb.append(menuNameTxt);
        int oTxtLength = (12 - getBytesLength(originPriceText)) - 1;
        for (int i = 0; i < oTxtLength; i++) {
            sb.append(" ");
        }
        sb.append(originPriceText);
        int middleNumTxtLength = 9 - getBytesLength(menuNameTxt);
        for (int i2 = 0; i2 < middleNumTxtLength; i2++) {
            sb.append(" ");
        }
        sb.append(priceTxt);
        int middlePriceTxtLength = (12 - getBytesLength(priceTxt)) - getBytesLength(numTxt);
        for (int i3 = 0; i3 < middlePriceTxtLength; i3++) {
            sb.append(" ");
        }
        sb.append(numTxt + "\n");
        sb.append(getSpanLine());
        for (String menu : menuList) {
            String[] menus = menu.split("[$]");
            if (!(menus == null || menus.length == 0)) {
                String STP = menus[0];
                String barCode = menus[4];
                String originPrice = menus[1];
                String price = menus[2];
                if (originPrice.equals("0.00")) {
                    originPrice = price;
                }
                String goodsCount = menus[3];
                sb.append(STP + "\n");
                sb.append(barCode + "\n");
                if (menus.length >= 6 && !TextUtils.isEmpty(menus[5])) {
                    sb.append(menus[5] + "\n");
                }
                sb.append("\n");
                for (int i4 = 0; i4 < (20 - getBytesLength(originPrice)) - 2; i4++) {
                    sb.append(" ");
                }
                sb.append(originPrice);
                sb.append(" ");
                sb.append(" ");
                int offsetL = 3 - getBytesLength(price);
                if (offsetL == 1) {
                    sb.append(" ");
                }
                if (offsetL <= 0) {
                    offsetL = 0;
                }
                sb.append(price);
                for (int i5 = 0; i5 < ((12 - getBytesLength(price)) - getBytesLength(goodsCount)) - offsetL; i5++) {
                    sb.append(" ");
                }
                sb.append(goodsCount + "\n");
                sb.append(getSpanLine());
            }
        }
        return sb.toString();
    }

//    public static String printSubOrderMSGV4(List<Goods> menuList) {
//        sb.delete(0, sb.length());
//        String menuNameTxt = "商品规格";
//        String originPriceText = "    ";
//        String priceTxt = "单价";
//        String numTxt = "数量";
//        sb.append(menuNameTxt);
//        int oTxtLength = (12 - getBytesLength(originPriceText)) - 1;
//        for (int i = 0; i < oTxtLength; i++) {
//            sb.append(" ");
//        }
//        sb.append(originPriceText);
//        int middleNumTxtLength = 9 - getBytesLength(menuNameTxt);
//        for (int i2 = 0; i2 < middleNumTxtLength; i2++) {
//            sb.append(" ");
//        }
//        sb.append(priceTxt);
//        int middlePriceTxtLength = (12 - getBytesLength(priceTxt)) - getBytesLength(numTxt);
//        for (int i3 = 0; i3 < middlePriceTxtLength; i3++) {
//            sb.append(" ");
//        }
//        sb.append(numTxt + "\n");
//        sb.append(getSpanLine());
//        for (Goods goods : menuList) {
//            String[] menus = goods.getShowMenu().split("[$]");
//            if (!(menus == null || menus.length == 0)) {
//                String STP = menus[0];
//                String barCode = menus[4];
//                String originPrice = menus[1];
//                String price = menus[2];
//                if (originPrice.equals("0.00")) {
//                    originPrice = price;
//                }
//                String goodsCount = menus[3];
//                sb.append(STP + "\n");
//                sb.append(barCode + "\n");
//                if (menus.length >= 6 && !TextUtils.isEmpty(menus[5])) {
//                    sb.append(menus[5] + "\n");
//                }
//                sb.append("\n");
//                for (int i4 = 0; i4 < (20 - getBytesLength(originPrice)) - 2; i4++) {
//                    sb.append(" ");
//                }
//                sb.append(originPrice);
//                sb.append(" ");
//                sb.append(" ");
//                int offsetL = 3 - getBytesLength(price);
//                if (offsetL == 1) {
//                    sb.append(" ");
//                }
//                if (offsetL <= 0) {
//                    offsetL = 0;
//                }
//                sb.append(price);
//                for (int i5 = 0; i5 < ((12 - getBytesLength(price)) - getBytesLength(goodsCount)) - offsetL; i5++) {
//                    sb.append(" ");
//                }
//                sb.append(goodsCount + "\n");
//                sb.append(getSpanLine());
//            }
//        }
//        return sb.toString();
//    }

    public static String printHejiMSG(String numTxt, String originPriceText, String realPriceText) {
        sb.delete(0, sb.length());
        String menuNameTxt = "商品总计";
        int leftMiddleNameLength = (getBytesLength("商品总价") - getBytesLength(menuNameTxt)) / 2;
        for (int i = 0; i < leftMiddleNameLength; i++) {
            sb.append(" ");
        }
        sb.append(menuNameTxt);
        int nextLength = ((20 - getBytesLength(menuNameTxt)) - getBytesLength(originPriceText)) - 2;
        for (int i2 = 0; i2 < nextLength; i2++) {
            sb.append(" ");
        }
        sb.append(originPriceText);
        int offsetL = 3 - getBytesLength(realPriceText);
        if (offsetL == 1) {
            sb.append(" ");
        }
        if (offsetL <= 0) {
            offsetL = 0;
        }
        sb.append(" ");
        sb.append(" ");
        sb.append(realPriceText);
        int middlePriceTxtLength = ((12 - getBytesLength(numTxt)) - getBytesLength(realPriceText)) - offsetL;
        for (int i3 = 0; i3 < middlePriceTxtLength; i3++) {
            sb.append(" ");
        }
        sb.append(numTxt);
        sb.append(getSpanLine());
        return sb.toString();
    }

    public static String printLeftRightMSG(List<String> leftMsg, List<String> rightMsg) {
        sb.delete(0, sb.length());
        int maxLength = getMaxLength(leftMsg.toArray());
        int maxLength2 = getMaxLength(rightMsg.toArray());
        for (int i = 0; i < leftMsg.size(); i++) {
            sb.append((String) leftMsg.get(i));
            if (i <= rightMsg.size() - 1) {
                int leftLength = getBytesLength((String) leftMsg.get(i));
                int rightLength = getBytesLength((String) rightMsg.get(i));
                for (int middle = 0; middle < (32 - leftLength) - rightLength; middle++) {
                    sb.append(" ");
                }
                sb.append((String) rightMsg.get(i));
            }
        }
        return sb.toString();
    }

    private static int getMaxLength(Object[] msgs) {
        int max = 0;
        for (Object oo : msgs) {
            int tmp = getBytesLength(oo.toString());
            if (tmp > max) {
                max = tmp;
            }
        }
        return max;
    }

    private static int getBytesLength(String msg) {
        return msg.getBytes(Charset.forName(StringUtils.GB2312)).length;
    }

    public static List getDivLines(String inputString, int length) {
        List divList = new ArrayList();
        String subStr = "";
        for (int i = 0; i < inputString.length(); i++) {
            subStr = subStr + inputString.charAt(i);
            if (getBytesLength(subStr) >= length) {
                divList.add(subStr);
                subStr = "";
            }
        }
        if (!subStr.equals("")) {
            divList.add(subStr);
        }
        return divList;
    }

    public static String getSpanLine() {
        return "--------------------------------\n";
    }
}
