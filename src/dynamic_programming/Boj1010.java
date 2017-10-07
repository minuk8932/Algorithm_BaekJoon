package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1010 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int NONE = 0;
	private static final int ONLY = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int westS = Integer.parseInt(st.nextToken());
			int eastS = Integer.parseInt(st.nextToken());

			double res = 1;
			int tmp = westS;
			
			if((westS == 0 && eastS == 0)
					|| (westS == 1 && eastS == 0)
					|| (westS == 0 && eastS == 1)){
				sb.append(NONE).append(NEW_LINE);
			}
			
			if(westS == 1 && eastS == 1){
				sb.append(ONLY).append(NEW_LINE);
			}
			
			if(westS >= 2 || eastS >= 2){
				for(int i = eastS; i > eastS - westS; i--){					
					res *= (double) i;
					
					if(i % tmp == 0){
						res /= (double) tmp;
						tmp--;
					}
				}
				
				
				if(tmp != 1){
					for(int i = tmp; i > 0; i--){
						res /= (double) i;
					}
				}
				
				sb.append((int) res).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());	
	}

}
