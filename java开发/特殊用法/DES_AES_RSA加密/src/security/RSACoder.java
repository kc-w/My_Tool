package security;

import util.HexUtil;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
/**
 * 生成密钥对 从密钥对里获取公钥 私钥
 * 1. KeyPairGenerator获取key；
 * 2. String获取key；
 * 3. modulus和exponent获取key。
 *
 */
public class RSACoder {

    public static String data="hello worldhello worldhello worldhello worldhello worldhello world";
    public static String publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCISLP98M/56HexX/9FDM8iuIEQozy6kn2JMcbZS5/BhJ+U4PZIChJfggYlWnd8NWn4BYr2kxxyO8Qgvc8rpRZCkN0OSLqLgZGmNvoSlDw80UXq90ZsVHDTOHuSFHw8Bv//B4evUNJBB8g9tpVxr6P5EJ6FMoR/kY2dVFQCQM4+5QIDAQAB";
    public static String privateKeyString="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIhIs/3wz/nod7Ff/0UMzyK4gRCjPLqSfYkxxtlLn8GEn5Tg9kgKEl+CBiVad3w1afgFivaTHHI7xCC9zyulFkKQ3Q5IuouBkaY2+hKUPDzRRer3RmxUcNM4e5IUfDwG//8Hh69Q0kEHyD22lXGvo/kQnoUyhH+RjZ1UVAJAzj7lAgMBAAECgYAVh26vsggY0Yl/Asw/qztZn837w93HF3cvYiaokxLErl/LVBJz5OtsHQ09f2IaxBFedfmy5CB9R0W/aly851JxrI8WAkx2W2FNllzhha01fmlNlOSumoiRF++JcbsAjDcrcIiR8eSVNuB6ymBCrx/FqhdX3+t/VUbSAFXYT9tsgQJBALsHurnovZS1qjCTl6pkNS0V5qio88SzYP7lzgq0eYGlvfupdlLX8/MrSdi4DherMTcutUcaTzgQU20uAI0EMyECQQC6il1Kdkw8Peeb0JZMHbs+cMCsbGATiAt4pfo1b/i9/BO0QnRgDqYcjt3J9Ux22dPYbDpDtMjMRNrAKFb4BJdFAkBMrdWTZOVc88IL2mcC98SJcII5wdL3YSeyOZto7icmzUH/zLFzM5CTsLq8/HDiqVArNJ4jwZia/q6Fg6e8KO2hAkB0EK1VLF/ox7e5GkK533Hmuu8XGWN6I5bHnbYd06qYQyTbbtHMBrFSaY4UH91Qwd3u9gAWqoCZoGnfT/o03V5lAkBqq8jZd2lHifey+9cf1hsHD5WQbjJKPPIb57CK08hn7vUlX5ePJ02Q8AhdZKETaW+EsqJWpNgsu5wPqsy2UynO";

    public static String modulusString="95701876885335270857822974167577168764621211406341574477817778908798408856077334510496515211568839843884498881589280440763139683446418982307428928523091367233376499779842840789220784202847513854967218444344438545354682865713417516385450114501727182277555013890267914809715178404671863643421619292274848317157";
    public static String publicExponentString="65537";
    public static String privateExponentString="15118200884902819158506511612629910252530988627643229329521452996670429328272100404155979400725883072214721713247384231857130859555987849975263007110480563992945828011871526769689381461965107692102011772019212674436519765580328720044447875477151172925640047963361834004267745612848169871802590337012858580097";



