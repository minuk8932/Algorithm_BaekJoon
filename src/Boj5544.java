import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj5544 {
	public static final String NEW_LINE = "\n";
	public static final int WIN = 3;
	public static final int DRAW = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int A[] = new int[N + 1];
		int match = (N * (N - 1)) / 2;
		int teamA = 0, teamB = 0, scoreA = 0, scoreB = 0;

		for (int i = 0; i < match; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			teamA = Integer.parseInt(st.nextToken());
			teamB = Integer.parseInt(st.nextToken());
			scoreA = Integer.parseInt(st.nextToken());
			scoreB = Integer.parseInt(st.nextToken());

			if (scoreA > scoreB) {
				A[teamA] += WIN;
			} else if (scoreA < scoreB) {
				A[teamB] += WIN;
			} else {
				A[teamA] += DRAW;
				A[teamB] += DRAW;
			}
		}


		// int[] B = new int[N+1];
		// int[] C = new int[N+1];
		//
		// for(int i = 1; i <= N; i++){
		// B[i] = A[i];
		// }
		// Arrays.sort(A);
		//
		// for(int i = 1; i <= N; i++){
		// for(int j = 1; j <=N; j++){
		// if(A[i] == B[j]){
		// C[i] = N + 1 - i;
		// }
		// }
		// if(i > 1 && A[i] == A[i - 1]){
		// C[i - 1] = C[i];
		// }
		// }
		//
		// for(int i = 1; i <= N; i++){
		// sb.append(C[i]).append(NEW_LINE);
		// }

		System.out.println(sb.toString());
	}

}
