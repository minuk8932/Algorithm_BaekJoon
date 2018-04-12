package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2179 {
	public static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
		
		int third = num1 / 100;
		int first = num1 - ((num1 / 10) * 10);
		int second = (num1 - (third * 100)) / 10;
		
		
		num1 = (first * 100) + (second * 10) + third;
		
		third = num2 / 100;
		first = num2 - ((num2 / 10) * 10);
		second = (num2 - (third * 100)) / 10;
		
		num2 = (first * 100) + (second * 10) + third;
		
		if(num1 > num2){
			System.out.println(num1);
		}
		else {
			System.out.println(num2);
		}
	}	

}
