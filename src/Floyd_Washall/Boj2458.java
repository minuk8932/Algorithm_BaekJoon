package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2458 {
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] smallTall = new int[N+1][N+1];
		int[] chk = new int[N + 1];

		for (int i = 1; i < M+1; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			smallTall[x][y] = 1;
		}

		for (int v = 1; v < N + 1; v++) {
			for (int s = 1; s < N + 1; s++) {
				for (int e = 1; e < N + 1; e++) {
					if(smallTall[s][v] + smallTall[v][e] == 2)
						smallTall[s][e] = 1;
				}
			}
		}
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < N + 1; j++){
				if(i != j && smallTall[i][j] == 0){
					if(smallTall[j][i] == 0){
						chk[i] = j;
					}
				}
			}
		}
		int cnt = 0;
		for(int i = 1; i < N+1; i++){
			if(chk[i] == 0){
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
