package next_permutation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10973번: 이전 순열
 *
 *	@see https://www.acmicpc.net/problem/10973/
 *
 */
public class Boj10973 {
	private static final String SPACE = " ";
	private static int[] arr = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(hasPrevPermutation(N)) {		// 이전 순열 메소드 실행
			for(int i = 0; i < N; i++) sb.append(arr[i]).append(SPACE);		// 메소드가 참인 경우 이전 순열을 버퍼에 담음
		}
		else {
			sb.append(-1);		// 거짓인 경우 버퍼에 -1 저장
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 이전 순열 메소드
	 * 
	 */
	private static boolean hasPrevPermutation(int n) {
		int i = n - 1;
		while(i > 0 && arr[i] >= arr[i - 1]) i--;		// 조건에 맞는 인덱스 중 가장 큰 값을 구함
		
		if(i == 0) return false;		// 조건에 맞는 인덱스가 없는 경우 거짓 반환
		
		int j = n - 1;
		while (arr[i - 1] <= arr[j]) j--;		// 배열 가장 뒤에서 i - 1 인덱스의 값보다 크거나 같은 수 중 제일 처음으로 나오는 수의 인덱스를 저장 
		
		int tmp = arr[j];		// 값 바꾸기 실행
		arr[j] = arr[i - 1];
		arr[i - 1] = tmp;
		
		j = n - 1;
		while(i < j) {		// i + 1부터 j까지 역순 저장
			tmp = arr[j];
			arr[j] = arr[i];
			arr[i] = tmp;
			
			i++;
			j--;
		}
		
		return true;		// 참 반환
	}
}
