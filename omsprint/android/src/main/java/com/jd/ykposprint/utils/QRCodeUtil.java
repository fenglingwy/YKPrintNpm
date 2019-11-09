package com.jd.ykposprint.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtil {
    public static Bitmap createCode(String content) throws WriterException {
        return createCode(content, 200, 200);
    }

    public static Bitmap createCode(String content, int width, int height) throws WriterException {
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
        int[] pixels = new int[(width * height)];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[(y * width) + x] = -16777216;
                } else {
                    pixels[(y * width) + x] = -1;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
