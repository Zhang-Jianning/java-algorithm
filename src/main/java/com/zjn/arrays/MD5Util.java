package com.zjn.arrays;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    protected static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public MD5Util() {
    }

    public static String calculateMD5(byte[] byteOfFile) {
        if (byteOfFile != null && byteOfFile.length != 0) {
            ByteBuffer bb = ByteBuffer.wrap(byteOfFile);
            MessageDigest messageDigest = null;

            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var4) {
            }

            messageDigest.update(bb);
            return bufferToHex(messageDigest.digest());
        } else {
            return null;
        }
    }

    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;

        for(int l = m; l < k; ++l) {
            appendHexPair(bytes[l], stringbuffer);
        }

        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 240) >> 4];
        char c1 = hexDigits[bt & 15];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "2407816531983";
        String encode = URLEncoder.encode(s, "UTF-8");
//        byte[] b = {2,4,0,7,8,1,6,5,3,1,9,8,3};
        long timeMillis = System.currentTimeMillis();
        for (int i = 0; i < 30000; i++) {
            MD5Util.calculateMD5(encode.getBytes());
        }

        long timeMillis1 = System.currentTimeMillis();
        System.out.println(timeMillis1 - timeMillis);
    }
}