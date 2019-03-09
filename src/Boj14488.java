import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14488 {
	private static final int FORMAT = 10_000;
	
	private static class Friend implements Comparable<Friend>{
		int pos;
		long vel;
		
		public Friend(int pos, int vel) {
			this.pos = pos;
			this.vel = vel;
		}

		@Override
		public int compareTo(Friend f) {
			return this.pos < f.pos ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long T = Integer.parseInt(st.nextToken()) * FORMAT;
		
		
		Friend[] f = new Friend[N];
		int[][] tmp = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tmp[i][0] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tmp[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			f[i] = new Friend(tmp[i][0] * FORMAT, tmp[i][1]);
		}
		
		System.out.println(possible(N, T, f));
	}
	
	private static int possible(int n, long t, Friend[] arr) {
		Arrays.sort(arr);
		long min = arr[0].pos, max = arr[n - 1].pos;
		
		if(max - (arr[n - 1].vel * t) > min + (arr[0].vel * t)) return 0;
		
		for(int i = 1; i < n - 1; i++) {
			
		}
		
		return 1;
	}
}
