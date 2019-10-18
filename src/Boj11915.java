import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11915 {
	private static final String UP = "^";
	private static final String DOWN = "v";
	private static final String MIX = "IMPOSSIBLE";
	
	private static class Range{
		int from;
		int to;
		
		public Range(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Range[] bulb = new Range[N];
		int[] input = new int[N * 2];
		
		for(int i = 0; i < N; i++) {
			bulb[i] = new Range(-1, -1);
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N * 2; i++) {
			input[i] = Integer.parseInt(st.nextToken()) - 1;
			
			if(bulb[input[i]].from == -1) bulb[input[i]].from = i;
			else bulb[input[i]].to = i;
		}
		
		System.out.println(draw(N, bulb, input));
	}
	
	private static String draw(int n, Range[] b, int[] arr) {
		int[] state = new int[arr.length];
		Range u = new Range(Integer.MAX_VALUE, -1);
		Range d = new Range(Integer.MAX_VALUE, -1);
		
		for(int i = 0; i < arr.length; i++) {
			if(state[i] != 0) continue;
			int target = arr[i];
			
			if((u.from < b[target].from && u.to > b[target].to) || u.to < b[target].from) {
				state[i] = state[b[target].to] = 1;
				if(i == 2) System.out.println(u.from + " " + u.to);
				u.from = i;
//				u.to = Math.min(u.to, b[target].to);
//				u.to = b[target].to;
				u.to = Math.max(u.to, b[target].to);
				continue;
			}
			
			if((d.from < b[target].from && d.to > b[target].to) || d.to < b[target].from) {
				state[i] = state[b[target].to] = 2;
				d.from = i;
//				d.to = Math.min(d.to, b[target].to);
//				d.to = b[target].to;
				d.to = Math.max(d.to, b[target].to);
				
				continue;
			}
			
			return MIX;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n * 2; i++) {
			sb.append(state[i] == 1 ? UP: DOWN);
		}
		
		return sb.toString();
	}
}
