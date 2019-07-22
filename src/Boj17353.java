import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17353 {
	private static long[] tree;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		long[] stars = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			stars[i] = Integer.parseInt(st.nextToken());
		}
		
		int S = init(N, stars);
		int Q = Integer.parseInt(br.readLine());
		
		while(Q-- > 0){
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			if(cmd == 1) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				sectionSum(S + start - 1, S + end - 1, end - start + 1);
			}
			else {
				sb.append(tree[Integer.parseInt(st.nextToken()) + S - 1]).append(NEW_LINE);
			}
			
			for(int i = 0; i < tree.length; i++) {
				System.out.print(tree[i] + " ");
			}
			System.out.println();
		}
		
		System.out.println(sb.toString());
	}
	
	private static int init(int n, long[] arr) {		
		int term = 1;
		while(n > term) term <<= 1;
		
		tree = new long[term * 2];
		int idx = 0;
		for(int i = term; i < term + n; i++) {
			tree[i] = arr[idx++];
		}
		
		return term;
	}
	
	private static void sectionSum(int from, int to, int count) {
		while(from <= to) {
			if(from % 2 == 1) tree[from++] += count;
			if(to % 2 == 0) tree[to--] += count;
			
			from /= 2;
			to /= 2;			
		}
	}
}
