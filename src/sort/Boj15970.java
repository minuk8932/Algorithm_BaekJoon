package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15970번: 화살표 그리기
 *
 *	@see https://www.acmicpc.net/problem/15970/
 *
 */
public class Boj15970 {		
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] list = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int point = Integer.parseInt(st.nextToken());
			int flag = Integer.parseInt(st.nextToken()) - 1;
			list[flag].add(point);
		}
		
		int result = 0;
		
		for(int i = 0; i < N; i++) {
			if(list[i] == null) continue;
			result += getDistance(N, list[i]);		// 각 케이스 별 화살표 그리기
		}
		
		System.out.println(result);
	}
	
	private static int getDistance(int n, ArrayList<Integer> arr) {
		Collections.sort(arr);
		int leng = 0;
		int size = arr.size();
		
		for(int i = 0; i < size; i++) {
			if(i == 0) leng += arr.get(i + 1) - arr.get(i);
			else if(i == size - 1) leng += arr.get(i) - arr.get(i - 1);
			else leng += Math.min(arr.get(i) - arr.get(i - 1), arr.get(i + 1) - arr.get(i));
		}
		
		return leng;
	}
}
