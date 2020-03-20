package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16288번: Passport Control
 *
 * @see https://www.acmicpc.net/problem/16288/
 *
 */
public class Boj16288 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] entrance = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			entrance[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(judgement(N, K, entrance));
	}

	private static String judgement(int n, int k, int[] ent){
		int[] comp = new int[k];
		Arrays.fill(comp, -1);

		for(int e: ent){
			boolean flag = false;

			for(int i = 0; i < k; i++){
				if(comp[i] < e){
					comp[i] = e;
					flag = true;
					break;
				}
			}

			if(!flag) return "NO";			// is ordered line ?
		}

		return "YES";
	}
}
