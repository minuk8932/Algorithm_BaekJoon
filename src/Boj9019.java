import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9019 {
	private static final String[] DSLR = {"D", "S", "L", "R"};
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			String[] str = new String[4];
			
			sb.append(bfs(A, B, str)).append(NEW_LINE);
		}
	}
	
	private static String bfs(int num, int fin, String[] s) {
		
		Queue<Search> q = new LinkedList<>();
		q.offer(new Search(num, ""));
		
		while(!q.isEmpty()) {
			Search current = q.poll();
			
			
		}
		
		return null;
	}
	
	private static class Search{
		int num;
		String oper;
		
		public Search(int num, String oper) {
			this.num = num;
			this.oper = oper;
		}
	}
}
