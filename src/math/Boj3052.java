package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3052번 : 나머지
 *
 *	@see https://www.acmicpc.net/problem/3052/
 *
 */
public class Boj3052 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int loop = 10, flag = 42;;
		boolean[] arr = new boolean[flag];		// 나머지의 최대 크기
		
		while(loop-- > 0){
			int mod = Integer.parseInt(br.readLine()) % flag;	// 나머지 연산
			
			if(!arr[mod]){				// 나머지에 해당하는 인덱스의 배열 값을 참으로 바꿔줌
				arr[mod] = true;
			}
		}
		
		int res = 0;
		for(int i = 0; i < arr.length; i++){		// 참인 경우 결과값 +1
			if(arr[i]){
				res++;
			}
		}
		
		System.out.println(res);			// 결과 출력
	}
}
