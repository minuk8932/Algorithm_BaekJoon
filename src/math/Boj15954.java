package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15954번: 인형들
 *
 *	@see https://www.acmicpc.net/problem/15954/
 *
 */
public class Boj15954 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		double[] fav = new double[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			fav[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		
		LinkedList<Double> getRes = new LinkedList<>();
		
		while(start < N - K + 1) {
			int loop = K;
			
			while(loop + start <= N) {
				double sum = 0;
				double total = 0;
		
				for (int i = start; i < loop + start; i++) {		// 합계산
					sum += fav[i];
				}
				
				sum /= loop;
				
				for (int i = start; i < loop + start; i++) {		// 표준편차 계산
					total += Math.pow((fav[i] - sum), 2) / loop;
				}
				
				total = Math.sqrt(total);
				
				getRes.add(total);
				loop++;
			}
			start++;
		}
		
		Collections.sort(getRes);
		System.out.println(getRes.get(0));		// 가장 작은 표준편차 출력
	}
}