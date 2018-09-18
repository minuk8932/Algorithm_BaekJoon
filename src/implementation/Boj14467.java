package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 144667번: 소가 길을 건너간 이유 1
 *
 *	@see https://www.acmicpc.net/problem/14467/
 *
 */
public class Boj14467 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Cows[] cow = new Cows[11];
		for(int i = 1; i < cow.length; i++) cow[i] = new Cows(-1, 0, false);		// 소 클래스 변수 초기화
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cNum = Integer.parseInt(st.nextToken());
			int pos = Integer.parseInt(st.nextToken());
			
			if(!cow[cNum].appear) {			// 처음 나타난 소의 경우
				cow[cNum].position = pos;	// 위치 저장 후
				cow[cNum].appear = true;	// 나타남 표시
			}
			else {
				if(cow[cNum].position != pos) {	// 이전에 나타났던 소면서, 현재 다른 위치에 존재하는 경우
					cow[cNum].position = pos;		// 위치를 변경하고
					cow[cNum].crossed++;			// 길건넘 +1
				}
			}
		}
		
		int sum = 0;
		for(int i = 1; i < cow.length; i++) {		// 소들이 최종적으로 길을 건넌 횟수를 모두 합
			if(cow[i].crossed == 0) continue;
			
			sum += cow[i].crossed;
		}
		
		System.out.println(sum);		// 결과 값 출력
	}
	
	/**
	 * 소 정보 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Cows{
		int position;
		int crossed;
		boolean appear;
		
		public Cows(int position, int crossed, boolean appear) {
			this.position = position;
			this.crossed = crossed;
			this.appear = appear;
		}
	}
}
