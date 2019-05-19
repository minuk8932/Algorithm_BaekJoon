package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9733번: 꿀벌
 *
 *	@see https://www.acmicpc.net/problem/9733/
 *
 */
public class Boj9733 {
	private static final HashMap<String, Integer> INDEX = new HashMap<>();
	
	private static final String[] WORK = {"Re", "Pt", "Cc", "Ea", "Tb", "Cm", "Ex"};
	private static final String SPACE = " ", NEW_LINE = "\n", T = "Total";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		
		int[] count = new int[7];
		double total = 0;
		
		String input = "";
		while((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(input);
			
			while(st.hasMoreTokens()) {
				String w = st.nextToken();
				
				if(INDEX.containsKey(w)) count[INDEX.get(w)]++;		// 존재하는 작업인 경우
				total++;
			}
		}
		
		System.out.println(getResult(total, count));
	}
	
	private static void init() {
		for(int i = 0; i < WORK.length; i++) {			// 작업에 해당하는 번호 부여
			INDEX.put(WORK[i], i);
		}
	}
	
	private static StringBuilder getResult(double sum, int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++) {
			sb.append(WORK[i]).append(SPACE).append(arr[i]).append(SPACE).append(String.format("%.2f", arr[i] / sum)).append(NEW_LINE);
		}
		
		sb.append(T).append(SPACE).append((int) sum).append(SPACE).append("1.00");
		
		
		return sb;
	}
}
