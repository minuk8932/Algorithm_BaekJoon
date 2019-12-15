import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj1036 {
	private static char[][] converted;
	
	private static class Words implements Comparable<Words>{
		int idx;
		char word;
		
		public Words(int idx, char word) {
			this.idx = idx;
			this.word = word;
		}

		@Override
		public int compareTo(Words w) {
			if(this.word < w.word) return -1;
			else if(this.word > w.word) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		converted = new char[N][];
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			converted[i] = br.readLine().toCharArray();
			if(converted[i].length > max) max = converted[i].length;
		}
		
		int K = Integer.parseInt(br.readLine());
		reset(N, K, max);
		
		System.out.println(calculate(N));
	}
	
	private static String calculate(int n) {
		char[] result = new char[converted[0].length];
		for(int i = 0; i < result.length; i++) {
			result[i] = converted[0][i];
		}
		
		int carry = 0;
		
		for(int i = 1; i < n; i++) {
			int rIdx = result.length - 1;
			
			for(int j = converted[i].length - 1; j >= 0; j--) {
				int num1 = charToInt(converted[i][j]);
				int num2 = charToInt(result[rIdx]);
				
//				System.out.print(num1 + ": " + converted[i][j] + " " + num2 + ": " + result[rIdx] + " " + carry + " ");
				int val = num1 + num2 + carry;
				carry = 0;
				
				if(val > 35) {
					carry += (val / 36);
					val %= 36;
				}
				
//				System.out.println(intToChar(val));
				result[rIdx--] = intToChar(val);
			}
			
//			for(int j = 0; j < result.length; j++) {
//				System.out.print(result[j]);
//			}
//			System.out.println(rIdx);
			
			while(carry != 0) {				
				if(rIdx == -1) {					
					char[] tmp = new char[result.length + 1];
					tmp[0] = (char) (carry + '0');
					
					for(int x = 0 ; x < result.length; x++) {
						tmp[x + 1] = result[x];
					}
					
					result = new char[tmp.length];
					for(int x = 0; x < result.length; x++) {
						result[x] = tmp[x];
					}
					
					carry = 0;
				}
				else {
					int val = charToInt(result[rIdx]);
					val += carry;
					carry = 0;
					
					if(val > 35) {
						carry += (val / 36);
						val %= 36;
					}
					
					result[rIdx] = intToChar(val);
					rIdx--;
				}
			}
			
//			for(int j = 0; j < result.length; j++) {
//				System.out.print(result[j]);
//			}
//			System.out.println();
		}
		
		StringBuilder sb = new StringBuilder();
		if(carry != 0) sb.append(carry);
		
		for(int i = 0; i < result.length; i++) {
			sb.append(result[i]);
		}
		
		return sb.toString();
	}
	
	private static void reset(int n, int k, int loop) {			// 여기만 바꾸면됨, 갯수만큼 Z가 변경시킬 알파벳 갯수 k를 지정
		while(k > 0) {
			PriorityQueue<Words> pq = new PriorityQueue<>();
			
			for(int i = 0; i < n; i++) {
				if(converted[i].length >= loop) {
					int idx = converted[i].length - loop;
					pq.offer(new Words(i, converted[i][idx]));
				}
			}
			
			while(!pq.isEmpty() && k > 0) {
				Words current = pq.poll();
				if(current.word == 'Z') continue;
				
				converted[current.idx][converted[current.idx].length - loop] = 'Z';
				k--;
			}
			
			loop--;
		}
	}
	
	private static int charToInt(char c) {
		return c > '9' ? c - 'A' + 10: c - '0';
	}
	
	private static char intToChar(int i) {
		return (char) (i < 10 ? i + '0': 'A' + (i - 10));
	}
}
