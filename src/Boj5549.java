import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5549 {
	private static char[][] map = null;
	
	private static final String SPACE = " ";
	private static final int INF = 1_001;
	private static final char JUNGLE = 'J';
	private static final char OCEAN = 'O';
	private static final char ICE = 'I';
	
	private static Geo[][] g = null;
	private static int N = 0;
	private static int M = 0;
	private static int K = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		map = new char[N + 1][M + 1];
		g = new Geo[INF][INF];
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < INF; i++) {
			for(int j = 0; j < INF; j++) {
				g[i][j] = new Geo(0, 0, 0);
			}
		}
		
		for(int i = 1; i < N + 1; i++){
			String line = br.readLine();
			int cntJ = 0, cntI = 0, cntO = 0;
			
			for(int j = 1; j < M + 1; j++){
				map[i][j] = line.charAt(j - 1);

				if(map[i][j] == JUNGLE) {
					cntJ++;
				}
				
				if(map[i][j] == OCEAN) {
					cntO++;
				}
				
				if(map[i][j] == ICE) {
					cntI++;
				}
			}
			
			initGeoRow(i, cntJ, cntO, cntI);
			initGeoCol(i);
		}
		
		for(int i = 1; i < N + 1; i++) {
			for(int j = 1; j < M + 1; j++) {
				System.out.print(g[i][j].o);
			}
			System.out.println();
		}
		
		
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			sb.append(g[x2][y2].j - g[x1-1][y2].j - g[x2][y1-1].j + g[x1-1][y1-1].j).append(SPACE);
		}
	
		System.out.println(sb.toString());
	}
	
	private static class Geo{
		int j;
		int i;
		int o;
		
		public Geo(int j, int i, int o) {
			this.j = j;
			this.i = i;
			this.o = o;
		}
	}
	
	private static void initGeoRow(int idx, int jun, int oce, int ice) {		// 각 줄마다 존재하는 지형의 갯수를 채워줌
		for(int i = 1; i < M + 1; i++) {
			if(map[idx][i] == JUNGLE) g[idx][i].j = jun;
			if(map[idx][i] == OCEAN) g[idx][i].o = oce;
			if(map[idx][i] == ICE) g[idx][i].i = ice;
		}
	}
	
	private static void initGeoCol(int idx) {
		for(int i = 1; i < M + 1; i++){
            g[idx][i].j += g[idx-1][i].j;
            g[idx][i].o += g[idx-1][i].o;
            g[idx][i].i += g[idx-1][i].i;
        }
	}
}
