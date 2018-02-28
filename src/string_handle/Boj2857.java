package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 * 	백준 2857번 : FBI
 * 
 * @see https://www.acmicpc.net/problem/2857
 *		
 */

public class Boj2857 {
	private static final String SPACE = " ";
	private static final String NO_ONE = "HE GOT AWAY!";
	private static final String FBI = "FBI";
	
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();				
		String[] name = new String[6];
		
		for(int i = 1; i < 6; i++){						// 5명의 이름을 하나씩 받으면서
			name[i] = br.readLine();
			
			if(name[i].contains(FBI)){				// 해당 이름에 FBI가 존재한다면
				sb.append(i).append(SPACE);		// 그 순번을 버퍼에 담아줌
			}
		}
		
		System.out.println(sb.length() == 0 ? NO_ONE : sb.toString());		// 버퍼가 비어있으면 'HE GOT AWAY!' 를 출력하고, 값이 있으면 해당 순번 출력
	}

}
