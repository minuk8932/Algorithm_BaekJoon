package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * 	@author minchoba
 *	백준 1251번: 단어 나누기
 *
 *	@see https://www.acmicpc.net/problem/1251/
 *
 */
public class Boj1251 {
	private static ArrayList<String> arr = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String res = split(br.readLine());	// 나누기 메서드를 통해 최종 문자열을 받아옴
		
		System.out.println(res);		// 결과 값 출력
	}

	private static String split(String input) {
		String[] w = new String[3];
		int leng = input.length();

		for (int i = 1; i < leng - 1; ++i) {			// 첫번째로 나올 수 있는 작은 알파벳까지
			for (int j = i + 1; j < leng; ++j) {		// 그다음으로 나올 수 있는 작은 알파벳까지

				w[0] = input.substring(0, i);		// 3 구간 설정
				w[1] = input.substring(i, j);
				w[2] = input.substring(j, leng);
				
				String tmp = "";
				
				for (int idx = 0; idx < 3; ++idx) {		// 가능한 경우의 수를 구해서
					int wLeng = w[idx].length();
					
					for (int wAt = wLeng - 1; wAt >= 0; --wAt) {
						tmp += w[idx].charAt(wAt);
					}
				}

				arr.add(tmp);		// 리스트에 담고
			}
		}

		Collections.sort(arr);		// 그 중 가장 앞에 올 수 있는 문자열을 가져와서
		return arr.remove(0);		// 반환
	}
}
