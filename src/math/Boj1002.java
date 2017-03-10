package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1002 {
	public static final int ONE_POINT = 1;
	public static final int TWO_POINTS = 2;
	public static final int NO_POINT = 0;
	public static final int UNLIMIT_POINTS = -1;
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		int[] rnd = new int[6];
		int[] rPlus = new int[T];
		int[] rMinus = new int[T];
		int[] dist = new int[T];
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < rnd.length; j++) {
				rnd[j] = Integer.parseInt(st.nextToken());
			}
			dist[i] = (int) Math.pow(rnd[3] - rnd[0],2) + (int) Math.pow(rnd[4] - rnd[1], 2);
			rPlus[i] = (int) Math.pow(rnd[2] + rnd[5], 2);
			rMinus[i] = (int) Math.pow(rnd[5] - rnd[2], 2);
			
		}
		
		for(int i = 0; i < T; i++){
			if(dist[i] < rPlus[i] && dist[i] > rMinus[i]){
				sb.append(TWO_POINTS).append(NEW_LINE);
			
			} else if((dist[i] == rPlus[i] || dist[i] == rMinus[i]) && dist[i] != 0){
				sb.append(ONE_POINT).append(NEW_LINE);
				
			} else if(dist[i] > rPlus[i] || dist[i] < rMinus[i] || 
					(dist[i] == 0 && rMinus[i] != 0)){
				sb.append(NO_POINT).append(NEW_LINE);
				
			} else if (dist[i] == 0 && rMinus[i] == 0){
				sb.append(UNLIMIT_POINTS).append(NEW_LINE);	
			}
		}
		br.close();
		System.out.println(sb.toString());
	}

}
