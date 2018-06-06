package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2530번: 인공지능 시계
 *
 *	@see https://www.acmicpc.net/problem/2530/
 *
 */
public class Boj2530 {
	private static final int H = 3600;
	private static final int M = 60;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 입력으로 들어오는 시간을 초단위로 변경
		int total = Integer.parseInt(st.nextToken()) * H + Integer.parseInt(st.nextToken()) * M + Integer.parseInt(st.nextToken());
		total += Integer.parseInt(br.readLine());		// 추가시간을 더하고
		
		int hour = total / H;		// 시 분 초 계산
		int min = (total % H) / M;
		int sec = (total % H) % M;
		
		if(hour > 23) {		// 만약 시간의 단위가 23을 넘어가면 24로 나눈 나머지를 담고
			hour %= 24;
		}
		
		System.out.println(hour + " " + min + " " + sec);		// 최종 시간 출력
	}
}
