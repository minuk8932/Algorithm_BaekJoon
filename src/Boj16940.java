import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16940 {	
	private static int[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] tree = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		int[] count = new int[N + 1];
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			count[to]++;
			tree[from].add(to);
		}
		
		int root = getRoot(count);
		
		int[] sequence = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		
		getParent(N, tree, sequence, root);
		System.out.println(getResult(N, tree, sequence));
	}
	
	private static int getRoot(int[] arr) {		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] == 0) {
				return i;
			}
		}
		
		return -1;
	}
	
	private static void getParent(int n, ArrayList<Integer>[] list, int[] seq, int start) {
		isVisited = new int[n + 1];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		
		isVisited[start] = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: list[current]) {
				if(isVisited[next] != 0) continue;
				isVisited[next] = current;
				
				q.offer(next);
			}
		}
	}
	
	private static int getResult(int n, ArrayList<Integer>[] list, int[] seq) {		
		Queue<Integer> q = new LinkedList<>();
		q.offer(seq[1]);
		
		int index = 1, count = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			int loop = list[current].size();
			
			while(loop-- > 0) {
				index++;
				if(current != isVisited[seq[index]]) return 0;
					
				count++;
				q.offer(seq[index]);
			}
		}
		
		return count != n - 1 ? 0 : 1;
	}
}
