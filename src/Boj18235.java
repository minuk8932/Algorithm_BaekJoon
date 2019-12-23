import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj18235 {
	private static int[] visit;
	
	private static class Pair{
		int day;
		int pos;
		
		public Pair(int day, int pos) {
			this.day = day;
			this.pos = pos;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken()) - 1;
		int B = Integer.parseInt(st.nextToken()) - 1;
		
		setFive(N, A);
		System.out.println(bfs(N, B));
	}
	
	private static void setFive(int n, int a) {
		visit = new int[n];
		Queue<Pair> q = new LinkedList<>();
		
		visit[a] = 1;
		q.offer(new Pair(visit[a], a));
		
		while(!q.isEmpty()) {
			Pair current = q.poll();
			
			final int[] DIRECTIONS = {current.day, -current.day};
			for(final int D: DIRECTIONS) {
				int next = current.pos + D;
				
				if(next < 0 || next >= n) continue;
				if(visit[next] != 0) continue;
				visit[next] = visit[current.pos] + 1;
				
				q.offer(new Pair(visit[next], next));
			}
		}
	}
	
	private static int bfs(int n, int b) {
		int[] isMeet = new int[n];
		
		Queue<Pair> q = new LinkedList<>();
		
		isMeet[b] = 1;
		q.offer(new Pair(isMeet[b], b));
		
		while(!q.isEmpty()) {
			Pair current = q.poll();
			
			final int[] DIRECTIONS = {current.day, -current.day};
			for(final int D: DIRECTIONS) {
				int next = current.pos + D;
				
				if(next < 0 || next >= n) continue;
				if(isMeet[next] != 0) continue;
				isMeet[next] = isMeet[current.pos] + 1;
				
				if(isMeet[next] <= visit[next]) return visit[next] - 1;
				
				q.offer(new Pair(isMeet[next], next));
			}
		}
		
		return -1;
	}
}
