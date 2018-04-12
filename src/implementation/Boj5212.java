package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5212번 : 지구온난화
 *
 * @see https://www.acmicpc.net/problem/5212
 *
 */

public class Boj5212 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final char OCEAN = '.';
	private static final char LAND = 'X';
	
	private static final int MAX = 12;
	
	public static void main(String[] args) throws Exception{
		//버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[] tmp = new char[MAX];
		
		char[][] map = new char[MAX][MAX];
		for(int i = 0; i < MAX; i++){					// 맵 전체에 바다로 가득 채움
			Arrays.fill(map[i], OCEAN);
		}
		
		for(int i = 1; i < R + 1; i++){
			tmp = br.readLine().toCharArray();
			for(int j = 1; j < C + 1; j++){				// 맵 위에 입력을 덮어 씌움
				map[i][j] = tmp[j - 1];
			}
		}
		br.close();
		
		boolean[][] isEdited = new boolean[MAX][MAX];
		
		for(int i = 1; i < R + 1; i++){
			for(int j = 1; j < C + 1; j++){
				if(map[i][j] == LAND){					// 해당 인덱스가 땅일 경우
					int isOcean = 0;
					
					// 동서남북 4방향의 값들이 수정되지 않은 바다 일 경우
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
					
					// 4방향중 3개 이상이 원래부터 바다였을 경우
					if(isOcean >= 3){
						isEdited[i][j] = true;	// 수정된것으로 설정하고
						map[i][j] = OCEAN;		// 바다로 값을 변경
					}
				}
			}
		}
		
		int idxIm = Integer.MAX_VALUE - 1, idxIM = 0;
		int idxJm = Integer.MAX_VALUE - 1,  idxJM = 0;
		
		for(int i = 1; i < R + 1; i++){
			for(int j = 1; j < C + 1; j++){
				if(map[i][j] == LAND){					// 줄어든 맵크기를 조정하기 위해 최소 최대 인덱스를 구함
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
				sb.append(map[i][j]);					// 버퍼에 수정된 맵을 저장 후
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값을 한번에 출력
	}
}
