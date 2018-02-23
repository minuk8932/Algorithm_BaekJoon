package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10816번 : 숫자 카드 2
 *	
 *	@see https://www.acmicpc.net/problem/10816
 *
 */
public class Boj10816 {
	private static final String SPACE = " ";
	private static final int MAX = 10_000_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] hasC = new int[N];							// 종류별로 몇개의 카드(index)를 가졌는지에 대해 기록 할 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < N; i++){
			hasC[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] chkC = new int[M];							// 가지고있는 카드중에 비교해 볼 카드의 종류
		int[][] cnt = new int[2][MAX];					// 음수 양수를 구분해 갯수를 체크 할 배열
		int[] chk = new int[M];
		
		st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < M; i++){
			int x = Integer.parseInt(st.nextToken());
			chkC[i] = x;
			chk[i] = x;
		}
		
		Arrays.sort(chkC);									// 배열 chkC 오름차순 정렬
		
		for(int i = 0; i < hasC.length; i++){			// 이분탐색 시작
			int left = 0, right = M - 1;					// 좌, 우, 중앙 커서 설정
			int mid = 0;
			
			while(right - left >= 0){						// 탐색을 안한 카드가 존재 할 때까지
				mid = (left + right) / 2;					// 중간 값 할당
				
				if(hasC[i] == chkC[mid]){					// 소지 카드와 비교 카드 중 같은것이 있다면
					if(hasC[i] >= 0){							// 이때 그 값이 0 또는 양수라면
						cnt[0][hasC[i]]++;					// 양수 배열([0][])에서 그 값에 해당하는 인덱스에 +1
					}
					else{											// 음수라면
						int num =  hasC[i] * (-1);			// 임시 변수에 해당 값을 양수처리 한 후
						cnt[1][num]++;						// 음수 배열([1][])에서 그 값에 해당하는 인덱스에 +1
					}
					break;
				}
				else if (hasC[i] < chkC[mid]){			// 만약 가지고 있는 카드의 값 중 하나가 비교 카드보다 작다면
					right = mid - 1;							// 우측 탐색 커서에 (중앙값 - 1)을 할당 : 즉 배열을 오름차순 정렬 했기 때문에, 순차적으로 반씩 탐색하며 찾아 낼 수 있음.
				}
				else{
					left = mid + 1;								// 비교 카드보다 클 땐, 좌측 탐색 커서에 (중앙값 - 1)을 할당
				}
			}
		}
		
		// 버퍼를 통해 결과 값을 담아둠
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++){
			if(chk[i] >= 0){
				sb.append(cnt[0][chk[i]]).append(SPACE);
			}
			else{
				int num = chk[i] * (-1);
				sb.append(cnt[1][num]).append(SPACE);
			}
		}
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}
