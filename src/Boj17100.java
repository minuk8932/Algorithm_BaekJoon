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
		
		public Candy(int sugar, int count, int cost) {
			this.sugar = sugar;
			this.count = count;
			this.cost = cost;
		}

		@Override
		public int compareTo(Candy c) {
			if(this.sugar < c.sugar) return -1;
			else if(this.sugar > c.sugar) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Candy[] c = new Candy[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			c[i] = new Candy(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(c);
		System.out.println(getMinCost(N, L, c));
	}
	
	private static StringBuilder getMinCost(int n, int l, Candy[] arr) {
		StringBuilder sb = new StringBuilder();
		
		
		
		return sb;
	}
}
