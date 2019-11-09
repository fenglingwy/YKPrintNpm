package com.jd.ykposprint.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.text.TextUtils;
import android.view.View;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class PrintTool {
    public static String LVDI_LOGO_URL = "http://imgsize.52shangou.com/img/n/06/14/1560504658909_8958.png";
    public static Bitmap LV_LOGO;
    public static Bitmap MINI_APP_BITMAP;
    public static String MINI_APP_QR_CODE_URL;
    public static Bitmap createBarCode(String content) throws WriterException {
        return createBarCode(content, 350, 50);
    }

    public static Bitmap createBarCode(String content, int w, int h) throws WriterException {
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, w, h);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[(width * height)];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[(y * width) + x] = -16777216;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static Bitmap createMinAppCodeBitmap() {
        if (TextUtils.isEmpty(MINI_APP_QR_CODE_URL) || MINI_APP_BITMAP == null) {
            return null;
        }
        return MINI_APP_BITMAP;
    }

    public static Bitmap createLHQRCodeBitmap(String url) throws WriterException {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return QRCodeUtil.createCode(url);
    }

//    public static void getQRCode(Context context) {
//        new QRCodeAction().startMini(context);
//    }

    public static void reset() {
        MINI_APP_QR_CODE_URL = null;
    }
}
