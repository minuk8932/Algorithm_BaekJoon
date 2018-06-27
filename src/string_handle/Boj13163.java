package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 13163번: 닉네임에 갓 붙이기
 *
 *	@see https://www.acmicpc.net/problem/13163/
 *
 */
public class Boj13163 {
	private static final char SPACE = ' ';
	private static final String GOD = "god";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			String line = br.readLine();
			boolean isFirst = false;
			
			for(char tmp : line.toCharArray()) {	// 문자열을 끊어 받아가며,
				if(tmp == SPACE && !isFirst) {		// 첫번째 공백이 나오면
					isFirst = true;
					sb.append(GOD);				// god을 버퍼에 담고
				}
				
				if(isFirst && tmp != SPACE) {	// 이후로 공백을 제외한 모든 문자를 버퍼에 담음
					sb.append(tmp);
				}
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
