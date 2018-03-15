package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1331번 : 나이트 투어
 *
 *	@see https://www.acmicpc.net/problem/1331
 *
 */
public class Boj1331 {
	private static final String OK = "Valid";
	private static final String NO = "Invalid";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 36;
		int[][] knight = new int[T][2];
		
		for(int i = 0; i < T; i++){
			String line = br.readLine();
			
			for(int j = 0; j < 2; j++){
				if(j == 0){
					knight[i][j] = (line.charAt(j)) - 'A';
				}
				else{
					knight[i][j] = Character.getNumericValue(line.charAt(j));
				}
			}
		}
		
		boolean[][] isVisited = new boolean[6][6];
		int inVal = 0;
		int dup = 0;
		
		isVisited[knight[0][0]][knight[0][1] - 1] = true;							// 초기위치 방문한것으로 처리
		
		for(int i = 1; i < T; i++){
			if(isVisited[knight[i][0]][knight[i][1] - 1]){							// 2번이상 방문시 dup 변수+1
				dup++;
				continue;
			}
			
			if(!isVisited[knight[i][0]][knight[i][1] - 1]){							// 첫 방문인 경우
				int alpha = Math.abs(knight[i - 1][0] - knight[i][0]);			// 알파벳의 ASCII 값 차이
				int nums = Math.abs(knight[i - 1][1] - knight[i][1]);			// 뒤 숫자의 값 차이
				
				if((alpha != 2 || nums != 1) && (alpha != 1 || nums != 2)){		// 만약 나이트가 이동 할 수 있는 경로가 아닌경우 inVal +1
					inVal++;
				}
				
				if(i == T - 1){																	// 마지막 인덱스에서
					if((Math.abs(knight[0][0] - knight[i][0]) != 2 || Math.abs(knight[0][1] - knight[i][1]) != 1) && 			//나이트의 이동경로를 통해 처음 출발지로 가지 못할 경우 inVal+1
							(Math.abs(knight[0][0] - knight[i][0]) != 1 || Math.abs(knight[0][1] - knight[i][1]) != 2)){
						inVal++;
					}
				}
				
				isVisited[knight[i][0]][knight[i][1] - 1] = true;					// 위에서 모든 처리를 끝낸 후 방문 한 것으로 설정
			}
		}
		
		int cnt = 0;
		
		for(int i = 0; i < 6; i++){															// 만약 방문하지 않은 칸이 1개라도 존재한다면 cnt+1
			for(int j = 0; j < 6; j++){
				if(!isVisited[i][j]){
					cnt++;
				}
			}
		}
		
		System.out.println((cnt > 0 || inVal > 0 || dup > 0)? NO : OK);		// 방문하지 않은칸이 존재하거나, 올바른 경로로 이동하지 않았거나, 한곳을 여러번 방문 한경우 Invalid, 그 외 Valid 출력
	}
}
