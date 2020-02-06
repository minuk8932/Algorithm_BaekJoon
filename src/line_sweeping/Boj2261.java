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
		int n = Integer.parseInt(br.readLine());
		ArrayList<Coordinate> list = new ArrayList<>();
            
    	for (int i = 0; i < n; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		list.add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    	}

    	list.sort(Comparator.comparingInt(p -> p.x));				// sorting by x, Anonymous comparator
    	TreeSet<Coordinate> candidate = new TreeSet<>();
    	
//    	TreeSet<Point> candidate = new TreeSet<>((p1, p2) -> {		// comparing more than 2
//    		if (p1.y == p2.y) return Integer.compare(p1.x, p2.x);
//    		
//    		return Integer.compare(p1.y,p2.y);
//    	});

    	candidate.add(list.get(0));
    	candidate.add(list.get(1));

    	int ans = getDistance(list.get(1), list.get(0));			// start point
    	int start = 0;
    	
    	for (int i = 2; i < n; i++) {
    		Coordinate current = list.get(i);
    		
    		while (start < i) {
    			Coordinate pivot = list.get(start);
    			int x = pivot.x - current.x;
    			
    			if(x * x <= ans) break;
    			candidate.remove(pivot);
    			start++;
    		}
                
    		int d = (int) Math.sqrt((double) ans) + 1;
    		Set<Coordinate> lower = candidate.subSet(new Coordinate(current.x, current.y - d), new Coordinate(current.x, current.y + d));
    		Iterator<Coordinate> it_lower = lower.iterator();
                
    		while (it_lower.hasNext()) {
    			Coordinate p = it_lower.next();
    			d = getDistance(current, p);
    			
    			if (d < ans) ans = d;							// The closest pair
    		}
    		
    		candidate.add(list.get(i));
    	}
    	
    	System.out.println(ans);
    }
	

	public static int getDistance(Coordinate p1, Coordinate p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
}