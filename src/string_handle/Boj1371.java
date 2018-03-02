package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1371번 : 가장 많은 글자
 *
 *	@see https://www.acmicpc.net/problem/1371
 *
 */
public class Boj1371 {
	public static void main(String[] args)throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] alpha = new int[26];
		String sent;
		
		while((sent = br.readLine()) != null){		// 더이상 입력이 없을시 반복문 종료
			int loop = sent.length();						/* 참고 : 본 문제 예제는 돌려보면 반복문을 빠져나오지 않는 경우가 발생하는데, 
																	  	본인이 임의로 반복문 빠져나올 수 있도록 처리하고 테스트 해보길 권장함 		*/
			for(int i = 0; i < loop; i++){
				if(sent.charAt(i) != ' '){					// 공백을 제외하고
					alpha[sent.charAt(i) - 97]++;		// 각 알파벳에 해당하는 26크기의 배열의 인덱스에 순서대로 +1 
				}
			}
		}
		
		br.close();
		
		int max = getMax(alpha);						// 가장 많이나온 알파벳의 갯수를 도출
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < alpha.length; i++){
			if(alpha[i] == max){
				sb.append((char) (i + 97));				// 맥스와 같은 값을 갖는 인덱스를 가져와서 + 97 (ASCII 'a') : 다시 알파벳으로 바꿔줌
			}
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
	
	/**
	 * 
	 * 최댓값 반환 메소드
	 * 	@param nums : 배열 내 가장 큰 값을 찾아내야함
	 * 	@return : 최댓값 반환
	 * 
	 */
	public static int getMax(int[] nums){
		int res = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] > res){
				res = nums[i];
			}
		}
		
		return res;
	}
}
