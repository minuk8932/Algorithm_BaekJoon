package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2865번: 나는 위대한 슈퍼스타K
 *
 *	@see https://www.acmicpc.net/problem/2865/
 *
 */
public class Boj2865 {
	private static final String DOT = ".";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] attend = new int[N + 1];						// 참가자 점수 입력 배열
		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < N + 1; j++){
				int idx = Integer.parseInt(st.nextToken());
				int score = (int) (Double.parseDouble(st.nextToken()) * 10);	// 참가자의 점수 * 10 하여 정수형으로 저장
				
				if(attend[idx] < score){	// 최댓값으로 배열을 초기화 해줌
					attend[idx] = score;
				}
			}
		}
		
		Arrays.sort(attend);		// 참가자의 점수를 오름차순 정렬
		
		int sum = 0;
		for(int i = N; i > N - K; i--){		// K개의 고득점자의 점수를 더해줌
			sum += attend[i];
		}
		
		String res = String.valueOf(sum);	// 점수를 문자열로 형변환
		int leng = res.length();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < leng + 1; i++){			// 문자열을 하나씩 받아오면서 맨뒤에서 두번째 자리에 소숫점을 더해 버퍼에 담아줌
			if(i == leng){
				sb.append(res.charAt(i - 1));
			}
			else if(i == leng - 1){
				sb.append(DOT);
			}
			else{
				sb.append(res.charAt(i));
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
