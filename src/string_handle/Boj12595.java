package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12596번: Odd Man Out(Small)
 *
 *	@see https://www.acmicpc.net/problem/12596/
 *
 */
public class Boj12595 {
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	private static final String INVITED = "-1";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < N + 1; i++) {
			int G = Integer.parseInt(br.readLine());
			String[] guest = new String[G];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < G; j++) {
				String line = st.nextToken();
				guest[j] = line;			// 게스트를 한명씩 받음
				
				for(int k = 0; k < G; k++) {
					if(j != k && line.equals(guest[k])) {	// 입력받은 게스트 번호가 초대장에 존재하는 경우
						guest[k] = INVITED;			// 두명 모두 초대함으로 체크
						guest[j] = INVITED;
					}
				}
			}
			
			String res = " ";
			for(int j = 0; j < G; j++) {
				if(!INVITED.equals(guest[j])) {		// 초대 받지 못한 게스트가 존재하면 그 게스트의 초대 번호를 결과 변수에 저장
					res = guest[j];
				}
			}
									// 각 케이스 별로 초대받지 않은 자의 번호를 버퍼에 담은 후
			sb.append(CASE).append(i).append(COLON).append(res).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
