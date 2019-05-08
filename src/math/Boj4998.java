package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4998번: 저금
 *
 *	@see https://www.acmicpc.net/problem/4998/
 *
 */
public class Boj4998 {	
	private static final String NEW_LINE = "\n";
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			double N = 0;
			double B = 0;
			double M = 0;
			
			try {
				String line = br.readLine();
				StringTokenizer st = new StringTokenizer(line);
				N = Double.parseDouble(st.nextToken());
				B = Double.parseDouble(st.nextToken());
				M = Double.parseDouble(st.nextToken());
			}
			catch(Exception e) {
				break;
			}
			
			sb.append(getYear(N, B, M)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int getYear(double source, double bonus, double target) {
		int year = 0;
		
		while(source < target) {					// 연 이율에 따른 총 저축액
			source += (source * bonus / 100.0);
			year++;
		}
		
		return year;
	}
}
