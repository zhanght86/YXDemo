package com.yuyh.library.utils.data.safe;

import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 *
 * @author yuyh.
 * @date 16/4/9.
 */
public class MD5 {
    private static final String TAG = MD5.class.getSimpleName();
    private static final int STREAM_BUFFER_LENGTH = 1024;

    public static MessageDigest getDigest(final String algorithm) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithm);
    }

    public static byte[] md5(String txt) {
        return md5(txt.getBytes());
    }

    public static byte[] md5(byte[] bytes) {
        try {
            MessageDigest digest = getDigest("MD5");
            digest.update(bytes);
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] md5(InputStream is) throws NoSuchAlgorithmException, IOException {
        return updateDigest(getDigest("MD5"), is).digest();
    }

    public static MessageDigest updateDigest(final MessageDigest digest, final InputStream data) throws IOException {
        final byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);

        while (read > -1) {
            digest.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }
        return digest;
    }

    public static String md5String(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
