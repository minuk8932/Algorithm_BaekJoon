package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17292번: 바둑이 포커
 *
 *	@see https://www.acmicpc.net/problem/17292/
 *
 */
public class Boj17292 {
	private static String[] seq = new String[15];
	private static final String NEW_LINE = "\n";
	
	private static class Sort implements Comparable<Sort>{
		int idx;
		int val;
		
		public Sort(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}

		@Override
		public int compareTo(Sort s) {
			if(this.val > s.val) return -1;
			else if(this.val < s.val) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), ",");
		
		char[][] card = new char[6][2];
		for(int i = 0; i < 6; i++) {
			String input = st.nextToken();
			card[i][0] = input.charAt(0);
			card[i][1] = input.charAt(1);
		}
		
		makeSequence(card);
		System.out.print(sorting());
	}
	
	private static void makeSequence(char[][] card) {
		StringBuilder str;
		int idx = 0;
		
		for(int i = 0; i < 6; i++) {
			for(int j = i + 1; j < 6; j++) {
				str = new StringBuilder();
				seq[idx++] = str.append(card[i][0]).append(card[i][1]).append(card[j][0]).append(card[j][1]).toString();
			}
		}
	}
	
	private static String sorting() {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Sort> pq = new PriorityQueue<>();
		
		for(int i = 0; i < seq.length; i++) {			
			int[] num = {getReal(seq[i].charAt(0)), getReal(seq[i].charAt(2))};						// 숫자로 변환
			
			int value = Math.abs(num[0] - num[1]) == 1 || Math.abs(num[0] - num[1]) == 14 ? 2: 1;	// 연속된 경우 2
			value *= 10;
			
			value = num[0] == num[1] ? value + 2: value + 1;										// 같을 경우 2
			value *= 10;
			
			value = seq[i].charAt(1) == seq[i].charAt(3) ? value + 2: value + 1;					// 같은 색을 갖는 경우 2
			value *= 100;
			
			int max = Math.max(num[0], num[1]) + 10;
			int min = Math.min(num[0], num[1]) + 10;
			value += max;																			// 자릿수가 다르므로 10을 더해 2자리로 만들어서 자리에 포함
			value *= 100;
			
			value += min;
			value *= 10;
			
			boolean flag = false;
			for(int idx = 0; idx < 4; idx += 2) {
				if(max == getReal(seq[i].charAt(idx)) + 10) {
					if(seq[i].charAt(idx + 1) == 'b') flag = true;
				}
			}
			
			value = flag ? value + 2: value + 1;													// 큰수에 검정색이 붙어있는 경우 2
			pq.offer(new Sort(i, value));
		}
		
		while(!pq.isEmpty()) {
			Sort current = pq.poll();
			sb.append(seq[current.idx]).append(NEW_LINE);
		}
		
		return sb.toString();
	}
	
	private static int getReal(char c) {
		return c > '9' ? (c - 'a' + 10): (c - '0');
	}
}
