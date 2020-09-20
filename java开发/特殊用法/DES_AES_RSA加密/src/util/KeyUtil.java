package util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class KeyUtil {

    /**
     * 密钥算法
     */
    private static final String DES_KEY_ALGORITHM = "DES";

    private static final String DES_CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    private static final String AES_KEY_ALGORITHM = "AES";

    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    static String IV = "PCNXSJYHJSNXSJYH";

    //第一种key生成方式，Factory
    public static SecretKey getKeyByFactory(byte[] keyBytes, String keyAlgorithm) {
        SecretKey key = null;
        try {
            DESKeySpec keySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyAlgorithm);
            key = keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    //第二种key生成方式，Generator
    public static SecretKey getKeyByGenerator(byte[] keyBytes, String keyAlgorithm, int keyLength) {
        SecretKey key = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
            keyGenerator.init(keyLength, new SecureRandom(keyBytes));//DES:key为8个字节，实际用了56位； 后面随机数用key作为种子seed生成  AES:key长可设为128，192，256位，这里只能设为128
            key = keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    //第三种key生成方式，SecretKeySpec
    public static SecretKey getKeyBySpec(byte[] keyBytes, String keyAlgorithm) {
        SecretKey key = null;
        try {
            key = new SecretKeySpec(keyBytes, keyAlgorithm);//SecretKeySpec类同时实现了Key和KeySpec接口
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static void main(String[] args) throws Exception {
        //DES加密 密钥长度为8个字节
        String key = "01234567";
        System.out.println(HexUtil.byte2HexStr(KeyUtil.getKeyByFactory(key.getBytes(),DES_KEY_ALGORITHM).getEncoded()));
        System.out.println(HexUtil.byte2HexStr(KeyUtil.getKeyByGenerator(key.getBytes(),DES_KEY_ALGORITHM, 56).getEncoded()));
        System.out.println(HexUtil.byte2HexStr(KeyUtil.getKeyBySpec(key.getBytes(),DES_KEY_ALGORITHM).getEncoded()));
    }
}
