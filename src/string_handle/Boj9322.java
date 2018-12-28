package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9322번: 철벽 보안 알고리즘
 *
 *	@see https://www.acmicpc.net/problem/9322/
 *
 */
public class Boj9322 {
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			HashMap<String, Integer> publicKey1  = new HashMap<>();
			String[] publicKey2  = new String[n];
			String[] pw = new String[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				publicKey1.put(st.nextToken(), i);
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				publicKey2[i] = st.nextToken();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				pw[i] = st.nextToken();
			}
			
			sb.append(decoding(publicKey1, publicKey2, pw)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static StringBuilder decoding(HashMap<String, Integer> hm, String[] arr, String[] password) {
		String[] res = new String[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			int idx = hm.get(arr[i]);			// 순서 조정
			res[idx] = password[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < res.length; i++) {		// 암호 해독
			sb.append(res[i]).append(SPACE);
		}
		
		return sb;
	}
}
