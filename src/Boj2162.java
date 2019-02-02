import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2162 {
	private static StringBuilder sb = new StringBuilder();
	
	private static final String NEW_LINE = "\n";
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] x = new int[N][2];
		int[][] y = new int[N][2];
			
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			x[i][0] = Integer.parseInt(st.nextToken());
			y[i][0] = Integer.parseInt(st.nextToken());
			x[i][1] = Integer.parseInt(st.nextToken());
			y[i][1] = Integer.parseInt(st.nextToken());
		}
			
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {				
				if(getIntersectCount(x[i], y[i])){
					//merge
				}
			}
		}
		
		
		System.out.print(sb);
	}
	
	private static boolean getIntersectCount(int[] x, int[] y) {
		double[] a = {x[1] - x[0], x[3] - x[2]};
		double[] b = {y[1] - y[0], y[3] - y[2]};
		
		if(a[0] * b[1] != a[1] * b[0]) return true;
		else return (x[2] - x[0]) * b[0] == (y[2] - y[0]) * a[0] ? true : false;
	}
}
