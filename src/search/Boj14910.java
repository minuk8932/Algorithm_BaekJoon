package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14910번: 오르막
 *
 *	@see https://www.acmicpc.net/problem/14910/
 *
 */
public class Boj14910 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = -1_000_001;
		boolean isASC = true;
		
		while(st.hasMoreTokens()) {
			int tmp = Integer.parseInt(st.nextToken());
			
			if(tmp >= num) {
				num = tmp;
			}
			else {				// 비내림 차순이 아닌 경우
				isASC = false;
				break;
			}
		}
		
		System.out.println(isASC ? "Good" : "Bad");
	}
}
