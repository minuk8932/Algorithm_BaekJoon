import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2246 {	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] cost = new int[N];
		int[] dist = new int[N];
		int flagC = 0, flagD = 0;
		
		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			dist[i] = Integer.parseInt(st.nextToken());
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				flagC = cost[i];
				flagD = dist[i];
				
				if(i == j){
					continue;
				}
					if(flagD < dist[j] && flagC <= cost[j]
							|| flagC < cost[j] && flagD <= dist[j]){
						break;
					}					
				
				if(j == N-1){
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

}
