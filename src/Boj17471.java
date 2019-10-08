import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17471 {
	private static int[] person;
	private static boolean[] visit;
	private static ArrayList<Long> comb = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		person = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer>[] link = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			link[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int node = Integer.parseInt(st.nextToken()) - 1;
				
				link[i].add(node);
				link[node].add(i);
			}
		}
		
		System.out.println(section(N, person, link));
	}
	
	private static int section(int n, int[] p, ArrayList<Integer>[] list) {
		int count = Integer.MAX_VALUE;
		
		for(int s = 1; s <= n; s++) {
			visit = new boolean[n];
			backTracking(n, s, s, 0);
		}
		
		for(long num: comb) {
			int idx = n - 1;
			int[] arr = new int[n];

			while(num > 0) {
				arr[idx--] = (int) (num % 10 == 0 ? num % 100: num % 10);
				num = (num % 10 == 0 ? num / 100: num / 10);
			}
			
			for(int i = 0; i < (n + 1) / 2; i++) {
				int s = i;
				
				ArrayList<Integer>[] seg = new ArrayList[2];
				seg[0] = new ArrayList<>();
				seg[1] = new ArrayList<>();
				
				for(int j = 0; j <= s; j++) {
					seg[0].add(arr[j]);
				}
				
				for(int j = s + 1; j < n; j++) {
					seg[1].add(arr[j]);
				}
				
				int[] population = {bfs(n, seg[0], list), bfs(n, seg[1], list)};
				
				if(population[0] == -1 || population[1] == -1) continue;
				count = Math.min(count, Math.abs(population[0] - population[1]));
			}
		}
		
		return count == Integer.MAX_VALUE ? -1: count;
	}
	
	private static int bfs(int n, ArrayList<Integer> seg, ArrayList<Integer>[] list) {
		int sum = 0;
		boolean[] istied = new boolean[n];
			
		for(int site: seg) {
			istied[site - 1] = true;
		}
			
		int count = seg.size() - 1;
			
		Queue<Integer> q = new LinkedList<>();
		q.offer(seg.get(0) - 1);
			
		sum += person[seg.get(0) - 1];
		istied[seg.get(0) - 1] = false;
	
		while(!q.isEmpty()) {
			int current = q.poll();
				
			for(int next: list[current]) {
				if(!istied[next]) continue;
				istied[next] = false;
					
				sum += person[next];
					
				count--;
				q.offer(next);
			}
		}

		return count == 0 ? sum: -1;
	}
	
	private static void backTracking(int n, int idx, long val, int count) {
		if(count == n - 1) {
			comb.add(val);
			return;
		}
		visit[idx - 1] = true;
		
		for(int i = 1; i < n + 1; i++) {
			if(visit[i - 1]) continue;
			
			backTracking(n, i, val * (i == 10 ? 100: 10) + i, count + 1);
			visit[i - 1] = false;
		}
	}
}
