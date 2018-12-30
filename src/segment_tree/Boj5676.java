package segment_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5676번: 음주 코딩
 *
 *	@see https://www.acmicpc.net/problem/5676/
 *
 */
public class Boj5676 {
	private static final char CHANGE = 'C';
	private static final char MULTI = 'P';
	private static final char PLUS = '+';
	private static final char MINUS = '-';
	private static final char ZERO = '0';
	private static final char NEW_LINE ='\n';
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line = "";
		
		while((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int S = 1;
			while(S < N) S <<= 1;
			
			int[] seg = new int[S * 2];
			Arrays.fill(seg, 1);
			
			st = new StringTokenizer(br.readLine());
			for(int i = S; i < S + N; i++) {
				int num = Integer.parseInt(st.nextToken());
				seg[i] = getValue(num);
			}
			
			seg = init(seg, S);
			
			while(K-- > 0) {
				st = new StringTokenizer(br.readLine());
				char cmd = st.nextToken().charAt(0);
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				
				switch(cmd) {
				case CHANGE:
					seg = getChangeArr(seg, num1 + S - 1, num2);
					break;
				
				case MULTI:
					int res = getMultiple(seg, num1 + S - 1, num2 + S - 1);
					sb.append(res == 0 ? ZERO : res > 0 ? PLUS : MINUS);
					break;
				}
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int[] init(int[] arr, int s) {
		for(int i = arr.length - 1; i > 0; i -= 2) {
			int num = getValue(arr[i]) * getValue(arr[i - 1]);
			arr[i / 2] = num;
		}
		
		return arr;		
	}
	
	private static int modifyArray(int mod, int idx, int[] arr) {
		int next = idx + (-2 * mod + 1);
		return getValue(arr[idx]) * getValue(arr[next]);
	}
	
	private static int[] getChangeArr(int[] arr, int idx, int num) {
		arr[idx] = getValue(num);
		
		while(idx > 0) {
			arr[idx / 2] = modifyArray(idx % 2, idx, arr);
			idx /= 2;
		}
		
		return arr;
	}

	private static int getMultiple(int[] arr, int from, int to) {
		int total = 1;
		
		while(from <= to) {
			if(from % 2 == 1) {
				int tmp = getValue(total) * getValue(arr[from]);
				total = tmp;
				from++;
			}
			if(to % 2 == 0) {
				int tmp = getValue(total) * getValue(arr[to]);
				total = tmp;
				to--;
			}
			
			from /= 2;
			to /= 2;
		}
		
		return total;
	}
	
	private static int getValue(int a) {
		if(a > 0) return 1;
		else if(a < 0) return -1;
		else return 0;
	}
}
