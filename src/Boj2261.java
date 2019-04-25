import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj2261 {
	private static final int INF = 100_000_000;
	
	private static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.x < p.x) return -1;
			else if(this.x > p.x) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Pair> pair = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pair.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(pair);		
		System.out.println(getTheClosestDistance(N, pair));
	}
	
	private static int getTheClosestDistance(int n, ArrayList<Pair> p) {
		int min = getDistance(p.get(0), p.get(1));
		TreeSet<Pair> set = new TreeSet<>();
		
		set.add(p.get(0));
		set.add(p.get(1));
	
		int start = 0;		
		for(int i = 2; i < n; i++) {
			Pair current = p.get(i);
			
			while(start < i) {
				Pair next = p.get(start);
				int x = current.x - next.x;
				
				if(x * x > min) {
					set.remove(next);
					start++;
				}
				else {
					break;
				}
			}
			
			int dist = (int) Math.sqrt(min) + 1;			
			int lower = lowerBound(set, current.y - dist);
			int upper = upperBound(set, current.y + dist);
			
			for(int idx = lower; idx != upper; idx++) {
				min = Math.min(min, getDistance(p.get(0), p.get(i)));		// 수정
			}
			
			set.add(p.get(i));
		}
		
		return min;
	}
	
	private static int getDistance(Pair p1, Pair p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
	
	private static int lowerBound(TreeSet<Pair> ts, int target) {		// 수정
		int index = 0;
		int start = 0;
		int end = ts.size();
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(mid < target) {
				start = mid + 1;
				index = mid;
			}
			else {
				end = mid - 1;
			}
		}
		
		return index;
	}
	
	private static int upperBound(TreeSet<Pair> ts, int target) {		// 수정 -> 트리셋이 아니고 다른걸..
		int index = 0;
		int start = 0;
		int end = ts.size();
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(mid > target) {
				end = mid - 1;
				index = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return index;
	}
}
