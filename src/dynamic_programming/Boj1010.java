package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	 백준 1010번 : 다리놓기
 *
 *	@see https://www.acmicpc.net/problem/1010/
 *
 */
public class Boj1010 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int NONE = 0;
	private static final int ONLY = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int westS = Integer.parseInt(st.nextToken());
			int eastS = Integer.parseInt(st.nextToken());

			double res = 1;
			int tmp = westS;
			
			if((westS == 0 && eastS == 0)
					|| (westS == 1 && eastS == 0)
					|| (westS == 0 && eastS == 1)){			// 다리가 아예 필요 없는경우
				sb.append(NONE).append(NEW_LINE);
			}
			
			if(westS == 1 && eastS == 1){					// 다리가 한개만 필요한 경우
				sb.append(ONLY).append(NEW_LINE);
			}
			
			if(westS >= 2 || eastS >= 2){						// 그 외의 경우, 양쪽 다리갯수에 의해 결정됨
				for(int i = eastS; i > eastS - westS; i--){	// 다리가 겹치는 경우는 없으므로 조건에 따른 반복문 작성
					res *= (double) i;
					
					if(i % tmp == 0){
						res /= (double) tmp;
						tmp--;
					}
				}
				
				
				if(tmp != 1){
					for(int i = tmp; i > 0; i--){
						res /= (double) i;
					}
				}
				
				sb.append((int) res).append(NEW_LINE);		// 테스트케이스별 결과값을 버퍼에 담음
			}
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}

}
