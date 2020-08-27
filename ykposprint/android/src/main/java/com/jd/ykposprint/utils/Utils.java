package com.jd.ykposprint.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.jd.ykposprint.entity.CashEntity;
import com.jd.ykposprint.entity.GoodEntity;
import com.jd.ykposprint.entity.OrderEntity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by limingguang on 2018/8/24.
 */

public class Utils {
    /**
     * 序列化订单信息
     * @param json
     * @return
     */
    public static OrderEntity serializeOrder(ReadableMap json) {
        if(json == null){
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();
        // pos机号
        orderEntity.setClientId(json.getInt("tml_client_id"));
        // pos机名称
        orderEntity.setClientName(json.getString("tml_client_name"));
        orderEntity.setEmpeName(json.getString("empe_name"));
        orderEntity.setFamount(json.getMap("tml_pay_hdr").getDouble("f_amount"));
        orderEntity.setMalingAmount(json.getMap("tml_pay_hdr").getDouble("maling_amount"));
        orderEntity.setNumId(json.getString("tml_num_id"));
        orderEntity.setOrderCreateDate(json.getString("create_date"));
        orderEntity.setOrderDate(json.getString("order_date"));
        orderEntity.setOrderType(json.getString("order_type"));
        orderEntity.setPamount(json.getMap("tml_pay_hdr").getDouble("all_pamount"));
        orderEntity.setPayDateTime(json.getMap("tml_pay_hdr").getString("f_payment_dtme"));
        orderEntity.setRamount(json.getMap("tml_pay_hdr").getDouble("r_amount"));
        orderEntity.setUnitAddr(json.getString("sub_unit_adr"));
        orderEntity.setUnitName(json.getString("sub_unit_name"));
        orderEntity.setUnitPhone(json.getString("sub_unit_telephone"));
        orderEntity.setVipNo(json.getString("vip_no"));
        if (!json.hasKey("repeat_print_sign")){
            orderEntity.setRePrint(false);
        } else {
            orderEntity.setRePrint(json.getInt("repeat_print_sign")==1);
        }
        // 小票明细
        orderEntity.setItemList(serializeGood(json.getArray("info_list")));
        // 提货存根
        orderEntity.setDeliveryItemList(serializeGood(json.getArray("delivery_item_list")));
        // 提货单
        orderEntity.setContainerItemList(serializeGood(json.getArray("item_container_info_list")));
        // 优惠信息
        orderEntity.setDeductList(serializeGood(json.getArray("deduct_list")));
        // 付款信息
        orderEntity.setCashList(serializeCash(json.getArray("cash_dtls")));
        return orderEntity;
    }

    /**
     * 序列化商品信息
     * @return
     */
    private static List<GoodEntity> serializeGood(ReadableArray goods){
        List<GoodEntity> itemList = new ArrayList<>();
        if(goods == null) {
            return itemList;
        }
        for (int i = 0; i< goods.size(); i++) {
            GoodEntity goodEntity = new GoodEntity();
            ReadableMap good = goods.getMap(i);
            // 品名
            String itemName = good.getString("item_name");
//            itemName = itemName.length() > 6 ? itemName.substring(0,6) : itemName;
            goodEntity.setItemName(itemName);
            // 货号
            goodEntity.setItemId(good.getString("itemid"));
            // 条码
            goodEntity.setBarcode(good.getString("barcode"));
            // 数量
            goodEntity.setQty(good.getInt("qty"));
            // 行号
            if (good.hasKey("seq_line")){
                goodEntity.setSeqLine(good.getInt("seq_line"));
            }
            // 库存简称
            if (good.hasKey("pty_sim_name")){
                goodEntity.setSimpleName(good.getString("pty_sim_name"));
            }
            // 金额
            if (good.hasKey("trade_amount")){
                goodEntity.setTradeAmount(good.getDouble("trade_amount"));
            }
            // 单价
            if (good.hasKey("trade_price")){
                goodEntity.setTradePrice(good.getString("trade_price"));
            }
            // 优惠金额
            if (good.hasKey("deduct_amount")){
                goodEntity.setDeductAmount(good.getDouble("deduct_amount"));
            }
            // 原价
            if (good.hasKey("standard_price")){
                goodEntity.setStandardPrice(good.getDouble("standard_price"));
            }
            // 提货单号
            if(good.hasKey("container_serlno")){
                goodEntity.setSerialNo(good.getString("container_serlno"));
            }
            // 库位
            if(good.hasKey("loc_name")){
                goodEntity.setLocName(good.getString("loc_name"));
            }
//            if(goodEntity.getSimpleName() != null && !goodEntity.getSimpleName().trim().isEmpty()){
//                goodEntity.setItemName("("+goodEntity.getSimpleName()+")" + goodEntity.getItemName());
//            }
            itemList.add(goodEntity);
        }
        return itemList;
    }

    /**
     * 序列化付款信息
     * @param cashs
     * @return
     */
    private static List<CashEntity> serializeCash(ReadableArray cashs){
        List<CashEntity> cashList = new ArrayList<>();
        if (cashs == null) {
            return cashList;
        }
        for (int i = 0; i< cashs.size(); i++) {
            CashEntity cashEntity = new CashEntity();
            ReadableMap cash = cashs.getMap(i);
            cashEntity.setPayType(cash.getString("pay_type_name"));
            cashEntity.setPayAmount(cash.getDouble("pay_amount"));
            if(cash.hasKey("r_cash_sign")){
                cashEntity.setReturnCashSign(cash.getInt("r_cash_sign") == 1);
            }
            cashList.add(cashEntity);
        }
        return cashList;
    }

    private static final String CODE = "utf-8";


    public static boolean isMobile(String mobile) {
        if (mobile == null || mobile.length() == 0 || mobile.length() != 11) {
            return false;
        }
        return Pattern.compile("^1\\d{10}$").matcher(mobile).matches();
    }

    public static boolean checkMobile(String mobile) {
        if (!TextUtils.isEmpty(mobile) && isMobile(mobile.toString().replaceAll("\\s", ""))) {
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        if (Pattern.compile("[0-9]*").matcher(str).matches()) {
            return true;
        }
        return false;
    }

    public static boolean isDate(String strDate) {
        if (Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$").matcher(strDate).matches()) {
            return true;
        }
        return false;
    }



    private static Bitmap createBitmap(int width, int height, int[] pixels) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }


    public static String scaleFloat(float value) {
        return new BigDecimal((double) value).setScale(2, 4).toString();
    }

    public static float scaleFloat(float value, int scale) {
        return new BigDecimal((double) value).setScale(scale, 1).floatValue();
    }


    public static String formatDate2String(long timeStill) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(timeStill));
    }

    public static String getFormatMobile(String mobile) {
        if (mobile == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        while (i < mobile.length()) {
            if (i == 3 || i == 7) {
                buffer.append("-");
            }
            buffer.append(mobile.charAt(i));
            i++;
        }
        return buffer.toString();
    }

    public static String formatPrice(String price) {
        if(price==null) return "0.00";
         DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(new BigDecimal(price));
    }





}
