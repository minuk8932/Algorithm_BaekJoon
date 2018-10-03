package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 2023번: 신기한 소수
 *
 *	@see https://www.acmicpc.net/problem/2023/
 *
 */
public class Boj2023 {	
	private static final char NEW_LINE = '\n';
	
	private static int N = 0;
	private static LinkedList<Character> res = null;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		boolean[] isPrime = new boolean[10];
		isPrime[2] = isPrime[3] = isPrime[5] = isPrime[7] = true;		// 가장 앞자리 수 선별
		
		for(int i = 0; i < 9; i++) {
			if(isPrime[i]) {
				res = new LinkedList();
				res.add((char) (i + '0'));		// i로 시작하는 수를 구하기 위해 저장
				
				dfs(i);							// backTracking 시작
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 백트래킹 메소드
	 * 
	 */
	private static void dfs(int start) {
		if(res.size() == N) {				// 크기가 N인 경우
			for(int i = 0; i < N; i++) {	// N자릴 수를 버퍼에 담고 개행
				sb.append(res.get(i));
			}
			
			sb.append(NEW_LINE);
			return;
		}
		
		for(int next = 0; next < 10; next++) {	// 다음에 이어 붙일 수를 판별
			res.add((char) (next + '0'));
			
			boolean isCorrect = isPrime();		// 이어 붙인 수가 소수인지 체크
			
			if(isCorrect) dfs(next);		// 소수면 다음 깊이로 넘어가고
			res.removeLast();				// 아닌 경우엔 들어왔던 숫자를 빼내줌
		}
	}
	
	/**
	 * 해당 숫자가 1 ~ N자리까지 소수인지 판별하는 메소드
	 * 
	 */
	private static boolean isPrime() {
		int leng = res.size();
		int num = 0;
		
		for(int i = 0; i < leng; i++) {
			int cipher = (res.get(i) - '0');				// 자릿수
			int powTen = (int) Math.pow(10, leng - 1 - i);	// 각 숫자를 원래 수로 복원하기 위한 10의 자리 설정
			
			num += (powTen * cipher);
		}
		
		int loop = num / 2;
		for(int i = 2; i < loop; i++) {
			if(num % i == 0) return false;		// 나누어 떨어지는 경우 소수가 아니므로 거짓 반환
		}
		
		return true;			// 종료되지 않은 경우 참 반환
	}
}
