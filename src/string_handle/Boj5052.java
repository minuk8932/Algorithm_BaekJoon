package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 5052번: 전화번호 목록
 *
 * @see https://www.acmicpc.net/problem/5052/
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
            
            Arrays.sort(str);

            boolean isValid = true;
            
            for (int i = 1; i < n; i++) {
            	boolean flag = false;
            	int len = str[i - 1].length();

                if (str[i].length() >= len) {
                	for(int x = 0; x < len; x++){
                		if(str[i].charAt(x) != str[i - 1].charAt(x)){		// dont use contains,
                			flag = true;
                			break;
						}
					}

                    if(!flag){
                    	isValid = false;
						break;
					}
                }
            }
			
			sb.append(isValid ? "YES" : "NO").append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
