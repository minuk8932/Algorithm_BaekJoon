package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 
 * 		Boj 10815 : 숫자 카드
 * 		https://www.acmicpc.net/problem/10815
 * 
 */

public class Boj10815 {
	private static final String SPACE = " ";
	private static final int HAS = 1;
	private static final int HAS_NOT = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < cards.length; i++){
			cards[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards);						// 이분탐색을 위한 배열 정렬
		
		int M = Integer.parseInt(br.readLine());
		int[] chk = new int[M];
		
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < chk.length; i++){
			chk[i] = Integer.parseInt(st.nextToken());
			
			boolean isVisit = false;
			int left = 0, right = N - 1;
			int mid = 0;
			
			while(right - left >= 0){
				mid = (left + right) / 2;				// 카드의 중간 값
				
				if(cards[mid] == chk[i]){				// 내가 찾는 답과 해당 카드[mid] 값이 같다면, isVisit = true
					isVisit = true;
					break;
				}
				else if(cards[mid] > chk[i]){		// 카드[mid] 값이 더 크다면, 답은 이전 카드[mid] 값과 가장 앞의 값 즉 카드[0] 사이에 있을 것
																	// 따라서 right = mid - 1 (mid 자체는 이미 확인 하였으므로)
					right = mid - 1;
				}
				else{											// 위와 반대의 경우로, 카드[mid] 값과 카드[length - 1] 사이에 답이 존재	
																	// 따라서 left = mid - 1;
					left = mid + 1;
				}
			}
			
			if(isVisit){													// isVisit == true 라면, cards[mid] == chk[i] 였던 값 chk[i]가 존재했다는 뜻
				sb.append(HAS).append(SPACE);
			}
			else{
				sb.append(HAS_NOT).append(SPACE);	// 위와 반대의 경우로, 해당 chk[i]가 존재하지 않았다는 뜻
			}
		}
		System.out.println(sb.toString());
	}
}
