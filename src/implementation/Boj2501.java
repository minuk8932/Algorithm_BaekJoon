package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2501 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int th = Integer.parseInt(st.nextToken());

		int cnt = 0;
		int[] val = new int[N];
		StringBuilder sb = new StringBuilder();
		for (int i = N; i >= 1; i--) {
			int spare = N % i;
			
			if (spare == 0) {
				cnt++;
				val[cnt] = N / i;
			}
		}
		
		if(cnt < th){
			sb.append(0);
		}
		else{
			sb.append(val[th]);
		}
		
		System.out.println(sb.toString());
	}

}
