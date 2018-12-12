package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2696번: 중앙값 찾기
 *
 *	@see https://www.acmicpc.net/problem/2696/
 *
 */
public class Boj2696 {
	private static final char NEW_LINE = '\n';
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int M = Integer.parseInt(br.readLine());
			int loop = M % 10 == 0 ? M / 10 : M / 10 + 1;
			
			int[] seq = new int[M];
			
			for(int x = 0; x < loop; x++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int leng = st.countTokens() + (10 * x);
				
				for(int i = (10 * x); i < leng; i++) {
					seq[i] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append(getRes(seq));
		}
		
		System.out.println(sb);
	}
	
	private static LinkedList<Integer> getMiddleValue(int[] arr){
		LinkedList<Integer> ll = new LinkedList<>();
		
		for(int i = 0; i < arr.length; i += 2) {
			ll.add(sorting(arr, i));				// 부분 정렬
		}
		
		return ll;
	}
	
	private static int sorting(int[] arr, int idx) {
		Arrays.sort(arr, 0, idx + 1);
		return arr[idx / 2];
	}
	
	private static StringBuilder getRes(int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		LinkedList<Integer> center = getMiddleValue(arr);
		int size = center.size();
		
		sb.append(size);
		
		for(int i = 0; i < size; i++) {
			if(i % 10 == 0) sb.append(NEW_LINE);
			sb.append(center.remove()).append(SPACE);
		}
		
		if(size % 10 != 0) sb.append(NEW_LINE);
		return sb;
	}
}
