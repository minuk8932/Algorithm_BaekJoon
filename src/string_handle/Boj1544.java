package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1544번: 사이클 단어
 *
 *	@see https://www.acmicpc.net/problem/1544/
 *
 */
public class Boj1544 {
	private static final int MAX = 50;
	
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		char[][] words = new char[N][];
		boolean[][] isSame = new boolean[MAX][MAX];

		for (int i = 0; i < N; i++) {
			words[i] = br.readLine().toCharArray();
			isSame[i][i] = true;
		}

		for (int start = 0; start < N; start++) {
			for (int i = 0; i < N; i++) {
				if (i != start) {
					int size = words[i].length;
					
					if(words[start].length == words[i].length) {	// 비교할 두 단어의 길이가 같은 경우
						for (int j = 0; j < size; j++) {
							if (words[start][0] == words[i][j]) {	// 한 단어의 첫 단어와 같은 단어가 있는지 찾아봄
								int cnt = 0;
	
								for (int k = 0; k < size; k++) {
									// 원형으로 돌듯이 단어 하나하나 비교해줌, 단어 하나라도 다른경우 반복문 종료
									if (words[start][k % size] == words[i][(j + k) % size]) cnt++;
									else break;
								}
								
								// 만약 단어의 길이와 같은 단어의 수가 같은경우, 순서에 맞는 배열 값을 참으로 변경
								if (size == cnt) isSame[start][i] = true;
							}
						}
					}
				}
			}
		}
		
		int res = 0;
		boolean[] chk = new boolean[MAX];
		
		for(int i = 0; i < MAX; i++) {
			if(chk[i]) continue;
			
			for(int j = 0; j < MAX; j++) {
				if(chk[j]) continue;
				
				if(isSame[i][j]) {				// 동일 단어 판별 배열의 값이 같은 경우
					chk[i] = chk[j] = true;			// 정답 체크 배열의 값을 모두 참으로 바꾸고
					
					for(int k = 0; k < MAX; k++) {	// 반복문을 돌면서 또 다른 순번에 같은 단어를 가진 배열이 있는지 확인
						if(i != k && k != j) {
							if(isSame[i][k]) chk[i] = chk[k] = true;
						}
					}
					
					res++;		// 다른 단어가 등장 할 때마다 res + 1
				}
			}
		}

		System.out.println(res);	// 결과 값 한번에 출력
	}
}
