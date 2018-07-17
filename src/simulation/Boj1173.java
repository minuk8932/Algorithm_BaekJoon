package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1173번: 운동
 *
 *	@see https://www.acmicpc.net/problem/1173/
 *
 */
public class Boj1173 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int timer = 0, X = m;
		boolean isBreak = false;
		
		if(m + T > M) timer = -1;		// 한번의 운동만에 맥박이 초과되는 경우
		
		if(timer != -1) {
			while(N > 0) {
				if(X + T > M) isBreak = true;		// 운동 후 값이 최대 맥박을 초과하는 경우
				else if(X - R < m) isBreak = false;			// 휴식 후 값이 최소 맥박 밑으로 떨어지는 경우
				
				if(!isBreak) {		// 운동하는 경우
					X += T;
					N--;
				}
				else {			// 휴식하는 경우
					X -= R;
					
					if(X < m) X = m;	// 휴식 수치보다 떨어지는 경우 최소 맥박값으로 갱신
					
					isBreak = false;	// 운동하는 경우로 바꿔줌
				}
				
				timer++;		// 운동 완료에 걸리는 시간을 체크
			}
		}
		
		System.out.println(timer);		// 결과 값 출력
	}
}
