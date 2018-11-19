import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1107 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		System.out.println(bfs(N, M, br.readLine()));
	}
	
	private static int bfs(int n, int m, String wrongNum) {
		StringTokenizer st = new StringTokenizer(wrongNum);
		boolean[] isError = new boolean[10];
		for(int i = 0; i < isError.length; i++) {
			isError[Integer.parseInt(st.nextToken())] = true;
		}
		
		
		
		return 0;
	}
}
