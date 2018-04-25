package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2774번 : 아름다운 수
 *
 *	@see https://www.acmicpc.net/problem/2774/
 *
 */
public class Boj2774 {
	private static final String END_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			boolean[] beauty = new boolean[10];							// 0~9 까지 해당하는 정수의 출현을 알려줄 배열
			String num = br.readLine();
			
			for(char tmp : num.toCharArray()){							// 숫자를 한개씩 향상된 포문으로 받아와
				beauty[Character.getNumericValue(tmp)] = true;	// 해당 인덱스 값을 참으로 바꿔주고
			}
			
			sb.append(res(beauty)).append(END_LINE);					// 결과 메소드를 통한 답을 버퍼에 담음
		}
		
		System.out.println(sb.toString());				// 결과값 한번에 출력
	}
	
	/**
	 * 결과 값 메소드
	 * 
	 * @param arr	 몇개의 수를 포함하고있는지 담은 배열
	 * @return			아름다운 정도를 반환
	 */
	private static int res(boolean[] arr){
		int cnt = 0;
		
		for(int i = 0; i < arr.length; i++){		// 배열 내부가 참의 값이면 아름다움을 증가시켜줌
			if(arr[i]){
				cnt++;
			}
		}
		
		return cnt;		// 아름다운 정도 반환
	}
}
