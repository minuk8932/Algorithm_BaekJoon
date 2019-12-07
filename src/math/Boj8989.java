package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *  백준 8989번: 시계
 *  
 *  @see https://www.acmicpc.net/problem/8989/
 *  
 */
public class Boj8989 {
	private static final String NEW_LINE = "\n";
	
	private static class Time implements Comparable<Time>{
		String time;
		
		public Time(String time) {
			this.time = time;
		}
		@Override
		public int compareTo(Time t) {
			double t1 = getAxis(this.time);						// get axis
			double t2 = getAxis(t.time);
			
			if(t1 < t2) {
				return -1;
			}
			else if(t1 > t2) {
				return 1;
			}
			else {
				StringTokenizer st = new StringTokenizer(this.time, ":");
				int a = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
				
				st = new StringTokenizer(t.time, ":");
				int b = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
				
				if(a < b) return -1;				// sort by early time
				else if(a > b) return 1;
				else return 0;
			}
		}
		
		public double getAxis(String t) {
			StringTokenizer st = new StringTokenizer(t, ":");
			double h = Double.parseDouble(st.nextToken()) % 12;
			double m = Double.parseDouble(st.nextToken());
			
			double res = Math.abs(h * 30 + m / 2 - m * 6);
			if(res == 360) return 0;

			return res > 180 ? 180 - (res % 180): res;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Time[] arr = new Time[st.countTokens()];
			for(int i = 0; i < arr.length; i++) {
				arr[i] = new Time(st.nextToken());
			}
			
			Arrays.sort(arr);
			sb.append(arr[2].time).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
