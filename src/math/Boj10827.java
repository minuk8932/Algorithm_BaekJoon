package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Boj10827 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BigDecimal bd = new BigDecimal(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		System.out.println(bd.pow(n).toPlainString());
	}
}
