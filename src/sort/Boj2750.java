package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2750번 : 수 정렬하기
 *
 *	@see https://www.acmicpc.net/problem/2750/
 *
 */
public class Boj2750 {
	private static final int INF = 1_001;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int loop = N;
		boolean isZero = false;									// 영이 존재하는 경우 체크해 줄 진리 변수
		
		boolean[][] nums = new boolean[2][INF];
		
		while(loop-- > 0){											// 숫자 갯수 만큼 반복문 실행을 통해 숫자 입력
			int num = Integer.parseInt(br.readLine());
			
			if(num > 0){												// 0보다 큰 경우
				nums[1][num] = true;
			}
			else if(num < 0){										// 0보다 작은 경우
				nums[0][Math.abs(num)] = true;
			}
			else{															// 0인 경우
				isZero = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = INF - 1; i > 0; i--){							// 음수의 경우 먼저 값이 참인 경우에만 해당 인덱스를 뒤에서부터 역순으로 음수화 하여 버퍼에 담음
			if(nums[0][i]){	
				sb.append(-i).append(NEW_LINE);					
			}
		}
			
		if(isZero){														// 0이 존재하는 경우 0을 버퍼에 담음
			sb.append(0).append(NEW_LINE);
		}
		
		for(int i = 1; i < INF; i++){									// 양수의 경우 값이 참인 경우 해당 인덱스를 1부터 차례로 버퍼에 담음
			if(nums[1][i]){					
				sb.append(i).append(NEW_LINE);
			}
		}
		
		
		System.out.println(sb.toString());					// 결과값 한번에 출력
	}
}
