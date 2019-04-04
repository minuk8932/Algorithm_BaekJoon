package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2529번: 부등호
 *
 *	@see https://www.acmicpc.net/problem/2529/
 *
 */
public class Boj2529 {
	private static boolean[] visit;
	private static LinkedList<Long> list = new LinkedList<>();
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		
		char[] sign = new char[k];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			sign[i] = st.nextToken().charAt(0);
		}
		
		int size = k + 1;
	
		for(int i = 0; i < 10; i++) {
			visit = new boolean[10];
			backTracking(size, i, i, 0);			// 가능한 순열 모두 생성
		}
		
		System.out.println(getNumber(k, sign));
	}
	
	private static void backTracking(int size, int current, long value, int depth) {
		if(visit[current]) return;
		visit[current] = true;
		
		if(size - 1 == depth) {
			list.add(value);
			return;
		}
		
		for(int next = 0; next < 10; next++) {
			if(visit[next]) continue;

			backTracking(size, next, value * 10 + next, depth + 1);
			visit[next] = false;
		}
	}
	
	private static StringBuilder getNumber(int k, char[] s) {
		StringBuilder sb = new StringBuilder();
		
		int limit = (int) Math.pow(10, k);
		long max = 0, min = Long.MAX_VALUE;
		
		while(!list.isEmpty()) {				// 순열 중에
			long num = list.removeFirst();
			
			String value = num < limit ? "0" + num: num + "";
			int leng = value.length();
			
			char head = value.charAt(0);
			char tail = ' ';
			
			boolean flag = true;
			
			for(int i = 1; i < leng; i++) {
				tail = value.charAt(i);
				
				if(s[i - 1] == '>') {
					if(head > tail) {
						head = tail;
					}
					else {
						flag = false;
						break;
					}
				}
				else {
					if(head < tail) {
						head = tail;
					}
					else {
						flag = false;
						break;
					}
				}
			}
			
			if(flag) {								// 입력을 만족하는 순열이 있으면 값 갱신
				long res = Long.parseLong(value);
				min = Math.min(res, min);
				max = Math.max(res, max);
			}
		}
		
		sb.append(max < limit ? "0" + max: max).append(NEW_LINE);
		sb.append(min < limit ? "0" + min: min);
		
		return sb;
	}
}
