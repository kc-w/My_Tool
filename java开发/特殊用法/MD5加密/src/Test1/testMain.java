package Test1;

import java.util.Scanner;

public class testMain {  
    public static void main(String[] args) {  
        String pwd1="lqw123456hgfjdh7890lqw";  
        String pwd2="";  
        System.out.println("未加密的密码:"+pwd1);  
        System.out.println("请输入你的密码：");
        Scanner input=new Scanner(System.in);
        String pwd3=input.next();
        pwd2 = testMD5.getMd5String(pwd1);  
        System.out.println("加密后的密码:"+pwd2);  
        System.out.print("验证密码是否正确:");  
        if(testMD5.JudgePassword(pwd2, pwd3)) {  
            System.out.println("正确");  
        }  
        else {  
            System.out.println("错误");  
        }  
    }  
}  