package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2941번 : 크로아티아 알파벳
 *
 *	@see https://www.acmicpc.net/problem/2941
 *
 */
public class Boj2941 {
	private static final String[] C_ALPHA = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
	private static final String SPACE = " ";
	private static final char CH_SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		//버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cAlpha = br.readLine();
		br.close();
		
		int alphaCnt = 0;
		int cLeng = cAlpha.length();									// 문제에서 주어진 단어의 길이

		for(final String CHK : C_ALPHA){								// 선언해둔 스트링 배열을 가져와서
			for(int i = 0; i < cLeng/2; i++){								// 문제 알파벳의 최소크기(2)에서 단어의 길이를 나눈 값 만큼 반복, 즉 단어를 빼먹지 않을 만큼의 최대 횟수 반복
				if(cAlpha.contains(CHK)){								// 주어진 단어 내에 크로아티아 알파벳이 존재하는가?
					cAlpha = cAlpha.replaceFirst(CHK, SPACE);	// 한다면 최초 해당 단어를 SPACE로 바꾸어주고
					alphaCnt++;												// 알파벳 카운트 +1
				}
			}
		}
		
		cLeng = cAlpha.length();											// 줄어든 알파벳의 길이를 다시 받아온 후
		
		for(int i = 0; i < cLeng; i++){									// 공백을 제외한 남은 알파벳의 갯수를 계산하여 알파 카운트에 더해주고
			if(cAlpha.charAt(i) != CH_SPACE){
				alphaCnt++;
			}
		}
		
		System.out.println(alphaCnt);									// 결과값 출력
	}
}
