package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3060번: 욕심쟁이 돼지
 *
 *	@see https://www.acmicpc.net/problem/3060/
 *
 */
public class Boj3060 {	
	private static final String NEW_LINE = "\n";
	
	private static int[] pigs = null;
	private static int[] curPigs = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int tmpN = N, sum = 0;
			
			pigs = new int[6];			// 전날 돼지들이 먹은 사료의 양
			curPigs = new int[6];		// 오늘 돼지들이 먹을 사료의 양
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < pigs.length; i++) {
				pigs[i] = Integer.parseInt(st.nextToken());	// 첫째날 돼지들이 먹은 양을 담고
				sum += pigs[i];		// 그 양의 총 합을 구함
			}
			
			int days = 1;		// 첫째날이 지나감
			
			if(sum <= tmpN) {				// 만약 첫째날에 무사히 돼지들에게 사료를 나누어 준 경우
				for(int i = 0; ; i++) {
					int idx = i % 6;		// 현재 사료를 줘야할 돼지의 번호
					int left = idx - 1;		// 사료를 받는 돼지 왼쪽의 돼지번호
					int right = idx + 1;	// 사료를 받는 돼지 오른쪽의 돼지번호
					int jump = idx >= 3 ? idx - 3 : idx + 3; // 사료를 받는 돼지 맞은편의 돼지번호
					
					if(left < 0) left = 5;				// 번호가 음수로 넘어가면 5번으로 초기화
					if(right > 5) right = 0;			// 번호가 6위로 넘어갈 경우 0으로 초기화
					if(idx == 0) days++;				// 첫번째 돼지가 사료를 받는 경우에 날짜수 +1
					
					// 현재 돼지가 먹을 사료 = 어제 (이 돼지가 먹은 사료 + 왼쪽 돼지가 먹은 사료 + 오른쪽 돼지가 먹은 사료 + 맞은편 돼지가 먹은 사료)
					curPigs[idx] = pigs[idx] + pigs[left] + pigs[right] + pigs[jump];
					
					tmpN -= curPigs[idx];				// 하루에 주어지는 사료의 양에 당일 돼지들에게 주는 사료를 빼줌
					if(tmpN < 0) break;			// 당일 준 사료에의해 사료가 다떨어지면 반복문 종료
					
					if(idx == 5) {		// 마지막 돼지까지 사료가 완전히 분배 되었다면
						arrInit();		// 당일 나눠준 사료를 메소드를 통해 전날 사료로 바꾸어주고
						tmpN = N;		// 일일 사료량 초기화
					}
				}
			}
			
			sb.append(days).append(NEW_LINE);	// 케이스별 날짜를 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	private static void arrInit() {		// 오늘 6번 돼지까지 나눠준 사료를 다음날로 넘어가면서 전날 돼지들이 먹은 사료로 바꾸어주는 메소드
		for(int i = 0; i < 6; i++) {
			pigs[i] = curPigs[i];
		}
	}
}
