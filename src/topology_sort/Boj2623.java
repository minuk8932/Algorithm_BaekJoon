package topology_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2623번: 음악 프로그램
 *
 * @see https://www.acmicpc.net/problem/2623/
 *
 */
public class Boj2623 {
	private static final String NEW_LINE = "\n";

	private static int[] indegree;
	private static ArrayList<Integer>[] seq;

	private static class Pair implements Comparable<Pair>{
		int idx;
		int cnt;

		public Pair(int idx, int cnt){
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pair p) {
			if (this.cnt < p.cnt) return -1;
			else if (this.cnt > p.cnt) return 1;
			else  return 0;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		seq = new ArrayList[N];
		indegree = new int[N];

		for(int i = 0; i < N; i++){
			seq[i] = new ArrayList<>();
		}

		while (M-- > 0){
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());

			int[] s = new int[count];
			for(int i = 0; i < count; i++) {
				s[i] = Integer.parseInt(st.nextToken()) - 1;
			}

			for(int i = 0; i < count - 1; i++) {
				seq[s[i]].add(s[i + 1]);
				indegree[s[i + 1]]++;					// make indegree
			}
		}

		System.out.println(topologySort(N));
	}

	private static String topologySort(int n){
		StringBuilder sb = new StringBuilder();

		Queue<Integer> q = new LinkedList<>();
		Pair[] visit = new Pair[n];

		for(int i = 0; i < n; i++){
			visit[i] = new Pair(-1, -1);
			if (indegree[i] != 0) continue;

			q.offer(i);
			visit[i] = new Pair(i, 1);				// first sequence
			indegree[i]--;
		}

		while(!q.isEmpty()){
			int current = q.poll();

			for(int next: seq[current]){
				indegree[next]--;

				if(visit[next].cnt < visit[current].cnt + 1) {					// find next
					visit[next] = new Pair(next, visit[current].cnt + 1);
				}

				if(indegree[next] == 0) q.offer(next);
			}
		}

		Arrays.sort(visit);
		for(int i = 0; i < visit.length; i++){
			if(visit[i].idx == -1) return "0";				// if she has any seq
			sb.append(visit[i].idx + 1).append(NEW_LINE);
		}

		return sb.toString();
	}
}