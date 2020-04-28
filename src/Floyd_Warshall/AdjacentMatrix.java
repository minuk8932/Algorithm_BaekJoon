package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AdjacentMatrix {				// 시간은 빠르지만 용량을 매우 많이 차지함.
	public static final String SPACE = " ";
	public static final String TAB = "\t";
	public static int[][] matrix = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(),  SPACE);		// 입력 값 : 0 1 2, 0 3 7, 2 0 6, 2 1 3, 3 0 7, 3 2 4
		int nodeCnt = Integer.parseInt(st.nextToken());
		int lineCnt = Integer.parseInt(st.nextToken());
		
		matrix = new int[nodeCnt][lineCnt];
		
		for(int i = 0; i < nodeCnt; i ++){
//			for(int j = 0; j < nodeCnt; j++){
//				if(i != j){
//					matrix[i][j] = Integer.MAX_VALUE;				// 빈 곳에 Integer의 MAX 값 할당.
//				}
//			}
			
			Arrays.fill(matrix[i], Integer.MAX_VALUE);
			// 열마다 바로 넣게 해서 간단화
		}
																			// 입력 값을 통한 인접 행렬 생성
		while(lineCnt-- > 0){													//   0 1 2 3
			st = new StringTokenizer(br.readLine(), SPACE);						// 0   2   7
			int start = Integer.parseInt(st.nextToken());						// 1 
			int end = Integer.parseInt(st.nextToken());							// 2   6 3
			int cost = Integer.parseInt(st.nextToken());						// 3   7   4
																				
			matrix[start][end] = cost;			
		}
		
		br.close();
		
		for(int i = 0; i < nodeCnt; i ++){
			for(int j = 0; j < nodeCnt; j++){
					System.out.print(matrix[i][j] + TAB);
			}
			System.out.println();
		}
		
		
		
	}

}
