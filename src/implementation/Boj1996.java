package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1996번: 지뢰찾기
 *
 *	@see https://www.acmicpc.net/problem/1996/
 *
 */
public class Boj1996 {
	private static final String NEW_LINE ="\n";
	private static final char DOT = '.';
	private static final char MINE = '*';
	private static final char MANY = 'M';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		
		for(int i = 0; i < N; i++){
			String line = br.readLine();
			for(int j = 0; j < N; j++){
				map[i][j] = line.charAt(j);
			}
		}
		
		char[][] make = new char[N][N];
		
		for(int i = 0; i < N; i++){						// map 배열을 돌면서
			for(int j = 0; j < N; j++){
				if(map[i][j] == DOT){					// 해당 인덱스에 '.'이 저장된 경우
					int sum = 0;
					
					for(final int[] DIRECTION : DIRECTIONS){		// 범위를 벗어나지 않는, 주변 8방향의 인덱스 값을 구해서
						int rowRound = i + DIRECTION[ROW];
						int colRound = j + DIRECTION[COL];
						
						if(rowRound >= 0 && rowRound < N && colRound >= 0 && colRound < N){
							int num = Character.getNumericValue(map[rowRound][colRound]);
							
							if(num >= 0 &&  num <= 9){		// 주변에 존재하는 숫자를 모두 더해 sum 변수에 담고
								sum += num;
							}
						}
					}
					
					char word = (char) (sum + '0');		// 문자형으로 형변환
					
					if(sum > 9){					// 만약 sum의 값이 10이상 이라면 M으로 값을 초기화
						word = MANY;
					}
					
					make[i][j] = word;			// 그외는 형변환 한 숫자를 새로운 make 배열에 담고
				}
				else{									// 만약 .이 아닌 숫자가 존재하는 칸이면 지뢰가 존재하는 것이므로 '*'로 초기화
					make[i][j] = MINE;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){					// 해당 배열을 모양에 맞게 버퍼에 담아줌
				sb.append(make[i][j]);
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
