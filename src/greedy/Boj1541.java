package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1541번: 잃어버린 괄호
 */
public class Boj1541 {
	private static final String MINUS = "-";
	private static final String PLUS = "+";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String formular = br.readLine();		// 전체 식을 담아온 후
		
		StringTokenizer st = new StringTokenizer(formular, MINUS);	// "-"를 기준으로 식을 잘라줌
		int size = st.countTokens();
		
		boolean startMinus = formular.charAt(0) == MINUS.charAt(0) ? true : false;	// 만약 식이 "-"으로 시작했다면 참, 아니면 거짓
		
		String[] seperate = new String[size];
		for(int i = 0; i < size; i++) {
			seperate[i] = st.nextToken();		// "+"가 붙어있채로 식을 문자열 배열에 할당
		}
		
		int[] nums = new int[size];
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(seperate[i], PLUS);		// 문자열을 "+" 기준으로 자르고
			
			while(st.hasMoreTokens())
				nums[i] += Integer.parseInt(st.nextToken());	// 해당 값을 각 정수 배열의 인덱스에 더해서 담아줌
		}
		
		nums[0] = startMinus ? -nums[0] : nums[0];				// 만약 startMinus가 참이면 nums[0]의 값을 음수로 변경
		
		int res = nums[0];
		for(int i = 1; i < size; i++) {					// "+"로 연결되어있던 수를 각각 더한 값을 음수로 변경하여 결과 변수에 저장
			res -= nums[i];
		}
		
		System.out.println(res);			// 괄호를 이용해 만들 수 있는 최소 값을 출력
	}
}
