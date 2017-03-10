package realization;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5533 {
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[][] cnts = new int[3][101];
		int[][] persons = new int[N][3];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < 3; j++) {
				
				persons[i][j] = Integer.parseInt(st.nextToken());
				cnts[j][persons[i][j]]++;
			}
		}
		
		br.close();
		
		
		for (int i = 0; i < N; i++) {
			int stPnt = persons[i][0];
			int ndPnt = persons[i][1];
			int rdPnt = persons[i][2];
			
			if(cnts[0][stPnt] != 1){
				stPnt = 0;
			}
			
			if(cnts[1][ndPnt] != 1){
				ndPnt = 0;
			}
			
			if(cnts[2][rdPnt] != 1){
				rdPnt = 0;
			}
			
			sb.append(stPnt + ndPnt + rdPnt).append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}

}
