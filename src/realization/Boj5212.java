package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj5212 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final char OCEAN = '.';
	private static final char LAND = 'X';
	
	private static final int MAX = 12;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[] tmp = new char[MAX];
		
		char[][] map = new char[MAX][MAX];
		for(int i = 0; i < MAX; i++){
			Arrays.fill(map[i], OCEAN);
		}
		
		for(int i = 1; i < R + 1; i++){
			tmp = br.readLine().toCharArray();
			for(int j = 1; j < C + 1; j++){
				map[i][j] = tmp[j - 1];
			}
		}
		br.close();
		
		boolean[][] isEdited = new boolean[MAX][MAX];
		
		for(int i = 1; i < R + 1; i++){
			for(int j = 1; j < C + 1; j++){
				if(map[i][j] == LAND){
					int isOcean = 0;
					
					if(!isEdited[i - 1][j] && map[i - 1][j] == OCEAN){
						isOcean++;
					}
					if(!isEdited[i + 1][j] && map[i + 1][j] == OCEAN){
						isOcean++;
					}
					if(!isEdited[i][j - 1] && map[i][j - 1] == OCEAN){
						isOcean++;
					}
					if(!isEdited[i][j + 1] && map[i][j + 1] == OCEAN){
						isOcean++;
					}
					
					if(isOcean >= 3){
						isEdited[i][j] = true;
						map[i][j] = OCEAN;
					}
				}
			}
		}
		
		int idxIm = Integer.MAX_VALUE - 1, idxIM = 0;
		int idxJm = Integer.MAX_VALUE - 1,  idxJM = 0;
		
		for(int i = 1; i < R + 1; i++){
			for(int j = 1; j < C + 1; j++){
				if(map[i][j] == LAND){
					idxIm = Math.min(idxIm, i);
					idxJm = Math.min(idxJm, j);
					idxIM = Math.max(idxIM, i);
					idxJM = Math.max(idxJM, j);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = idxIm; i < idxIM + 1; i++){
			for(int j = idxJm; j < idxJM + 1; j++){
				sb.append(map[i][j]);
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
