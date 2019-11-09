package com.jd.ykposprint.entity;

import java.util.List;

/**
 * @author WY
 * @date 2019/10/21 0021
 * @Description
 */
public class OmsOrder {

    /**
     * channel_num_id : 98
     * channel_name : 微信小程序
     * tran_type_num_id : 5
     * tran_type_name : 自提
     * consumer_usr_num_id : 1809140010001006
     * consumer_name : 17312137420
     * consumer_telephone : 17312137420
     * consumer_prv_num_id : 330000
     * consumer_city_num_id : 330200
     * consumer_city_area_num_id : 330212
     * consumer_adr : 浙江省宁波市鄞州区东部银泰城B1-121
     * shop_tml_num : 7
     * shop_total_num : 12
     * source_tml_num_id : 0
     * refund_tml_num_id : 901189140020389006
     * so_no :
     * shop_id : 100032001
     * shop_name : null
     * sub_unit_num_id : 100032
     * sub_unit_name : 宁波银泰城店
     * sub_unit_adr : 浙江省宁波市鄞州区东部银泰城B1-121
     * container_no :
     * verification_code :
     * order_date : 2019-10-18 15:28:58
     * so_sign : 0
     * operate_status_num_id : 90910
     * operate_status_name : 订单失效
     * tml_type : 0
     * mall_vip_type_id : 0
     * tml_oper_num_id : 90910
     * tml_time : 246669665
     * refund_tml_sign : null
     * freight_flag : null
     * vendor_memo :
     * buyer_meno : 缺货商品退款，其他商品继续配送,
     * item_qty : 1
     * real_pay_amount : 0
     * need_pay_amount : 0.5
     * p_amount : 0.5
     * d_amount : 0
     * ef_amount : 0
     * ist_amount : null
     * maling_amount : null
     * point_amount : 0
     * card_point : 0
     * shop_coupon_amount : null
     * r_amount : null
     * shop_activity_amount : 0
     * third_activity_amount : 0
     * rider_name :
     * rider_phone :
     * distribution_type_name :
     * pay_type :
     * af_service_flag : 0
     * whale_money : 0
     * electronic_wallet : 0
     * order_item_detail_for_onlines : [{"tml_num_id":"901189140020389006","tml_line":"1910182080010006","style_num_id":13678,"item_pic":"https://obs-gsuper.obs.cn-east-2.myhuaweicloud.com:443/135-1.jpg","item_name":"切片盒装糖年糕（白糖）","item_num_id":135,"weight":0,"unit_name":"包","barcode":"6922631902697","qty":1,"lock_qty":0,"loc_type":1,"short_qty":null,"standard_price":"0.50","trade_price":"0.50","remain_back_qty":0,"remain_cancel_qty":1,"pmt_sign":0,"source_series":"0","ship_time_begin":"2019-10-18 15:00:00","ship_time_end":"2019-10-18 16:00:00"}]
     * refund_tmls : []
     * operate_his_list : []
     */

    private String channel_num_id;
    private String channel_name;
    private String tran_type_num_id;
    private String tran_type_name;
    private String consumer_usr_num_id;
    private String consumer_name;
    private String consumer_telephone;
    private String consumer_prv_num_id;
    private String consumer_city_num_id;
    private String consumer_city_area_num_id;
    private String consumer_adr;
    private String shop_tml_num;
    private String shop_total_num;
    private String source_tml_num_id;
    private String refund_tml_num_id;
    private String so_no;
    private String shop_id;
    private Object shop_name;
    private String sub_unit_num_id;
    private String sub_unit_name;
    private String sub_unit_adr;
    private String container_no;
    private String verification_code;
    private String order_date;
    private String so_sign;
    private String operate_status_num_id;
    private String operate_status_name;
    private String tml_type;
    private String mall_vip_type_id;
    private String tml_oper_num_id;
    private String tml_time;
    private Object refund_tml_sign;
    private Object freight_flag;
    private String vendor_memo;
    private String buyer_meno;
    private String item_qty;
    private String real_pay_amount;
    private String need_pay_amount;
    private String p_amount;
    private String d_amount;
    private String ef_amount;
    private String ist_amount;
    private Object maling_amount;
    private String point_amount;
    private String card_point;
    private Object shop_coupon_amount;
    private Object r_amount;
    private String shop_activity_amount;
    private String third_activity_amount;
    private String rider_name;
    private String rider_phone;
    private String distribution_type_name;
    private String pay_type;
    private String af_service_flag;
    private String whale_money;
    private String electronic_wallet;
    private List<OrderItemDetailForOnlinesBean> order_item_detail_for_onlines;
    private List<?> refund_tmls;
    private List<?> operate_his_list;

