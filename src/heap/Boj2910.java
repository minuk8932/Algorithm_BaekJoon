package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
	
	private static class Info implements Comparable<Info>{
		int index;
		int value;
		int count;
		
		public Info(int index, int value, int count) {
			this.index = index;
			this.value = value;
			this.count = count;
		}

		@Override
		public int compareTo(Info i) {
			if(this.count > i.count) {
				return -1;
			}
			else if(this.count < i.count) {
				return 1;
			}
			else {
				if(this.index < i.index) return -1;
				else if(this.index > i.index) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> hm = new HashMap<>();
		HashMap<Integer, Integer> arr = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());
			
			set.add(value);			// 숫자 종류
			
			if(!arr.containsKey(value)) arr.put(value, i);		// 숫자와 등장 순서
			else arr.put(value, Math.min(arr.get(value), i));
			
			if(!hm.containsKey(value)) hm.put(value, 1);		// 숫자와 등장 빈도
			else hm.put(value, hm.get(value) + 1);
		}
		
		System.out.println(getSequence(N, set, arr, hm));
	}
	
	private static StringBuilder getSequence(int n, HashSet<Integer> set, HashMap<Integer, Integer> arr, HashMap<Integer, Integer> hm) {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Info> pq = new PriorityQueue<>();
		
		for(int val: set) {
			int idx = arr.get(val);
			int cnt = hm.get(val);
			
			pq.offer(new Info(idx, val, cnt));			// 우선순위 큐에서 조건에 따라 정렬
		}
		
		while(!pq.isEmpty()) {
			Info current = pq.poll();
			
			while(current.count-- > 0) {
				sb.append(current.value).append(SPACE);
			}
		}
		
		return sb;
	}
}
