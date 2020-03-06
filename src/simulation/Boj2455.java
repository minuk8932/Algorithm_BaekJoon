package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 
 * 	@author minchoba
 *	백준 2455번 : 지능형 기차
 *
 *	@see https://www.acmicpc.net/problem/2455
 *	
 */
public class Boj2455 {
	private static final int TRAINS = 4;
	private static final int MAX = 10_000;
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int[] take = new int[TRAINS + 1];
		int[] off = new int[TRAINS + 1];
		int res = 0;
		int p = 0;
		
		for(int i = 1; i < TRAINS + 1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			off[i] = Integer.parseInt(st.nextToken());
			take[i] = Integer.parseInt(st.nextToken());

			p -= off[i];
			p += take[i];

			if(p > MAX) p = MAX;
			if(p > res) res = p;
		}
		
		System.out.println(res);
		
	}
}
