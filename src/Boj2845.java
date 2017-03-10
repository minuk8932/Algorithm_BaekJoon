import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2845 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int L = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int total = L * P;
		
		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < L; i++){
			int attend = Integer.parseInt(st1.nextToken());
			int diff = Math.abs(attend - total);
			
			if(total > attend){
				sb.append(-diff);
			}
			else{
				sb.append(diff);
			}
			sb.append(SPACE);
		}
		System.out.println(sb.toString());
	}

}
