package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * 	@author minchoba
 *	백준 1377번: 버블 정렬
 *
 *	@see https://www.acmicpc.net/problem/1377/
 *
 */
public class Boj1377 {
	
	private static class Pair{
		int idx;
		int val;
		
		public Pair(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Pair> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			list.add(new Pair(i, Integer.parseInt(br.readLine())));
		}
		
		System.out.println(swapCount(N, list));
	}
	
	private static int swapCount(int n, ArrayList<Pair> list) {
		ListSort sorter = new ListSort();
		Collections.sort(list, sorter);
		
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			int currentIdx = list.get(i).idx;
			if(count < currentIdx - i) count = currentIdx - i;		// 앞으로 댕겨야 하는 만큼 값 추가
		}
		
		return count + 1;
	}
	
	private static class ListSort implements Comparator<Pair>{		// Anonymous Class 구현

		@Override
		public int compare(Pair p1, Pair p2) {
			if(p1.val > p2.val) return 1;
			if(p1.val < p2.val) return -1;
			else return 0;
		}
		
	}
}
