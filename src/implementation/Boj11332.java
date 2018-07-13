package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11332번: 시간초과
 *
 *	@see https://www.acmicpc.net/problem/11332/
 *
 */
public class Boj11332 {
	private static final String AC = "May Pass.";
	private static final String TLE = "TLE!";
	private static final String NEW_LINE = "\n";
	
	private static final long TIME_LIMIT = 100_000_000;
	private static final long MAX_TIME = 1_000_000_000;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int C = Integer.parseInt(br.readLine());
		
		while(C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] S = st.nextToken().toCharArray();
			long N = Long.parseLong(st.nextToken());
			long T = Long.parseLong(st.nextToken());
			long L = Long.parseLong(st.nextToken());
			
			boolean res = false;
			
			switch (S.length) {				// 입력된 문자열 길이에 따라 해당 시간복잡도를 구분
			case 4:
				res = integerTime(N, T, L);
				break;
				
			case 5:
				res = factorialTime(N, T, L);
				break;
				
			case 6:
				res = exponentialTime(S, N, T, L);
				break;
			}
			
			sb.append(res ? AC : TLE).append(NEW_LINE);		// 결과 변수에 따라 버퍼에 값 저장
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * O(N) 시간 복잡도 계산 메소드
	 * @param number	입력 수
	 * @param testCase	테스트 케이스 수
	 * @param limit		시간 제한(초)
	 * @return			시간 초과 여부
	 */
	private static boolean integerTime(long number, long testCase, long limit) {
		long totalTime = number * testCase;
		limit *= TIME_LIMIT;
		
		return totalTime <= limit ? true : false;
	}
	
	/**
	 * O(N^2), O(N^3) or O(2^N) 시간 복잡도 계산 메소드
	 * @param timeComlpexity	위의 세가지 중 어떤것인지 확인해 구분할 문자열
	 * @param number	입력 수
	 * @param testCase	테스트 케이스 수
	 * @param limit		시간 제한(초)
	 * @return			시간 초과 여부
	 */
	private static boolean exponentialTime(char[] timeComplexity, long number, long testCase, long limit) {
		char under = timeComplexity[2];
		long totalTime = 0;
		limit *= TIME_LIMIT;
		
		if(under != 'N') {
			if(number >= 30) {				// O(2^N) 의 경우 N이 30을 넘어가면 최대 시간 제한보다 커지므로
				totalTime = MAX_TIME * 2;		// 최대 시간제한 < totalTime < long의 범위에 벗어나지 않는 수 설정
			}
			else {							// 그 외, 연산이 커져도 long을 벗어나진 않으므로 그대로 계산
				number = (long) Math.pow(2, number);
				totalTime = number * testCase;
			}
		}
		else {								// O(N^2), O(N^3)의 경우
			int leng = timeComplexity[4] - '0';
			long tmp = number;
			
			for(int i = 0; i < leng - 1; i++) {		// 제곱 연산이 아닌 반복문으로 회차마다 수의 크기를 확인
				number *= tmp;
				
				if(number > limit) break;		// 시간제한보다 커지면 바로 정지
			}
			
			totalTime = number * testCase;
		}
		
		return totalTime <= limit ? true : false;
	}
	
	/**
	 * O(N!) 시간 복잡도 계산 메소드
	 * @param number	입력 수
	 * @param testCase	테스트 케이스 수
	 * @param limit		시간 제한(초)
	 * @return			시간 초과 여부
	 */
	private static boolean factorialTime(long number, long testCase, long limit) {
		long totalTime = 0;
		limit *= TIME_LIMIT;
		
		long[] factorial = new long[(int)number + 1];
		int size = (int) number;
		
		factorial[size] = number;
		factorial[size - 1] = number * (number - 1);
		
		int idx = 0;
		
		for(int i = size - 2; i > 0; i--) {
			if(factorial[i + 1] > limit) {		// 팩토리얼 계산 중 시간 제한을 넘어가면 정지
				idx = i + 1;
				break;
			}
			
			factorial[i] = factorial[i + 1] * i;
		}
		
		// 반복문 수행도중 정지한 경우 그때의 팩토리얼 값을, 아닌 경우엔 마지막의 팩토리얼 값을 담아줌
		totalTime = testCase * (idx == 0 ? factorial[1] : factorial[idx]);
		return totalTime <= limit ? true : false;
	}
}
