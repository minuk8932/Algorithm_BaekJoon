package depth_first_search;

/*		이러한 그래프에서 cycle 찾기
 * 
 * 			1 -> 2 
 *			A 	/
 *			\ V
 * 			3 -> 4 -> 5 
 */

public class FindCycle {
	private static int[][] matrix = null;
	private static boolean isFinish;
	
	public static void main(String[] args)throws Exception{
		matrix = new int[6][6];
		// [x][y] x에서 y로 갈 수 있으면 1
		matrix[1][2] = 1;
		matrix[2][3] = 1;
		matrix[3][1] = 1;
		matrix[3][4] = 1;
		matrix[4][5] = 1;
		
		for(int start = 1; start <= 5; start++){ 	// 1번 노드에서 끝번 노드까지 돌릴거임
			boolean[] isVisited = new boolean[6];	// 돌릴때마다 초기화 해줘야 하므로
			
			isFinish = false;						// 이또한 마찬가지로 초기화
			dfs(start, start, isVisited);
		}
	}
	
	private static void dfs(int start, int current, boolean[] isVisited){
		if(isVisited[current] && start == current){		// 다시 자신에게 온것인지 확인하는 구문
														// 맨처음 값도 1, 1로 같으므로 다른데 들려보고 온것인지 확인하기위해 isVisited[current]로 체크
			isFinish = true;
			System.out.println(start+" is cycle");
		}
		
		if(!isVisited[current] && !isFinish){
			isVisited[current] = true;
			
			for(int next = 1; next<= 5; next++){
				if(matrix[current][next] == 1){
					dfs(start, next, isVisited);
				}
			}
			
		}
	}

}
