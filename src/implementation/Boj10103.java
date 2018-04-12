package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10103 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		int A = 100, B =100;
		
		StringBuilder sb = new StringBuilder();		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int scoreA = Integer.parseInt(st.nextToken());
			int scoreB = Integer.parseInt(st.nextToken());
			
			if(scoreA > scoreB){
				B -= scoreA;
			}
			else if(scoreB > scoreA){
				A -= scoreB;
			}
		}
		sb.append(A).append("\n").append(B);
		System.out.println(sb.toString());
	}

}
