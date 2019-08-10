package search;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 
 * 	@author exponential-e
 *	백준 10432번: 데이터 스트림의 섬
 *
 *	@see https://www.acmicpc.net/problem/10432/
 *
 */
public class Boj10432 {	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int P = Integer.parseInt(br.readLine());
		
		while(P-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			
			int[] seq = new int[12];
			HashSet<Integer> set = new HashSet<>();
			
			for(int i = 0; i < 12; i++) {
				seq[i] = Integer.parseInt(st.nextToken());
				if(seq[i] != 0) set.add(seq[i]);
			}
			
			ArrayList<Integer> sort = sorting(set);										// 등장하는 수를 저장
			sb.append(T).append(SPACE).append(countIsland(seq, sort)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static ArrayList<Integer> sorting(HashSet<Integer> hs){
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int num: hs) {
			list.add(num);
		}
		
		Collections.sort(list);
		return list;
	}
	
	private static int countIsland(int[] arr, ArrayList<Integer> list) {
		int count = 0;
		
		for(int elem: list) {
			boolean flag = false;
			
			for(int i = 0; i < arr.length; i++) {
				if(!flag && arr[i] == elem) {			// 타겟 수로 묶이는 섬 +1
					count++;
					flag = true;
				}
				
				if(arr[i] < elem) flag = false;
			}
		}
		
		return count;
	}
}
