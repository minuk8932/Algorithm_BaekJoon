package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4378번: 트ㅏㅊ;
 *
 *	@see https://www.acmicpc.net/problem/4378/
 *
 */
public class Boj4378 {
	private static final String[] ARR = {"`1234567890-=", "QWERTYUIOP[]\\", "ASDFGHJKL;'",
			"ZXCVBNM,./", " "};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		
		while((line = br.readLine()) != null) {
			StringBuilder sb = new StringBuilder();
			
			int leng = line.length();
			
			for(int idx = 0; idx < leng; idx++) {
				if(line.charAt(idx) == ' ') {
					sb.append(" ");
					continue;
				}
				
				for(int i = 0; i < ARR.length; i++) {
					if(ARR[i].contains(line.charAt(idx) + "")) {		// 문장에 존재할 경우
						int size = ARR[i].length();
						
						for(int j = 0; j < size; j++) {					// 앞의 단어로 바꾸어서 버퍼 저장
							if(ARR[i].charAt(j) == line.charAt(idx)) sb.append(ARR[i].charAt(j - 1));
						}
					}
				}
			}
			
			System.out.println(sb);
		}
	}
}
