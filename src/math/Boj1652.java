package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 * 백준 1652번: 누울 자리를 찾아라
 * 
 * 	@see https://www.acmicpc.net/problem/1562/
 * 
 */
public class Boj1652 {
	private static final String SPACE = " ";
	
	private static char[][] map = null;
	private static char DOT = '.';
	private static char BLOCK = 'X';
	private static int N = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		for(int i = 0; i < N; i++){
			String line = br.readLine();
			
			for(int j = 0; j < N; j++){
				map[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(rowSearch() + SPACE + colSearch());		// 가로 세로로 누울 자리를 각각 출력
	}
	
	private static int rowSearch(){			// 가로로 누울 자리 탐색
		int lie = 0;
		
		for(int i = 0; i < N; i++){
			int isDot = 0;
			
			for(int j = 0; j < N; j++){
				if(map[i][j] == BLOCK){			// 가로로 진행해가며, 짐이 놓여져있으면 현재까지 누울자리의 크기를 0으로 초기화 후 다음으로 넘어감
					isDot = 0;
					
					continue;
				}
				
				if(map[i][j] == DOT){				// 누울 자리가 1칸 있다면, 공간크기 +1
					isDot++;
				}
				
				if(isDot == 2){		// 만약 누울자리가 2칸으로 적당한 크기일 경우, 누울자리 +1
					lie++;
				}
				
			}
		}
		
		return lie;			// 가로로 누울 수 있는 자리의 수를 반환
	}
	
	private static int colSearch(){			// 세로로 누울 자리 탐색
		int lie = 0;
		
		for(int i = 0; i < N; i++){
			int isDot = 0;
			
			for(int j = 0; j < N; j++){
				if(map[j][i] == BLOCK){			// 세로로 진행해가며, 짐이 놓여져있으면 현재까지 누울자리의 크기를 0으로 초기화 후 다음으로 넘어감
					isDot = 0;
					
					continue;
				}
				
				if(map[j][i] == DOT){				// 누울 자리가 1칸 있다면, 공간크기 +1
					isDot++;
				}
				
				if(isDot == 2){				// 만약 누울자리가 2칸으로 적당한 크기일 경우, 누울자리 +1
					lie++;
				}
				
			}
		}
		
		return lie;				// 세로로 누울 수 있는 자리의 수를 반환
	}
}
