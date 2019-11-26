package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 * 	백준 18120번: 대농부 김상혁
 * 
 * 	@see https://www.acmicpc.net/problem/18120/
 *	
 */
public class Boj18120 {	
	private static class Crops implements Comparable<Crops>{
		double d;
		int w;
		
		public Crops(double d, int w) {
			this.d = d;
			this.w = w;
		}

		@Override
		public int compareTo(Crops c) {
			if(this.d < c.d) return -1;
			else if(this.d > c.d) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		
		Crops[] crop = new Crops[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			crop[i] = new Crops(getRadian(x, y), w);
		}
		
		Arrays.sort(crop);
		System.out.println(cultivation(N, A, crop));
	}
	
	private static double cultivation(int n, int a, Crops[] arr) {		
		double profit = 0;
		double b = 0, c = 0;
			
		for(int i = 0; i < n; i++) {							// make quadratic formula
			b += arr[i].w;
			c += arr[i].w * arr[i].d;
			
			double x = b / (a * 2);
			
			double max = -a * x * x + b * x - c;				// get value
			if(max > profit) profit = max;						// is max radian?
		}
		
		return profit;
	}
	
	private static double getRadian(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}
}
