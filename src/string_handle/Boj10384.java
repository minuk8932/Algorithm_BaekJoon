package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10384번: 팬그램
 *
 *	@see https://www.acmicpc.net/problem/10384/
 *
 */
public class Boj10384 {
	private static final String NOT_P = ": Not a pangram";
	private static final String P = ": Pangram!";
	private static final String DOUBLE_P = ": Double pangram!!";
	private static final String TRIPLE_P = ": Triple pangram!!!";
	private static final String CASE = "Case ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int time = 0;
		
		StringBuilder sb = new StringBuilder();
		
		while(n-- > 0){
			int[] pangram = new int[26];	// 팬그램인지 확인할 정수형 배열
			boolean isT = false;			// triple
			boolean isD = false;		// double
			boolean isU = false;		// uni
			boolean isZ = false;		// not
			
			for(char word : br.readLine().toCharArray()){		// 문자를 하나씩 받아와 인덱스로 변환해 +1
				if(word >= 'a' && word <= 'z'){
					pangram[word - 97]++;
				}
				else if(word >= 'A' && word <= 'Z'){
					pangram[word - 65]++;
				}
			}
			
			for(int i = 0; i < pangram.length; i++){
				if(pangram[i] == 0){			// 0이 하나라도 존재 할 때
					isZ = true;
					break;
				}
				else if(pangram[i] == 1){		// 1이 존재 할 때
					isU = true;
				}
				else if(pangram[i] == 2){		// 2가 존재 할 때
					isD = true;
				}
				else{						// 3 이상인 경우
					isT = true;
				}
			}
			
			time++;				// 케이스 +1
			
			// 각 경우에 따른 결과를 버퍼에 담아줌
			if(isZ){
				sb.append(CASE).append(time).append(NOT_P).append(NEW_LINE);		// Not
				continue;
			}
			else{
				if(!isU && !isD){
					sb.append(CASE).append(time).append(TRIPLE_P).append(NEW_LINE);		// Triple
					continue;
				}
				
				if(!isU && isD){
					sb.append(CASE).append(time).append(DOUBLE_P).append(NEW_LINE);	// Double
					continue;
				}
				
				sb.append(CASE).append(time).append(P).append(NEW_LINE);		// Uni
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
