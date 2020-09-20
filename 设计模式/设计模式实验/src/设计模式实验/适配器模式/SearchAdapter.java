package 设计模式实验.适配器模式;

public class SearchAdapter extends BinarySearch implements DataOperation {
	public int search(int[]a,int x) {
		System.out.println("调用接口中的search方法...");
		System.out.println("实现类BinarySearch中的二分查找方法...");
		int b = super.binarySearch(a, x);
		return b+1;
		
	}
	public void sort(int[]a,int low,int high){}
}
	
	

