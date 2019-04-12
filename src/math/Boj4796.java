package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4796번: 캠핑
 *
 *	@see https://www.acmicpc.net/problem/4796/
 *
 */
public class Boj4796 {
	private static final String CASE = "Case ";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = 1;
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			if(L + P + V == 0) break;
			sb.append(CASE).append(t++).append(COLON).append(timeSlice(L, P, V)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int timeSlice(int limit, int pack, int vaca) {
		int result = 0;
		
		result = limit * (vaca / pack);
		int mod = vaca % pack;
		result += mod < limit ? mod: limit;		// 남은 날짜가 기한보다 크면 기한만큼, 아니면 남은 날짜 만큼만
		
		return result;
	}
}
