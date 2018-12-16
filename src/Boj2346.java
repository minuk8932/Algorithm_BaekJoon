import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj2346 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Balloon> deq = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			deq.offer(new Balloon(i, Integer.parseInt(st.nextToken())));
		}
		br.close();
		
		StringBuilder sb = new StringBuilder();
		Balloon start = deq.poll();
		boolean isMinus = start.seq > 0 ? false : true;
		sb.append(start.idx + 1).append(' ');
		
		while(!deq.isEmpty()) {
			int loop = !isMinus ? start.seq - 1: Math.abs(start.seq) - 1;
			
			while(loop-- > 0) {				
				Balloon tmp = null;
				
				if(!isMinus) {
					tmp = deq.pollFirst();
					deq.offerLast(tmp);
				}
				else {
					tmp = deq.pollLast();
					deq.offerFirst(tmp);
				}
			}
			
			start = !isMinus ? deq.pollFirst() : deq.pollLast();
			isMinus = start.seq > 0 ? false : true;
			sb.append(start.idx + 1).append(' ');
		}
		
		System.out.println(sb);
	}
	
	private static class Balloon{
		int idx;
		int seq;
		
		public Balloon(int idx, int seq) {
			this.idx = idx;
			this.seq = seq;
		}
	}
}
