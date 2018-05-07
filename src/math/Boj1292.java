package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1292번: 쉽게 푸는 문제
 */
public class Boj1292 {
	private static final int INF = 1_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[INF];
		int tmp = 1;
		
		for(int i = 1; tmp < INF; i++){	// 해당 인덱스를 최대 100까지만
			for(int j = 1; j < i + 1; j++){
				if(tmp == INF) break;		// 1000번째가 되면 반복문 종료

				nums[tmp] = i;		// i개 인덱스에 i를 넣어줌
				tmp++;
			}
		}
		
		
		int res = 0;
		
		for(int i = A; i < B + 1; i++){		// A~B 인덱스에 해당하는 값들을 모두 더하고
			res += nums[i];
		}
		
		System.out.println(res);			// 결과값 출력
	}
}
