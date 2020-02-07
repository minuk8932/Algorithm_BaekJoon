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
 *	백준 2261번: 가장 가까운 두 점
 *
 *	@see https://www.acmicpc.net/problem/2261/
 *
 */
public class Boj2261 {
	private static TreeSet<Coordinate> candidate;
	
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
		int N = Integer.parseInt(br.readLine());
		ArrayList<Coordinate> coors = new ArrayList<>();
            
    	for (int i = 0; i < N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		coors.add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    	}
    	
    	System.out.println(lineSweeping(N, coors));
    }
	
	private static int lineSweeping(int n, ArrayList<Coordinate> list) {
		list.sort(Comparator.comparingInt(p -> p.x));				// sorting by x, Anonymous comparator
    	candidate = new TreeSet<>();
    	
//    	TreeSet<Point> candidate = new TreeSet<>((p1, p2) -> {		// comparing with more than 2
//    		if (p1.y == p2.y) return Integer.compare(p1.x, p2.x);
//    		
//    		return Integer.compare(p1.y,p2.y);
//    	});

    	candidate.add(list.get(0));
    	candidate.add(list.get(1));

    	int result = getDistance(list.get(1), list.get(0));			// start point (candidate)
    	int start = 0;
    	
    	for (int i = 2; i < n; i++) {
    		Coordinate current = list.get(i);						// get another coordinate
    		
    		while (start < i) {
    			Coordinate pivot = list.get(start);
    			int x = pivot.x - current.x;
    			
    			if(x * x <= result) break;								// if x differ is bigger than distance of two coordinates, remove TreeSet's component
    			candidate.remove(pivot);								// => is not candiate
    			start++;
    		}
    		
    		result = findTheClosest(result, current);
    		candidate.add(list.get(i));
    	}
    	
    	return result;
	}
	
	private static int findTheClosest(int res, Coordinate cur) {
		int d = (int) Math.sqrt((double) res) + 1;
		
		Set<Coordinate> lower = candidate.subSet(new Coordinate(cur.x, cur.y - d), new Coordinate(cur.x, cur.y + d));		// Sub Range sort
		Iterator<Coordinate> itLower = lower.iterator();
		
		while (itLower.hasNext()) {
			Coordinate p = itLower.next();
			d = getDistance(cur, p);						// make part distance
			
			if (d < res) res = d;							// The closest pair's distance
		}
		
		return res;
	}

	public static int getDistance(Coordinate p1, Coordinate p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
}