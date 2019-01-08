package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11943번: 파일 옮기기
 *
 *	@see https://www.acmicpc.net/problem/11943/
 *
 */
public class Boj11943 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int o = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		o += Integer.parseInt(st.nextToken());
		a += Integer.parseInt(st.nextToken());
		
		System.out.println(Math.min(a, o));
	}
}
