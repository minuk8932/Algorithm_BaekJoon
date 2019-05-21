package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17248번: 물리 공부
 *
 *	@see https://www.acmicpc.net/problem/17248/
 *
 */
public class Boj17248 {
	private static ArrayList<Integer>[] velocity = new ArrayList[10_001];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		init();			// 가속도 증가량 미리 계산
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int Z = Integer.parseInt(st.nextToken());
			
			sb.append(getTiming(A, B, Z)).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static void init() {
		for(int i = 0; i < velocity.length; i++) {
			velocity[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < velocity.length; i++) {
			int value = i;
			
			while(value < velocity.length) {
				velocity[i].add(value);
				value += i;
			}
			
			velocity[i].add(value);
		}
	}
	
	private static int getTiming(int a, int b, int idx) {
		int time = 0;
				
		for(int adder: velocity[idx]) {
			if(a >= b) break;
				
			a += adder;						// 속도가 같아질때까지 ++
			time++;
		}

		return time;
	}
}
