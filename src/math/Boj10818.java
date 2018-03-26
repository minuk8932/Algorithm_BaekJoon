package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10818 번 : 최소, 최대
 *
 *	@see https://www.acmicpc.net/problem/10818/
 *
 */
public class Boj10818 {
	private static final String SPACE = " ";
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;;
		
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			for(int i = 0; i < N; i++){	
				int tmp = Integer.parseInt(st.nextToken());		// 값을 하나씩 받고
				
				if(tmp < max && tmp > min){							// 최대나 최소가 아닌 경우 다음 반복문
					continue;
				}
				
				if(max <= tmp){												// 저장된 max보다 큰 tmp가 들어온 경우
					max = tmp;
				}			
				
				if(min >= tmp){												// 저장된 min보다 작은 tmp가 들어온 경우
					min = tmp;
				}
			}
			
			System.out.println(min + " " + max);					// 결과값 한번에 출력
	}
}
