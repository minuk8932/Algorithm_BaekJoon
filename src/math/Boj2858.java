package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2858번: 기숙사 바닥
 *
 *	@see https://www.acmicpc.net/problem/2858/
 *
 */
public class Boj2858 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int size = 0;
		int L = 1, W = 0;
		
		StringBuilder sb = new StringBuilder();
		
		for(; ; L++) {						// 한변의 값을 계속 더해가며
			if(B % L == 0) {				// 약수를 찾아냄
				W = B / L;
				size = L * 2 + W * 2 + 4;	// 찾아낸 값을 이용해 테두리 크기를 구한 후
				
				if(size == R) {			// 그 크기가 R의 갯수와 같다면
					if(L < W) {
						int tmp = L;
						L = W;
						W = tmp;
					}
					
					sb.append(L + 2).append(SPACE).append(W + 2);	// 버퍼에 값을 담아줌
					break;
				}
			}
		}
		
		System.out.println(sb.toString());	// 결과값 출력
	}
}