    public static void main(String[] args) throws Exception {
        /*****方式一：生成密钥对方式******/
        System.out.println("****方式一：生成密钥对方式****");
        KeyPair keyPair=genKeyPair(1024);
        //获取公钥，并以base64格式打印出来
        PublicKey publicKey=keyPair.getPublic();
        System.out.println("公钥："+new String(Base64.getEncoder().encode(publicKey.getEncoded())));

        //获取私钥，并以base64格式打印出来
        PrivateKey privateKey=keyPair.getPrivate();
        System.out.println("私钥："+new String(Base64.getEncoder().encode(privateKey.getEncoded())));

        //公钥加密
        byte[] encryptedBytes=encrypt(data.getBytes(), publicKey);
        System.out.println("加密后："+new String(encryptedBytes,"utf-8"));

        //私钥解密
        byte[] decryptedBytes=decrypt(encryptedBytes, privateKey);
        System.out.println("解密后："+new String(decryptedBytes,"utf-8"));

        /*****方式二：公钥私钥字符串生成密钥方式******/
        System.out.println("****方式二：公钥私钥字符串生成密钥方式****");
        //获取公钥
        PublicKey publicKey2=getPublicKey(publicKeyString);

        //获取私钥
        PrivateKey privateKey2=getPrivateKey(privateKeyString);

        //公钥加密
        byte[] encryptedBytes2=encrypt(data.getBytes(), publicKey2);
        System.out.println("加密后："+new String(encryptedBytes2,"utf-8"));

        //私钥解密
        byte[] decryptedBytes2=decrypt(encryptedBytes2, privateKey2);
        System.out.println("解密后："+new String(decryptedBytes2,"utf-8"));

        /*****方式三：公钥e 私钥d 模n方式******/
        System.out.println("****方式三：公钥e 私钥d 模n方式****");
        //由n和e获取公钥
        PublicKey publicKey3=getPublicKey(modulusString, publicExponentString);

        //由n和d获取私钥
        PrivateKey privateKey3=getPrivateKey(modulusString, privateExponentString);

        //公钥加密
        byte[] encryptedBytes3=encrypt(data.getBytes(), publicKey3);
        System.out.println("加密后："+new String(encryptedBytes3,"utf-8"));

        //私钥解密
        byte[] decryptedBytes3=decrypt(encryptedBytes3, privateKey3);
        System.out.println("解密后："+new String(decryptedBytes3,"utf-8"));

    }

    //方式一：生成密钥对
    public static KeyPair genKeyPair(int keyLength) throws Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    //方式二：将base64编码后的公钥字符串转成PublicKey实例
    public static PublicKey getPublicKey(String publicKey) throws Exception{
        byte[ ] keyBytes=Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //方式二：将base64编码后的私钥字符串转成PrivateKey实例
    public static PrivateKey getPrivateKey(String privateKey) throws Exception{
        byte[ ] keyBytes=Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }


    //方式三：根据m e生成PublicKey实例
    public static PublicKey getPublicKey(String modulusStr, String exponentStr) throws Exception{
        BigInteger modulus=new BigInteger(modulusStr);
        BigInteger exponent=new BigInteger(exponentStr);
        RSAPublicKeySpec publicKeySpec=new RSAPublicKeySpec(modulus, exponent);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicKeySpec);
    }

    //方式三：根据m e成PrivateKey生成实例
    public static PrivateKey getPrivateKey(String modulusStr, String exponentStr) throws Exception{
        BigInteger modulus=new BigInteger(modulusStr);
        BigInteger exponent=new BigInteger(exponentStr);
        RSAPrivateKeySpec privateKeySpec=new RSAPrivateKeySpec(modulus, exponent);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privateKeySpec);
    }

    //公钥加密  16进制字符串
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{
        //需转换成RSAPrivateKey才能获取modulus
        StringBuffer sb = new StringBuffer();
        Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int splitLength=((RSAPublicKey)publicKey).getModulus().bitLength()/8-11;
        byte[][] arrays=splitBytes(content, splitLength);
        // 对数据分段加密
        for(byte[] array : arrays){
            sb.append(HexUtil.byte2HexStr(cipher.doFinal(array)));
        }
        return sb.toString().getBytes();
    }

    //私钥解密
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{
        StringBuffer sb = new StringBuffer();
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int splitLength=((RSAPrivateKey)privateKey).getModulus().bitLength()/8;
        byte[] contentBytes=HexUtil.hexStr2Bytes(new String(content));
        byte[][] arrays=splitBytes(contentBytes, splitLength);
        // 对数据分段加密（分段加密原因见说明文档）
        for(byte[] array : arrays){
            sb.append(new String(cipher.doFinal(array)));
        }
        return sb.toString().getBytes();
    }

    //拆分byte数组
    public static byte[][] splitBytes(byte[] bytes, int splitLength) {
        int x; //商，数据拆分的组数，余数不为0时+1
        int y; //余数
        y = bytes.length % splitLength;
        if (y != 0) {
            x = bytes.length / splitLength + 1;
        } else {
            x = bytes.length / splitLength;
        }
        byte[][] arrays = new byte[x][];
        byte[] array;
        for (int i = 0; i < x; i++) {

            if (i == x - 1 && bytes.length % splitLength != 0) {
                array = new byte[bytes.length % splitLength];
                System.arraycopy(bytes, i * splitLength, array, 0, bytes.length % splitLength);
            } else {
                array = new byte[splitLength];
                System.arraycopy(bytes, i * splitLength, array, 0, splitLength);
            }
            arrays[i] = array;
        }
        return arrays;
    }





}
