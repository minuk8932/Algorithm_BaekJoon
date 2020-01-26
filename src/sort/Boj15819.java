package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 15819번: 너의 핸들은
 *
 *	@see https://www.acmicpc.net/problem/15819/
 *
 */
public class Boj15819 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int I = Integer.parseInt(st.nextToken());
		
		String[] name = new String[N];
		for(int i = 0; i < N; i++) {
			name[i] = br.readLine();
		}
		
		Arrays.sort(name);
		System.out.println(name[I - 1]);
	}
}
