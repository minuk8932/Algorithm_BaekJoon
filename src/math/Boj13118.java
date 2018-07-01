package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13118번: 뉴턴과 사과
 *
 *	@see https://www.acmicpc.net/problem/13118/
 *
 */
public class Boj13118 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] p = new int[4];
		for(int i = 0; i < 4; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());		// 사과가 낙하할 지점
		int idx = 0;
		
		boolean isCrash = false;
		for(int i = 0; i < 4; i++) {
			if(x == p[i]) {				// 사과 낙하 지점에 사람이 존재하면
				isCrash = true;			// 충돌 = 참, 해당 사람의 번호를 저장 후 반복문 종료
				idx = i;
				
				break;
			}
		}
		
		
		
		System.out.println(isCrash ? idx + 1 : 0);	// 충돌을 했다면, 사람의 번호 +1, 안했다면 0을 출력
	}
}
