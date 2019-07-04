package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17289번: 에니그마
 *
 *	@see https://www.acmicpc.net/problem/17289/
 *
 */
public class Boj17289 {
	private static char[] KEY = {'C', 'H', 'I', 'C', 'K', 'E', 'N', 'S'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] pw = br.readLine().toCharArray();
		
		System.out.println(getPlainText(pw));
	}
	
	private static String getPlainText(char[] p) {
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		
		for(int i = 0; i < p.length; i++) {
			char target = p[i];
			
			for(int k = 0; k < KEY.length; k++) {									// KEY만큼 돌려가면서 암호 해독
				target = (char) (((target - 'A' + KEY[k] - 'A') % 26) + 'A');
			}
			
			KEY[idx]++;
			if(KEY[idx] == 26) {			// 첫 키가 한바퀴를 다돌면 다음키로 넘어감
				KEY[idx] = 'A';
				idx++;
			}
			
			sb.append(target);
		}
		
		return sb.toString();
	}
}
