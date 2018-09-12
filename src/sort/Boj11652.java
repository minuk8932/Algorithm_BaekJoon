package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 11652번: 카드
 *
 *	@see https://www.acmicpc.net/problem/11652/
 *
 */
public class Boj11652 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		
		for(int i = 0; i < N; i++) arr[i] = Long.parseLong(br.readLine());
		
		Arrays.sort(arr);
		
		int cardCnt = 0;
		long tmp = arr[0];
		int cnt = 0;
		
		for(int i = 1; i < N; i++) {
			cnt = arr[i] == arr[i - 1] ? cnt + 1 : 0;		// 배열 값의 빈도를 체크
			
			if(cardCnt < cnt) {		// 앞에서부터 빈도가 더 큰 숫자와 횟수를 저장 -> 빈도가 큰 숫자 중 가장 작은 값 저장
				cardCnt = cnt;
				tmp = arr[i - 1];
			}
		}
		
		System.out.println(tmp);		// 빈도가 제일 높은 숫자 중 가장 작은 숫자 출력
	}
}
