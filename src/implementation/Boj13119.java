package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13119번: Mountains Beyond Mountains
 *
 *	@see https://www.acmicpc.net/problem/13119/
 *
 */
public class Boj13119 {
	private static final char MOUNTAIN = '#';
	private static final char BRIDGE = '-';
	private static final char TURNNEL = '*';
	private static final char PIER = '|';
	private static final char EMPTY = '.';
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int[] height = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < M + 1; i++) {
			height[i] = Integer.parseInt(st.nextToken());	// 산의 높이 저장 
		}
		
		char[][] img = new char[N + 1][M + 1];
		
		for(int col = 1; col < M + 1; col++) {
			int mtImg = height[col], cntX = 0;	// 산의 높이를 각 열마다 꺼내올 변수
			boolean needPier = false;			// 교각이 필요한지 체크
			
			for(int row = N; row > 0; row--) {
				cntX++;						// 고속도로 위치를 찾기위한 변수
				
				if(row == N) {				// 반복문 시작전 교각이 필요한지 체크
					if(mtImg < X) {			// 산의 높이가 고속도로보다 작은 경우
						needPier = true;
					}
				}
				
				if(mtImg > 0){						// 산을 아직 덜 그린 경우
					if(cntX == X) {					// 다리와 겹치는 위치면, 터널을
						img[row][col] = TURNNEL;
					}
					else {							// 겹치지 않는다면 산을 그림
						img[row][col] = MOUNTAIN;
					}
				}
				
				else {								// 산을 다 그린 경우
					if(needPier) {
						if(cntX == X) {				// 다리가 놓일 자리면, 다리를
							img[row][col] = BRIDGE;
						}
						else if(cntX < X) {			// 다리보다 낮은 위치인 경우
							if(col % 3 == 0)			// 이 때 해당 열의 위치가 3의 배수이면, 교각을
								img[row][col] = PIER;
							else						// 그 외, 빈 공간
								img[row][col] = EMPTY;
						}
						else {						// 또는 다리보다 높은 경우 빈공간
							img[row][col] = EMPTY;
						}
					}
					else {							// 교각이 필요없는 경우
						if(cntX == X) {				// 다리의 위치엔 다리를 그리고
							img[row][col] = BRIDGE;
						}
						else {						// 그 외, 빈공간
							img[row][col] = EMPTY;
						}
					}
				}
				
				mtImg--;			// 산의 높이를 하나씩 줄여가며 반복문 계산
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < N + 1; i++) {
			for(int j = 1; j < M + 1; j++) {		// 버퍼에 완성된 그림을 담고
				sb.append(img[i][j]);
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
