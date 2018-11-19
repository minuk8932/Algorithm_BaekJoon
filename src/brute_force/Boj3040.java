package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3040번: 백설공주와 일곱 난쟁이
 *
 *	@see https://www.acmicpc.net/problem/3040/
 *
 */
public class Boj3040 {
	private static boolean[] except = new boolean[9];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dwarf = new int[9];
		
		for(int i = 0; i < dwarf.length; i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
		}
		
		getDwarf(dwarf);
		System.out.println(print(dwarf));	// 결과 출력
	}
	
	private static void getDwarf(int[] d) {
		for(int i = 0; i < d.length; i++) {
			for(int j = 0; j < d.length; j++) {
				if(i == j) continue;
				int sum = 0;
				
				for(int k = 0; k < d.length; k++) {
					if(i != k && j != k) {		// 제외할 두 난쟁이를 뺀 나머지 난쟁이 총합
						sum += d[k];
						except[k] = true;
					}
				}
				
				if(sum == 100) return;			// 7명 총합이 100인 경우
				else except = new boolean[9];	// 잘못된 난쟁이 조합인 경우
			}
		}
	}
	
	private static StringBuilder print(int[] dwarf) {		// 결과 출력 메소드
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < except.length; i++) {
			if(except[i]) sb.append(dwarf[i]).append(NEW_LINE);
		}
		
		return sb;
	}
}
