package test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by Milo on 2017/1/10.
 */
public class Base64Utils {
    public Base64Utils() {
    }

    public static String toBase64(String s) {
        return s == null ? null : (new BASE64Encoder()).encode(s.getBytes());
    }

    public static String fromBase64(String s) {
        if (s == null) {
            return null;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] e = decoder.decodeBuffer(s);
                return new String(e);
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public static String encodeTimestamp(long timestamp) {
        byte[] bytes = new byte[]{(byte) ((int) (timestamp >> 40 & 255L)), (byte) ((int) (timestamp >> 32 & 255L)), (byte) ((int) (timestamp >> 24 & 255L)), (byte) ((int) (timestamp >> 16 & 255L)), (byte) ((int) (timestamp >> 8 & 255L)), (byte) ((int) (timestamp & 255L))};
        return (new BASE64Encoder()).encode(bytes);
    }

    public static long decodeTimestamp(String base64) {
        try {
            byte[] e = (new BASE64Decoder()).decodeBuffer(base64);
            return (byte2Long(e[0]) << 40) + (byte2Long(e[1]) << 32) + (byte2Long(e[2]) << 24) + (byte2Long(e[3]) << 16) + (byte2Long(e[4]) << 8) + byte2Long(e[5]);
        } catch (Exception var2) {
            return System.currentTimeMillis();
        }
    }

    public static byte[] decode(String s) {
        if (s == null) {
            return null;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] e = decoder.decodeBuffer(s);
                return e;
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public static String encode(byte[] b) {
        return b == null ? null : (new BASE64Encoder()).encode(b);
    }

    private static long byte2Long(byte b) {
        long result = (long) b;
        if (result < 0L) {
            result += 256L;
        }

        return result;
    }
}