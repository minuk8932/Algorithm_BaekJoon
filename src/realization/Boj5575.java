package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5575 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[][] hour = new int[3][6];
		int[] time = new int[3];
		int res = 0;

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 6; j++) {
				hour[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < 3; i++){
			time[i] = (hour[i][3] - hour[i][0])*3600 + (hour[i][4] - hour[i][1])*60 + (hour[i][5] - hour[i][2]);
			sb.append(time[i] / 3600).append(SPACE).append(time[i] % 3600 / 60).append(SPACE)
						.append(time[i] % 3600 % 60).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
		
	}

}
