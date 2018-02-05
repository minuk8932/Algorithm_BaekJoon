package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9205번 : 맥주마시며 걸어가기
 *
 * @see https://www.acmicpc.net/problem/9205
 *
 */
public class Boj9205 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String H = "happy";
	private static final String S = "sad";
	
	private static final int MAX = 102;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(t-- > 0){
			int n = Integer.parseInt(br.readLine()) + 2;
			int[][] fest = new int[MAX][MAX];
			ArrayList<Point> dest = new ArrayList<>();
			
			// 거리에 해당하는 값을 받아 Point 이너 클래스에 담아줌
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
	            dest.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	            	// 배열 내의 값 초기화
	                if (i != j){
	                    fest[i][j] = MAX;
	                }
	            }
			}
			
			for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                if (i == j) {
	                    continue;
	                }
	                
	                // 포인트에 담았던 해당 값들을 가져
	                Point start = dest.get(i);
	                Point finish = dest.get(j);
	 
	                int distance = Math.abs(start.row - finish.row) + Math.abs(start.col - finish.col);
	                
	                // 해당 구간이 맥주를 마시며 이동 가능하면 1 입력
	                if (distance <= 1000) {
	                    fest[i][j] = 1;
	                }
	            }
	        }
			
			// 플로이드 와샬 알고리즘
			for (int v = 0; v < n; ++v) {
		        for (int s = 0; s < n; ++s) {
		            for (int e = 0; e < n; ++e) {
		                    fest[s][e] = Math.min(fest[s][e], fest[s][v] + fest[v][e]);
		            }
		        }
		    }
			
			// 집에서 페스티벌까지 갔을 때 해당 조건내에 포함 되는지 확인
	        if (0 < fest[0][n - 1] && fest[0][n - 1] < MAX) {
	            sb.append(H).append(NEW_LINE);
	        } else {
	          	sb.append(S).append(NEW_LINE);
	        }
		}
		br.close();
		
		// 결과 값 출력
		System.out.println(sb.toString());
	}
	
	private static class Point{
		int row;
		int col;
			public Point(int row, int col){
				this.row = row;
				this.col = col;
			}
	}
}
