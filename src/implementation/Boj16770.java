package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 16770번: The Bucket List
 *
 *	@see https://www.acmicpc.net/problem/16770/
 *
 */
public class Boj16770 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] buckets = new int[1_001];
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			
			for(int x = from; x < to; x++) {
				buckets[x] += count;
				if(buckets[x] > max) max = buckets[x];			// 최대 필요한 갯수
			}
		}
		
		System.out.println(max);
	}
}
