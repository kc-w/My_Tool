package 设计模式实验.组合模式;
/**
 * 组合模式
 * @author 小石头
 *
 */
public class Test {
	public static void main(String[] args) {
		ImageFile i = new ImageFile();
		i.shadu1();
		MediaFile m = new MediaFile();
		m.shadu1();
		TextFile f = new TextFile();
		f.shadu1();
		
		AntiVirus obj1 = new ImageFile();
		AntiVirus obj2 = new MediaFile();
		Folder f1 = new Folder();
		f1.add(obj1);
		f1.add(obj2);
		
		AntiVirus obj3 = new TextFile();
		AntiVirus obj4 = new TextFile();
		Folder f2 = new Folder();
		f2.add(obj3);
		f2.add(obj4);
		
		AntiVirus obj5 = new ImageFile();
		Folder f3 = new Folder();
		f3.add(f1);
		f3.add(f2);
		f3.add(obj5);
		
		f3.shadu();
	}

}
