package ���ģʽʵ��.������ģʽ;
import java.util.*;
/**
 * ������ģʽ
 * @author Сʯͷ
 *
 */
public class Test {
	public static void main(String[] args) {
		int a[] = {5,7,6,1,4,9,2,3,8,10};
		DataOperation d = new SortAdapter();
		d.sort(a, 0, a.length-1);
		System.out.println("�����...");
		for(int i = 0;i<a.length;i++) {
			System.out.print(a[i]+" ");
		}
		System.out.print("\n");
		
		DataOperation d1 = new SearchAdapter();
		int x = d1.search(a, 2);
		System.out.println("���ֲ��ң�"+x);
	}
	
	
	

}
