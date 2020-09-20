package 设计模式实验.适配器模式;

public class SortAdapter extends QuickSort implements DataOperation {
	public void sort(int[] a, int low, int high) {
		System.out.println("调用接口中的sort方法...");
		System.out.println("实现类QuickSort中的快速排序方法...");
		super.quickSort(a, low, high);
	}

	public int search(int[] b, int x) {return -1;};
}

