package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1213번: 펠린드롬 만들기
 *
 *	@see https://www.acmicpc.net/problem/1213/
 *
 */
public class Boj1213 {
	private static final String WRONG = "I'm Sorry Hansoo";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int[] name = new int[26];
		int isPossible = 0;
		int leng = line.length();
		
		for(char word : line.toCharArray()){	// name 배열에 등장하는 알파벳 갯수를 채워줌
			name[word - 65]++;
		}
		
		int idx = 0;
		
		for(int i = 0; i < name.length; i++){			// 홀수개가 1개를 넘어가면 펠린드롬 생성이 불가하므로
			if(name[i] % 2 != 0){				// 홀수개가 등장하면 isPossible + 1
				isPossible++;					// 해당 인덱스를 저장, isPossible이 1인 경우에만 사용 할 것
				idx = i;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(isPossible > 1){			// 홀수개가 1개보다 많으면 펠린드롬 생성 불가
			sb.append(WRONG);
		}
		else{
			for(int i = 0; i < name.length; i++){
				if(name[i] != 0){
					for(int j = 0; j < name[i] / 2; j++){		// 해당 알파벳들의 절반만큼을 버퍼에 담아줌
						sb.append((char) (i + 65));
					}
				}
			}
			
			String head = " ";
			
			if(leng % 2 == 1) {					// 만약 첫 입력의 길이가 홀수였다면
				sb.append((char) (idx + 65));			// 그때의 홀수개 알파벳 하나를 버퍼에 담고
				head = sb.substring(0, sb.length() - 1);	// head 변수에 맨뒤의 알파벳 하나를 뺀 값을 담아줌
			}
			else {
				head = sb.toString();		// 입력의 길이가 짝수인 경우엔 바로 head 변수에 버퍼의 값을 담아줌
			}
			
			
			int rLeng = head.length();
			for(int i = rLeng - 1; i >= 0; i--){	// head값을 역순으로 버퍼에 담고
				sb.append(head.charAt(i));
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
