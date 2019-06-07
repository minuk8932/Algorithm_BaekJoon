package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17262번: 팬덤이 흘러 넘쳐
 *
 *	@see https://www.acmicpc.net/problem/17262/
 *
 */
public class Boj17262 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int max = 0, min = 100_001;
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(max < from) max = from;
			if(min > to) min = to;
		}
		
		System.out.println(max - min < 0 ? 0: max - min);		// max - min < 0 이면 엮인 구간
	}
}
