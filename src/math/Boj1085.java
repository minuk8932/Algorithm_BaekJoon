package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1085 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int[] num = new int[6];
		int min = Integer.MAX_VALUE;
		
		num[0] = Integer.parseInt(st.nextToken());
		num[1] = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		num[2] = w - num[0];
		num[3] = h - num[1];
		num[4] = (int)(Math.sqrt(Math.pow(num[2], 2) + Math.pow(num[3], 2)));
		num[5] = (int)(Math.sqrt(Math.pow(num[0], 2) + Math.pow(num[1], 2)));
		
		for(int i = 0; i < num.length; i++){
			min = Math.min(min, num[i]);
		}
		
		System.out.println(min);
	}
}
