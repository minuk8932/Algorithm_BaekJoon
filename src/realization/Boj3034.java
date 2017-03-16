package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3034 {
	public static final String IN = "DA";
	public static final String OUT = "NE";
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i ++){
			int x = Integer.parseInt(br.readLine());
			
			if((int) Math.pow(x, 2) <= (int) Math.pow(W, 2) + (int) Math.pow(H, 2)) {
				sb.append(IN).append(NEW_LINE);
			} else {
				sb.append(OUT).append(NEW_LINE);
			}
		}
		System.out.println(sb.toString());
	}

}
