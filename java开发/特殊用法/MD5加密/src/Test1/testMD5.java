package Test1;  
  
import java.security.MessageDigest;  
/**  
 * 对密码进行加密和验证的类 
 */  
public class testMD5{  
      
    //十六进制下数字到字符的映射数组  
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",  
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f","g","h",
        "i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};  
      
    /** * 把传入的字符加密    返回一个加密后的字符 */  
    public static String getMd5String(String str){  
        return MD5encryption(str);  
    }  
      
      /** 
       * 验证输入的密码是否正确 
     * @param password    加密后的密码 
     * @param inputString    输入的字符串 
     * @return    验证结果，TRUE:正确 FALSE:错误 
     */  
    public static boolean JudgePassword(String password, String inputString){  
        if(password.equals(getMd5String(inputString))){  
            return true;  
        } else{  
            return false;  
        }  
    }  
    /**  对字符串进行MD5加密     */  
    private static String MD5encryption(String str1){  
        if (str1 != null){  
            try{  
                //创建具有指定算法名称的信息摘要  
                MessageDigest md = MessageDigest.getInstance("MD5");  
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算  
                byte[] results = md.digest(str1.getBytes());  
                //将得到的字节数组变成字符串返回  
                String resultString = hexadecimal(results);  
                return resultString.toUpperCase();  
            } catch(Exception ex){  
                ex.printStackTrace();  
            }  
        }  
        return null;  
    }  
      
    /**  
     * 转换字节数组为十六进制字符串 
     * @param     字节数组 
     * @return    十六进制字符串 
     */  
    private static String hexadecimal(byte[] b){  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < b.length; i++){  
            sb.append(ToHexadecimalString(b[i]));  
        }  
        return sb.toString();  
    }  
      
    /** 将一个字节转化成十六进制形式的字符串     */  
    private static String ToHexadecimalString(byte b){  
        int n = b;  
        if (n < 0)  
            n = 256 + n;  
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    }  
}  