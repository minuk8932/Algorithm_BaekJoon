package line_sweeping;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 13334번: 철로
 *
 *	@see https://www.acmicpc.net/problem/13334/
 *
 */
public class Boj13334 {	
	private static class Rail implements Comparable<Rail>{
		int house;
		int office;
		
		public Rail(int house, int office) {
			this.house = house;
			this.office = office;
		}

		@Override
		public int compareTo(Rail r) {
			if(this.house < r.house) {
				return -1;
			}
			else if(this.house > r.house) {
				return 1;
			}
			else {
				if(this.office < r.office) return -1;
				else if(this.office > r.office) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Rail[] rail = new Rail[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int o = Integer.parseInt(st.nextToken());
			
			rail[i] = new Rail(Math.min(h, o), Math.max(h, o));			// 수직선같이 구간 구성
		}
		
		int d = Integer.parseInt(br.readLine());						// 최소 간격
		System.out.println(getResident(n, d, rail));
	}
	
	private static int getResident(int n, int interval, Rail[] arr) {
		PriorityQueue<Rail> pq = reset(interval, arr);
		
		int count = 0, max = 0;
		while(!pq.isEmpty()) {
			Rail current = pq.poll();
			
			if(current.office != 0) {		// 범위를 벗어났다면
				count--;
				continue;
			}
			
			count++;
			max = Math.max(count, max);
		}
		
		return max;
	}
	
	private static PriorityQueue<Rail> reset(int interval, Rail[] arr) {
		PriorityQueue<Rail> tmp = new PriorityQueue<>();
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].office - arr[i].house <= interval) {			// 최소간격 안에 포함되는 사람인 경우
				tmp.offer(new Rail(arr[i].house, 1));
				tmp.offer(new Rail(arr[i].office - interval, 0));
			}
		}
		
		return tmp;
	}
}
