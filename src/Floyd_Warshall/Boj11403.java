package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11403번: 경로찾기
 *
 *	@see https://www.acmicpc.net/problem/11403/
 *
 */
public class Boj11403 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		StringTokenizer st = null;
		for(int i = 0; i < N; i++){											// 경로를 담아줌
			st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int v = 0; v < N; v++){				// Floyd-Washall 알고리즘 수행
			for(int s = 0; s < N; s++){
				if(map[s][v] == 1){
					for(int e = 0; e < N; e++){		// 연결 가능한 경로 추가
						if(map[v][e] == 1){
							map[s][e] = 1;
						}
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++){
			
			for(int j =0; j < N; j++){								// 경로가 추가된 상태를 배열 형식으로 버퍼에 담아줌
				sb.append(map[i][j]).append(SPACE);
			}
			sb.append(NEW_LINE);
		}
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}

}
