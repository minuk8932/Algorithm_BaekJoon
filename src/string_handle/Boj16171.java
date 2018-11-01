package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16171번: 나는 친구가 적다. (small)
 *
 *	@see https://www.acmicpc.net/problem/16171/
 *
 */
public class Boj16171 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		
		for(char w: br.readLine().toCharArray()) {	// 숫자를 제외하고 문자열에 저장
			if(!(w >= '0' && w <= '9')) line += w;
		}
		
		System.out.println(line.contains(br.readLine()) ? 1 : 0);	// 입력으로 들어오는 문자열이 포함된 경우 1 or 0
	}
}
