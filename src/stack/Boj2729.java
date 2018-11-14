package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2729번: 이진수 덧셈
 *
 *	@see https://www.acmicpc.net/problem/2729/
 *
 */
public class Boj2729 {
	private static final String NEW_LINE = "\n";
	private static final char ZERO = '0';
	private static final char ONE = '1';
	
	private static Deque<Character> lifo;
	private static int counts = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			lifo = new ArrayDeque<>();
			counts = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			String b1 = st.nextToken();
			String b2 = st.nextToken();
			
			int leng1 = b1.length();
			int leng2 = b2.length();
			int leng = Math.max(leng1, leng2);
			
			char[] bin1 = new char[leng];
			char[] bin2 = new char[leng];
			
			if(leng1 > leng2) {										// 길이에 따른 setArray 메소드 호출
				setArray(leng, b1, b2, bin1, bin2, leng1, leng2);
			}
			else {
				setArray(leng, b2, b1, bin2, bin1, leng2, leng1);
			}

			sb.append(output(leng));	// 결과를 뽑아낼 메소드 호출
		}
		
		System.out.println(sb);			// 결과 값 한번에 출력 
	}
	
	/**
	 * 두 이진수의 길이가 다른경우 짧은 이진수의 앞부분을 0으로 채워주는 메소드
	 * 
	 * @param leng: 두 이진수 중 긴 이진수의 길이
	 * @param oneBin: 처음 입력받은 긴 이진수
	 * @param anotherBin: 처음 입력받은 짧은 이진수
	 * @param arr1: 긴 이진수
	 * @param arr2: 짧은 이진수
	 * @param leng1: 긴 이진수의 길이
	 * @param leng2: 짧은 이진수의 길이
	 */
	private static void setArray(int leng, String oneBin, String anotherBin, char[] arr1, char[] arr2, int leng1, int leng2) {
		int limit = leng - leng2;
		
		for(int i = 0; i < leng; i++) {
			arr1[i] = oneBin.charAt(i);
			
			if(i < limit) arr2[i] = ZERO;					// 짧은 이진수 배열 앞에 0 입력
			else arr2[i] = anotherBin.charAt(i - limit);
		}
		
		stackConsist(leng, arr1, arr2);			// 스택 구성 메소드 호출
	}
	
	/**
	 * 스택 구성 메소드
	 * 
	 * @param leng
	 * @param arr1
	 * @param arr2
	 */
	private static void stackConsist(int leng, char[] arr1, char[] arr2) {
		int carry = 0;
		
		for(int i = leng - 1; i >= 0; i--) {
			int sum = (arr1[i] - ZERO) + (arr2[i] - ZERO);		// sum -> 0 + 1 or 1 + 1 or 0 + 0 세가지 조건으로 나뉨
			carry = options(carry, sum);						// options 메소드를 통해 carry 갱신
		}
		
		if(carry == 1) lifo.push(ONE);			// 마지막 연산 중 캐리가 발생 할 경우
	}
	
	/**
	 * 각 연산에 따라 조건을 나누어 연산
	 * 
	 * @param carry
	 * @param opt
	 * @return carry 갱신 값
	 */
	private static int options(int carry, int opt) {
		if(opt == 0) counts++;						// 이진수에 포함된 0의 갯수를 세어줌
		
		if(carry == 0) {
			lifo.push((char) ((opt % 2) + ZERO));	// carry에 따른 sum의 경우의수가 2가지뿐이므로 2로 나눈 나머지를 통해 스택에 값을 갱신
			
			if(opt == 2) carry = 1;
		}
		else {
			lifo.push((char) (((opt + 1) % 2) + ZERO));
			
			if(opt == 0) carry = 0;
			else carry = 1;
		}
		
		return carry;
	}
	
	/**
	 * 결과 출력 메서드
	 * 
	 */
	private static String output(int leng) {
		StringBuilder tmp = new StringBuilder();
		
		if(counts == leng) {				// 모든 값이 0인 경우
			tmp.append(0);
		}
		else {
			boolean isOne = false;
			
			while(!lifo.isEmpty()) {
				if(lifo.peek() == ONE) isOne = true; 
				
				if(isOne) tmp.append(lifo.pop());		// 1이 나오기전까진 앞자린 필요없으므로 다 버림
				else lifo.pop();
			}
		}

		tmp.append(NEW_LINE);
		return tmp.toString();
	}
}
