package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	
 *	백준 9517번 : 아이 러브 크로아티아
 *
 *	@see https://www.acmicpc.net/problem/9517
 *
 */
public class Boj9517 {
	private static final String SPACE = " ";
	private static final String TRUE = "T";
	
	private static final int TIME_LIMIT = 210;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int[] T = new int[9];				// 경과 시간을 받을 배열
		String[] Z = new String[9];		// 해당 인원의 답변 결과를 받을 배열
		
		int chk = K;
		int res = 0, idx = 0;
		
		for(int i = 1; i < N + 1; i ++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			if(chk > 8){						// 인원이 1~8명 이므로 8이 넘어갈 시 1로 되돌려줌
				chk = 1;
			}
			
			T[chk] = Integer.parseInt(st.nextToken());
			Z[chk] = st.nextToken();
			
			res += T[chk];						// 경과시간을 res 변수에 계속 더해줌
			
			if(res >= TIME_LIMIT){			// res가 TIME_LIMIT(210)보다 크거나 같을 때,
				if(res == TIME_LIMIT){		// 같다면
					idx = chk + 1;				// 현재 인원의 왼쪽에 있는 사람의 번호
				}
				else{								// 크다면
					idx = chk;					// 해당 인원의 번호
				}
				break;
			}
			
			if(TRUE.equals(Z[chk])){		// 만약 올바른 대답을 한 경우 폭탄을 해당 인원의 왼쪽으로 옮겨줌
				chk++;
			}
		}
		br.close();
		
		System.out.println(idx);			// 폭탄을 터트린 사람의 번호를 출력
	}
}
