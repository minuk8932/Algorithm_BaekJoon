package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 12904번: A와 B
 *
 *	@see https://www.acimcpc.net/problem/12904/
 *
 */
public class Boj12904 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		
		System.out.println(compare(S.toCharArray(), T.toCharArray()));
	}
	
	private static int compare(char[] start, char[] target) {
		int length = start.length;
		int tLeng = target.length;
		
		while(length <= tLeng) {						// 쿼리 역 연산
			char tail = target[tLeng - 1];				// 맨 뒤에 하나 빼줌
			target[tLeng - 1] = ' ';
			
			if(tail == 'B') {							// 맨 뒤 문자가 B인 경우 뒤집기
				char[] tmp = new char[tLeng - 1];
				
				for(int i = 0; i < tmp.length; i++) {
					tmp[i] = target[tLeng - 2 - i];
				}
				
				for(int i = 0; i < tmp.length; i++) {
					target[i] = tmp[i];
				}
			}
			
			tLeng--;
			if(tLeng == length && arrayEquals(target, start)) return 1;
		}
		
		return 0;
	}
	
	private static boolean arrayEquals(char[] arr1, char[] arr2) {
		for(int i = 0; i < arr2.length; i++) {
			if(arr1[i] != arr2[i]) return false;
		}
		
		return true;
	}
}
