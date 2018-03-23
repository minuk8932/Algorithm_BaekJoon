import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2790 {
	private static final int INF = 300_001;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] race = new int[N];
		int[] first = new int[N];
		int[] second = new int[N];
		
		boolean[] isWinner = new boolean[INF];
		
		for(int i = 0; i < N; i++){
			race[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < N; i++){
			first[i] = N + race[i];
			second[i] = (N - 1) + race[i];
		}
		
		for(int i = 0; i < N; i++){
			for(int j = i + 1; j < N; j++){
				if(first[i] > second[j]){
					isWinner[first[i]] = false;
				}
				else{
					isWinner[first[i]] = true;
				}
			}
		}
		
		int res = 0;
		
		for(int i = 1; i < isWinner.length; i++){
			if(isWinner[i]){
				res++;
			}
		}
		
		
		System.out.println(res);
	}
}
