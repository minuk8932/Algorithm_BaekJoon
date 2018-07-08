import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1215 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int res = n > k ? (n - k) * k : 0;
		int half = k / 2;
		
		for(int i = 1; i < half + 1; i++) {
			if(k % i == 0) continue;
			
			res += k % i;
		}
		
		if(k % 2 == 0) half = (half - 1) * half / 2;
		else half = (half + 1) * half /2;
		
		System.out.println(res + half);
	}
}
