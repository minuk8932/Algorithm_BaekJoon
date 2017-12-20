import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2660 {
	private static final String SPACE = " ";
	private static final int STOP = -1;

	private static final int MAX = 100;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] mem = new int[N + 1][N + 1];
		
		for(int i = 1; i < N + 1; i++){
			Arrays.fill(mem[i], MAX);
		}
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(x == STOP || y == STOP){
				break;
			}
			mem[x][y] = 1;
		}
		
		
		for(int v = 1; v < N + 1; v++){
			for(int s = 1; s < N + 1; s++){
				for(int e = 1; e < N + 1; e++){
					mem[s][e] = Math.min(mem[s][e], mem[s][v] + mem[v][e]);
					
//					if(mem[s][e] == mem[s][v] + mem[v][e]){
//						mem[s][e]++;
//					}
				}
			}
		}
		
		int[] min = new int[N + 1];
		Arrays.fill(min, MAX);
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < N + 1; j++){
				if(mem[i][j] != MAX){
					min[i] = Math.max(min[i], mem[i][j]);
				}
			}
		}
		
		
		int[] cand = new int[N + 1];
		int candCnt = 0;
		
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < N + 1; j++){
				if(min[i] == mem[i][j]){
					cand[i] = j;
				}
				System.out.print(mem[i][j] + "\t");
			}
			System.out.println();
		}
		Arrays.sort(cand);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < N + 1; i++){
			if(cand[i] != 0 && cand[i] != cand[i - 1]){
				candCnt++;
				sb.append(cand[i] - 1).append(SPACE);
			}
		}
		
		System.out.println(min + " " + candCnt);

		System.out.println(sb.toString());
	}
}