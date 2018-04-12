package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2798 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer rule = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(rule.nextToken());
		int M = Integer.parseInt(rule.nextToken());
		int[] cards = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++){
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		int BlackJack = 0;
		for(int i = 0; i < N; i++){
			for(int j = i+1; j < N; j++){
				for(int k = j+1; k < N; k++){
					int sum = cards[i] + cards[j] + cards[k];
					
					if(sum <= M){
						BlackJack = Math.max(BlackJack, sum);
					}
				}
			}
		}
		System.out.println(BlackJack);
	}

}
