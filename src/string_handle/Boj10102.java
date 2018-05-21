package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10102번: 개표
 *
 *	@see https://www.acmicpc.net/problem/10102/
 *
 */
public class Boj10102 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		
		int[] vote = new int[2];					// 투표수 저장 배열

		for(char ref : br.readLine().toCharArray()){	// 문자열을 문자로 하나씩 쪼개어 받아와서
			if(ref == 'A'){			// 값 비교 후 각 배열에 +1 씩 추가
				vote[0]++;
			}
			else{
				vote[1]++;
			}
		}
		
		System.out.println(vote[0] == vote[1] ? "Tie" : (vote[0] > vote[1] ? "A" : "B"));		// 동률일 경우 Tie 0번 인덱스가 크면 A 1번 인덱스가 크면 B 출력
	}
}
