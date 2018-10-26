package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 12780번: 원피스
 *
 *	@see https://www.acmicpc.net/problem/12780/
 *
 */
public class Boj12780 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] H = br.readLine().toCharArray();
		char[] N = br.readLine().toCharArray();
		
		int res = 0;
		
		for(int i = 0; i < H.length; i++) {
			if(H[i] == N[0]) {					// 찾으려는 글자의 첫 글자와 같은 글자가 발견되면 진행
				boolean isSame = true;
				
				for(int j = 1; j < N.length; j++) {		// 찾으려는 글자와 모두 동일한지 확인함
					if(i + j >= H.length || H[i + j] != N[j]){			// 길이가 주어진 비교 문자열의 길이를 초과하거나, 둘의 문자가 하나라도 다른경우	
						isSame = false;									// 다른 문자열이므로 거짓처리 후 반복문 종료
						break;
					}
				}
				
				if(isSame) {				// 같은 문자열인경우
					res++;					// 갯수 증가
					i += (N.length - 1);	// 같은 문자열이었으니 그 후부터 검사하면 되므로 찾는 문자열의 길이만큼 더해주고 반복문 재 시작
				}
			}
		}
		
		System.out.println(res);			// 찾는 문자열의 총 갯수를 출력
	}
}
