package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1920번 : 수 찾기
 *
 *	@see https://www.acmicpc.net/problem/1920
 *
 *	- 참고
 *		백준 10816번 문제와 매우 유사함
 *		@see https://www.acmicpc.net/problem/10816
 *
 */
public class Boj1920 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int EXIST = 1;
	private static final int NOT_EXIST = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numN = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i =0; i < numN.length; i++){
			numN[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numN);											// numN 배열 오름차순 정렬
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), SPACE);
		while(M-- > 0){
			int key = Integer.parseInt(st.nextToken());
			int left = 0, right = N - 1;								// 좌, 우, 중앙 탐색 커서 선언
			int mid = 0, cnt = 0;
			
LOOP:		while(right - left >= 0){									// 탐색 할 숫자가 없을때 까지 실행
				mid = (left + right) / 2;								// 중앙 탐색 커서 설정
				
				if(key == numN[mid]){									// 소지한 숫자 중 중앙 커서가 가리키는 값과 비교 숫자의 값이 같다면
					sb.append(EXIST).append(NEW_LINE);		// 출력 버퍼에 존재 한다(1)를 담고 카운트 +1 후 루프 종료
					cnt++;
					break LOOP;
				}
				else if(key > numN[mid]){							// 비교 숫자 값이 크다면
					left = mid + 1;											// 좌측 탐색 커서에 (중앙값 + 1)을 할당
				}
				else{															// 비교 숫자 값이 작다면
					right = mid - 1;										// 우측 탐색 커서에 (중앙값 + 1)을 할당
				}
			}
			
			if(cnt == 0){													// 카운트가 증가하지 않았을 경우 해당 숫자가 존재하지 않으므로, 존재하지않음(0)을 출력 버퍼에 담아줌
				sb.append(NOT_EXIST).append(NEW_LINE);
			}
		}
		System.out.println(sb.toString());						// 숫자의 존재여부가 담겨있는 버퍼의 결과를 한번에 출력
	}
}
