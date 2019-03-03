package two_pointer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16472번: 고냥이
 *
 *	@see https://www.acmicpc.net/problem/16472/
 *
 */
public class Boj16472 {
	private static boolean[] alpha;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] word = br.readLine().toCharArray();
		
		System.out.println(word.length == 1 ? 1 : getCatLanguageLength(N, word, 0, getdiff(N, word)));
	}
	
	private static int getdiff(int limit, char[] arr) {		
		int count = 0, pos = -1;
		int index = 0;
		
		while(true) {
			if(index == arr.length - 1) break;
			
			while(arr[index] != arr[index + 1]){					// 서로 다른 경우 길이 확인
				count++;
				
				if(count == limit || index == arr.length - 2) {		// 제한길이에 도달한 경우, 또는 배열 끝까지 탐색 완료
					pos = index;
					break;
				}
				
				index++;
			}
			
			if(pos != -1) break;
			index++;
		}

		return pos == -1 ? arr.length - 1 : pos;
	}
	
	private static int getCatLanguageLength(int limit, char[] arr, int start, int end) {
		int max = 0;

		while(end != arr.length) {			// 마지막 인덱스가 배열의 가장 끝이 아닌 경우
			int count = 0;
			alpha = new boolean[26];
			
			for(int i = start; i <= end; i++) {
				int idx = arr[i] - 'a';
				
				if(!alpha[idx]) count++;		// 존재하는 연속적인 알파벳의 길이를 찾음
				alpha[idx] = true;
			}
			
			if(count <= limit) end++;			// 투 포인터
			else start++;
			
			max = Math.max(max, end - start);
		}
		
		return max;
	}
}
