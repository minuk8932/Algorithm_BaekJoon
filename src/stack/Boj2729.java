package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
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
	
	private static Stack<Character> lifo;
	private static int counts = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			lifo = new Stack<>();
			counts = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			String b1 = st.nextToken();
			String b2 = st.nextToken();
			
			int leng1 = b1.length();
			int leng2 = b2.length();
			int leng = Math.max(leng1, leng2);
			
			char[] bin1 = new char[leng];
			char[] bin2 = new char[leng];
			
			if(leng1 > leng2) {							// 길이가 안맞는 이진수의 길이를 맞춰줌
				setArray(leng, b1, b2, bin1, bin2, leng1, leng2);
			}
			else {
				setArray(leng, b2, b1, bin2, bin1, leng2, leng1);
			}

			sb.append(output(leng));
		}
		
		System.out.println(sb);			// 결과 값 한번에 출력 
	}
	
	private static void setArray(int leng, String oneBin, String anotherBin, char[] arr1, char[] arr2, int leng1, int leng2) {		
		for(int i = 0; i < leng; i++) {
			arr1[i] = oneBin.charAt(i);
			
			if(i < leng - leng2) arr2[i] = ZERO;
			else arr2[i] = anotherBin.charAt(i - (leng - leng2));
		}
		
		stackConsist(leng, arr1, arr2);
	}
	
	private static void stackConsist(int leng, char[] arr1, char[] arr2) {
		int carry = 0;
		
		for(int i = leng - 1; i >= 0; i--) {		// 조건에 따라 스택에 들어갈 값을 결정
			int sum = (arr1[i] - ZERO) + (arr2[i] - ZERO);
			carry = options(carry, sum);
		}
		
		if(carry == 1) lifo.push(ONE);			// 마지막 연산 중 캐리가 발생 할 경우
	}
	
	private static int options(int carry, int opt) {
		if(opt == 0) counts++;
		
		if(carry == 0) {
			lifo.push((char) ((opt % 2) + ZERO));
			
			if(opt == 2) carry = 1;
		}
		else {
			lifo.push((char) (((opt + 1) % 2) + ZERO));
			
			if(opt == 0) carry = 0;
			else carry = 1;
		}
		
		return carry;
	}
	
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
