package line_sweeping;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 
 * 	@author exponential-e
 *	백준 5620번: 가장 가까운 두 점 사이의 거리
 *
 *	@see https://www.acmicpc.net/problem/5620/
 *
 */
public class Boj5620 {	
	private static class Coordinate implements Comparable<Coordinate>{
		int x;
		int y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Coordinate c) {
			if(this.y < c.y) {
				return -1;
			}
			else if(this.y > c.y) {
				return 1;
			}
			else {
				if(this.x < c.x) return -1;
				else if(this.x > c.x) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		ArrayList<Coordinate> coor = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			coor.add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(lineSweeping(N, coor));
	}
	
	private static long lineSweeping(int n, ArrayList<Coordinate> list) {
		list.sort(Comparator.comparingInt(p -> p.x));						// sort x
		TreeSet<Coordinate> candidate = new TreeSet<>();
		
		candidate.add(list.get(1));
		candidate.add(list.get(0));
		long result = distancePow(list.get(0), list.get(1));				// get range
		
		int start = 0;
		
		for(int idx = 2; idx < n; idx++) {
			Coordinate current = list.get(idx);
			
			while(start < idx) {
				Coordinate pivot = list.get(start);
				long x = pivot.x - current.x;								// x diff
				
				if(x * x <= result) break;									// x diff is larger than result, is except from closest
				candidate.remove(pivot);
				
				start++;
			}
			
			result = findClosestPair(n, list, candidate, current, result);
    		candidate.add(list.get(idx));
		}
		
		return result;
	}
	
	private static long findClosestPair(int n, ArrayList<Coordinate> list, TreeSet<Coordinate> cand, Coordinate cur, long res) {
		int d = (int) Math.sqrt((double) res) + 1;
		
		Set<Coordinate> lower = cand.subSet(new Coordinate(cur.x, cur.y - d), new Coordinate(cur.x, cur.y + d)); 		// make limit range
		Iterator<Coordinate> itLower = lower.iterator();
		
		while(itLower.hasNext()) {
			Coordinate p = itLower.next();
			d = distancePow(p, cur);
			
			if(d < res) res = d;						// find the closest
		}
		
		return res;
	}
	
	private static int distancePow(Coordinate c1, Coordinate c2) {
		return (c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y);
	}
}
