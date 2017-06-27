package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj6359 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int loop = T;
		
		while(T > 0){
			int N = Integer.parseInt(br.readLine());
			boolean[] rooms = new boolean[N+1];
			
			for(int i = 1; i < (N+1); i++){
				for(int j = 1; i*j < (N+1); j++){
					if(!rooms[i*j]){
						rooms[i*j] = true;
					}
					else{
						rooms[i*j] = false;
					}
				}
			}
			
			int cnt = 0;
			for(int i = 1; i < (N+1); i++){
				if(rooms[i]){
					cnt++;
				}
			}
			sb.append(cnt).append(NEW_LINE);
			
			T--;
		}
		System.out.println(sb.toString());
	}

}
