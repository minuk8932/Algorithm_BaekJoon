package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9872번: Record Keeping
 *
 *	@see https://www.acmicpc.net/problem/9872/
 *
 */
public class Boj9872 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[][] member = new String[N][3];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 3; j++) {
				member[i][j] = st.nextToken();
			}
			
			Arrays.sort(member[i]);				// 이름 순 정렬
		}
		
		System.out.println(getMost(N, member));
	}
	
	private static int getMost(int n, String[][] arr) {
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			int count = 1;
			
			for(int j = i + 1; j < n; j++) {
				boolean flag = true;
				
				for(int k = 0; k < 3; k++) {
					if(!arr[i][k].equals(arr[j][k])) {		// 서로 다른 팀인 경우
						flag = false;
						break;
					}
				}
				
				if(flag) count++;
			}
			
			max = Math.max(max, count);
		}
		
		return max;
	}
}
