import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2605 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		int[] man = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			man[i] = i+1; 
		}
		
		for(int i = 0; i < N; i++){
			if(num[i] == 0){
				man[num[i]] = man[i];
				sb.append(man[i]).append(SPACE);
			}
			else {
				man[i] = man[i];
			}
		}
		
		sb.append(NEW_LINE);
		System.out.println(sb.toString());
	}
	
}
