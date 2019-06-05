import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Boj5639 {
	private static final String NEW_LINE = "\n";
	private static Node[] tree = new Node[1_000_001];
	
	private static class Node{
		int parent;
		int left;
		int right;
		
		public Node(int parent, int left, int right) {
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = new int[10_000];
		int idx = 0;
		
		while(true) {
			try {
				input[idx++] = Integer.parseInt(br.readLine());
			}
			catch(Exception e) {
				break;
			}
		}
		
		makeTree(input);
		
		System.out.println(getPostOrder());
	}
	
	private static void makeTree(int[] arr) {
		for(int i = 0; i < tree.length; i++) {
			tree[i] = new Node(-1, -1, -1);
		}
		
		int root = arr[0];
		int parent = -1;
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] == 0) break;
			
			int node = arr[i];
			
			if(node < root) {
				tree[root].left = node;
				parent = root;
				root = node;
				
				tree[root].parent = parent;
			}
			else {
				if(tree[root].parent > node && node > root) {
					tree[root].right = node;
				}
			}
		}
		
		for(int i = 0; i < tree.length; i++) {
			if(tree[i].left == -1 && tree[i].right == -1 && tree[i].parent == -1) continue;
			System.out.println(tree[i].parent);
			System.out.println(tree[i].left + " " + i + " " + tree[i].right);
		}
	}
	
	private static StringBuilder getPostOrder() {
		StringBuilder sb = new StringBuilder();
		
		
		
		return sb;
	}
}
