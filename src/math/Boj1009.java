package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 1009번 : 분산처리
 *
 *	@see https://www.acmicpc.net/problem/1009
 *
 */

public class Boj1009 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int TEN = 10;					// 값들을 나누어줄 기준
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		double[][] variance = new double[TEN + 1][5];														// 각 숫자별 뒷자리 계산 결과가 들어갈 배열
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			a %= TEN;		// a가 10을 넘어갔을 시
			if(a == 0){		// 만약 a가 10에 나누어 떨어진다면 처리해 줄 조건식
				a = 10;
			}
			
			for(int i = 1; i < 5; i++){							// 각 숫자 a별(1~10까지) 배열 1~4까지 뒷자리를 넣어줌
				double tmp = (int) (Math.pow(a, i));		// (제곱수 별 가장 많은 뒷자리의 경우의 수를 가진 수의 개수는 4개이므로)
				
				if(tmp > TEN - 1){								// 만약 위에서 pow 계산이 10을 넘는다면 다시 1~10 사이의 숫자로 조정
					tmp %= TEN;
					variance[a][i] = (int) tmp;
				}
				else{													// 넘지 않으면 바로 값 할당
					variance[a][i] = tmp;
				}
			}
			
			b %= 4;													// 만약 4로 나누어 떨어지는 b가 있다면 0 이아닌 4가 들어가도록 처리
			if(b == 0){
				b = 4;
			}
			
			int res = (int)variance[a][b];
			
			if(res == 0){																// 결과값이 0이라면 10의 제곱수 이므로 바로 10 버퍼에 입력
				sb.append(TEN).append(NEW_LINE);
			}
			else{																			// 그 외 : 계산식대로 버퍼에 입력
				sb.append((int)variance[a][b]).append(NEW_LINE);
			}
		}
		br.close();
		
			System.out.println(sb.toString());								// 버퍼에 저장된 테스트 케이스의 결과 값들 출력
		}
}
