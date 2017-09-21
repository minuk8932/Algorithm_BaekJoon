package heap;

/*
 * 	- Binary Tree (B - Tree)
 *	code를 이용한 생성
 *	1. Class Node
 */

public class MakeBinaryTreebyClass {
	public static void main(String[] args) throws Exception{
		Node n3 = new Node(null, null, 3);
		Node n2 = new Node(null, null, 2);
		Node n1 = new Node(n2, n3, 1);
		
		/*
		 *  search : pre post in order -> concentrate root
		 *  
		 *  - Stack
		 *  orders
		 *  pre : Root - L - R
		 *  in : L - Root - R
		 *  post : L - R - Root
		 *  
		 *  - Queue
		 *  level order : 레벨별 탐색 1 - 2 - 3 - 4 ...
		 */
		
		
		
	}
	
	private static class Node{
		public int val;
		public Node left;
		public Node right;
		
		public Node(Node left, Node right, int val){
			this.val = val;
			this.left = left;
			this.right = right;
		}
		
		public void preOrder(Node n){
			if(n != null){
				System.out.println(n.val);
				preOrder(n.left);		// preOrder(n2);
				preOrder(n.right);		// preOrder(n3);
			}
		}
		
		public void inOrder(Node n){
			if(n != null){
				inOrder(n.left);
				System.out.println(n.val);
				inOrder(n.right);
			}
		}
		
		public void postOrder(Node n){
			if(n != null){
				postOrder(n.left);
				postOrder(n.right);
				System.out.println(n.val);
			}
		}
	}

}
