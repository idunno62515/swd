package com.example.swd1.utils;

import java.text.DecimalFormat;

public  class CommonConstant {
    public static final String TABLE_ID = "table_id";
    public static final int DEFAULT_COLUMN_COUNT = 0;
    public static final int FULL_WIDTH_COLUMN = 1;
    public static final String MASTER_CATE_ID = "master_cate_id";
    public static final String CATE_ID = "cate_id" ;
    public static final String APP_SHARE_PREFERENCE = "app_share_preference" ;
    public static final String CURRENT_TABLE_ID = "current_table_id" ;


    public static final String STAFF_NAME = "staff_name";


    public static final int INVALID_INT = -1;
    public static final String CURRENT_ORDER_ID = "current_order_id";
    public static final String TOKEN = "tokenauth";

    public static String currencyFormat(double input) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(input)+"đ";
    }
}


