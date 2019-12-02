import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17833 {
	private static class Model{
		int height;
		int time;
		int exit1;
		int exit2;
		
		public Model(int height, int time, int exit1, int exit2) {
			this.height = height;
			this.time = time;
			this.exit1 = exit1;
			this.exit2 = exit2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		Model[] campus = new Model[M];
		
		for(int i = 0; i < M ;i++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			int E1 = Integer.parseInt(st.nextToken());
			int E2 = Integer.parseInt(st.nextToken());
			
			campus[i] = new Model(H, T, E1, E2);
		}
		
		System.out.println(construction(N, R, D, M, campus));
	}
	
	private static int construction(int n, int r, int d, int m, Model[] arr) {
		
		
		return 0;
	}
}
