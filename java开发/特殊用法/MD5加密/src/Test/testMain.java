package Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class testMain {  
    public static void main(String[] args) {  
        String pwd1="root";  
        String pwd2="";  
        System.out.println("未加密的密码:"+pwd1);
        pwd2 = testMD5.getMd5String(pwd1);  
        System.out.println("加密后的密码:"+pwd2);
        System.out.println("请输入你的密码：");
        Scanner input=new Scanner(System.in);
        String pwd3=input.next();
        System.out.print("验证密码是否正确确:");  
        if(testMD5.JudgePassword(pwd2, pwd3)) {  
            System.out.println("正确");  
        }  
        else {  
            System.out.println("错误");  
        }  
    }  
    /** * 把传入的字符加密    返回一个加密后的字符 */  
    public static String getMd5String(String str){  
    	System.out.println("hhhh"+getString(str));
        return getString(str);  
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
    /**对
     * 信息摘要算法
     * @param algorithm 算法类型
     * @param data 要加密的字符串
     * @return 返回加密后的摘要信息
     */
	private static String getString(String str) {
        try {
        	//创建具有指定算法名称的信息摘要  
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byteArrayToHexStr(md.digest(str.getBytes())).toUpperCase();
        } catch(NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * 将字节数组转换成16进制字符串
     * @param byteArray 要转码的字节数组
     * @return 返回转码后的16进制字符串
     */
    public static String byteArrayToHexStr(byte byteArray[]) {
        StringBuffer buffer = new StringBuffer(byteArray.length * 2);
        int i;
        for (i = 0; i < byteArray.length; i++) {
            if (((int) byteArray[i] & 0xff) < 0x10)//小于十前面补零
                buffer.append("0");
            buffer.append(Long.toString((int) byteArray[i] & 0xff, 16));
        }
        return buffer.toString();
    }
}  