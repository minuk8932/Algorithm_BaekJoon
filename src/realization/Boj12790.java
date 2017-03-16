package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj12790 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		while(N > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int HP = 0, MP = 0, ATK = 0, DEF = 0;
			
			for(int i = 0; i < 2; i++){
				HP += Integer.parseInt(st.nextToken());
				MP += Integer.parseInt(st.nextToken());
				ATK += Integer.parseInt(st.nextToken());
				DEF += Integer.parseInt(st.nextToken());
				
				if(HP < 1){
					HP = 1;
				}
				
				if(MP < 1){
					MP = 1;
				}
				
				if(ATK < 0){
					ATK = 0;
				}
			}
			
			int Total = HP + (5 * MP) + (2 * ATK) + (2 * DEF);
			sb.append(Total).append(NEW_LINE);
			
			N--;
		}
		System.out.println(sb.toString());
	}

}
