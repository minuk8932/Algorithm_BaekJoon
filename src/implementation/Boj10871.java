package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10871 {
	public static final String SPACE = " ";
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		for(int i = 0 ; i < N; i++){
			int A = Integer.parseInt(st1.nextToken());
			
			if(A < X){
				sb.append(A).append(SPACE);
			}
		}
		System.out.println(sb.toString());
	}

}
