package com.jd.ykposprint.utils;

import java.io.IOException;

public class PrinterWriter80mm extends PrinterWriter {
    public static final int TYPE_80 = 80;
    public int width = 500;

    public PrinterWriter80mm() throws IOException {
    }

    public PrinterWriter80mm(int parting) throws IOException {
        super(parting);
    }

    public PrinterWriter80mm(int parting, int width2) throws IOException {
        super(parting);
        this.width = width2;
    }

    /* access modifiers changed from: protected */
    public int getLineWidth() {
        return 24;
    }

    /* access modifiers changed from: protected */
    public int getLineStringWidth(int textSize) {
        switch (textSize) {
            case 1:
                return 23;
            default:
                return 47;
        }
    }

    /* access modifiers changed from: protected */
    public int getDrawableMaxWidth() {
        return this.width;
    }
}
