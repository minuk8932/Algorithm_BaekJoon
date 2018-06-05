package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	2495번: 연속구간
 *
 *	@see https://www.acmicpc.net/problem/2495/
 *
 */
public class Boj2495 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int loop = 3;
		
		StringBuilder sb = new StringBuilder();
		
		while(loop-- > 0) {
			String line = br.readLine();
			int size = line.length() + 1;
			int idx1 = 10, idx2 = 0;
			int max = 0;
			
			char[] num = new char[size];
			num = line.toCharArray();
			
			for(int i = 0; i < num.length - 1; i++) {
				if(num[i] == num[i + 1]) {			// 현재와 다음의 값이 같은 경우
					idx1 = Math.min(idx1, i);		// 해당하는 최소와 최대 인덱스를 계속해서 입력
					idx2 = Math.max(idx2, i + 1);
				}
				else {								// 다른 숫자가 나온 경우
					int leng = idx2 - idx1 + 1;
					
					if(max < leng) max = leng;		// 최대 길이를 뽑아서 최대값에 넣어주고 인덱스 값 초기화
					idx1 = 10;
					idx2 = 0;
				}
			}
			
			if(idx2 == num.length - 1) {			// 마지막에 같은 숫자가 나올경우
				if(max < idx2 - idx1 + 1) max = idx2 - idx1 + 1;	// 그 중 마지막에 같은 숫자가 나온 경우의 길이가 최대일 때
			}
			
			sb.append(max == 0 ? 1 : max).append(NEW_LINE);	// 최대 길이가 0이면 1을 나머지는 해당 최댓값을 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
