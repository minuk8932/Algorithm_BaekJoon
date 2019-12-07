package divide_and_conquer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 2261번: 가장 가까운 두 점사이의 거리
 *
 *	@see https://www.acmicpc.net/problem/2261/
 *
 */
public class Boj2261 {
	private static class PointComparator implements Comparator<Point>{				// sort by y
	    @Override
	    public int compare(Point o1, Point o2) {
	        return o1.y-o2.y;
	    }
	    
	}
	private static class Point implements Comparable<Point>{
	    int x;
	    int y;
	    
	    public Point(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	 
	    @Override
	    public int compareTo(Point o) {
	        int r = this.x - o.x;
	        if(r == 0) r = this.y - o.y;
	        
	        return r;
	    }
	}
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        ArrayList<Point> dots = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            dots.add(new Point(x, y));
        }
        
        Collections.sort(dots);
        System.out.println(recursion(dots, 0, n - 1));
    }
 
    private static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
    
    private static int getDist(ArrayList<Point> list, int x, int y) {
        int res = -1;
        
        for(int i = x; i <= y - 1; i++) {
            for(int j = i + 1; j <= y; j++) {
                int k = dist(list.get(i), list.get(j));
                if(res == -1 || res > k) res = k;
            }
        }
        return res;
    }
 
    private static int recursion(ArrayList<Point> points, int x, int y) {
        int n = y - x + 1;
        if(n <= 3) return getDist(points, x, y);
        
        int mid = (x + y) / 2;
        int left = recursion(points, x, mid);				// divide & conquer
        int right = recursion(points, mid + 1, y);
        int ans = Math.min(left, right);
        
        ArrayList<Point> list = new ArrayList<>();
        
        for(int i = x; i <= y; i++) {
            int dist = points.get(i).x - points.get(mid).x;
            dist *= dist;
            
            if(dist < ans) list.add(points.get(i));
        }
        
        Collections.sort(list, new PointComparator());      
        int m = list.size(); 
        
        for(int i = 0; i < m - 1; i++) {
            for(int j = i + 1; j < m; j++) {
                int k = list.get(j).y - list.get(i).y;
                k *= k;
                
                if(k < ans) {
                    int dist = dist(list.get(i), list.get(j));			// calculate distance
                    if(dist < ans) ans = dist;
                }
                else {
                    break;
                }
            }
        }
        
        return ans;
    }
}
