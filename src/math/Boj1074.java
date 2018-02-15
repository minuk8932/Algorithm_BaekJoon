package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1074번 : Z
 *
 *	@see https://www.acmicpc.net/problem/1074
 *
 */
public class Boj1074 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		//버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		br.close();
		
		int res = 0;
		int cntZ = (int) Math.pow(2, N) / 2;			
		int calZ = cntZ;										// Z에서 원하는 위치를 찾아 줄 계산된 값
		
		while(N-- > 0){
			int tmp = (int) Math.pow(2, N) / 2;
			int passBlock = (int) Math.pow(4, N);	// 지나칠 사분면의 갯수에 따른 값 추가
			
			// 위치별 값에 따라 크기에 맞는 수치를 합 또는 차
			if (r < cntZ && calZ < c + 1) {				// 제 1사분면
	            calZ += tmp;									
	            cntZ -= tmp;									
	            
	            res += passBlock;
	        }
			else if (r < cntZ && c < calZ) {				// 제 2사분면 (시작지점)
	            calZ -= tmp;
	            cntZ -= tmp;
	        }  
			else if (cntZ < r + 1 && c < calZ) {			// 제 3사분면
	            calZ -= tmp;
	            cntZ += tmp;
	            
	            res += passBlock * 2;
	        } 
			else {												// 제 4사분면
	            calZ += tmp;
	            cntZ += tmp;
	            
	            res += passBlock * 3;
	        }
		}
		
		System.out.println(res);							// 결과값 출력
	}
}
