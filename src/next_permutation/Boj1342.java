package next_permutation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 1342번: 행운의 문자열
 *
 *	@see https://www.acmicpc.net/problem/1342/
 *
 */
public class Boj1342 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] seq = br.readLine().toCharArray();
		int res = 0;
		boolean isGood = true;
		
		Arrays.sort(seq);
		for(int i = 1; i < seq.length; i++) {		// 정렬 된 첫 문자열이 행운의 문자열인지 검사
			if(seq[i - 1] == seq[i]) {
				isGood = false;
				break;
			}
		}
		
		if(isGood) res++;
		
		while(true) {
			StringBuilder sb = new StringBuilder();
			int idx = -1;
			isGood = true;
			
			for(int i = 1; i < seq.length; i++) {		// 가장 큰 수가 존재하는 바로 이전 인덱스를 가져와서 저장
				if(seq[i - 1] < seq[i]) idx = i - 1; 
			}
			
			if(idx == -1) break;		// 문자열이 가장 마지막 순열이 지난 경우 종료
			
			for(int i = seq.length - 1; i > idx; i--) {
				if(seq[idx] < seq[i]) {				// 뽑아낸 수보다 큰 수가 있따면 자리 바꿈
					char tmp = seq[i];
					seq[i] = seq[idx];
					seq[idx] = tmp;
					break;
				}
			}
			
			for(int i = 0; i < idx + 1; i++) sb.append(seq[i]);			// 구한 다음 순열을 버퍼에 저장
			for(int i = seq.length - 1; i > idx; i--) sb.append(seq[i]);
			
			for(int i = 0; i < seq.length; i++) {		// 다음 순열로 본래 배열을 갱
				seq[i] = sb.toString().charAt(i);
				
				if(i > 0) {								// 그 순열이 행운의 문자열인지 체크
					if(seq[i - 1] == seq[i]) isGood = false;
				}
			}
			
			if(isGood) res++;			// 갯수 +1
		}
		
		System.out.println(res);		// 행운의 문자열 갯수 출력
	}
}
