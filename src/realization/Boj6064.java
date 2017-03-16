package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj6064 {
	public static final int NOT_INVOLVED = -1;
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int[] M = new int[T];
		int[] N = new int[T];
		int[] x = new int[T];
		int[] y = new int[T];
		int cntX = 1, cntY = 1, cnt = 1;

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				M[i] = Integer.parseInt(st.nextToken());
				N[i] = Integer.parseInt(st.nextToken());
				x[i]  =Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
				while(true){
					
					if(cntX < M[i] || cntY < N[i]){
						if(cntX >= M[i]){
							cntX = 1;
							cnt++;
						} else if(cntY >= N[i]){
							cntY = 1;
							cnt++;
						} else {
							cntX++;
							cntY++;
							cnt++;
						}
					} else if (cntX == x[i] && cntY == y[i]){
						sb.append(cnt).append(NEW_LINE);
						break;
					} else if (cntX == M[i] && cntY == N[i]){
						sb.append(NOT_INVOLVED).append(NEW_LINE);
						break;
					}
				}
				
		}
		System.out.println(sb.toString());
	}
}
