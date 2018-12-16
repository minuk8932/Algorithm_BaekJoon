package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 		- 최소 신장 트리
 * 		1.  간선 최소, min cost
 * 		2. cycle x, 즉 순환이 없다.
 * 		3. 신장 -> 자라난다.
 * 		
 * 		특징
 * 		Node - 1 개 = Edge 개수
 * 		
 * 		사용 알고리즘
 * 		1. 프림
 * 			
 * 			
 * 		2. 크루스칼(더 많이 씀) : 사이클인지 아닌지 판단하는 자료구조(union find), 힙 필요, 시간 복잡도 : VlogV ( v : vertex)
 * 			주어진 간선이 넉넉할 때 더 빠름
 * 
 * 			임의의 정점 1개를 잡는다.
 * 			선택된 정점 1개로부터 갈 수 있는 간선 하나를 선택 (단, 해당 간선은 사이클이 생기지 않는 것을 조건)
 * 			이후 선택된 정점 두개에서 모든 간선을 대상으로 가장 짧은 간선 하나 선택(단, 해당 간선은 사이클이 생기지 않는 것을 조건)
 * 			간선의 개수가 (노드의 개수 - 1) 이 되었을 때 종료
 * 
 * 			s		e		c
 * 			1		2		1
 * 			2		3		5
 * 			3		1		3
 * 							- Queue(heap)에서 저장 순서
 * 									1
 * 									1		2
 * 									1		3		2
 * 
 * 			과정
 * 			1) 우선순위 큐 (min heap) 간선 정보 insert
 * 			2) if(간선 수 != 노드수 - 1)
 * 			3)	큐에서 간선 1개 가져
 * 			4)	if(가져온 간선 !=  cycle) -> Union - Find algorithm
 * 			5) 추가
 * 						
 * 			arr	0	1	2	3	4	
 * 					0	1	2	3
 * 							
 * 				Union-Find																Find(int a)
 * 					union(int a, int b)	a,b 는 노드								if(a == arr[a]){		//조상이 같으냐?
 * 					aP = Find(a);	// 0														return a;
 * 					bP = Find(b);	// 1													}
 * 																									
 * 					if(ap == bp){		// 조상이 같으면								
 * 						return false;														
 * 					}																				
 * 					else{					// 다르면 연결								return arr[a] = Find(arr[a]);	//	압축 코드, 시간 복잡도 : log *N
 * 						arr[b]=a;																														* : 이런식으로 점점 줄어 들더라...
 * 					}																																			어떤 것을 쓸지 모르기 때문에 정확하게 설정 x
 * 
 * 					조상이 같다 = cycle이 생긴다
 * 					size가 커지면 시간복잡도가 커지기 때문에 재귀를 줄이기 위해, 압축을 통해 줄여준다
 * 					- 아래와 같이 2 계층 트리 생성.
 * 									0
 * 								1	2	3
 */

public class Explain {
	private static int[] set;
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		initSet(V);
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			pq.offer(new Edge(start, end, cost));
		}

		br.close();
		
		int totalCost = 0;
		int edgeCnt = 0;

		while (!pq.isEmpty() && edgeCnt < V) {
			Edge edge = pq.poll();
			
			if (union(edge.start, edge.end)){
				edgeCnt++;
				totalCost += edge.cost;
				
				System.out.println(edge.start + " " + edge.end + "에 다리를 놔야 한다.");
			}
		}
		
		System.out.println(totalCost + " " + "원이 필요");
	}

	private static void initSet(final int V) {
		set = new int[V];

		for (int i = 0; i < V; i++) {
			set[i] = i;
		}
	}

	private static boolean union(final int A, final int B) {
		if (find(A) == find(B)) {
			return false;
		}

		set[B] = A;

		return true;
	}

	private static int find(int A) {
		return A == set[A] ? A : (set[A] = find(set[A])); // compress
	}

}
