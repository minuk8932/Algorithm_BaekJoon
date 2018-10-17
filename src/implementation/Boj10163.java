package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10163번: 색종이
 *
 *	@see https://www.acmicpc.net/problem/10163/
 *
 */
public class Boj10163 {
	private static final int INF = 102;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[INF][INF];
		
		for(int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startCol = Integer.parseInt(st.nextToken());
			int startRow = Integer.parseInt(st.nextToken());
			int sizeCol = Integer.parseInt(st.nextToken());
			int sizeRow = Integer.parseInt(st.nextToken());
			
			for(int j = startRow; j <  startRow + sizeRow; j++) {
				for(int k = startCol; k < startCol + sizeCol; k++) {		// 색종이 순번에따라 배열 칸을 숫자로 덮어줌
					map[j][k] = i;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N + 1; i++) {
			int res = 0;
			
			for(int j = 0; j < INF; j++) {
				for(int k = 0; k < INF; k++) {		// 각 케이스 별 차지하고있는 넓이를 구하고
					if(map[j][k] == i) res++;
				}
			}
			
			sb.append(res).append(NEW_LINE);		// 그 넓이를 버퍼에 저장
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
