package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2783번: 삼각김밥
 *
 *	@see https://www.acmicpc.net/problem/2783/
 *
 */
public class Boj2783 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double X = Double.parseDouble(st.nextToken()) * 100;
		double Y = Double.parseDouble(st.nextToken());
		
		double min = X / Y;
		
		int N = Integer.parseInt(br.readLine());
		double[] rice = new double[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			rice[i] = (Double.parseDouble(st.nextToken()) * 100) / Double.parseDouble(st.nextToken());
			
			if(min > rice[i]) min = rice[i];
		}
		
		min *= 10;
		System.out.printf("%.2f", min);		// 최소가격!
	}
}
