package Bean.time;

import java.text.SimpleDateFormat;
import java.util.Date;
//时间模型
public class getDate {
	private Date NowTime;
	private String dataStyle="YYY-MM-dd hh:mm:ss";
	private SimpleDateFormat format=new SimpleDateFormat(dataStyle);
	
	public getDate() {
		
	}
	
	//获得格式化后的当前时间
	public String getNowTime(){
		NowTime=new Date();
		String Time=format.format(NowTime);
		return Time;
	}
}
