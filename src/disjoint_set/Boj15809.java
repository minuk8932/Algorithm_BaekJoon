package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15809번: 전국 시대
 *
 *	@see https://www.acmicpc.net/problem/15809/
 *
 */
public class Boj15809 {
	private static int[] parent;
	private static int[] size;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		for(int i = 0; i < N; i++) {
			size[i] = Integer.parseInt(br.readLine());		// 나라 규모
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			merge(x, y, cmd);
		}
		
		System.out.println(getRemainder(N));
	}
	
	private static void init(int n) {
		parent = new int[n];
		size = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y, int cmd) {
		x = find(x);
		y = find(y);
		
		if(size[x] < size[y]) {
			parent[x] = y;
			
			if(cmd == 1) size[y] += size[x];		// 동맹
			else size[y] -= size[x];				// 전쟁
			size[x] = 0;
		}
		else {
			parent[y] = x;
			
			if(cmd == 1) size[x] += size[y];
			else size[x] -= size[y];
			size[y] = 0;
		}
	}
	
	private static StringBuilder getRemainder(int n) {
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> list = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			if(size[i] == 0) continue;
			list.add(size[i]);
		}
		
		Collections.sort(list);
		sb.append(list.size()).append(NEW_LINE);
		
		while(!list.isEmpty()) {
			sb.append(list.removeFirst()).append(SPACE);
		}
		
		return sb;
	}
}
