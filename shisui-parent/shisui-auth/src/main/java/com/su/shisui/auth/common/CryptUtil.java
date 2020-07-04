package com.su.shisui.auth.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * author sly
 */
public class CryptUtil {

    private static final Logger log = LoggerFactory.getLogger(CryptUtil.class);
    private static final String KEY_MD5 = "MD5";
    private static final String KEY_SHA = "SHA";
    private static final String KEY_MAC = "HmacMD5";
    private static String KEY_VALUE = "";
    private static final String ALGORITHM = "DES";

    public CryptUtil() {
    }

    private static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    private static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    private static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    public static String encryptMD5(String data) throws Exception {
        String result = new String(DigestUtils.md5Hex(data));
        return result;
    }

    private static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(data);
        return sha.digest();
    }

    private static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    private static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    private static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = null;
        if (!"DES".equals("DES") && !"DES".equals("DESede")) {
            secretKey = new SecretKeySpec(key, "DES");
        } else {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            secretKey = keyFactory.generateSecret(dks);
        }

        return (Key)secretKey;
    }

    public static byte[] decrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, k);
        return cipher.doFinal(data);
    }

    public static String decrypt(String data, String key) throws Exception {
        return new String(decrypt(Base64.decodeBase64(data), key), "UTF-8");
    }

    public static byte[] encrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, k);
        return cipher.doFinal(data);
    }

    public static String encrypt(String data, String key) throws Exception {
        return Base64.encodeBase64String(encrypt(data.getBytes("UTF-8"), key));
    }

    private static String initKey() throws Exception {
        return initKey((String)null);
    }

    public static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;
        if (seed != null) {
            secureRandom = new SecureRandom(decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }

        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(secureRandom);
        SecretKey secretKey = kg.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    public static String encryptHMAC(String password) {
        String result = null;
        if (!StringUtils.isEmpty(password)) {
            try {
                byte[] c = encryptHMAC(password.getBytes(), initMacKey());
                result = (new BigInteger(c)).toString(16);
            } catch (Exception var3) {
                log.error("CryptUtils encryptHMAC Exception Error:{}", var3);
            }
        }

        return result;
    }

    public static String encrypt(String password) {
        String result = null;

        try {
            byte[] c = encrypt(password.getBytes(), KEY_VALUE);
            result = (new BigInteger(c)).toString(16);
        } catch (Exception var3) {
            log.error("CryptUtils encrypt Exception Error:{}", var3);
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            String s = "{}";
            String b = encryptBASE64(s.getBytes("UTF-8"));
            byte[] c = decryptBASE64(b);
            String key = initMacKey();
            key = initKey();
            System.out.println("DES密钥:\t" + key);
            String en = encrypt(s, key);
            System.out.println("DES String   加密后:" + en);
            String de = decrypt(en, key);
            System.out.println("DES String  解密后:" + de);
            c = encrypt(s.getBytes("UTF-8"), key);
            System.out.println("DES   加密后:" + (new BigInteger(c)).toString(16));
            c = decrypt(c, key);
            System.out.println("DES   解密后:" + new String(c, "UTF-8"));
        } catch (UnsupportedEncodingException var7) {
            log.error("CryptUtils encrypt UnsupportedEncodingException Error:{}", var7);
        } catch (Exception var8) {
            log.error("CryptUtils encrypt Exception Error:{}", var8);
        }

    }

    static {
        try {
            KEY_VALUE = initKey("v7Pg5fiYwek");
        } catch (Exception var1) {
            log.error("CryptUtils Exception Error:{}", var1);
        }

    }
}