    public String getChannel_num_id() {
        return channel_num_id;
    }

    public void setChannel_num_id(String channel_num_id) {
        this.channel_num_id = channel_num_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getTran_type_num_id() {
        return tran_type_num_id;
    }

    public void setTran_type_num_id(String tran_type_num_id) {
        this.tran_type_num_id = tran_type_num_id;
    }

    public String getTran_type_name() {
        return tran_type_name;
    }

    public void setTran_type_name(String tran_type_name) {
        this.tran_type_name = tran_type_name;
    }

    public String getConsumer_usr_num_id() {
        return consumer_usr_num_id;
    }

    public void setConsumer_usr_num_id(String consumer_usr_num_id) {
        this.consumer_usr_num_id = consumer_usr_num_id;
    }

    public String getConsumer_name() {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name = consumer_name;
    }

    public String getConsumer_telephone() {
        return consumer_telephone;
    }

    public void setConsumer_telephone(String consumer_telephone) {
        this.consumer_telephone = consumer_telephone;
    }

    public String getConsumer_prv_num_id() {
        return consumer_prv_num_id;
    }

    public void setConsumer_prv_num_id(String consumer_prv_num_id) {
        this.consumer_prv_num_id = consumer_prv_num_id;
    }

    public String getConsumer_city_num_id() {
        return consumer_city_num_id;
    }

    public void setConsumer_city_num_id(String consumer_city_num_id) {
        this.consumer_city_num_id = consumer_city_num_id;
    }

    public String getConsumer_city_area_num_id() {
        return consumer_city_area_num_id;
    }

    public void setConsumer_city_area_num_id(String consumer_city_area_num_id) {
        this.consumer_city_area_num_id = consumer_city_area_num_id;
    }

    public String getConsumer_adr() {
        return consumer_adr;
    }

    public void setConsumer_adr(String consumer_adr) {
        this.consumer_adr = consumer_adr;
    }

    public String getShop_tml_num() {
        return shop_tml_num;
    }

    public void setShop_tml_num(String shop_tml_num) {
        this.shop_tml_num = shop_tml_num;
    }

    public String getShop_total_num() {
        return shop_total_num;
    }

    public void setShop_total_num(String shop_total_num) {
        this.shop_total_num = shop_total_num;
    }

    public String getSource_tml_num_id() {
        return source_tml_num_id;
    }

    public void setSource_tml_num_id(String source_tml_num_id) {
        this.source_tml_num_id = source_tml_num_id;
    }

    public String getRefund_tml_num_id() {
        return refund_tml_num_id;
    }

    public void setRefund_tml_num_id(String refund_tml_num_id) {
        this.refund_tml_num_id = refund_tml_num_id;
    }

    public String getSo_no() {
        return so_no;
    }

    public void setSo_no(String so_no) {
        this.so_no = so_no;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public Object getShop_name() {
        return shop_name;
    }

    public void setShop_name(Object shop_name) {
        this.shop_name = shop_name;
    }

    public String getSub_unit_num_id() {
        return sub_unit_num_id;
    }

    public void setSub_unit_num_id(String sub_unit_num_id) {
        this.sub_unit_num_id = sub_unit_num_id;
    }

    public String getSub_unit_name() {
        return sub_unit_name;
    }

    public void setSub_unit_name(String sub_unit_name) {
        this.sub_unit_name = sub_unit_name;
    }

    public String getSub_unit_adr() {
        return sub_unit_adr;
    }

    public void setSub_unit_adr(String sub_unit_adr) {
        this.sub_unit_adr = sub_unit_adr;
    }

    public String getContainer_no() {
        return container_no;
    }

    public void setContainer_no(String container_no) {
        this.container_no = container_no;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getSo_sign() {
        return so_sign;
    }

    public void setSo_sign(String so_sign) {
        this.so_sign = so_sign;
    }

    public String getOperate_status_num_id() {
        return operate_status_num_id;
    }

    public void setOperate_status_num_id(String operate_status_num_id) {
        this.operate_status_num_id = operate_status_num_id;
    }

    public String getOperate_status_name() {
        return operate_status_name;
    }

    public void setOperate_status_name(String operate_status_name) {
        this.operate_status_name = operate_status_name;
    }

    public String getTml_type() {
        return tml_type;
    }

    public void setTml_type(String tml_type) {
        this.tml_type = tml_type;
    }

    public String getMall_vip_type_id() {
        return mall_vip_type_id;
    }

    public void setMall_vip_type_id(String mall_vip_type_id) {
        this.mall_vip_type_id = mall_vip_type_id;
    }

    public String getTml_oper_num_id() {
        return tml_oper_num_id;
    }

    public void setTml_oper_num_id(String tml_oper_num_id) {
        this.tml_oper_num_id = tml_oper_num_id;
    }

    public String getTml_time() {
        return tml_time;
    }

    public void setTml_time(String tml_time) {
        this.tml_time = tml_time;
    }

    public Object getRefund_tml_sign() {
        return refund_tml_sign;
    }

    public void setRefund_tml_sign(Object refund_tml_sign) {
        this.refund_tml_sign = refund_tml_sign;
    }

    public Object getFreight_flag() {
        return freight_flag;
    }

    public void setFreight_flag(Object freight_flag) {
        this.freight_flag = freight_flag;
    }

    public String getVendor_memo() {
        return vendor_memo;
    }

    public void setVendor_memo(String vendor_memo) {
        this.vendor_memo = vendor_memo;
    }

    public String getBuyer_meno() {
        return buyer_meno;
    }

    public void setBuyer_meno(String buyer_meno) {
        this.buyer_meno = buyer_meno;
    }

    public String getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(String item_qty) {
        this.item_qty = item_qty;
    }

    public String getReal_pay_amount() {
        return real_pay_amount;
    }

    public void setReal_pay_amount(String real_pay_amount) {
        this.real_pay_amount = real_pay_amount;
    }

    public String getNeed_pay_amount() {
        return need_pay_amount;
    }

    public void setNeed_pay_amount(String need_pay_amount) {
        this.need_pay_amount = need_pay_amount;
    }

    public String getP_amount() {
        return p_amount;
    }

    public void setP_amount(String p_amount) {
        this.p_amount = p_amount;
    }

    public String getD_amount() {
        return d_amount;
    }

    public void setD_amount(String d_amount) {
        this.d_amount = d_amount;
    }

    public String getEf_amount() {
        return ef_amount;
    }

    public void setEf_amount(String ef_amount) {
        this.ef_amount = ef_amount;
    }

    public String getIst_amount() {
        return ist_amount;
    }

    public void setIst_amount(String ist_amount) {
        this.ist_amount = ist_amount;
    }

    public Object getMaling_amount() {
        return maling_amount;
    }

    public void setMaling_amount(Object maling_amount) {
        this.maling_amount = maling_amount;
    }

    public String getPoint_amount() {
        return point_amount;
    }

    public void setPoint_amount(String point_amount) {
        this.point_amount = point_amount;
    }

    public String getCard_point() {
        return card_point;
    }

    public void setCard_point(String card_point) {
        this.card_point = card_point;
    }

    public Object getShop_coupon_amount() {
        return shop_coupon_amount;
    }

    public void setShop_coupon_amount(Object shop_coupon_amount) {
        this.shop_coupon_amount = shop_coupon_amount;
    }

    public Object getR_amount() {
        return r_amount;
    }

    public void setR_amount(Object r_amount) {
        this.r_amount = r_amount;
    }

    public String getShop_activity_amount() {
        return shop_activity_amount;
    }

    public void setShop_activity_amount(String shop_activity_amount) {
        this.shop_activity_amount = shop_activity_amount;
    }

    public String getThird_activity_amount() {
        return third_activity_amount;
    }

    public void setThird_activity_amount(String third_activity_amount) {
        this.third_activity_amount = third_activity_amount;
    }

    public String getRider_name() {
        return rider_name;
    }

    public void setRider_name(String rider_name) {
        this.rider_name = rider_name;
    }

    public String getRider_phone() {
        return rider_phone;
    }

    public void setRider_phone(String rider_phone) {
        this.rider_phone = rider_phone;
    }

    public String getDistribution_type_name() {
        return distribution_type_name;
    }

    public void setDistribution_type_name(String distribution_type_name) {
        this.distribution_type_name = distribution_type_name;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getAf_service_flag() {
        return af_service_flag;
    }

    public void setAf_service_flag(String af_service_flag) {
        this.af_service_flag = af_service_flag;
    }

    public String getWhale_money() {
        return whale_money;
    }

    public void setWhale_money(String whale_money) {
        this.whale_money = whale_money;
    }

    public String getElectronic_wallet() {
        return electronic_wallet;
    }

    public void setElectronic_wallet(String electronic_wallet) {
        this.electronic_wallet = electronic_wallet;
    }

    public List<OrderItemDetailForOnlinesBean> getOrder_item_detail_for_onlines() {
        return order_item_detail_for_onlines;
    }

    public void setOrder_item_detail_for_onlines(List<OrderItemDetailForOnlinesBean> order_item_detail_for_onlines) {
        this.order_item_detail_for_onlines = order_item_detail_for_onlines;
    }

    public List<?> getRefund_tmls() {
        return refund_tmls;
    }

    public void setRefund_tmls(List<?> refund_tmls) {
        this.refund_tmls = refund_tmls;
    }

    public List<?> getOperate_his_list() {
        return operate_his_list;
    }

    public void setOperate_his_list(List<?> operate_his_list) {
        this.operate_his_list = operate_his_list;
    }

    public static class OrderItemDetailForOnlinesBean {
        /**
         * tml_num_id : 901189140020389006
         * tml_line : 1910182080010006
         * style_num_id : 13678
         * item_pic : https://obs-gsuper.obs.cn-east-2.myhuaweicloud.com:443/135-1.jpg
         * item_name : 切片盒装糖年糕（白糖）
         * item_num_id : 135
         * weight : 0
         * unit_name : 包
         * barcode : 6922631902697
         * qty : 1
         * lock_qty : 0
         * loc_type : 1
         * short_qty : null
         * standard_price : 0.50
         * trade_price : 0.50
         * remain_back_qty : 0
         * remain_cancel_qty : 1
         * pmt_sign : 0
         * source_series : 0
         * ship_time_begin : 2019-10-18 15:00:00
         * ship_time_end : 2019-10-18 16:00:00
         */

        private String tml_num_id;
        private String tml_line;
        private String style_num_id;
        private String item_pic;
        private String item_name;
        private String item_num_id;
        private String weight;
        private String unit_name;
        private String barcode;
        private String qty;
        private String lock_qty;
        private String loc_type;
        private Object short_qty;
        private String standard_price;
        private String trade_price;
        private String remain_back_qty;
        private String remain_cancel_qty;
        private String pmt_sign;
        private String source_series;
        private String ship_time_begin;
        private String ship_time_end;

        public String getTml_num_id() {
            return tml_num_id;
        }

        public void setTml_num_id(String tml_num_id) {
            this.tml_num_id = tml_num_id;
        }

        public String getTml_line() {
            return tml_line;
        }

        public void setTml_line(String tml_line) {
            this.tml_line = tml_line;
        }

        public String getStyle_num_id() {
            return style_num_id;
        }

        public void setStyle_num_id(String style_num_id) {
            this.style_num_id = style_num_id;
        }

        public String getItem_pic() {
            return item_pic;
        }

        public void setItem_pic(String item_pic) {
            this.item_pic = item_pic;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_num_id() {
            return item_num_id;
        }

        public void setItem_num_id(String item_num_id) {
            this.item_num_id = item_num_id;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getUnit_name() {
            return unit_name;
        }

        public void setUnit_name(String unit_name) {
            this.unit_name = unit_name;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getLock_qty() {
            return lock_qty;
        }

        public void setLock_qty(String lock_qty) {
            this.lock_qty = lock_qty;
        }

        public String getLoc_type() {
            return loc_type;
        }

        public void setLoc_type(String loc_type) {
            this.loc_type = loc_type;
        }

        public Object getShort_qty() {
            return short_qty;
        }

        public void setShort_qty(Object short_qty) {
            this.short_qty = short_qty;
        }

        public String getStandard_price() {
            return standard_price;
        }

        public void setStandard_price(String standard_price) {
            this.standard_price = standard_price;
        }

        public String getTrade_price() {
            return trade_price;
        }

        public void setTrade_price(String trade_price) {
            this.trade_price = trade_price;
        }

        public String getRemain_back_qty() {
            return remain_back_qty;
        }

        public void setRemain_back_qty(String remain_back_qty) {
            this.remain_back_qty = remain_back_qty;
        }

        public String getRemain_cancel_qty() {
            return remain_cancel_qty;
        }

        public void setRemain_cancel_qty(String remain_cancel_qty) {
            this.remain_cancel_qty = remain_cancel_qty;
        }

        public String getPmt_sign() {
            return pmt_sign;
        }

        public void setPmt_sign(String pmt_sign) {
            this.pmt_sign = pmt_sign;
        }

        public String getSource_series() {
            return source_series;
        }

        public void setSource_series(String source_series) {
            this.source_series = source_series;
        }

        public String getShip_time_begin() {
            return ship_time_begin;
        }

        public void setShip_time_begin(String ship_time_begin) {
            this.ship_time_begin = ship_time_begin;
        }

        public String getShip_time_end() {
            return ship_time_end;
        }

        public void setShip_time_end(String ship_time_end) {
            this.ship_time_end = ship_time_end;
        }
    }
}
