package next_permutation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10974번: 모든 순열
 *
 *	@see https://www.acmicpc.net/problem/10974/
 *
 */
public class Boj10974 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(nextPermutation(N));		// 다음 순열 메소드를 통한 결과 값 출력
	}
	
	/**
	 * 다음 순열 메소드
	 * 
	 */
	private static String nextPermutation(int size) {
		StringBuilder sb = new StringBuilder();
		
		int[] arr = new int[size];
		int[] nums = new int[size];
		
		for(int i = 0; i < arr.length; i++) {	// 값의 위치 변경을 위해 배열을 두개 사용
			arr[i] = nums[i] = i + 1;
			sb.append(arr[i]).append(SPACE);		// 첫 순열을 버퍼에 저장
		}
		sb.append(NEW_LINE);
		
		while(true) {
			int idx = -1;
			
			for(int i = 0; i < arr.length; i++) {			// 기준이 될 다음 수보다 작은 수의 인덱스 중 가장 큰 인덱스를 저장
				if(i > 0) {
					if(arr[i - 1] < arr[i]) idx = i - 1;
				}
			}
			
			if(idx == -1) break;						// idx == -1 인 경우, 다음 순열이 존재하지 않으므로 반복문 종료
			
			for(int i = arr.length - 1; i > idx; i--) {	// 위치를 바꿀 수를 찾음, idx의 배열 값보다 큰 가장 뒤의 수
				if(arr[idx] < arr[i]) {
					int tmp = arr[idx];
					arr[idx] = nums[idx] = arr[i];		// nums를 통해 arr 값 유지
					arr[i] = nums[i] = tmp;
					
					break;
				}
			}
			
			for(int i = 0; i < idx + 1; i++) {		// idx까지 버퍼에 순서대로 담음
				sb.append(arr[i]).append(SPACE);
			}
			
			for(int i = arr.length - 1; i > idx; i--) {		// 뒤에서부터 idx + 1까지 버퍼에 담고,
				sb.append(arr[i]).append(SPACE);			// nums를 통해 arr의 값을 유지
				
				int tmp = arr[i];
				arr[i] = nums[arr.length - (i - idx)];
				nums[arr.length - (i - idx)] = tmp;
			}
			
			sb.append(NEW_LINE);
		}
		
		return sb.toString();			// 버퍼의 값을 반환
	}
}
