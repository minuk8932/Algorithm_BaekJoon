package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10250번 : ACM 호텔
 *
 *	@see https://www.acmicpc.net/problem/10250/
 *
 */
public class Boj10250 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String ZERO = "0";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			int cnt = 0, floor = 0, ho = 0;
			boolean[][] hotel = new boolean[H + 1][W + 1];
			
			for(int i = 1; i < W + 1; i++){
				for(int j = 1; j < H + 1; j++){		// 각 호텔이 방이 채워지는 순서대로 반복문을 구현하고
					cnt++;
					
					if(cnt <= N){						// 방이 N까지 채워질때마다 값을 참으로 바꿔줌
						hotel[j][i] = true;
					}
				}
			}
			
			for(int i = 1; i < W + 1; i++){
				for(int j = 1; j < H + 1; j++){					
					if(hotel[j][i]){					// 방이 채워져 있는 것 중에 가장 마지막 방의 번호를 변수에 담아줌
						floor = j;
						ho = i;
					}
				}
			}
			
			sb.append(floor).append(ho < 10 ? ZERO + ho : ho).append(NEW_LINE);		// 호수가 10이하이면 앞에 0을 아니라면 층수와 호수를 순서대로 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
