package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1004번: 어린왕자
 *
 *	@see https://www.acmicpc.net/problem/1004/
 *
 */
public class Boj1004 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			
			int N = Integer.parseInt(br.readLine());
			
			int[][] c = new int[N][3];
			for(int i = 0; i < N; i++) {					// 존재하는 원의 정보를 배열에 입력
				st = new StringTokenizer(br.readLine());
				c[i][0] = Integer.parseInt(st.nextToken());
				c[i][1] = Integer.parseInt(st.nextToken());
				c[i][2] = Integer.parseInt(st.nextToken());
			}
			
			int thru = 0;
			
			for(int i = 0; i < N; i++) {
				long sDistance = (long) Math.pow(c[i][0] - sx, 2) + (long) Math.pow(c[i][1] - sy, 2);	// 각 원의 중심으로부터 s, e 사이의 거리를 제곱으로 구함
				long eDistance = (long) Math.pow(c[i][0] - ex, 2) + (long) Math.pow(c[i][1] - ey, 2);
				long r = c[i][2] * c[i][2];			// 그때의 해당하는 원의 반지름의 제곱
				
				if(r > sDistance && r > eDistance) continue;	// 만약 반지름의 크기가 두 점 s, e의 거리보다 큰 경우 두 점은 해당 원 내부에 있으므로 넘김 
				
				if(r > sDistance || r > eDistance) {		// 만약 둘중 하나의 길이보다 반지름이 크다면, 반드시 지나쳐야 하는 원이므로 thru +1
					thru++;
				}
			}
			
			sb.append(thru).append(NEW_LINE);		// 최종적으로 원의 둘레를 지나야하는 횟수를 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
