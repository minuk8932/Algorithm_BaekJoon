package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 11656번 : 접미사 배열
 *
 *	@see https://www.acmicpc.net/problem/11656
 *
 */
public class Boj11656 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		String[] res = new String[S.length];
		br.close();
		
		for(int i = 0; i < S.length; i++){
			for(int j = i; j < S.length; j++){
				if(j == i){
					res[i] = String.valueOf(S[j]);		// null 값 제거를 위한 처음 받는 값은 할당
				}
				else{
					res[i] += S[j];								// 이후 값들은 뒤에 붙여줌
				}
			}
		}
		Arrays.sort(res);									// 배열 정렬
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < S.length; i++){
			sb.append(res[i]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}
