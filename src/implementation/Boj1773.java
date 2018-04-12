package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1773 {
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] chk = new int[C+1];
		int[] time = new int[N];
		int res = 0;

		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < N; i++){
			for(int j = 1; j*time[i] <= C; j++){
				chk[time[i]*j] = 1;
			}
		}
		
		for(int i = 0; i < chk.length; i++){
			if(chk[i] == 1){
				res++;
			}
		}
		sb.append(res).append(NEW_LINE);
		System.out.println(sb.toString());
	}

}
