package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5073번: 삼각형과 세변
 *
 *	@see https://www.acmicpc.net/problem/5073/
 *
 */
public class Boj5073 {
	private static final String EQ = "Equilateral";
	private static final String SC = "Scalene";
	private static final String IS = "Isosceles";
	private static final String IN = "Invalid";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			if(A == 0 && B == 0 && C == 0) break;	// 반복문 정지 조건
			int max = getMax(A, B, C);					// 3변 중 최댓값 반환
			
			int sum = 0;
			int[] arr = {A, B, C};
			boolean pass = false;
			
			for(int i = 0; i < arr.length; i++){
				if(max == arr[i] && !pass){		// 타당한 세 변 중 중복된 값이 존재 할 수 있으므로 1회 고정 통과
					pass = true;
					continue;
				}
					sum += arr[i];		// 최댓값을 제외한 나머지 변의 합
				
			}
			
			if(sum <= max){									// 최댓값보다 두변의 합이 작거나 같은 경우 = 삼각형이 될 수 없다
				sb.append(IN).append(NEW_LINE);	// 해당 결과를 버퍼에 담음
				continue;
			}
			
			// 삼각형이 생성 가능 할때 각 조건들의 결과를 버퍼에 담음
			if(A == B && B == C) sb.append(EQ).append(NEW_LINE);			// 정 삼각형의 경우
			else if((A == B && B != C) || (A == C && C != B) || B == C && A != B) sb.append(IS).append(NEW_LINE);		// 이등변 삼각형의 경우
			else if(A != B && B != C && A != C) sb.append(SC).append(NEW_LINE);		// 세 변의 길이가 모두 다른 경우
			
		}
		
		System.out.println(sb.toString());				// 결과값 한번에 출력
	}
	
	/**
	 * a, b, c 세변의 길이를 가져와 그 중 최댓값 반환
	 * @param a
	 * @param b
	 * @param c
	 * @return: Math.max(a, b, c);
	 */
	private static int getMax(int a, int b, int c){
		int max = 0;
		max = Math.max(a, b);
		max = Math.max(max, c);
		
		return max;
	}
}
