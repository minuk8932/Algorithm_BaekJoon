import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj12865 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Knapsack[] knap = new Knapsack[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			knap[i] = new Knapsack(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(knap);
		
		
	}
	
	private static class Knapsack implements Comparable<Knapsack>{
		int w;
		int v;
		
		public Knapsack (int w, int v) {
			this.w = w;
			this.v = v;
		}

		@Override
		public int compareTo(Knapsack k) {
			if(this.w < k.w) {
				return -1;
			}
			else if(this.w > k.w) {
				return 1;
			}
			else {
				if(this.v > k.v) {
					return -1;
				}
				else if(this.v < k.v) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
	}
}
