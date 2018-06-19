package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10157번: 자리배정
 *
 *	@see https://www.acmicpc.net/problem/10157/
 *
 */
public class Boj10157 {
	private static final String SPACE = " ";
	
	private static StringBuilder sb = new StringBuilder();
	private static boolean isOver = false;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		int[][] map = new int[R + 1][C + 1];
		
		if(K <= C * R) {		// 배정 가능한 자릿수 내의 K인 경우
			boolean north = true, east = false, south = false, west = false;
			int val = 1, nCost = 1, eCost = R, sCost = C, wCost = 1;
			
			while(val <= K) {
				if(north) {										// 북쪽으로 움직이는 경우
					for(int i = wCost; i < eCost + 1; i++) {	// 행값 고정 열값은 서쪽과 동쪽 시작값에 의해 결정됨
						if(map[i][nCost] == 0) {
							map[i][nCost] = val;				// 인원 배치마다 val+1
							val++;
						}
					}
					nCost++;									// 북쪽 행 1개를 채웠으므로 +1
					
					north = false;								// 북쪽 닫고 동쪽을 열어줌
					east = true;
				}											// 아래도 북쪽과 같이 지속적으로 수행
				
				if(east) {
					for(int i = nCost; i < sCost + 1; i++) {
						if(map[eCost][i] == 0) {
							map[eCost][i] = val;
							val++;
						}
					}
					eCost--;
					
					east = false;
					south = true;
				}
				
				if(south) {
					for(int i = eCost; i > wCost - 1; i--) {
						if(map[i][sCost] == 0) {
							map[i][sCost] = val;
							val++;
						}
					}
					sCost--;
					
					north = false;
					west = true;
				}
				
				if(west) {
					for(int i = sCost; i > nCost - 1; i--) {
						if(map[wCost][i] == 0) {
							map[wCost][i] = val;
							val++;
						}
					}
					wCost++;
					
					west = false;
					north = true;
				}
			}
			
			sb = new StringBuilder();
			
			for(int i = 1; i < R + 1; i++) {				// K와 같은 값을 가지는 map이 나올경우 버퍼에 행과 열을 역으로 담고
				for(int j = 1; j < C + 1; j++) {
					if(K == map[i][j]) {
						sb.append(j).append(SPACE).append(i);
						isOver = true;
						break;
					}
				}
			}
		}
		
		System.out.println(isOver ? sb.toString() : 0);		// 배정 가능한 번호일 경우 결과값 출력
	}
}
