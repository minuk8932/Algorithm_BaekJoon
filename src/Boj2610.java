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
		int[][] seminar = new int[N + 1][N + 1];
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < N + 1; i++){
			Arrays.fill(seminar[i], MAX);
		}
		
		for(int i = 0; i < M; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			seminar[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		
		int cnt = 0, solo = 0;
		int tmp = 0;
		int[] max = new int[N + 1];
		
		for(int v = 1; v < N + 1; v++){
			for(int s = 1; s < N + 1; s++){
				for(int e = 1; e < N + 1; e++){
					seminar[s][e] = Math.min(seminar[s][e], seminar[s][v] + seminar[v][e]);
					
					if(seminar[s][e] != MAX){
						tmp = Math.max(tmp, seminar[s][e]);
					}
				}
				max[v] = tmp;
			}
		}
		
		// TODO : 최댓값 어떻게 뽑아 낼 것인가?, 출력은 어떻게?
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < N + 1; j++){
				System.out.print(seminar[i][j] + "\t");
			}
			System.out.println();
		}
		
		Arrays.sort(max);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < N + 1; i++){
			if(max[i] > 1 && max[i] != max[i - 1]){
				cnt++;
				sb.append(max[i]).append(NEW_LINE);
			}
		}
		
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
}
