package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4307 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final int INF = 2_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			int max = 0, min = INF;
			int half = l / 2;
			
			for(int i = 0; i < n; i++) {
				int ant = Integer.parseInt(br.readLine());
				int antDiff = ant < half ? ant : l - ant;
				
				if(max < antDiff) max = antDiff;
				if(min > antDiff) min = antDiff;
			}
			
			sb.append(max).append(SPACE).append(l - min).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
