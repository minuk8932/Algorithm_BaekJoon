package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14593번: 2017 아주대학교 프로그래밍 경시대회 (Large)
 *
 *	@see https://www.acmicpc.net/problem/14593/
 *
 */
public class Boj14593 {
	private static class Participant implements Comparable<Participant>{
		int idx;
		int score;
		int report;
		int time;
		
		public Participant(int idx, int score, int report, int time) {
			this.idx = idx;
			this.score = score;
			this.report = report;
			this.time = time;
		}

		@Override
		public int compareTo(Participant p) {
			if(this.score > p.score) {
				return -1;
			}
			else if(this.score < p.score) {
				return 1;
			}
			else {
				if(this.report < p.report) {
					return -1;
				}
				else if(this.report > p.report) {
					return 1;
				}
				else {
					if(this.time < p.time) return -1;
					else if(this.time > p.time) return 1;
					else return 0;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Participant> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.offer(new Participant(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(pq.poll().idx + 1);		// 1등
	}
}
