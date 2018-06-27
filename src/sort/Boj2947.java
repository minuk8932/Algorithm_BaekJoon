package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2947번: 나무 조각
 *
 *	@see https://www.acmicpc.net/problem/2947/
 *
 */
public class Boj2947 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] nums = new int[5];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			int isSorting = 0;
			
			for(int i = 0; i < nums.length; i++) {	// 분류가 완료되었는지 체크
				if(nums[i] == i + 1) {
					isSorting++;
				}
			}
			
			if(isSorting == nums.length) break;		// isSorting == 5이면 분류 완료
			
			for(int i = 1; i < nums.length; i++) {
				if(nums[i - 1] > nums[i]) {		// 조각 위치 변경이 필요한 경우
					int tmp = nums[i - 1];
					nums[i - 1] = nums[i];
					nums[i] = tmp;
					
					// 위치 변경 후 결과를 버퍼에 담아둠
					for(int j = 0; j < nums.length; j++) sb.append(nums[j]).append(SPACE);
					sb.append(NEW_LINE);
				}
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
