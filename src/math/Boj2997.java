package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2997번 : 네번째 수
 */
public class Boj2997 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < nums.length; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);						// 오름차순 정렬
		
		int[] diff = new int[2];
		for(int i = 0; i < diff.length; i++){		// 각 숫자끼리 차를 구해 배열에 담음
			diff[i] = nums[i + 1] - nums[i];
		}
		
		if(diff[0] == diff[1]){									// 두 값이 같으면, 4번째 수는 해당 등차수열에서 가장 작거나 가장 큰 수
			System.out.println(nums[2] + diff[0]);	// 등차 수열에서 가장 큰 수 출력
		}
		else{
			if(diff[0] > diff[1]){													 
				System.out.println(nums[0] + diff[0] - diff[1]);		// diff[0]이 더 크다면, 4번째 수는 등차수열에서 2번째로 작은 수
			}
			else{
				System.out.println(nums[1] + diff[1] - diff[0]);			// diff[1]이 더 크면, 2번째로 큰 수
			}
		}
	}
}
