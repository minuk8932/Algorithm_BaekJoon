package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5218 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N =  Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(N-- > 0){
			
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			String before = st.nextToken();
			String after = st.nextToken();
			
			int len = before.length();
			
			char[] w1 = new char[len];
			char[] w2 = new char[len];
			
			w1 = before.toCharArray();
			w2= after.toCharArray();
			
			sb.append("Distances:").append(SPACE);
			
			for(int i = 0; i < len; i++){
				
				if(w1[i] <= w2[i]){
					sb.append(w2[i] - w1[i]).append(SPACE);
					
				}
				
				else{
					sb.append(26 + (w2[i] - w1[i])).append(SPACE);
					
				}
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}

}
