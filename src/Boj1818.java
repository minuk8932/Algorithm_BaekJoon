import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1818 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] books = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			books[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			
		}
		
		System.out.println(cnt);
	}
}
