package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2948번: 2009년
 *
 *	@see https://www.acmicpc.net/problem/2948
 *
 */
public class Boj2948 {
	private static final String[] WEEK = {"Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday"};
	private static final int[] DATE = {0, 31,28,31,30,31,30,31,31,30,31,30,31};
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int totalDate = 0;
		for(int i = 1; i < M; i++) {		// 해당 월 전까지의 날의 총합
			totalDate += DATE[i];
		}
		
		totalDate += D;						// 입력으로 들어온 일수 +
		System.out.println(WEEK[totalDate % 7]);	// 날짜를 7로 나눈 나머지에 맞는 인덱스의 요일을 출력
	}
}
