import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12603번: Store Credit
 *
 *	@see https://www.acmicpc.net/problem/12630/
 *
 */
public class Boj12603 {
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static class Pair{
		int item1;
		int item2;
		
		public Pair(int item1, int item2) {
			this.item1 = item1;
			this.item2 = item2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < N; t++) {
			int C = Integer.parseInt(br.readLine());
			int I = Integer.parseInt(br.readLine());
			
			int[] arr = new int[I];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < I; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Pair result = getResult(C, I, arr);
			sb.append(CASE).append(t + 1).append(COLON).append(result.item1).append(SPACE).append(result.item2).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static Pair getResult(int credits, int items, int[] item) {
		for(int i = 0; i < items; i++) {
			for(int j = i + 1; j < items; j++) {
				if(credits == item[i] + item[j]) return new Pair(i + 1, j + 1);
			}
		}
		
		return null;
	}
}
