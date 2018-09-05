package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14888번: 연산자 끼워넣기
 *
 *	@see https://www.acmicpc.net/problem/14888/
 *
 */
public class Boj14888 {
	private static int max = Integer.MIN_VALUE;
	private static int min = Integer.MAX_VALUE;
	private static final String NEW_LINE = "\n";
	
	private static int[] num = null;
	private static int N = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		 int[] op = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < op.length; i++) op[i] = Integer.parseInt(st.nextToken());		// 연산자 저장
		
		process(op[0], op[1], op[2], op[3], num[0], 1);		// 프로세스 메소드를 통한 모든 경우의 수 계산
		
		System.out.println(max + NEW_LINE + min);		// 최댓 값과 최솟 값을 한 줄 간격으로 출력
	}
	
	/**
	 * 모든 연산 경우의 수를 계산하는 프로세스 메소드
	 * 
	 */
	private static void process(int p, int m, int t, int d, int ans, int idx) {		
		if(idx == N) {					// 최종적으로 연산자를 모두 사용 한 경우 결과값을 저장
			max = Math.max(max, ans);
			min = Math.min(min, ans);
			
			return;
		}
		
		// 임시로 각 연산자 마다의 값을 저장
		int[] tmp = {ans + num[idx], ans - num[idx], ans * num[idx], ans / num[idx]};
		
		if(p > 0) process(p - 1, m, t, d, tmp[0], idx + 1);		// '+'가 남아 있는 경우
		if(m > 0) process(p, m - 1, t, d, tmp[1], idx + 1);		// '-'가 남아 있는 경우
		if(t > 0) process(p, m, t - 1, d, tmp[2], idx + 1);		// '*'가 남아 있는 경우
		if(d > 0) process(p, m, t, d - 1, tmp[3], idx + 1);		// '/'가 남아 있는 경우
	}
}
