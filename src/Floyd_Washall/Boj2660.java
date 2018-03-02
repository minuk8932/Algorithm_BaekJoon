package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2660번 : 회장뽑기
 *
 *	@see https://www.acmicpc.net/problem/2660
 *
 */
public class Boj2660 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final int STOP = -1;

	private static final int MAX = 100;

	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] mem = new int[N + 1][N + 1];
		
		for(int i = 1; i < N + 1; i++){																		// 멤버들의 점수 배열을 최대값으로 모두 채움
			Arrays.fill(mem[i], MAX);
		}
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(x == STOP && y == STOP){																	// 루프문 정지
				break;
			}
			mem[x][y] = mem[y][x] = 1;																	// 서로 친구인 경우 양방향으로 1씩 넣어줌
		}
		
		for(int v = 1; v < N + 1; v++){																	// floyd washall 알고리즘 실행
			for(int s = 1; s < N + 1; s++){
				for(int e = 1; e < N + 1; e++){					
					mem[s][e] = Math.min(mem[s][e], mem[s][v] + mem[v][e]);			// 조건에 따라 값을 더해주거나 친구이면서 친구의 친구인 경우 : 1로 바꿔주기 위함
				}
			}
		}
		
		int[] point = new int[N + 1];
		int candiScore = Integer.MAX_VALUE, cnt = 0;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < N + 1; j++){
				if(i != j){
					point[i] = Math.max(point[i], mem[i][j]);										// 자기자신을 제외한 인원들과의 관계 점수 중 큰 수를 점수 배열에 할당
				}
			}
			candiScore = Math.min(point[i], candiScore);										// 점수 배열 내에서 가장 작은 값(회장 후보)을 저장
		}
		sb.append(candiScore).append(SPACE);													// 회장 후보의 점수를 버퍼에 저장
		
		for(int i = 1; i < N + 1; i++){
			if(point[i] == candiScore){																	// 회장 후보 점수에 해당하는 후보의 수
				cnt++;
			}
		}
		sb.append(cnt).append(NEW_LINE);															// 회장 후보의 수 버퍼에 저장
		
		for(int i = 1; i < N + 1; i++){
			if(point[i] == candiScore){																	// 회장 후보인 인원의 번호를 오름차순으로 버퍼에 저장
				sb.append(i).append(SPACE);
			}
		}
		
		System.out.println(sb.toString().trim());													// 결과값 한번에 출력
	}
}