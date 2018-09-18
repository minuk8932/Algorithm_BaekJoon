package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1057번: 토너먼트
 *
 *	@see https://www.acmicpc.net/problem/1057/
 *
 */
public class Boj1057 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		
		// 시작 라운드의 값을 짝수로 변경
		if(r1 % 2 == 1) r1++;
        if(r2 % 2 == 1) r2++;
		
		int rCnt = 1;
		
		while((r1 /= 2) != (r2 /= 2)) {		// 현 라운드 값을 2로 나누면 다음 라운드에서의 순번이 정해짐, 두 값이 같아지는 경우 해당 라운드에서 만남
			if(r1 % 2 == 1) r1++;			// 라운드마다 홀수가 생기면 짝수로 변경	
            if(r2 % 2 == 1) r2++;
			
			rCnt++;				// 라운드 + 1
		}
		
		System.out.println(rCnt); // 최종 만나는 라운드를 출력
	}
}
