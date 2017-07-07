package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5544 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] teams = new int[N+1];
		int[] score = new int[N+1];
		int match = (N * (N - 1)) / 2;
		StringTokenizer st; 
		
		while(match-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			teams[A] = Integer.parseInt(st.nextToken());
			teams[B] = Integer.parseInt(st.nextToken());
			
			if(teams[A] > teams[B]){
				score[A] += 3;
			}
			else if(teams[B] > teams[A]){
				score[B] += 3;
			}
			else{
				score[A]++;
				score[B]++;
			}
			
		}
		
		int[] rank = new int[N+1];
		
		for(int i = 1; i < N+1; i ++){
			for(int j = 1; j < N+1; j++){
				if(score[i] < score[j]){
					rank[i]++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N+1; i++){
			sb.append(rank[i]+1).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}

}
