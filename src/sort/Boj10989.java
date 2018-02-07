package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10989 : 수 정렬하기 3
 *
 *	@see https://www.acmicpc.net/problem/10989
 *
 */

public class Boj10989 {
	private static final String NEW_LINE = "\n";
	private static final int MAX = 10_001;
	
	/**
	 * 
	 * 	@param args
	 * 	@throws Exception
	 * 
	 * 이 문제의 포인트 : 제한시간 5초, 메모리 제한 8MB
	 * 입력 될 숫자의 최대 갯수 10_000_000, 입력될 숫자의 범위 1 <= X <= 10_000 : 일반적인 정렬 문제와는 다른 방법으로 생각해야함
	 * 
	 */
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int[] nums = new int[MAX];									// 어떤 숫자가 들어올지 모르기 때문에 범위에 해당되는 크기로 배열 설정

		for(int i = 1 ; i < N + 1; i++){									// 입력되는 숫자에 해당되는 배열 index에 ++ 연산
			nums[Integer.parseInt(br.readLine())]++;
		}
		br.close();
		
		for(int i = 1; i < MAX; i++){
			if(nums[i] != 0){												// i번째 인덱스에 해당하는 배열의 값이 0이 아닐경우 -> 즉, 입력에 포함되어 있다.
				while(nums[i]-- > 0){									// 그 배열의 값 만큼 반복시켜서
					sb.append(i).append(NEW_LINE);				// i번째 인덱스의 수를 1줄씩 출력해 낸다.
				}
			}
		}
		System.out.println(sb.toString());						// 결과 값 출력
	}
}
