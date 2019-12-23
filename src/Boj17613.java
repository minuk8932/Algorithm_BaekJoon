import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17613 {
	private static final int[] JUMP = {1, 3, 7, 15, 31, 63, 127, 255, 511,
			1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143,
			524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431,
			67108863, 134217727, 268435455, 536870911};
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			sb.append(search(start, end)).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static int search(int s, int e) {					// 얼마나 많은 수의 조합인가.
		
		
		return 0;
	}
}
