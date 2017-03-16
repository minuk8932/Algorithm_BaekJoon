package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10214 {
	public static final int TEST_CASE = 9;
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
        

		StringBuilder sb = new StringBuilder();
		
        for(int i = 0; i < T; i++){
            int Y = 0, K = 0;
	    	for (int j = 0; j < TEST_CASE; j++) {
	    		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		    	Y += Integer.parseInt(st.nextToken());
			    K += Integer.parseInt(st.nextToken());        
            }
			if (Y > K) {
				sb.append("Yonsei");
            } else if (Y < K) {
				sb.append("Korea");
			} else {
				sb.append("Draw");
			}
			sb.append(NEW_LINE);
        }
		
		System.out.println(sb.toString());
	}

}
