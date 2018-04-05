package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2592번 : 대표값
 *
 *	@see https://www.acmicpc.net/problem/2592/
 *
 */
public class Boj2592 {
	private static final int LOOP = 10;
	private static final int INF = 101;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = new int[INF];
		int avg = 0, max = 0;
		
		for(int i = 0; i < LOOP; i++){									// 평균을 구할 변수에 값을 모두 다 합해 넣어주고
			int num = Integer.parseInt(br.readLine());
			avg += num;
			
			nums[num / 10]++;											// 최대 1000까지 10의 배수만 나오므로 배열 크기를 101으로 잡고, 해당 숫자를 10으로 나눈 값을 각 인덱스로 취급하여 값을 더해줌
		}
		
		int idx = 0;
		
		for(int i = 1; i < nums.length; i++){						// 최빈값 구하기 위한 배열의 최댓값을 찾고
			max = Math.max(max, nums[i]);
		}
		for(int i = 1; i < nums.length; i++){						// 최빈값을 가지는 인덱스를 찾아줌
			if(max == nums[i]){
				idx = i;
				break;
			}
		}
		
		System.out.println(avg / LOOP);							// 평균 값 출력
		System.out.println(idx * LOOP);							// 최빈값을 출력 (해당 인덱스 x 10)
	}
}
