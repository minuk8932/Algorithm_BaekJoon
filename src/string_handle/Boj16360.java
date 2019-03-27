package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16360번: Go Latin
 *
 *	@see https://www.acmicpc.net/problem/16360/
 *
 */
public class Boj16360 {
	private static final String[] FORMAT = {"a as", "i ios", "y ios", "l les",
			"n anes", "ne anes", "o os", "r res", "t tas", "u us", "v ves", "w was"};
	private static final String DEFAULT = "us";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] str = new String[N];
		for(int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}
		
		System.out.println(process(N, str));
	}
	
	private static StringBuilder process(int n, String[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(String str: arr) {
			boolean flag = false;
			
			for(String f: FORMAT) {
				StringTokenizer st = new StringTokenizer(f);
				String post = st.nextToken();
				String change = st.nextToken();
				
				int leng = post.length();
				int loop = leng;
				int strLeng = str.length();
				
				int diff = 0;
				
				while(loop-- > 0) {
					if(post.charAt(leng - diff - 1) != str.charAt(strLeng - diff - 1)) break;		// 접미사가 부분적으로 다른 경우
					diff++;
				}
				
				if(diff == leng) {		// 겹치는 접미사만큼 자르고 뒤에 새로운 접미사를 붙여줌
					flag = true;
					sb.append(str.substring(0, strLeng - leng)).append(change).append(NEW_LINE);
					break;
				}
			}
			
			if(!flag) sb.append(str).append(DEFAULT).append(NEW_LINE);			// 조건에 해당하는 접미사가 없는 경우
		}
		
		return sb;
	}
}
