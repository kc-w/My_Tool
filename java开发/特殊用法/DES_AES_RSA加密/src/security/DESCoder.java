package security;

import util.HexUtil;
import util.KeyUtil;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DESCoder {
    private static final String DES_KEY_ALGORITHM = "DES";
    private static final String DES_CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
    //DES IV只能8位
    private static String IV = "01234567";

    public static byte[] encrypt(byte[] content, byte[] keyBytes){
        SecretKey key = KeyUtil.getKeyBySpec(keyBytes,DES_KEY_ALGORITHM);
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(DES_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
            result = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] decrypt(byte[] content, byte[] keyBytes){
        SecretKey key = KeyUtil.getKeyBySpec(keyBytes,DES_KEY_ALGORITHM);
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(DES_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
            result = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String content = "风一evolf";
        String key = "01234567";//getKeyBySpec 生成密钥时 长度只能为8 即：64位
        System.out.println("加密原文："+ HexUtil.byte2HexStr(content.getBytes()));
        System.out.println("加密前16进制："+HexUtil.byte2HexStr(content.getBytes()));
        byte[] encryptedCont = encrypt(content.getBytes(),key.getBytes());
        System.out.println("加密后16进制："+HexUtil.byte2HexStr(encryptedCont));
        byte[] decryptedCont = decrypt(encryptedCont, key.getBytes());
        System.out.println("解密后16进制："+HexUtil.byte2HexStr(decryptedCont));
        System.out.println("解密原文："+HexUtil.hexStr2Str(HexUtil.byte2HexStr(decryptedCont)));
    }
}
