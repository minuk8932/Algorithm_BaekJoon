import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1937 {
	private static final String SPACE = " ";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final int MAX = 501;
	private static final int UNVISITED = 0;
	
	private static int n = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int[][] forest = new int[MAX][MAX];
		int[][] isVisited = new int[MAX][MAX];
		
		for(int i = 1; i < n + 1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 1; j < n + 1; j++){
				forest[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		int res = 0;
		
		System.out.println(bfs(forest, isVisited, res));
	}
	private static int bfs(int[][] forest, int[][] isVisited, int res){
		return res;
		
	}
	
}
