import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj17298 {
	private static final String SPACE = " ";
	
	private static class Info{
		int from;
		int to;
		int val;
		
		public Info(int from, int to, int val) {
			this.from = from;
			this.to = to;
			this.val = val;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		int max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			if(A[i] > max) max = A[i];
		}
		
		System.out.println(NGE(N, A, max));
	}
	
	private static String NGE(int n, int[] arr, int m) {
		StringBuilder sb = new StringBuilder();
		ArrayList<Info> list = new ArrayList<>();
		int s = 0;
		int num = arr[s];
		
		for(int e = 1; e < n; e++) {
//			if(arr[e] == m) {
//				list.add(new Info(e, e))
//			}
//			
//			if(arr[e] > num) {
//				list.add(new Info(s, e, arr[e]));
//				s = e;
//				num = arr[e];
//			}
		}
		
		for(Info in: list) {
			for(int i = in.from; i < in.to; i++) {
				sb.append(in.val).append(SPACE);
			}
		}
		
		sb.append(-1);
		return sb.toString();
	}
}
