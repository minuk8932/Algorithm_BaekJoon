package string_handle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17219번: 비밀번호 찾기
 *
 *	@see https://www.acmicpc.net/problem/17219/
 *
 */
public class Boj17219 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, String> hm = new HashMap<>();
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			hm.put(st.nextToken(), st.nextToken());
		}
		
		while(M-- > 0) {
			bw.write(hm.get(br.readLine()));
			bw.write(NEW_LINE);
		}
		
		bw.flush();
	}
}
