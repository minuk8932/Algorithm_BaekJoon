package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 5052번: 전화번호 목록
 */
public class Boj5052 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			String[] str = new String[n];
            for (int i = 0; i < n; i++) {
                str[i] = br.readLine();
            }
            
            Arrays.sort(str);		// 전화번호 정렬
            
            boolean isValid = true;	// 타당성 체크
            
            for (int i = 1; i < n; i++) {
                if (str[i].length() >= str[i - 1].length()
                        && str[i].contains(str[i - 1])) {		// 현재 번호가 이전 번호보다 길이가 길고, 이전 번호가 현재 번호에 포함된 경우
                    isValid = false;				// 타당치 못함
                    break;
                }
            }
			
			sb.append(isValid ? "YES" : "NO").append(NEW_LINE);		// isValid 값에 따라 버퍼에 값 입력
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
