package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13904번: 과제
 *
 *	@see https://www.acmicpc.net/problem/13904/
 *
 */
public class Boj13904 {
	
	private static class Pair implements Comparable<Pair>{
		int day;
		int score;
		
		public Pair(int day, int score) {
			this.day = day;
			this.score = score;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.day < p.day) return -1;
			else if(this.day > p.day) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Pair[] sub = new Pair[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sub[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getMax(N, sub));
	}
	
	private static int getMax(int n, Pair[] sub) {
		int max = 0;
		Arrays.sort(sub);		// 빠른 날짜 순 정렬
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			pq.offer(sub[i].score);		// 해당 날짜의 과제 점수 저장 (제외 시킬 과제 설정)
			max += sub[i].score;		// 일단 과제 점수는 모두 더해줌
			
			if(pq.size() > sub[i].day) max -= pq.poll();		// 큐의 크기: 과제 기한
		}
		
		return max;
	}
}
