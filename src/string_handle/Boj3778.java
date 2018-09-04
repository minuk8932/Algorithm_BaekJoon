package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3778번: 애너그램 거리
 *
 *	@see https://www.acmicpc.net/problem/3778/
 *
 */
public class Boj3778 {
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < N; t++) {
			int[] alpha = new int[26];
			int[] comp = new int[26];
			
			for(char w: br.readLine().toCharArray()) {	// 처음으로 들어오는 단어에 따른 갯수를 배열에 저장
				alpha[w - 'a']++;
			}
			
			for(char w: br.readLine().toCharArray()) {	// 다음 비교 대상 단어에 따른 갯수를 배열에 저장
				comp[w - 'a']++;
			}
			
			int sum = 0;
			
			for(int i = 0; i < 26; i++) {		// 전체 배열의 차를 구하며 이때의 값을 절대값으로 바꾸어 갯수의 총합을 구하고
				sum += alpha[i] - comp[i] < 0 ? -(alpha[i] - comp[i]) : alpha[i] - comp[i];
			}
			
			sb.append(CASE).append(t + 1).append(COLON).append(sum).append(NEW_LINE);	// 버퍼에 양식에 맞추어 저장
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
