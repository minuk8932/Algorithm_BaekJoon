package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 1339번: 단어 수학
 *
 *	@see https://www.acmicpc.net/problem/1339/
 *
 */
public class Boj1339 {
	private static final char NULL = '\u0000';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] alpha = new char[10];
		int[] value = new int[10];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			int size = line.length();
			
			int cost = 1;
			for(int j = size - 1; j >= 0; j--) {
				char word = line.charAt(j);
				
				for(int k = 0; k < 10; k++) {
					if (alpha[k] == word) {		// 숫자가 입력된 경우
                        value[k] += cost;
                        break;
                    }
					
					if (alpha[k] == NULL) {		// 빈 공간인 경우
                    	alpha[k] = word;
                    	value[k] = cost;
                        break;
                    }
				}
				
				cost *= 10;
			}
		}

		System.out.println(getMax(value));
	}
	
	private static int getMax(int[] arr) {
		Arrays.sort(arr);
		
		int max = 0;
		for(int i = arr.length - 1; i >= 0; i--) {
			max += i * arr[i];
		}
		
		return max;
	}
}
