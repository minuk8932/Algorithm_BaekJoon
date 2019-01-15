package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10823번: 더하기 2
 *
 *	@see https://www.acmicpc.net/problem/10823/
 *
 */
public class Boj10823 {
	private static final String COMMA = ",";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = "";
		
		while((line = br.readLine()) != null) {					// try ~ catch 없어도 됨
			try {
				if(line == null || "".equals(line)) break;
			}
			catch(Exception e) {
				break;
			}
			
			sb.append(line);
		}
		
		long res = 0;
		StringTokenizer st = new StringTokenizer(sb.toString(), COMMA);
		
		while(st.hasMoreTokens()) {						// 연결된 입력 문자열에서 숫자들의 합
			res += Long.parseLong(st.nextToken());
		}
		
		System.out.println(res);
	}
}
