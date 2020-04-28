package Floyd_Warshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 	어떤 노드에서 어떤 노드 까지 갈 수 있는가? BFS, DFS를 사용 한다.
 * 	인접 리스트로 풀이
 * 	Virus 복습 필요 
 * 
 */

//public class Boj2606 {
//	public static final String SPACE = " ";
//	
//	private static ArrayList<Integer>[] list;
//	
//	public static void main (String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine());
//		int M = Integer.parseInt(br.readLine());
//		
//		list = new ArrayList[N+1];	// 갈 수 있는 아이들을 담아놓은 배열리스트
//		
//		for(int i = 1; i <= N; i++){
//			list[i] = new ArrayList<>();
//		}
//		while(M-- > 0){
//			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
//			int a = Integer.parseInt(st.nextToken());
//			int b = Integer.parseInt(st.nextToken());
//			
//			list[a].add(b);
//			list[b].add(a);
//		}
//		br.close();
//		
//		boolean[] isVisited = new boolean[N+1];
//		
//		dfs(1, isVisited);
//		
//		int cnt = -1; // 무조건 자기 자신 컴퓨터 1대는 있으니까 제끼고 생각하기 위해 -1 초기화
//		
//		for(final boolean RES : isVisited){
//			if(RES){
//				cnt++;
//			}
//		}
//		System.out.println(cnt);
//	}
//	
//	private static void dfs(int current, boolean[] isVisited){
//		if(!isVisited[current]){	// 나와 연결된 아이를 탐색 해야 한다
//			isVisited[current] = true;		// 방문했으니까 true
//			
//			for(final int NEXT : list[current]){
//				dfs(NEXT, isVisited);
//			}
//		}
//	}
//
//}

public class Boj2606 {
	private static final int INF = Integer.MAX_VALUE / 2;
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int com = Integer.parseInt(br.readLine());
		int time = Integer.parseInt(br.readLine());

		int val = 0;

		int map[][] = new int[com + 1][com + 1];

		for (int i = 0; i < com + 1; i++) {
			for (int j = 0; j < com + 1; j++) {
				map[i][j] = i == j ? 0 : INF;
			}
		}

		while (time-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			map[x][y] = map[y][x] = 1;
		}

		for (int v = 1; v <= com; v++) {
			for (int s = 1; s <= com; s++) {
				for (int e = 1; e <= com; e++) {
					map[s][e] = Math.min(map[s][e], map[s][v] + map[v][e]);
				}
			}
		}

		int cnt = -1;

		for (int e = 0; e <= com; e++) {
			if (map[1][e] < INF) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}
}
