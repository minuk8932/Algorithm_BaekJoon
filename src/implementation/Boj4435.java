package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4435 {
	private static final int[] GANDALF = {1, 2, 3, 3, 4, 10};
	private static final int[] SAURON = {1, 2, 2, 2, 3, 5, 10};
	
	private static final String BATTLE = "Battle ";
	private static final String LOSE = ": Evil eradicates all trace of Good\n";
	private static final String WIN = ": Good triumphs over Evil\n";
	private static final String DRAW = ": No victor on this battle field\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int g = getCount(br.readLine(), GANDALF);
			int s = getCount(br.readLine(), SAURON);
			
			sb.append(BATTLE).append(t + 1).append(g == s ? DRAW : g > s ? WIN: LOSE);
		}
		
		System.out.print(sb);
	}
	
	private static int getCount(String input, int[] arr) {
		StringTokenizer st = new StringTokenizer(input);
		int total = 0;
		
		for(int i = 0; i < arr.length; i++) {
			total += arr[i] * Integer.parseInt(st.nextToken());
		}
		
		return total;
	}
}
