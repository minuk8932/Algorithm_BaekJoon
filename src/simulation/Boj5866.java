package simulation;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 
 * 	@author exponential-e
 *	백준 5866번: Meet and Greet
 *
 *	@see https://www.acmicpc.net/problem/5866/
 *
 */
public class Boj5866 {
	private static final char RIGHT = 'R';
	
	private static class Route{
		int cnt;
		char dir;
		
		public Route(int cnt, char dir) {
			this.cnt = cnt;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		Route[] bessie = new Route[B];
		Route[] elsie = new Route[E];
		
		int[] time = new int[2];
		for(int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			bessie[i] = new Route(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
			time[0] += bessie[i].cnt;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			elsie[i] = new Route(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
			time[1] += elsie[i].cnt;
		}
		
		System.out.println(getMeetCount(B, E, bessie, elsie, time));
	}
	
	private static int getMeetCount(int n, int m, Route[] r1, Route[] r2, int[] t) {
		boolean flag = true;
		int count = 0;
		
		int leng = Math.max(t[0], t[1]);
		int[] pos = new int[2];
		int[] idx = new int[2];
		
		while(leng-- > 0) {									// 최대 이동시간에 맞춰 시뮬레이션
			if(idx[0] < n) {
				pos[0] += r1[idx[0]].dir == RIGHT ? 1: -1;
				r1[idx[0]].cnt--;
				
				if(r1[idx[0]].cnt == 0) idx[0]++;			// 해당 방향에 대한 이동을 끝마친 경우 다음 이동으로
			}
			
			if(idx[1] < m) {
				pos[1] += r2[idx[1]].dir == RIGHT ? 1: -1;
				r2[idx[1]].cnt--;
				
				if(r2[idx[1]].cnt == 0) idx[1]++;
			}
			
			if(pos[0] != pos[1]) flag = false;
			
			if(!flag && pos[0] == pos[1]) {					// 1초전 만나지 않았고 현재 만난 경우
				count++;
				flag = true;
			}
		}
		
		return count;
	}
}
