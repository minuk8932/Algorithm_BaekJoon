package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj8979 {
	private static final String SPACE = " ";
	private static final int MEDAL = 4;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] nation = new int[N + 1][MEDAL]; // gold : 1, silver : 2, bronze : 3
		int[] totalRank = new int[N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			int nationCnt = Integer.parseInt(st.nextToken());
			for (int j = 1; j < MEDAL; j++) {
				nation[nationCnt][j] = Integer.parseInt(st.nextToken());
			}
		}


		for (int i = 1; i < N + 1; i++) {				// 나라 별 비교
			for(int j = 1; j < N + 1; j++){
				if(nation[i][1] > nation[j][1]){
					totalRank[i]++;
				}
				else if(nation[i][1] < nation[j][1]){
					totalRank[j]++;
				}
				else{
					if(nation[i][2] > nation[j][2]){
						totalRank[i]++;
					}
					else if(nation[i][2] < nation[j][2]){
						totalRank[j]++;
					}
					else{
						if(nation[i][3] > nation[j][3]){
							totalRank[i]++;
						}
						else if(nation[i][3] < nation[j][3]){
							totalRank[j]++;
						}
					}
				}
			}
		}
		
		int chkRank = totalRank[K];
		int cnt = 1;
		
		for(int i = 1 ; i < N + 1; i++){
			if(chkRank < totalRank[i]){
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
