package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11507번: 카드 셋트
 *
 *	@see https://www.acmicpc.net/problem/11507
 *
 */
public class Boj11507 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] cards = br.readLine().toCharArray();
		
		System.out.println(cardCheck(cards));
	}
	
	private static StringBuilder cardCheck(char[] arr) {
		StringBuilder sb = new StringBuilder();
		int[] count = {13, 13, 13, 13};
		boolean[][] check = new boolean[4][14];
		
		for(int i = 0; i < arr.length; i += 3) {
			int num = 0;
			int index = 0;
			
			for(int j = i; j < i + 3; j++) {				
				if(j % 3 == 0) {
					char w = arr[j];
					
					switch(w) {
					case 'P':
						index = 0;
						break;
						
					case 'K':
						index = 1;
						break;
						
					case 'H':
						index = 2;
						break;
						
					case 'T':
						index = 3;
						break;
					}
				}
				else if(j % 3 == 1) {
					num += (arr[j] - '0') * 10;
				}
				else {
					num += (arr[j] - '0');
				}
			}
			
			if(check[index][num]) return sb.append("GRESKA");
			check[index][num] = true;
			
			count[index]--;
		}
		
		
		return sb.append(count[0]).append(SPACE).append(count[1]).append(SPACE).append(count[2]).append(SPACE).append(count[3]);
	}
}
