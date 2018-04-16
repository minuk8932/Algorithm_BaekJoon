package depth_first_search;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	스택과 재귀함수를 통한 DFS 구현
 *
 *	출처 - 엔지니어 대한민국
 *	@see https://www.youtube.com/watch?v=_hxFgg7TLZQ
 *	
 */

class Graph{
	class Node{
		int data;								// 해당 노드의 값
		LinkedList<Node> adjacent;		// 인접 노드 연결리스트로 구현
		boolean isVisited;					// 방문 여부 확인
		
		Node (int data){						// 노드 생성자
			this.data = data;								// 데이터 입력
			this.isVisited = false;							// 방문하지 않음
			adjacent = new LinkedList<Node>();		// 연결리스트 초기화
		}
	}
	
	// 그래프 생성
	Node[] nodes;			// 노드 저장 배열
	Graph(int size){
		nodes = new Node[size];		// 노드 갯수를 받아 그 크기만큼 배열 사이즈 정의
		
		for(int i = 0; i < size; i++){	// 데이터와 해당 데이터의 인덱스 번호를 동일하게 입력
			nodes[i] = new Node(i);
		}
	}
	
	void addEdge(int i1, int i2){		// 두 노드의 관계를 지정하는 함수 : 임의의 한 노드의 인접한 노드는 어떤것인지에 대해 정의해줄 메소드
		Node n1 = nodes[i1];
		Node n2 = nodes[i2];
		
		if(!n1.adjacent.contains(n2)){		// 해당 노드(n1)가 나머지 한노드와 인접했는지 안했는지 확인 후
			n1.adjacent.add(n2);				// 둘이 인접하지 않았다면, 양방향 인접 노드로 설정
		}
		
		if(!n2.adjacent.contains(n1)){
			n2.adjacent.add(n1);
		}
	}
	
	void dfs(){						// 인자 없이 호출 될 시 default 인자를 0으로 처리
		dfs(0);
	}
	
	void dfs(int index){		// 노드를 index로 받아 dfs 순회 결과를 출력
		Node root = nodes[index];							// 루트 노드 선언, 첫 회차에 루트노드가 되고 이후론 인접 노드들이 들어간다, 즉 현재노드
		
		Stack<Node> stack = new Stack<Node>();		// 노드를 담아 줄 스택 선언
		stack.push(root);										// 현재 노드를 스택에 담아줌
		root.isVisited = true;									// 스택 방문함으로 표시
		
		while(!stack.isEmpty()){								// 스택에 데이터가 있는 경우에	
			Node r = stack.pop();								// 스택 가장위의 데이터를 가져와 노드 r에 담고
			
			for(Node n : r.adjacent){						// 노드 r에 인접한 노드를 가져와 노드 n에 담고 반복문 실행
				if(!n.isVisited){									// 해당 노드 n들이 아직 스택에 방문하지 않았다면
					n.isVisited = true;							// 방문함으로 바꿔주고
					stack.push(n);								// 노드 n들을 스택에 담아줌
				}
			}
			printPop(r);					// 방문한 노드(스택에서 pop된) 출력
		}
	}
	
	void dfsRecursive(Node r){				// 재귀 호출시 연결리스트가 노드의 주소를 갖기 때문에, 인자는 노드로 받음
		if(r == null){						// 아무것도 받아오지 않았다면, 바로 함수 종료
			return;
		}
		
		r.isVisited = true;				// r이 방문한것으로 처리하고,
		printPop(r);						// 인접노드를 불러오기전, 현재 노드를 출력
		
		for(Node n : r.adjacent){	// 호출되지 않은 인접 노드들을 반복문을 통해 재귀적으로 호출
			if(!n.isVisited){
				dfsRecursive(n);
			}
		}
	}
	
	void dfsRecursive(int index){	// 시작 노드를 다양하게 테스트하기 위한 인자를 index로 받음
		Node r = nodes[index];		// 인덱스에 해당하는 노드를 불러와 재귀적 호출
		dfsRecursive(r);	
	}
	
	void dfsRecursive(){				// 아무것도 받아오지 않고 호출 시 마찬가지로 0부터 시작
		dfsRecursive(0);
	}
	
	void printPop(Node n){					// 팝한 노드 출력 메소드
		System.out.print(n.data + " ");
	}
}

public class GraphSearchByDFS {
	public static void main(String[] args) {
		Graph g = new Graph(9);						// 예제대로 그래프 구성
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(5, 7);
		g.addEdge(5, 6);
		g.addEdge(6, 8);
		
//		g.dfs();						// stack 0부터
//		g.dfs(3);					// stack 3부터
//		g.dfsRecursive();		// recursive 0부터
		g.dfsRecursive(3); 		// recursive 3부터
		
	}
}
