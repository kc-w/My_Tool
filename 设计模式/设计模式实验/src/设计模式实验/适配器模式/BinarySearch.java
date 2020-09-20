package 设计模式实验.适配器模式;

public class BinarySearch {
	public  int binarySearch(int[] a, int x) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (x == a[middle]) {
				return middle;
			} else if (x < a[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}
  }
