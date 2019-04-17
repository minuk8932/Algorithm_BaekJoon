import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj2261 {
	
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
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			pair.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(pair);		
		System.out.println(getTheClosestDistance(N, pair));
	}
	
	private static int getTheClosestDistance(int n, ArrayList<Pair> p) {
		int min = getDistance(p.get(0), p.get(1));
		
		
		return 0;
	}
	
	private static int binarySearch(Pair start, Pair end, int target) {
		
		
		return 0;
	}
	
	private static int getDistance(Pair p1, Pair p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
}
