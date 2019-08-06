import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17366 {
	private static final String NEW_LINE = "\n";
	private static int[] portal;
	
	private static class Path{
		int from;
		int to;
		
		public Path(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		char[] str = br.readLine().toCharArray();
		makePortal(N, str);
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(interval(N, str, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void makePortal(int n, char[] arr) {
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		portal = new int[n];
		Arrays.fill(portal, -1);
		
		for(int i = 0; i < n; i++) {
			if(arr[i] == '.') continue;
			
			if(arr[i] == '(') {
				stack.push(-i);
			}
			else {
				if(stack.isEmpty()) continue;
				int node = -stack.pop();
				
				portal[i] = node;
				portal[node] = i;
			}
		}
	}
	
	
	private static int interval(int n, char[] arr, int from, int to) {
		
		return 0;
	}
}
