package heap;
import common.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * 	@author minchoba
 *	백준 2910번: 빈도 정렬
 *
 *	@see https://www.acmicpc.net/problem/2910/
 *
 */
public class Boj2910 {
	private static final String SPACE = " ";

	private static HashMap<Integer, Integer> map = new HashMap<>();
	private static HashMap<Integer, Integer> index = new HashMap<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st.nextToken();

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());

			map.merge(value, 1, Integer::sum);

			if(index.containsKey(value)) continue;
			index.put(value, i);
		}

		System.out.println(getSequence());
	}

	private static StringBuilder getSequence() {
		PriorityQueue<Pair<Integer>> pq = new PriorityQueue<>(Comparator
				.comparingInt(Pair<Integer>::getSecond)
				.thenComparingInt(Pair::getThird));

		for(int key: map.keySet()){
			pq.offer(new Pair.Builder(key, -map.get(key))
					.third(index.get(key))
					.build());
		}

		StringBuilder sb = new StringBuilder();

		while(!pq.isEmpty()) {
			Pair<Integer> current = pq.poll();
			int loop = -current.getSecond();

			while(loop-- > 0) {
				sb.append(current.getFirst()).append(SPACE);
			}
		}

		return sb;
	}
}

