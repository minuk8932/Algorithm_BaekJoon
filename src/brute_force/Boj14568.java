package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14568번: 2017 연세대학교 프로그래밍 경진대회
 *
 *	@see https://www.acmicpc.net/problem/14568/
 *
 */
public class Boj14568 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        
        System.out.println(count(N));
	}
    
    private static int count(int n){
        int count = 0;
        
        for(int i = 2; i <= n; i+= 2){				// 짝수로 가져가는 경우
            for(int j = n - i; j > 0; j--){			// 남은 것 중
                int diff = n - (i + j);
                if(diff != 0 && diff + 2 <= j) count++;		// 나머지 한 친구보다 2개이상 더 많이 가져가면서 각각 1개이상의 사탕을 가진 경우
            }
        }
        
        return count;
    }
}
