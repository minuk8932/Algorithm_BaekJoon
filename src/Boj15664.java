import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15664 {
	private static StringBuilder sb = new StringBuilder();
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		backTracking(N, M, seq, 0, 0, "");
		System.out.println(sb);
	}
	
	private static void backTracking(int n, int m, int[] arr, int depth, int count, String line) {
		if(depth == m) {
			sb.append(line).append(NEW_LINE);
			return;
		}
		
		if(count == n) return;
		
		backTracking(n, m, arr, depth + 1, count + 1, line + arr[count] + SPACE);
		backTracking(n, m, arr, depth + 1, count, line);
	}
}
