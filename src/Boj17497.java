import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj17497 {
	private static final String OPEN = "[";
	private static final String CLOSE = "] ";
	private static final String NEW_LINE = "\n";
	private static final String[] OPS = {" ", "*", "+", "/", "-"};
	
	private static final long INF = 10_000_000_000_00L;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		System.out.println(bfs(N));
	}
	
	private static String bfs(long n) {
		StringBuilder sb = new StringBuilder();
		
		
		return sb.append(-1).toString();
	}
}
