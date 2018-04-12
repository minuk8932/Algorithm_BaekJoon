package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2846번 : 오르막길
 *
 *	@see https://www.acmicpc.net/problem/2846/
 *
 */
public class Boj2846 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		for(int i = 0; i < N; i++){										// 배열에 길의 정보를 담아줌
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int leng = 0, tmp = 0;
		
		for(int i = 1; i < N; i++){			
			if(nums[i] > nums[i - 1]){									// 오르막길이면
				tmp += nums[i] - nums[i - 1];						// 지속적으로 오르막길의 크기를 축적하고
			}
			else{																// 오르막길이 끊기면 데이터를 없애고
				tmp = 0;
			}
			
			leng = Math.max(leng, tmp);							// 연산마다 오르막길의 최대 길이를 leng에 담아줌
		}
		
		System.out.println(leng);									// 결과값 출력
	}
}
