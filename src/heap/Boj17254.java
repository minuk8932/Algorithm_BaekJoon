package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17254번: 키보드 이벤트
 *
 *	@see https://www.acmicpc.net/problem/17254/
 *
 */
public class Boj17254 {
	
	private static class Process implements Comparable<Process>{
		int num;
		int sec;
		char key;
		
		public Process(int num, int sec, char key) {
			this.num = num;
			this.sec = sec;
			this.key = key;
		}

		@Override
		public int compareTo(Process p) {
			if(this.sec < p.sec) {
				return -1;
			}
			else if(this.sec > p.sec) {
				return 1;
			}
			else {
				if(this.num < p.num) return -1;
				else if(this.num > p.num) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Process> keyboard = new PriorityQueue<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			char word = st.nextToken().charAt(0);
		
			keyboard.offer(new Process(number, time, word));
		}
		
		System.out.println(getWord(keyboard));
	}
	
	private static StringBuilder getWord(PriorityQueue<Process> pq) {
		StringBuilder sb = new StringBuilder();
		
		while(!pq.isEmpty()) {			// 키보드 우선순위에 따라 값을 뽑아줌
			sb.append(pq.poll().key);
		}
		
		return sb;
	}
}
