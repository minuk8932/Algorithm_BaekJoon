package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14488번: 준오는 급식충이야!
 *
 *	@see https://www.acmicpc.net/problem/14488/
 *
 */
public class Boj14488 {
	private static final String DOT = ".";
	
	private static class Friend implements Comparable<Friend>{
		int pos;
		int vel;
		
		public Friend(int pos, int vel) {
			this.pos = pos;
			this.vel = vel;
		}

		@Override
		public int compareTo(Friend f) {
			if(this.pos < f.pos) return -1;
			else if(this.pos > f.pos) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		double T = Double.parseDouble(st.nextToken());
		
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
			f[i] = new Friend(tmp[i][0], tmp[i][1]);
		}
		
		Arrays.sort(f);
		System.out.println(possible(N, T, f));
	}
	
	private static int possible(int n, double t, Friend[] arr) {
		double min = arr[n - 1].pos - (arr[n - 1].vel * t);
		double max = arr[0].pos + (arr[0].vel * t);
		
		min = truncate(String.valueOf(min));			// 소숫점 아래 4째자리 까지 자르기
		max = truncate(String.valueOf(max));

		if(min > max) return 0;
		
		for(int i = 1; i < n - 1; i++) {
			double[] range = {arr[i].pos - (arr[i].vel * t), arr[i].pos + (arr[i].vel * t)};
			
			if(arr[i].pos >= min && arr[i].pos <= max) {		// 범위 내에 존재하는 경우
				if(range[0] >= min) min = range[0];
				if(range[1] <= max) max = range[1];
				
				continue;
			}
			
			if((arr[i].pos < min && range[1] < min) || arr[i].pos > max && range[0] > max) return 0;
			if(arr[i].pos < min && range[1] <= max) max = range[1];		// 범위 밖에서 도달 가능 한 경우, 범위 조정
			if(arr[i].pos > max && range[0] >= min) min = range[0];
		}
		
		return 1;
	}
	
	private static double truncate(String str) {		
		StringTokenizer st = new StringTokenizer(str, DOT);
		StringBuilder sb = new StringBuilder();
		
		String head = st.nextToken();
		String tmp = st.nextToken();
		String tail = tmp.length() <= 4 ? tmp : tmp.substring(0, 4);		// 길이가 짧은 경우 패스
		
		sb.append(head).append(DOT).append(tail);
		
		return Double.parseDouble(sb.toString());
	}
}
