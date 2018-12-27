package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2139번: 나는 너가 살아온 날을 알고있다.
 *
 *	@see https://www.acmicpc.net/problem/2139/
 *
 */
public class Boj2139 {
	private static final int[] NORMAL_YEAR = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final int[] LEAP_YEAR = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int date = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());
			
			if(date + month + year == 0) break;
			sb.append(calculateTotalDate(date, month, year)).append(NEW_LINE);
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static int calculateTotalDate(int d, int m, int y) {
		boolean isLeap = getLeapYear(y);
		int total = 0;
		
		if(isLeap) total += getDate(d, m, LEAP_YEAR);		// 윤년
		else total += getDate(d, m, NORMAL_YEAR);
		
		return total;
	}
	
	private static boolean getLeapYear(int y) {		// 윤년 체커
		boolean check = false;
		
		if(y % 4 == 0) check = true;
		if(y % 100 == 0) check = false;
		if(y % 400 == 0) check = true;
		
		return check;
	}
	
	private static int getDate(int date, int month, final int[] YEAR) {
		int sum = date;			// 해당 월의 일수
		
		for(int mon = 1; mon < month; mon++) {		// 입력 월 전까지 총 일수를 더함
			sum += YEAR[mon];
		}

		return sum;
	}
}
