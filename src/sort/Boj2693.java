package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2693번 : N번째 큰 
 */
public class Boj2693 {
	private static final int INF = 1_001;				// 입력가능한 최대 크기 상수
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int loop = T;
		
		StringBuilder sb = new StringBuilder();
		
		while(loop-- > 0){
			boolean[] nums = new boolean[INF];							// 진리 배열을 입력가능한 최대 수로 크기 설정
			
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for(int i = 0; i < 10; i++){
				nums[Integer.parseInt(st.nextToken())] = true;	// 들어온 수에 해당하는 인덱스를 참으로 변환
			}
			
			int N = 3;
			
			for(int i = INF - 1; i > 0; i--){						// 뒤에서부터 값을 빼 나가면서
				if(nums[i]){											// 값이 참인 것에서 N -1
					N--;
					
					if(N == 0){											// N이 0이되는 순간 : 뒤에서 3번째 큰 수
						sb.append(i).append(NEW_LINE);		// 해당 인덱스를 버퍼에 담아줌
					}
				}
			}
		}
		
		System.out.println(sb.toString());					// 결과값 한번에 출력 
	}
}