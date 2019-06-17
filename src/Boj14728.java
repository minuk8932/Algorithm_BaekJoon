import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj14728 {
	
	private static class Test implements Comparable<Test>{
		int time;
		int score;
		
		public Test(int time, int score) {
			this.time = time;
			this.score = score;
		}

		@Override
		public int compareTo(Test t) {
			return (double) this.score / (double) this.time > (double) t.score / (double) t.time ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Test> t = new PriorityQueue<>();
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			t.offer(new Test(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(getOptimalScore(T, t));
	}
	
	private static int getOptimalScore(int t, PriorityQueue<Test> pq) {
		int result = 0;
		
		while(!pq.isEmpty()) {
			Test current = pq.poll();
			
			if(current.time <= t) {
				result += current.score;
				
			}
			else {
				
			}
		}
		
		return result;
	}
}
