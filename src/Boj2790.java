import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2790 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] race = new int[N + 1];
		for(int i = 1; i < N + 1; i++){
			race[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(race);
		int max = 0, cnt = 0;
		
		for(int i = 1; i < N+1; i++){
			System.out.println(race[i]);
		}
		
		for(int i = 1; i < N + 1; i++){
			race[i] += (N + 1) - i;
			if(race[i] > max){
				max = Math.max(race[i], max);
			}
		}
		
		for(int i = 1; i < N + 1; i++){
			if(max == race[i]){
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
