import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj5549 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		
		int jungle = 0, ocean = 0, ice = 0;
		
		char[][] area = new char[M][N];
		
		for(int i = 0; i < M; i++){
			area[i] = br.readLine().toCharArray();
		}
		
		int[][] map = new int[K][K];
		int[][] ans = new int[3][K];
		
		for(int i = 0; i < K; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 0; j < K; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		 
		StringBuilder sb = new StringBuilder();
		
//		for(int i = 0; i < K; i++){
//			ice = 0;
//			jungle = 0;
//			ocean = 0;
//			for(int j = 1; j < K; j = j+2){
//				
//				switch(area[map[i][j - 1]][map[i][j]]){
//					case 'I':
//						
//						ice++;
//						break;
//						
//					case 'J':
//						
//						jungle++;
//						break;
//						
//					case 'O':
//						
//						ocean++;
//						break;
//				}
//			}
//			sb.append(jungle).append(SPACE).append(ocean).append(SPACE).append(ice).append(NEW_LINE);
//		}
		
		
		
	}

}
