package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1008 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		double A = Double.parseDouble(st.nextToken());
		double B = Double.parseDouble(st.nextToken());
		
		double res = A/B;
		
		System.out.println(res);
	}
}
