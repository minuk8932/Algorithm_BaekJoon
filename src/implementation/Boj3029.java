package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 3029번: 경고
 * 
 * @see https://www.acmicpc.net/problem/3029/
 *
 */
public class Boj3029 {
	private static final int[] TIME_FORMAT = {86400, 3600, 60, 1};
	private static final String COLON = ":";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] time = new int[2];
		
		for(int i = 0; i < time.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), COLON);
			
			for(int j = 0; j < 3; j++) {
				time[i] += (TIME_FORMAT[j + 1] * Integer.parseInt(st.nextToken()));
			}
		}
		
		System.out.println(waiting(time));
	}
	
	private static String waiting(int[] t) {
		StringBuilder sb = new StringBuilder();		
		int diff = t[1] - t[0];
		diff = diff <= 0 ? TIME_FORMAT[0] + diff : diff;
		
		if(diff % TIME_FORMAT[0] == 0) return "24:00:00";			// 정확히 24 대기
		
		for(int i = 1; i < TIME_FORMAT.length; i++) {
			int tmp = diff / TIME_FORMAT[i];
			sb.append(tmp < 10 ? "0" + tmp: tmp).append(COLON);		// 한자리 수가 나오면 앞에 0을 붙여줌
			
			diff %= TIME_FORMAT[i];
		}
		
		return sb.substring(0, sb.length() - 1);
	}
}
