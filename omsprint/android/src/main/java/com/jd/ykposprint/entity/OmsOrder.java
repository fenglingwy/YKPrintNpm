package com.jd.ykposprint.entity;


import java.util.List;

public class OmsOrder {


    /**
     * tml_num_id : 901379111421000076
     * so_no :
     * channel_num_id : 98
     * channel_name : 微信小程序
     * consumer_usr_num_id : 1911120000003076
     * consumer_name : 燕金语
     * consumer_telephone : 15062656527
     * consumer_prv_num_id : 320000
     * consumer_city_num_id : 320500
     * consumer_city_area_num_id : 320583
     * consumer_adr : 浦江大厦3号楼
     * buyer_meno : 缺货商品退款，其他商品继续配送,
     * shop_tml_num : 59
     * shop_total_num : 83
     * sub_unit_num_id : 100032
     * sub_unit_name : 宁波银泰城店
     * sub_unit_adr : 浙江省宁波市鄞州区东部银泰城B1-121
     * sub_unit_tel : 0574-87155242
     * order_date : 2019-11-12 21:51:15
     * real_pay_amount : 106
     * need_pay_amount : 0
     * p_amount : 115
     * d_amount : 10
     * ef_amount : 1
     * point_amount : 0
     * shop_coupon_amount : null
     * item_qty : 1
     * order_item_detail_for_onlines : [{"tml_num_id":"901379111421000076","tml_line":"1911122100004076","style_num_id":15424,"item_pic":"https://obs-gsuper.obs.cn-east-2.myhuaweicloud.com:443/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20191109101605.jpg","item_name":"天然葡萄籽油","item_num_id":88,"weight":0,"unit_name":"袋","barcode":"6937589400218","qty":1,"lock_qty":1,"loc_type":0,"short_qty":null,"standard_price":"115.00","trade_price":null,"remain_back_qty":0,"remain_cancel_qty":1,"pmt_sign":null,"source_series":"0","barcode_type_num_id":0,"son_item_info":null,"ship_time_begin":"2019-11-13 10:00:00","ship_time_end":"2019-11-13 11:00:00"}]
     */

    private String tml_num_id;
    private String so_no;
    private String channel_num_id;
    private String channel_name;
    private String consumer_usr_num_id;
    private String consumer_name;
    private String consumer_telephone;
    private String consumer_prv_num_id;
    private String consumer_city_num_id;
    private String consumer_city_area_num_id;
    private String consumer_adr;
    private String buyer_meno;
    private String shop_tml_num;
    private String shop_total_num;
    private String sub_unit_num_id;
    private String sub_unit_name;
    private String sub_unit_adr;
    private String sub_unit_tel;
    private String order_date;
    private String real_pay_amount;
    private String need_pay_amount;
    private String p_amount;
    private String d_amount;
    private String ef_amount;
    private String point_amount;
    private Object shop_coupon_amount;
    private String item_qty;
    private String applet_code;
    private List<OrderItemDetailForOnlinesBean> order_item_detail_for_onlines;

    public String getApplet_code() {
        return applet_code;
    }

    public void setApplet_code(String applet_code) {
        this.applet_code = applet_code;
    }

    public String getTml_num_id() {
        return tml_num_id;
    }

    public void setTml_num_id(String tml_num_id) {
        this.tml_num_id = tml_num_id;
    }

    public String getSo_no() {
        return so_no;
    }

    public void setSo_no(String so_no) {
        this.so_no = so_no;
    }

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

    public String getBuyer_meno() {
        return buyer_meno;
    }

    public void setBuyer_meno(String buyer_meno) {
        this.buyer_meno = buyer_meno;
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

    public String getSub_unit_tel() {
        return sub_unit_tel;
    }

    public void setSub_unit_tel(String sub_unit_tel) {
        this.sub_unit_tel = sub_unit_tel;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
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

    public String getPoint_amount() {
        return point_amount;
    }

    public void setPoint_amount(String point_amount) {
        this.point_amount = point_amount;
    }

    public Object getShop_coupon_amount() {
        return shop_coupon_amount;
    }

    public void setShop_coupon_amount(Object shop_coupon_amount) {
        this.shop_coupon_amount = shop_coupon_amount;
    }

    public String getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(String item_qty) {
        this.item_qty = item_qty;
    }

    public List<OrderItemDetailForOnlinesBean> getOrder_item_detail_for_onlines() {
        return order_item_detail_for_onlines;
    }

    public void setOrder_item_detail_for_onlines(List<OrderItemDetailForOnlinesBean> order_item_detail_for_onlines) {
        this.order_item_detail_for_onlines = order_item_detail_for_onlines;
    }

    public static class OrderItemDetailForOnlinesBean {
        /**
         * tml_num_id : 901379111421000076
         * tml_line : 1911122100004076
         * style_num_id : 15424
         * item_pic : https://obs-gsuper.obs.cn-east-2.myhuaweicloud.com:443/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20191109101605.jpg
         * item_name : 天然葡萄籽油
         * item_num_id : 88
         * weight : 0
         * unit_name : 袋
         * barcode : 6937589400218
         * qty : 1
         * lock_qty : 1
         * loc_type : 0
         * short_qty : null
         * standard_price : 115.00
         * trade_price : null
         * remain_back_qty : 0
         * remain_cancel_qty : 1
         * pmt_sign : null
         * source_series : 0
         * barcode_type_num_id : 0
         * son_item_info : null
         * ship_time_begin : 2019-11-13 10:00:00
         * ship_time_end : 2019-11-13 11:00:00
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
        private Object trade_price;
        private String remain_back_qty;
        private String remain_cancel_qty;
        private Object pmt_sign;
        private String source_series;
        private String barcode_type_num_id;
        private Object son_item_info;
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

        public Object getTrade_price() {
            return trade_price;
        }

        public void setTrade_price(Object trade_price) {
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

        public Object getPmt_sign() {
            return pmt_sign;
        }

        public void setPmt_sign(Object pmt_sign) {
            this.pmt_sign = pmt_sign;
        }

        public String getSource_series() {
            return source_series;
        }

        public void setSource_series(String source_series) {
            this.source_series = source_series;
        }

        public String getBarcode_type_num_id() {
            return barcode_type_num_id;
        }

        public void setBarcode_type_num_id(String barcode_type_num_id) {
            this.barcode_type_num_id = barcode_type_num_id;
        }

        public Object getSon_item_info() {
            return son_item_info;
        }

        public void setSon_item_info(Object son_item_info) {
            this.son_item_info = son_item_info;
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
