package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14468번: 소가 길을 건너간 이유 2
 *
 *	@see https://www.acmicpc.net/problem/14468/
 *
 */
public class Boj14468 {
	private static final char INITIALIZATION = 'A';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] path = br.readLine().toCharArray();
		boolean[][] isMet = new boolean[26][26];
		
		Cows[] cow = new Cows[26];
		for(int i = 0; i < cow.length; i++) {		// 소 클래스 변수 초기화
			cow[i] = new Cows(-1, -1);
		}
			
		int idx = 1;
		for(char c: path) {
			int seq = c - INITIALIZATION;			
			if(cow[seq].in == -1) {		// 아직 들어오지 않은 소
				cow[seq].in = idx++;
			}
			else {						// 들어왔다가 나가는 소
				cow[seq].out = idx++;
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < isMet.length; i++) {
			for(int j = 0; j < isMet.length; j++) {
				if(i == j) continue;
				
				if(cow[i].in < cow[j].in && cow[i].out > cow[j].in && cow[i].out < cow[j].out) {
					isMet[i][j] = isMet[j][i] = true;		// 좌표 평면상에서 범위가 걸치는 경우, 참
				}
			}
		}
		
		for(int i = 0; i < isMet.length; i++) {
			for(int j = 0; j < isMet.length; j++) {
				if(isMet[i][j]) cnt++;					// i, j / j, i 가 모두 참인 경우가 존재
			}
		}
		
		System.out.println(cnt / 2);			// 총 갯수 / 2 를 결과로 출력
	}
	
	/**
	 * 소 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Cows{
		int in;
		int out;
		
		public Cows(int in, int out) {
			this.in = in;
			this.out = out;
		}
	}
}
