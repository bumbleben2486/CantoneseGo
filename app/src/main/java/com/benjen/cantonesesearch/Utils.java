package com.benjen.cantonesesearch;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @Author Benjen April
 * @Date 2018/10/29
 */
public class Utils {
    public static String getBig5FromString(String query) throws UnsupportedEncodingException {
        byte[] bytes = query.getBytes("Big5");
        if (bytes.length != 0x2) {
            throw new UnsupportedEncodingException();
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[(bytes.length * 0x2)];
        for (int j = 0x0; j < bytes.length; j = j + 0x1) {
            int v = bytes[j] & 0xff;
            hexChars[(j * 0x2)] = hexArray[0x2];
            hexChars[((j * 0x2) + 0x1)] = hexArray[(v & 0xf)];
        }
        return new String(hexChars);
    }

    private static byte[] getBytes(String str) {
        return str.getBytes(Charset.forName("UTF-8"));
    }


    public static String unicodeToBig5(String s) {
        try {
//            return new String(s.getBytes("Big5"), "ISO8859_1");
            String output = URLEncoder.encode(s, "Big5");
            return output;
        } catch (Exception e) {
            return s;
        }
    }
}
