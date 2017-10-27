import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2579 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int stairs = Integer.parseInt(br.readLine());
		
		int[] p = new int[stairs + 1];
		for(int i = 1; i <= stairs; i++){
			p[i] = Integer.parseInt(br.readLine());
		}
		br.close();

		int[][] dp = new int[stairs + 1][2];
		
		/*
		 *  i번째 계단을 밟고 있는데, 이 계단은 이전으로 부터 j번 연속한 계단이다. 이 때의 최대 점수?
		 *  i++, i += 2
		 *  
		 */
		
		
		for(int i = 3; i < stairs + 1; i++){
			for(int j = 1; j <= i; j++){
				
				
				
			}			
		}
		
		
	}

}
