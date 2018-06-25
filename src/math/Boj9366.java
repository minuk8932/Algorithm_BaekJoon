package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9366번: 삼각형 분류
 *
 *	@see https://www.acmicpc.net/problem/9366/
 *
 */
public class Boj9366 {
	private static final String TRIPLE = "equilateral";
	private static final String DOUBLE = "isosceles";
	private static final String UNIQUE = "scalene";
	private static final String INVALID = "invalid!";
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int max = 0;
			int[] tri = new int[3];
			
			for(int i = 0; i < tri.length; i++) {
				tri[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, tri[i]);			// 가장 큰 값을 저장
			}
			
			int sum = 0, comp = -1, isDouble = 0;	// 차례로, 최댓값 제외한 합, 비교할 수, 이등변인지 검사할 변수
			boolean isSame = false;					// 이등변인지 부등변인지 검사할 변수
			
			for(int i = 0; i < tri.length; i++) {
				if(max != tri[i]) {
					sum += tri[i];		// 최대 길이를 제외하고 총합을 구함
					
					if(tri[i] == comp) isSame = true;	// 만약 남은 두 변의 길이가 동일하면, 참으로 변경
					
					comp = tri[i];			// 비교 할 변을 comp에 담아줌
				}
				
				if(max == tri[i]) {		// 같은 길이를 가진 변이 max 자신포함 2개인지 확인
					isDouble++;
				}
			}
			
			if(sum == 0) {		// 나머지 총합이 0인 경우 정삼각형, (모두 같은 변이므로 덧셈 연산이 수행되지 x)
				sb.append(CASE).append(tc).append(COLON).append(TRIPLE).append(NEW_LINE);
				continue;
			}
			
			if(max >= sum) {	// 합보다 최대값이 큰 경우 중 이등변변수 == 2: 이등변, 그 외: 삼각형이 아님
				if(isDouble == 2) sb.append(CASE).append(tc).append(COLON).append(DOUBLE).append(NEW_LINE);
				else sb.append(CASE).append(tc).append(COLON).append(INVALID).append(NEW_LINE);
			}
			else {				// 합이 최댓값보다 큰 경우 중 두 변이 같다: 이등변, 그 외: 부등변
				if(isSame) sb.append(CASE).append(tc).append(COLON).append(DOUBLE).append(NEW_LINE);
				else sb.append(CASE).append(tc).append(COLON).append(UNIQUE).append(NEW_LINE);				
			}
		}		// 조건에 따라 버퍼에 담은 후
		
		System.out.println(sb.toString());		// 결과값 한번에 출력	
	}
}
