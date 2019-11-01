import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj2261{	
	private static class Descending implements Comparator<Point> {
		public int compare(Point p1, Point p2) {
			if (p1.x < p2.x) return -1;
			else if (p1.x > p2.x) return 1;
			else return 0;
		}
	}
	
	private static class ComparatorSet implements Comparator<Point> {
		public int compare(Point p1, Point p2) {
			if (p1.y == p2.y) {
				if (p1.x < p2.x) return -1;
				else if (p1.x > p2.x) return 1;
				else return 0;
			}
			else {
				return p1.y < p2.y ? 1 : -1;
			}
		}
	}
	
	private static class Point implements Comparable<Point> {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int compareTo(Point p) {
			return x < p.y && y > p.y ? 1 : -1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Point> Pairs = new ArrayList<Point>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Pairs.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(lineSweeping(N, Pairs));
	}
	
	private static int distance(Point p1, Point p2){
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
	
	private static int lineSweeping(int n, ArrayList<Point> list) {
		TreeSet<Point> candidate = new TreeSet<Point>(new ComparatorSet());
		Collections.sort(list, new Descending());
		
		candidate.add(list.get(0));
		candidate.add(list.get(1));
		int result = distance(list.get(0), list.get(1));
		
		int start = 0;
		for (int i = 2; i < n; i++) {
			Point current = list.get(i);
			
			while (start < i) {
				Point pivot = list.get(start);
				int x = pivot.x - current.x;
				if(x * x <= result) break;
				
				candidate.remove(pivot);
				start += 1;
			}
			
			int d = (int) Math.sqrt((double) result) + 1;
			Point lower_point = new Point(current.y - d, current.y + d);
			SortedSet<Point> lower = candidate.tailSet(lower_point);
			Iterator<Point> it_lower = lower.iterator();
			
			while (it_lower.hasNext()) {
				Point p = it_lower.next();
				d = distance(current, p);
				
				if (d < result) result = d;
			}
			
			candidate.add(list.get(i));
		}
		
		return result;
	}
}