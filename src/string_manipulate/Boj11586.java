package string_manipulate;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj11586 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		char[][] mirror = new char[N][N];
		
		for(int row = 0 ; row < N; row++){
				mirror[row] = br.readLine().toCharArray();
		}
		
		int K = Integer.parseInt(br.readLine());
		br.close();
		
		switch(K){
		
		case 1:		// origin
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					sb.append(mirror[i][j]);
				}
				sb.append(NEW_LINE);
			}
			break;
			
		case 2:		// switch left - right
			for(int i = 0; i < N; i++){
				for(int j = N-1; j >= 0; j--){
					sb.append(mirror[i][j]);
				}
				sb.append(NEW_LINE);
			}
			break;
			
		case 3:		// switch top - bottom
			for(int i = N-1; i >= 0; i--){
				for(int j = 0; j < N; j++){
					sb.append(mirror[i][j]);
				}
				sb.append(NEW_LINE);
			}
			break;
		}
		System.out.println(sb.toString());
	}

}
