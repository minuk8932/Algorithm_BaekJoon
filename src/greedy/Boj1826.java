package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1826번: 연료 채우기
 *
 *	@see https://www.acmicpc.net/problem/1826/
 *
 */
public class Boj1826 {
	private static class Gas implements Comparable<Gas>{
		int distance;
		int refill;
		
		public Gas(int distance, int refill) {
			this.distance = distance;
			this.refill = refill;
		}

		@Override
		public int compareTo(Gas g) {
			return this.distance < g.distance ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Gas[] pos = new Gas[N];
		StringTokenizer st;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i] = new Gas(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		Gas town = new Gas(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		System.out.println(stopCount(N, pos, town));
	}
	
	private static int stopCount(int n, Gas[] arr, Gas target) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int count = 0, index = 0;
		
		Arrays.sort(arr);

		while(target.refill < target.distance) {						// 누적 연료로 목적지 도착까지
			while(index < n && arr[index].distance <= target.refill) {
				pq.offer(-arr[index++].refill);					// 현 연료로 갈 수 있는 주유소의 연료값 내림차순
			}
			
			if(pq.isEmpty()) break;
			count++;
			target.refill -= pq.poll();				// 연료 누적
		}
		
		return target.refill < target.distance ? -1 : count;
	}
}
