package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 9339번: 마라토너
 *
 *	@see https://www.acmicpc.net/problem/9339/
 *
 */
public class Boj9339 {
	private static final int LIMIT = 360;
	private static final int RETIRE = 2_000;
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static class Marathon implements Comparable<Marathon>{
		int num;
		int time;
		boolean isStd;
		
		public Marathon(int num, int time, boolean isStd) {
			this.num = num;
			this.time = time;
			this.isStd = isStd;
		}

		@Override
		public int compareTo(Marathon m) {
			return this.time < m.time ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			
			HashSet<Integer> lec = new HashSet<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(K-- > 0) {
				lec.add(Integer.parseInt(st.nextToken()));				// 수강생
			}
			
			PriorityQueue<Marathon> athlete = new PriorityQueue<>();
			
			int N = Integer.parseInt(br.readLine());
			while(N-- > 0) {
				st = new StringTokenizer(br.readLine());
				int no = Integer.parseInt(st.nextToken());
				int hour = Integer.parseInt(st.nextToken());
				int min = Integer.parseInt(st.nextToken());
				
				int timer = 0;
				if(hour == -1 || min == -1) timer = RETIRE;				// 중도 포기자
				else timer = hour * 60 + min;							// 완주자 기록
				
				if(lec.contains(no)) athlete.offer(new Marathon(no, timer, true));
				else athlete.offer(new Marathon(no, timer, false));
			}
			
			sb.append(getList(athlete)).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static String getList(PriorityQueue<Marathon> pq) {
		StringBuilder sb = new StringBuilder();
		int idx = -1, count = 0;
		
		while(!pq.isEmpty()) {
			Marathon current = pq.poll();
			if(current.time > LIMIT) break;			// 시간초과
			
			if(current.isStd) {
				if(idx == -1) idx = current.num;	// 1등
				count++;							// 인증
			}
		}
		
		return sb.append(idx).append(SPACE).append(count).toString();
	}
}
