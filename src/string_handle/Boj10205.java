package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10205번: 헤라클레스와 히드라
 *
 *	@see https://www.acmicpc.net/problem/10205/
 *
 */
public class Boj10205 {
	private static final char[] CUT = {'b', 'c'};
	private static final String ANS = "Data Set ";
	private static final String COLON = ":";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		int loop = 1;
		
		while(K-- > 0){
			int h = Integer.parseInt(br.readLine());		// 머리의 갯수 
			String head = br.readLine();				// 머리 갯수 방식
			int leng = head.length();
			
			
			for(int i = 0; i < leng; i++){
				if(h == 0){							// 제거 할 머리가 없다면 종료
					break;
				}
				
				if(head.charAt(i) == CUT[0]){	// 머리자르고 불로지짐
					h--;
				}
				
				if(head.charAt(i) == CUT[1]){	// 머리 자르기만함
					h++;
				}
			}
			
			
			sb.append(ANS).append(loop).append(COLON).append(NEW_LINE);		// 결과를 버퍼에 담음
			sb.append(h).append(NEW_LINE).append(NEW_LINE);
			loop++;
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
