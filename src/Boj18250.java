import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj18250 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			path[a].add(b);
			path[b].add(a);
		}
		
		System.out.println(getPath(N, path));
	}
	
	private static int getPath(int n, ArrayList<Integer>[] list) {
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			if(list[i].size() == 1) count++;
		}
		
		int res = (count + 1) / 2;
		return res == 0 ? 1: res;
	}
}
