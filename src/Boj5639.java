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
		LinkedList<Integer> input = new LinkedList<>();
		
		String in = "";
		while((in = br.readLine()) != null) {
			input.add(Integer.parseInt(in));
		}
		
		makeTree(input);
		
		System.out.println(getostOrder());
	}
	
	private static void makeTree(LinkedList<Integer> list) {
		for(int i = 0; i < tree.length; i++) {
			tree[i] = new Node(-1, -1, -1);
		}
		
		int root = list.remove();
		int parent = -1;
		
		while(!list.isEmpty()) {
			int node = list.remove();
			
			if(node < root) tree[root] = new Node(parent, node, tree[root].right);
			else tree[root] = new Node(parent, tree[root].left, node);
			
		}
	}
	
	private static StringBuilder getostOrder() {
		StringBuilder sb = new StringBuilder();
		
		
		
		return sb;
	}
}
