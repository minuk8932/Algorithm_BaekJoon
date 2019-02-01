package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6487번: 두 직선의 교차 여부
 *
 *	@see https://www.acmicpc.net/problem/6487/
 *
 */
public class Boj6487 {
	private static StringBuilder sb = new StringBuilder();
	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	private static final String POINT = "POINT ";
	private static final String ZERO = "NONE";
	private static final String INFINITY = "LINE";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			int[] x = new int[4];
			int[] y = new int[4];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			getIntersectCount(x, y);
		}
		
		System.out.print(sb);
	}
	
	private static void getIntersectCount(int[] x, int[] y) {
		double[] a = {x[1] - x[0], x[3] - x[2]};		// 기울기
		double[] b = {y[1] - y[0], y[3] - y[2]};		// y 절편
		
		if(a[0] * b[1] != a[1] * b[0]) {
			double point = b[1] * (x[2] - x[0]) - a[1] * (y[2] - y[0]);
			point /= (a[0] * b[1] - a[1] * b[0]);
			
			double resX = a[0] * point + x[0];		// 일치 정점 구하기
			double resY = b[0] * point + y[0];
			
			sb.append(POINT).append(String.format("%.2f", resX)).append(SPACE).append(String.format("%.2f", resY)).append(NEW_LINE);
		}
		else {
			sb.append((x[2] - x[0]) * b[0] == (y[2] - y[0]) * a[0] ? INFINITY : ZERO).append(NEW_LINE);
		}
	}
}
