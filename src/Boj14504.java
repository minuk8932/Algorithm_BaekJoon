import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14504 {
	private static final String NEW_LINE = "\n";
	
	private static final int SIZE = 100_002;
	private static final int INF = 1_000_000_001;
	
	private static int[] sNums = null;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sNums = new int[SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			sNums[i] = INF;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++){
			sNums[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		
		while(M-- > 0){
			st = new StringTokenizer(br.readLine());
			
			int query = Integer.parseInt(st.nextToken());
			
			if(query == 1){
				int res = compare(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()));			
				
				sb.append(res).append(NEW_LINE);
				continue;
			}
			
			if(query == 2){				
				swap(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Sort implements Comparable<Sort> {
		int num;
		
		public Sort(int num) {
			this.num = num;
		}

		@Override
		public int compareTo(Sort s) {
			return this.num < s.num ? -1 : 1;
		}
	}
	
	private static int compare(int from, int to, int element) {
		int count = 0;
		
		
//		if(sort[from].num > element) {
//			count = to - from + 1;
//		}
//		else if(sort[from].num < element) {
//			if(sort[to].num <= element) {
//				count = 0;
//			}
//			else {
//				
//				
//				
//			}
//		}
//		else {
//			count = to - from;
//		}
		
		return count;
	}
	
	private static void swap(int idx, int sub) {
		sNums[idx] = sub;
	}
}
