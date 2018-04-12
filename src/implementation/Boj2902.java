package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2902 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		
		while (st.hasMoreTokens()) {	
			
			char word = st.nextToken().charAt(0);
			sb.append(word);			
		}
		System.out.println(sb.toString());
	}
}
