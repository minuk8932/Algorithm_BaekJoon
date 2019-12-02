package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 5639번: 이진 검색 트리
 *
 *	@see https://www.acmicpc.net/problem/5639/
 *
 */
public class Boj5639 {
	private static final String NEW_LINE = "\n";
	private static StringBuilder sb = new StringBuilder();

	private static class Node {
		int data;
		Node left;
		Node right;
	    
	    public Node(){
	        this.left = null;
	        this.right = null;
	    }
	    
	    public Node(int data){
	        this.data = data;
	        this.left = null;
	        this.right = null;
	    }
	}
	
	private static class BSTree{
		private Node root = new Node();
	    
	    public Node insertKey(Node root, int x) {
	    	Node p = root;
	    	Node newNode = new Node(x);
	        
	        if(p == null) return newNode;
	        else if(p.data > newNode.data) p.left = insertKey(p.left, x);
	        else if(p.data < newNode.data) p.right = insertKey(p.right, x);
	        else p = root;
	        
	        return p;
	    }
	    
	    public void insertBST(int x){
	        root = insertKey(root, x);
	    }
	    
	    public void postorder(Node root){								// get postorder
	        if(root!=null){
	        	postorder(root.left);
	            postorder(root.right);
	            if(root.data > 0) sb.append(root.data).append(NEW_LINE);
	        }
	    }
	    
	    public void printBST(){
	        postorder(root);
	    }
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = new int[10_000];
		int idx = 0;

		while (true) {
			try {
				input[idx++] = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				break;
			}
		}

		makeTree(input);						// make BST
		System.out.println(sb.toString());
	}

	private static void makeTree(int[] arr) {
		BSTree tree = new BSTree();
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 0) break;
			tree.insertBST(arr[i]);
		}
		
		tree.printBST();						// print
	}
}
