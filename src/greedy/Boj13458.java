package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13458번: 시험감독
 *
 *	@see https://www.acmicpc.net/problem/13458/
 *
 */
public class Boj13458 {	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] nums = new long[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(st.nextToken());	// 시험장마다 응시인원 입력
		}
		
		st = new StringTokenizer(br.readLine());
		Long head = Long.parseLong(st.nextToken());	// 총 감독관의 감시 가능 수
		Long tail = Long.parseLong(st.nextToken());	// 보조 감독관의 감시 가능 수
		
		long cnt = 0, chk = 0;
		
		for(int i = 0; i < N; i++) {
			if(head <= nums[i]) {		// 총 감독관의 감시 가능수 보다 응시 인원수가 이상인 경우
				nums[i] -= head;		// 인원수에서 감시가능 인원을 뺀 값을 배열에 담아줌
			}
			else {				// 총 감독관이 해당 시험장을 혼자 감시 가능한 경우
				nums[i] = 0;	// 해당 배열을 0으로 초기화 하고, chk +1 
				chk++;
			}
			
			cnt++;		// 모든 경우마다 총 감독관이 들어간 횟수 +1
		}
		
		if(chk != N) {						// 만약 한 시험장이라도 총 감독관 혼자 커버가 안된 경우
			for(int i = 0; i < N; i++) {
				if(nums[i] != 0) {
					if(nums[i] % tail == 0) {		// 보조 감독관이 커버하는 경우 중 인원수가 딱 떨어지게 감시가 가능하면
						cnt += (nums[i] / tail);	// 감독관 총 필요수에 몫을 더해주고
					}
					else {
						cnt += (nums[i] / tail + 1);	// 나머지가 남는다면 몫 +1 값을 감독관 총 필요수에 더해줌
					}
				}
			}
		}
		
		System.out.println(cnt);		// 결과값 한번에 출력
	}
}
