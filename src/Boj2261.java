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
			Pair lowerPair = new Pair(current.y - dist, -INF);
			Pair upperPair = new Pair(current.y + dist, INF);
			
			int lower = lowerBound(p, lowerPair);
			int upper = upperBound(p, upperPair);
			
			set.add(p.get(i));
		}
		
		return min;
	}
	
	private static int getDistance(Pair p1, Pair p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
	
	private static int lowerBound(ArrayList<Pair> list, Pair range) {
		int index = 0;
		
		return index;
	}
	
	private static int upperBound(ArrayList<Pair> list, Pair range) {
		int index = 0;
		
		return index;
	}
}
