package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1598번: 꼬리를 무는 숫자 나열
 *
 *	@see https://www.acmicpc.net/problem/1598/
 *
 */
public class Boj1598 {
	private static Pair[] p = new Pair[2];
	
	private static class Pair{
		int a1;
		int d;
		
		public Pair(int a1, int d) {
			this.a1 = a1;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		System.out.println(getDistance(A, B));
	}
	
	private static int getDistance(int x, int y) {
		getPair(x, 0);
		getPair(y, 1);
		
		return Math.abs(p[0].d - p[1].d) + Math.abs(p[0].a1 - p[1].a1);		// 등차의 차이와 첫항의 차이에 따른 거리 합
	}
	
	private static void getPair(int val, int idx) {
		for(int i = 1; i < 5; i++) {
			int diff = val - i;
			
			if(diff % 4 == 0) p[idx] = new Pair(i, diff / 4);
		}
	}
}
