package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10797 {
	public static final int CAR_CNT = 5;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int m = 0;
		int cnt = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < CAR_CNT; i++){
			m = Integer.parseInt(st.nextToken());
			if(m == N){
				cnt++;
			};
		}
		System.out.println(cnt);
	}

}
