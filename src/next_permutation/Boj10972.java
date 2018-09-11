package next_permutation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10972번: 다음 순열
 *
 *	@see https://www.acmicpc.net/problem/10972/
 *
 */
public class Boj10972 {
	private static int[] arr = null;
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int idx = -1;
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(i > 0) {				// 다음 원소보다 큰 원소 중 가장 큰 인덱스를 가져옴
				if(arr[i - 1] < arr[i]) idx = i - 1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(idx == -1) {			// 다음 원소보다 큰 원소가 전혀 없는 경우 -> 마지막 순열
			sb.append(idx);
		}
		else {
			for(int i = arr.length - 1; i > idx; i--) {		// 위에서 찾은 인덱스의 값보다 큰 숫자를 뒤에서부터 탐색 후 찾아내면
				if(arr[idx] < arr[i]) {		// 즉시 idx와 해당 인덱스의 값을 바꾸고 반복문 종료
					int tmp = arr[i];
					arr[i] = arr[idx];
					arr[idx] = tmp;
					break;
				}
			}
			
			for(int i = 0; i < idx + 1; i++) sb.append(arr[i]).append(SPACE);	// 0 ~ idx 까지는 정방향
			for(int i = N - 1; i > idx; i--) sb.append(arr[i]).append(SPACE);	// idx + 1 부터 수열의 마지막까지 순서를 바꾸어줌
			
			// 배열에 순서를 뒤집어 저장하는 경우
//			for(int i = idx + 1; i < (arr.length + idx + 1) / 2; i++) {	
//				int tmp = arr[i];
//				arr[i] = arr[arr.length - (i - idx)];
//				arr[arr.length - (i - idx)] = tmp;
//			}
			
			for(int i = 0; i < N; i++) sb.append(arr[i]).append(SPACE);		// 배열 값을 버퍼에 담은 후
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
