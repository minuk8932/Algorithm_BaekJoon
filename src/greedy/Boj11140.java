package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 11140번: LOL
 *
 *	@see https://www.acmicpc.net/problem/11140/
 *	
 */
public class Boj11140 {
	private static final String[] subString = {"lol", "lo", "ol", "ll"};
	private static final String NEW_LINE = "\n";
	private static final int YET = -1;
	private static final int INF = 50;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			String word = br.readLine();
			
			int res = YET;
			for(int i = 0; i < subString.length; i++) {		// 배열 내의 값이 존재하는지 순서대로 탐색
				if(word.contains(subString[i])) {
					res = subString.length - i == 4 ? 0 : 1;	// 존재 할 경우 조건에 따라 값을 담고 반복문 종료
					break;
				}
			}
			
			if(res == YET) {				// 아직 결과값이 안나온 경우
				int[] lNums = new int[INF];
				
				Arrays.fill(lNums, YET);
				
				int idx = 0, total = 0;
				char[] words = word.toCharArray();
				
				for(int i = 0; i < words.length; i++) {
					if(words[i] == 'l') {			// 문자열에 'l'이 존재하는 경우
						lNums[idx] = i;
						idx++;
						total++;
					}
					
					if(words[i] == 'o') {		// o가 존재하는 경우
						total++;
					}
				}
				
				res = total == 0 ? 3 : YET;		// 문자열에 l, o가 단 한개도 없는 경우 3, 그 외는 아직 결과값이 안나온 상태
				
				if(res == YET) {
					int min = INF;
					
					for(int i = 1; i < lNums.length; i++) {			// 배열 값이 음수가 아닐 때
						if(lNums[i] == -1 || lNums[i - 1] == -1) continue;
						
						int diff = Math.abs(lNums[i] - lNums[i - 1] - 1);	// l간의 간격을 구함
						
						if(diff <= 2 && diff > 0) {		// 간격이 1 ~ 2 사이라면, 그 중 최솟값을 구함
							min = Math.min(min, diff);
						}
					}
					
					res = min == INF ? 2 : min;		// 최솟값이 존재하는 경우 min을 그 외는 2를 저장
				}
			}
			
			sb.append(res).append(NEW_LINE);		// 각 값들을 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
