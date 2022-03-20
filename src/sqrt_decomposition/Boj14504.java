package sqrt_decomposition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14504번: 수열과 쿼리 18
 *
 * @see https://www.acmicpc.net/problem/14504
 *
 */
public class Boj14504 {
	private static List<Integer>[] bucket;
	private static int[] components;

	private static int N;
	private static int size;

	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		components = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			components[i] = Integer.parseInt(st.nextToken());
		}

		init();

		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());

		while(M-- > 0){
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j;
			int k;

			if(cmd == 2){
				k = Integer.parseInt(st.nextToken());
				update(i, k);
			}
			else{
				j = Integer.parseInt(st.nextToken()) - 1;
				k = Integer.parseInt(st.nextToken());
				sb.append(query(i, j, k)).append(NEW_LINE);
			}
		}

		System.out.println(sb.toString());
	}

	/**
	 *
	 * Initialize
	 *
	 * line 71: set bucket size
	 * line 79: bucket components settings
	 * line 83: sort each buckets
	 *
	 */
	private static void init(){
		size = (int) Math.sqrt(N);

		bucket = new ArrayList[N / size + 1];
		for(int i = 0; i < bucket.length; i++) {
			bucket[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			bucket[i / size].add(components[i]);
		}

		for (int i = 0; i < bucket.length; i++) {
			Collections.sort(bucket[i]);
		}
	}

	/**
	 *
	 * Update
	 *
	 * line 98: find component's index by lowerBound
	 * line 100 ~ 101: data update
	 *
	 * @param position
	 * @param value
	 */
	private static void update(int position, int value) {
		int key = lowerBound(bucket[position / size], components[position]);

		components[position] = value;
		bucket[position / size].set(key, value);

		Collections.sort(bucket[position / size]);
	}

	/**
	 *
	 * Query
	 *
	 * line 122 ~ 125: traversal left side and count components which is smaller than k
	 * line 127 ~ 130: traversal right side and count components which is smaller than k
	 * line 132 ~ 136: traversal all buckets and count components which is smaller than k
	 *
	 * @param start
	 * @param end
	 * @param k
	 * @return
	 */
	private static int query(int start, int end, int k) {
		int result = 0;

		while (start % size > 0 && start <= end) {
			if (components[start++] <= k) continue;
			result++;
		}

		while ((end + 1) % size > 0 && start <= end) {
			if (components[end--] <= k) continue;
			result++;
		}

		while (start <= end) {
			int total = bucket[start / size].size();
			result += total - upperBound(bucket[start / size], k);
			start += size;
		}

		return result;
	}

	private static int upperBound(List<Integer> bucket, int target) {
		int start = 0;
		int end = bucket.size();

		while(start < end) {
			int mid = (start + end) >> 1;

			if(bucket.get(mid) <= target) start = mid + 1;
			else end = mid;
		}

		return end;
	}

	private static int lowerBound(List<Integer> bucket, int target) {
		int start = 0;
		int end = bucket.size();

		while(start < end) {
			int mid = (start + end) >> 1;

			if(bucket.get(mid) >= target) end = mid;
			else start = mid + 1;
		}

		return end;
	}
}
