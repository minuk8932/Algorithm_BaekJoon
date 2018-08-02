package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 10546번: 배부른 마라토너
 *
 *	@see https://www.acmicpc.net/problem/10546/
 *
 */
public class Boj10546 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] part = new String[N];
		String[] finish = new String[N - 1];
		
		for(int i = 0; i < N; i++) part[i] = br.readLine();
		for(int i = 0; i < N - 1; i++) finish[i] = br.readLine();
		
		Arrays.sort(part);		// 이름 순 정렬
		Arrays.sort(finish);
		
		int idx = -1;
		
		for(int i = 0; i < N - 1; i++) {
			if(!part[i].equals(finish[i])) {	// 이름이 달라지는 순간의 번호를 저장 후 종료
				idx = i;
				
				break;
			}
		}
		
		if(idx == -1) idx = N - 1;			// 이름이 달라지는 순간이 없었다면, 마지막 선수가 비 완주자
		
		System.out.println(part[idx]);		// 해당 번호의 선수 이름을 출력
	}
}
