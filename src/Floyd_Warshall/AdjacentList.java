package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AdjacentList {
	public static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int nodeCnt = Integer.parseInt(st.nextToken());
		int lineCnt = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] list = new ArrayList[nodeCnt];
		
		for(int i = 0; i < nodeCnt; i++){
			list[i] = new ArrayList<>();
		}
		
		while(lineCnt-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			int start = Integer.parseInt(st.nextToken());
			int finish = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(finish, cost));			// 해당 start의 배열 위치에 생성자를 통해 (finish, cost) 모두를 넣어 줍니다
		}
		br.close();
		
		for(int start = 0; start < nodeCnt; start++){
			System.out.print(start + " 로 부터 갈 수 있는 정점들과 그 코스트");
			
			for(Node finishNode : list[start]){
				System.out.print("(" + finishNode.idx + " " + finishNode.cost + ")");
			}
			System.out.println();
		}
	}
	
	static class Node{
		public int idx;
		public int cost;
		
		public Node(int idx, int cost){
			this.idx = idx;
			this.cost = cost;
		}
	}

}
