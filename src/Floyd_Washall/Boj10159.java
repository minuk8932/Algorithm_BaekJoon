package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10159번: 저울
 *
 *	@see https://www.acmicpc.net/problem/10159/
 *
 */
public class Boj10159 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int LIMIT = 100_000;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] thing = new int[N+1][N+1];		// 물건의 무게 비교 여부를 넣을 배열
		
		for(int i = 0; i < N+1; i++){
			for(int j = 0; j < N+1; j++){
				thing[i][j] = LIMIT;				// 최댓값으로 초기화
			}
		}
		
		while(M-- > 0){
			StringTokenizer st =  new StringTokenizer(br.readLine(), SPACE);
			
			thing[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;	
		}
		
		for(int v = 1; v < N+1; v++){						// Floyd Washall 알고리즘 수행
			for(int s = 1; s < N+1; s++){
				for(int e = 1; e < N+1; e++){
					thing[s][e] = Math.min(thing[s][e], thing[s][v] + thing[v][e]);
				}
			}
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < N+1; i++){			
			for(int j = 1; j < N+1; j++){
				if(i != j && thing[i][j] != LIMIT){		// 양방향으로 값을 입력
					thing[j][i] = thing[i][j];
				}
			}
		}
		
		for(int i = 1; i < N+1; i++){
			int cnt = 0;
			for(int j = 1; j < N+1; j++){
				if(i != j && thing[i][j] == LIMIT){	// 각 번호별로 측정 가능한 무게의 갯수를 구해서
					cnt++;
				}
			}
			sb.append(cnt).append(NEW_LINE);	// 그 값을 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}

}
