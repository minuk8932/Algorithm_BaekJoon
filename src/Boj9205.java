import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9205 {
	public static final String ENOUGH = "happy";
	public static final String LACK = "sad";
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		
		while(T > 0){
		
			int CU = Integer.parseInt(br.readLine());
			int site = CU + 2;
			int[] dist = new int[site];
			int checker = 0;
			
			for(int i = 0; i < dist.length; i++){
				
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");				
				dist[i] = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
				
				if(i > 0){
					if(Math.abs(dist[i] - dist[i-1]) > 1000){
						checker++;
					}
				}
				
			}
			
			
			if(checker == 0){
				sb.append(ENOUGH).append(NEW_LINE);
			}
			
			else{
				sb.append(LACK).append(NEW_LINE);
			}
			
			
				
			T--;
		}
		System.out.println(sb.toString());
	}

}
