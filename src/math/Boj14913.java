package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14913 {
	private static final String NOPE = "X";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int diff = (k - a);
		int res = diff / d + 1;
		
		System.out.println(diff % d != 0 ? NOPE : res < 1 ? NOPE : res);
	}
}
