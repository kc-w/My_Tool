package image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/image/image_servlet")
public class image_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //告诉客户端，输出的格式
		  response.setContentType("image/jpeg");
		 
		  int width = 80;
		  int height = 30;
		  int lines = 10;
		  BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		 
		  Graphics g = img.getGraphics();
		 
		  //设置背景色
		  g.setColor(Color.white);
		  g.fillRect(0, 0, width, height);
		 
		  //设置字体
		  g.setFont(new Font("宋体", Font.BOLD, 20));
		 
		  //随机数字
		  Random r = new Random(new Date().getTime());
		  String str="";
		  for(int i=0;i<4;i++){
		   int a = r.nextInt(10);
		   int y = 10+r.nextInt(20);//10~30范围内的一个整数，作为y坐标
		 
		   Color c = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		   g.setColor(c);
		 
		   g.drawString(""+a, 5+i*width/4, y);
		   
		   str+=String.valueOf(a);
		  }
		  HttpSession session=request.getSession();
		  session.setAttribute("yzm", str);
		  //干扰线
		  for(int i=0;i<lines;i++){
		   Color c = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		   g.setColor(c);
		   g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		  }
		 
		  g.dispose();//类似于流中的close()带动flush()---把数据刷到img对象当中
		 
		  ImageIO.write(img, "JPG", response.getOutputStream());
	}

}
