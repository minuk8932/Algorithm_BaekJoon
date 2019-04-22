import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17100 {
	private static final String SPACE = " ";
	
	private static class Candy implements Comparable<Candy>{
		int sugar;
		int count;
		int cost;
		int cover;
		
		public Candy(int sugar, int count, int cost, int cover) {
			this.sugar = sugar;
			this.count = count;
			this.cost = cost;
			this.cover = cover;
		}

		@Override
		public int compareTo(Candy c) {
			if(this.sugar < c.sugar) {
				return -1;
			}
			else if(this.sugar > c.sugar) {
				return 1;
			}
			else {
				if(this.cost < c.cost) return -1;
				else if(this.cost > c.cost) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Candy[] candy = new Candy[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			candy[i] = new Candy(a, m, c, a * m);
		}
		
		Arrays.sort(candy);
		System.out.println(getMinCost(N, L, candy));
	}
	
	private static StringBuilder getMinCost(int n, int l, Candy[] arr) {
		StringBuilder sb = new StringBuilder();
		int[] k = new int[l + 1];
		Arrays.fill(k, -1);		
		
		for(int i = 1; i < l + 1; i++) {
			sb.append(k[i]).append(SPACE);
		}
		
		return sb;
	}
}
