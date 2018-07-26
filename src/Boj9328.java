import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9328 {
	private static int[][][] isVisited = null;
	
	private static final char BLOCK = '*';
	private static final char DOC = '$';
	private static final char KEY = 'a';
	private static final char DOOR_LOCK = 'A';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[R][C];
			isVisited = new int[R][C][1 << 26];		// 메모리 초과
			
			for(int i = 0; i < R; i++) {
				String line  = br.readLine();
				
				for(int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			for(char key: br.readLine().toCharArray()) {
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						isVisited[i][j][key] = key | 1 << key - KEY;
					}
				}
			}
		}
	}
}
