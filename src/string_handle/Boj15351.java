package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15351번 : 인생 점수
 *
 *	@see https://www.acmicpc.net/problem/15351/
 *
 */
public class Boj15351 {
	private static final String HUNDRED = "PERFECT LIFE";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args)throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(N-- > 0){
			int[] alpha = new int[27];		// 각 알파벳 별로 갯수를 담을 배열
			int lifeScore = 0;
			
			String life = br.readLine();
			int loop = life.length();
			for(int i = 0; i < loop; i++){
				char tmp = life.charAt(i);		// 자리별로 하나씩 뽑아
				
				if(tmp >= 'A' && tmp <= 'Z'){	// 알파벳만 계산하는데
					alpha[tmp - 64]++;				// 이 때의 값들을 A(65)는 1,  Z(90)는 26으로 변환해 각 배열 인덱스에 맞춰 +1
				}
			}
			
			for(int i = 1; i < alpha.length; i++){
				if(alpha[i] != 0){
					lifeScore += (alpha[i] * i);		// 해당 배열 인덱스와 배열 내용을 이용해 인생 점수를 합산
				}
			}
			
			sb.append(lifeScore == 100 ? HUNDRED : lifeScore).append(NEW_LINE);	// 결과를 버퍼에 담는데, 100점일 경우엔 PERFECT LIFE로 바꾸어 출력
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
