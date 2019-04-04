package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1822번: 차집합
 *
 *	@see https://www.acmicpc.net/problem/1822/
 *
 */
public class Boj1822 {	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nA = Integer.parseInt(st.nextToken());
		int nB = Integer.parseInt(st.nextToken());
		
		int[] A = new int[nA];
		int[] B = new int[nB];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < nA; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < nB; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);		// 각 원소 정렬
		Arrays.sort(B);
		
		System.out.println(getElement(nA, nB, A, B));
	}
	
	private static StringBuilder getElement(int n, int m, int[] arr, int[] brr) {
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> set = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			int target = arr[i];
			
			boolean isSet = binarySearch(0, m, brr, target);		// 해당 타겟 원소가 집합 B에 포함되는가? 
			if(!isSet) set.add(target);
		}
		
		int size = set.size();
		if(size == 0) return sb.append(size);			// A에만 존재하는 원소가 없을 때
		
		Collections.sort(set);
		sb.append(size).append(NEW_LINE);
		
		for(int elem: set) {
			sb.append(elem).append(SPACE);
		}
		
		return sb;
	}
	
	private static boolean binarySearch(int start, int end, int[] arr, int target) {
		while(start <= end) {
			int mid = (start + end) / 2;
			if(mid > arr.length - 1) break;		// 배열 크기를 넘었다 -> 해당 배열의 원소는 타겟보다 모두 작거나 모두 큰 경우
			
			if(arr[mid] > target) end = mid - 1;
			else if(arr[mid] < target) start = mid + 1;
			else return true;
		}
		
		return false;
	}
}
