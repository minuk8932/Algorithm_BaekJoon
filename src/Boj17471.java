import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17471 {
	private static int[] person;
	private static boolean visit[];
	private static ArrayList<Integer>[] list;
	private static ArrayList<Integer> groups = new ArrayList<>();
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N];
		person = new int[N];
		visit = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<Integer>();
		}
		
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			arr[i] = i;
			
			while(count-- > 0) {
				list[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}

		for (int i = 0; i < N; i++) {
			if (!visit[i]) groups.add(bfs(i, arr));
		}

		switch (groups.size()) {
			case 1:
				division(N);
				break;
			case 2:
				min = Math.abs(groups.get(0) - groups.get(1));
				break;
			default:
				min = -1;
		}

		System.out.println(min);
	}

	private static void division(int n) {
		int half = list.length / 2;

		for (int i = 0; i < half; i++) {
			combination(new int[n], n, i, 0, 0, i + 1);
		}
	}

	public static void combination(int[] comb, int n, int r, int index, int target, int len) {
		if(target == n - 1) return;
		
		if (r == 0) {
			int[] first = new int[len];
			
			for(int i = 0; i < len; i++) {
				first[i] = comb[i];
			}
			
			int[] second = makeSet(n, first);

			visit = new boolean[n];
			int adder = bfs(first[0], first);
			if(!isConnected(first)) return;
			
			visit = new boolean[n];
			adder -= bfs(second[0], second);
			if(!isConnected(second)) return;
			
			int diff = Math.abs(adder);
			if (diff < min) min = diff;
		} 
		else {
			comb[index] = target;
			combination(comb, n, r - 1, index + 1, target + 1, len);
			combination(comb, n, r, index, target + 1, len);
		}
	}

	public static boolean isConnected(int[] group) {
		for (int i = 0; i < group.length; i++) {
			if (!visit[group[i]]) {
				return false;
			}
		}
		
		return true;
	}
	
	public static int bfs(int s, int[] group) {
		boolean flag = false;
		int sum = 0;

		Queue<Integer> q = new LinkedList<>();
		q.add(s);

		while (!q.isEmpty()) {
			int current = q.poll();
			sum += person[current];
			visit[current] = true;

			for (int next: list[current]) {
				flag = false;
				
				for (int g: group) {
					if (next == g) {
						flag = true;
						break;
					}
				}
				
				if (flag && !visit[next]) {
					q.add(next);
					visit[next] = true;
				}
			}
		}
		
		return sum;
	}

	public static int[] makeSet(int n, int[] first) {
		int[] second = new int[n - (first.length - 1)];
		int index = 0;
		boolean flag;

		for (int i = 0; i < n; i++) {
			flag = false;
			
			for (int j = 0; j < first.length; j++) {
				if (first[j] == i) {
					flag = true;
					break;
				}
			}
			
			if (!flag) second[index++] = i;
		}
		
		return second;
	}

}