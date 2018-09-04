package tree_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1991번: 트리 순회
 *
 *	@see https://www.acmicpc.net/problem/1991/
 *
 */
public class Boj1991 {
	private static Node[] tree = new Node[27];
	private static final String NEW_LINE = "\n";
	
	private static final char EMPTY = '.';
	private static final char I_TO_C = 'A';
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < tree.length; i++) {		// 트리 배열 초기화
			tree[i] = new Node(-1, -1);
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			char parent = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			if(left != EMPTY) tree[parent - I_TO_C].left = (left - I_TO_C);
			if(right != EMPTY) tree[parent - I_TO_C].right = (right - I_TO_C);
		}
		
		preOrder(0);			// 전위 순회 메소드
		sb.append(NEW_LINE);
		
		inOrder(0);				// 중위 순회 메소드
		sb.append(NEW_LINE);
		
		postOrder(0);			// 후위 순회 메소드
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 노드 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Node {
		int left;
		int right;
		
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
				
	}
	
	/**
	 * 전위 순회 메소드
	 * 
	 */
	private static void preOrder(int start) {
		sb.append((char) (start + I_TO_C));			// 가장 먼저 나오는 노드를 버퍼에 저장
		
		if(tree[start].left != -1) preOrder(tree[start].left);		// 좌측 노드
		if(tree[start].right != -1) preOrder(tree[start].right);	// 우측 노드
	}
	
	/**
	 * 중위 순회 메소드
	 * 
	 */
	private static void inOrder(int start) {
		if(tree[start].left != -1) inOrder(tree[start].left);		// 좌측 노드
		sb.append((char) (start + I_TO_C));						// 좌측 순회 후 끝자락 부터 입력
		if(tree[start].right != -1) inOrder(tree[start].right);		// 우측 노드
	}
	
	/**
	 * 후위 순회 메소드
	 * 
	 */
	private static void postOrder(int start) {
		if(tree[start].left != -1) postOrder(tree[start].left);		// 좌측 노드
		if(tree[start].right != -1) postOrder(tree[start].right);	// 우측 노드
		
		sb.append((char) (start + I_TO_C));			// 좌측 우측 모두 순회 후 차례로 버퍼에 저장
	}
}
