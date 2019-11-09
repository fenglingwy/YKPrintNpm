package com.jd.ykposprint.utils;

import java.io.IOException;

public class PrinterWriter58mm extends PrinterWriter {
    public static final int TYPE_58 = 58;
    public int width = 380;

    public PrinterWriter58mm() throws IOException {
    }

    public PrinterWriter58mm(int parting) throws IOException {
        super(parting);
    }

    public PrinterWriter58mm(int parting, int width2) throws IOException {
        super(parting);
        this.width = width2;
    }

    /* access modifiers changed from: protected */
    public int getLineWidth() {
        return 16;
    }

    /* access modifiers changed from: protected */
    public int getLineStringWidth(int textSize) {
        switch (textSize) {
            case 1:
                return 15;
            default:
                return 32;
        }
    }

    /* access modifiers changed from: protected */
    public int getDrawableMaxWidth() {
        return this.width;
    }
}
