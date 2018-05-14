package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2153번: 소수 단어
 *
 *	@see https://www.acmicpc.net/problem/2153/
 *
 */
public class Boj2153 {
	private static final int INF = 2_000;
	private static final String RIGHT = "It is a prime word.";
	private static final String WRONG = "It is not a prime word.";
	
	private static boolean[] isPrime = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String words = br.readLine();
		
		int[] alpha = new int[53];
		for(int i = 1; i < alpha.length; i++){		// 알파벳 배열에 해당하는 숫자를 채워줌
			alpha[i] = i;
		}
		
		int alphaVal = 0;
		isPrime = new boolean[INF];			// 소수 판별 배열, 소수면 false, 소수가 아니면 true 설정하였음.
		
		for(char word : words.toCharArray()){	// 단어하나씩 받아온 후
			if(word >= 'a' && word <= 'z'){		// 조건에 맞는 단어에 해당하는 수치를 alphaVal 값에 더해줌
				alphaVal += alpha[word - 96];
			}
			else{
				alphaVal += alpha[word - 38];
			}
		}
		
		eratosthenesSieve();		// 에라토스테네스의 체 알고리즘 실행

		System.out.println(isPrime[alphaVal] ? WRONG : RIGHT);		// 해당 값이 소수라면 "It is a prime word."를 아니면 "It is not a prime word."를 출력
	}
	
	private static void eratosthenesSieve(){		// 에라토스테네스의 체 응용 메소드
		for(int i = 2; i < INF; i++){				// 1을 소수로 칭하기로 했으므로 2부터 시작하는데
			if(isPrime[i]) continue;				// 이미 소수가 아닌것으로 판별 난 것은 넘기고
			
			for(int j = i + i; j < INF; j += i){		// 그외의 소수가 아닌것들에 ture를 넣어줌
				isPrime[j] = true;
			}
		}
	}
}
