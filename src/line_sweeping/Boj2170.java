package line_sweeping;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2170번: 선 긋기
 *
 *	@see https://www.acmicpc.net/problem/2170/
 *
 */
public class Boj2170 {
	
	private static class Pair implements Comparable<Pair>{
		int from;
		int to;
		
		public Pair(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.from < p.from) return -1;
			else if(this.from > p.from) return 1;
			else return 0;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Pair[] line = new Pair[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			line[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getLength(N, line));
	}
	
	private static int getLength(int n, Pair[] p) {
		Arrays.sort(p);			// 좌표순 정렬
		int total = 0;
		
		Pair set = new Pair(p[0].from, p[0].to);		// 초기 위치 설정
		
		for(int i = 1; i < n; i++) {
			if(set.to > p[i].from) {
				set.to = Math.max(set.to, p[i].to);		// 저장된 범위에 걸치는 범위인 경우
			}
			else {										// 동 떨어진 범위인 경우 총 길이에 범위를 합하
				total += set.to - set.from;				// 현재 범위로 초기화
				set = p[i];
			}
		}
		
		total += set.to - set.from;
		return total;
	}
}
