import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14846 {
	private static StringTokenizer st = null;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 11;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N * N + 1];
		
		for(int i = 1; i < N + 1; i ++){
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < N + 1; j++){
				nums[(i - 1) * N + j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		Point[] pos = new Point[Q + 1];
		
		for(int i = 1; i < Q + 1; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			pos[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		StringBuilder sb  = new StringBuilder();
		
		for(int i = 1; i < Q + 1; i++){
			int start = N * (pos[i].xFrom - 1) + pos[i].yFrom;
			int end = N * (pos[i].xTo - 1) + pos[i].yTo;
			
			boolean[] chk = new boolean[INF];
			int cnt = 0;
			
			for(int j = start; j < end + 1; j++){
				if(!chk[nums[j]]){
					chk[nums[j]] = true;
					cnt++;
				}
			}
			
			sb.append(cnt).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Point {
		int xFrom;
		int yFrom;
		int xTo;
		int yTo;
		
		public Point(int xFrom, int yFrom, int xTo, int yTo){
			this.xFrom = xFrom;
			this.yFrom = yFrom;
			this.xTo = xTo;
			this.yTo = yTo;
		}
	}
}
