import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14589 {
	private static Friend[] parent;
	
	private static class Friend{
		int value;
		int relate;
		
		public Friend(int value, int relate) {
			this.value = value;
			this.relate = relate;
		}
	}
	
	private static class Line implements Comparable<Line>{
		int from;
		int to;
		
		public Line(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int compareTo(Line l) {
			if(this.from < l.from) {
				return -1;
			}
			else if(this.from > l.from) {
				return 1;
			}
			else {
				if(this.to < l.to) return -1;
				else if(this.to > l.to) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Line[] sort = new Line[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sort[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(sort);
		init(N, sort);
		
		
	}
	
	private static void init(int n, Line[] arr) {
		parent = new Friend[n];
		for(int i = 0; i < n; i++) {
			parent[i] = new Friend(-1, 0);
		}
		
		for(int i = 1; i < arr.length; i++) {
			if(isLinked(arr[i - 1], arr[i])) {
				// mst로 가능하지 않을까. 연결된 곳의 비용은 1로, 나머지는 어떻게 모델링?
			}
		}
	}
	
	private static boolean isLinked(Line l1, Line l2) {
		return (l1.from >= l2.from && l1.from <= l2.to) || (l2.from >= l1.from && l2.from <= l1.to) ? true: false;
	}
	
	private static int find(int x) {
		if(parent[x].value < 0) return x;
		return parent[x].value = find(parent[x].value);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x].value < parent[y].value) {
			parent[x].value += parent[y].value;
			parent[y].value = x;
		}
		else {
			parent[y].value += parent[x].value;
			parent[x].value = y;
		}
	}
}
