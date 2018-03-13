import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2610 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int MAX = 101;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] sem = new int[N + 1][N + 1];
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < N + 1; i++){
			Arrays.fill(sem[i], MAX);
		}
		
		for(int i = 0; i < M; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			sem[x][y] = sem[y][x] = 1;
		}
		
		for(int v = 1; v < N + 1; v++){
			for(int s = 1; s < N + 1; s++){
				for(int e = 1; e < N + 1; e ++){
					sem[s][e] = Math.min(sem[s][e], sem[s][v] + sem[v][e]);
				}
			}
		}
		
		int[] score = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < N + 1; j++){
				if(i != j){
					if(sem[i][j] != MAX){
						score[i] = Math.max(score[i], sem[i][j]);
					}
				}
			}
		}
		
		int[] chk = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < N + 1; j++){
				if(sem[i][j] != MAX){
					chk[i] = Math.max(j, chk[i]);
				}
			}
		}
		
		int tmp = 0;
		int cnt = 0;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N + 1; i++){
			int repre = 0;
			
			if(chk[i] == 0){
				sb.append(i).append(NEW_LINE);
				cnt++;
			}
			else{
				for(int j = i; j < N + 1; j++){
					if(chk[i] == chk[j]){
							repre++;
						if(tmp != chk[j] && repre > 1){	
							sb.append(j).append(NEW_LINE);
							
							tmp = chk[i];
							cnt++;
						}
					}
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
}
