package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1940번: 주몽
 *
 *	@see https://www.acmicpc.net/problem/1940/
 *
 */
public class Boj1940 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];								// 갑옷 재료를 나타내는 숫자를 담을 배열
		boolean[] isVisited = new boolean[N];					// 해당 재료가 이미 쓰인 재료인지 확인
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		
		for(int i = 0; i < N; i++){
			if(isVisited[i]) continue;			// 이미 사용된 재료라면 다음 재료로 넘어감
			
			for(int j = i + 1; j < N; j++){
				if(M - nums[i] == nums[j]){	// (제작할 갑옷의 재료합 - 임의의 재료 값) == (임의의 재료를 제외한 나머지 중 하나) 일 경우
					isVisited[i] = true;		// 제작한 재료로 표시 한 후
					res++;				// 제작 갑옷 +1
					
					break;				// 다음 i번째 재료로 바로 넘어감
				}
			}
		}
		
		System.out.println(res);		// 총 제작된 갑옷의 수를 출력
	}
}
