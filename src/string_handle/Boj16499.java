package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * 	@author minchoba
 *	백준 16499번: 동일한 단어 그룹화하기
 *
 *	@see https://www.acmicpc.net/problem/16499/.
 *
 */
public class Boj16499 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		HashMap<String, Integer> hm = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			hm.put(sorting(br.readLine()), 1);	// 중복 단어 배제
		}
		
		System.out.println(hm.size());			// 그룹 갯수 출력
	}
	
	private static String sorting(String str) {
		char[] tmp = str.toCharArray();
		Arrays.sort(tmp);
		
		String res = "";
		
		for(int i = 0; i < tmp.length; i++) {
			res += tmp[i];
		}
		
		return res;
	}
}
