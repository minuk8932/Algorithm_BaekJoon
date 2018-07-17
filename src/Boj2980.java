import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2980 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Sign[] s = new Sign[L + 1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s[Integer.parseInt(st.nextToken())] = new Sign(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		int timer = 0;
		
		for(int i = 0; i < L + 1; i++) {
			
		}
	}
	
	private static class Sign{
		int r;
		int g;
		
		public Sign(int r, int g) {
			this.r = r;
			this.g = g;
		}
	}
}
