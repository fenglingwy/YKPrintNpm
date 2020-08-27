package com.jd.ykposprint.utils;

import java.util.List;

public interface PrintDataMaker {
    List<byte[]> getPrintData(PrintBean printBean);
}
