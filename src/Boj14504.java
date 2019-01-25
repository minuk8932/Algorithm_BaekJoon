import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14504 {
	private static final String NEW_LINE = "\n";
	private static final int INF = 1_000_000_001;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int S = 1;
		while(S < N) S <<= 1;
		
		int[] seg = new int[S * 2];
		Arrays.fill(seg, 0);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = S; i < S + N; i++){
			seg[i] = Integer.parseInt(st.nextToken());
		}
		
		seg = init(seg);
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		
		while(M-- > 0){
			st = new StringTokenizer(br.readLine());
			
			int query = Integer.parseInt(st.nextToken());
			
			if(query == 1) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				
				sb.append(getBiggerThan(seg, from + S - 1, to + S - 1, value)).append(NEW_LINE);
			}
			else {
				int idx = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				
				seg = changeValue(seg, idx + S - 1, value);
			}
		}
		
		System.out.println(sb);
	}
	
	private static int[] init(int[] arr) {
		for(int i = arr.length - 1; i > 0; i -= 2) {
			arr[i / 2] = Math.max(arr[i], arr[i - 1]);
		}
		
		return arr;
	}
	
	private static int getBiggerThan(int[] tree, int from, int to, int val) {
		int count = 0;
		int level = 0;
		
		while(from < to) {
			if(from % 2 == 1) {
				count = tree[from] > val ? count + 1: count;
				from++;
			}
			
			if(to % 2 == 0) {
				count = tree[to] > val ? count + 1: count;
				to--;
			}
			
			from /= 2;
			to /= 2;
			level++;
		}
		
		// 어떤 처리를 해줘야 제대로 셀 수 있는가...
		
		
		return count;
	}
	
	private static int[] changeValue(int[] tree, int idx, int val) {
		tree[idx] = val;
		
		while(idx > 0) {
			if(idx % 2 == 0) tree[idx / 2] = Math.max(tree[idx], tree[idx + 1]);
			else tree[idx / 2] = Math.max(tree[idx], tree[idx - 1]);
			
			idx /= 2;
		}
		
		return tree;
	}
}
