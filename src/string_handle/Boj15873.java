package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15873번: 공백 없는 A + B
 */
public class Boj15873 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		int leng = line.length();
		
		if(leng == 4) {		// 길이가 4인 경우: 1010 -> 20
			sb.append(20);
		}
		else {				// 그 외
			int num = 0;
			int res = 0;
			
			for(char tmp: line.toCharArray()) {
				if(tmp == '0') {	// 2. 더하는 중에, 만약 뒤에 0이 나오는경우 -> 앞에 res로 더한 num을 빼고, 10을 더하고 다음으로 패스
					res -= num;
					res += 10;
					
					continue;
				}
				
				num = (tmp - '0');	// 1. 문자를 숫자로 변형후 num에 그 값을 res에 계속 더함
				res += num;
			}
			
			sb.append(res);			// res를 버퍼에 담고
		}		
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
