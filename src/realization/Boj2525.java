package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2525 {
	private static final String SPACE = " ";
	
	private static final int CHK_HOUR = 23;
	private static final int CHK_MINUTE = 59;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());
		
		int time = Integer.parseInt(br.readLine());
		
		hour += (time / (CHK_MINUTE + 1));
		minute += (time % (CHK_MINUTE + 1));
		
		if(minute > CHK_MINUTE){
			hour++;
			minute = minute - (CHK_MINUTE + 1);
		}
		
		if(hour > CHK_HOUR){
			hour = hour - (CHK_HOUR + 1);
		}
		
		System.out.println(hour + " " + minute);
	}
}
