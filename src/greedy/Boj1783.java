package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1783번: 병든 나이트
 *
 *	@see https://www.acmicpc.net/problem/1783/
 *
 */
public class Boj1783 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		System.out.println(move(N, M));	// 최대 이동 할 수 있는 칸의 갯수를 출력
	}
	
	/**
	 * 나이트 움직임 메소드
	 *
	 */
	private static int move(int n, int m) {
		if(n == 1 || m == 1) return 1;		// 1줄인 경우	
		if(n == 2) {				// 세로길이가 2인 경우
			int tmp = (m + 1) / 2;	// 이동 횟수를 구한 후
			
			if(tmp > 4) return 4;	// 만약 이동한 칸의 갯수가 5개 이상이라면, 무조건 4
			else return tmp;		// 그 이하로는 계산된 값을 반환
		}
		
		if(m > 6) {			// 가로가 6보다 큰 경우
			return m - 2;	// 이동 가능 횟수 4회를 모두 쓰고 이후로는 최단으로 이동 (1,2 / -1,2 로 인한 횟수: -2)
		}
		else if(m < 5){		// 5보다 작은 이동시, 각 위치마다 최단으로 이동 가능
			return m;
		}
		else {				// 5, 6의 경우는 최대 4회만 가능
			return 4;
		}
	}
}
