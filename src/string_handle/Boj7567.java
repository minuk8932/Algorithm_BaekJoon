package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 7567번 : 그릇
 *
 *	@see https://www.acmicpc.net/problem/7567/
 *
 */
public class Boj7567 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] dishes = br.readLine().toCharArray();
		int high = 10;		// 첫 그릇의 크기는 무조건 10
		
		for(int i = 0; i < dishes.length - 1; i++){		// 첫 그릇을 제외하고 나머지 그릇들의 높이를 각 그릇이 놓인 모양에따라 더해줌
			if(dishes[i] == dishes[i + 1]){
				high += 5;
			}
			else{
				high += 10;
			}
		}
		
		System.out.println(high);			// 그릇의 총 높이 출력
	}
}
