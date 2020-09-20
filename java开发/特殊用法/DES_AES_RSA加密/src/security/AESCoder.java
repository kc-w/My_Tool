package security;

import util.HexUtil;
import util.KeyUtil;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;

/*
* 1.使用KeyUtil第一种Factory方式生成key 会异常
* 2.使用KeyUtil第二种Generator方式生成key init参数必须设置128位（key参数可任意长，Generator后生成128位）
* 3.使用KeyUtil第三种SecretKeySpec方式生成key key初始长度必须128位
*
* */

public class AESCoder {
    private static final String AES_KEY_ALGORITHM = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static String IV = "PCNXSJYHJSNXSJYH";

    public static byte[] encrypt(byte[] content, byte[] keyBytes){
        SecretKey key = KeyUtil.getKeyByGenerator(keyBytes,AES_KEY_ALGORITHM,128);
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
            result = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] decrypt(byte[] content, byte[] keyBytes){
        SecretKey key = KeyUtil.getKeyByGenerator(keyBytes,AES_KEY_ALGORITHM, 128);
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
            result = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String content = "风一evolf";
        String key = "01234567012345670";//AES:SecretKeySpec生成key 必须为128位否则异常
        System.out.println("加密原文："+ HexUtil.byte2HexStr(content.getBytes()));
        System.out.println("加密前16进制："+HexUtil.byte2HexStr(content.getBytes()));
        byte[] encryptedCont = encrypt(content.getBytes(),key.getBytes());
        System.out.println("加密后16进制："+HexUtil.byte2HexStr(encryptedCont));
        byte[] decryptedCont = decrypt(encryptedCont, key.getBytes());
        System.out.println("解密后16进制："+HexUtil.byte2HexStr(decryptedCont));
        System.out.println("解密原文："+HexUtil.hexStr2Str(HexUtil.byte2HexStr(decryptedCont)));
    }
}
