package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5622 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String dial = br.readLine();
		br.close();
		
		int len = dial.length();
		char[] call = new char[len];
		call = dial.toCharArray();
		
		int time = 0;
		
		for(int i = 0; i < call.length; i++){
			switch(call[i]){
			case 'A':
			case 'B':
			case 'C':
				time += 3;
				break;
				
			case 'D':
			case 'E':
			case 'F':
				time += 4;
				break;
				
			case 'G':
			case 'H':
			case 'I':
				time += 5;
				break;
				
			case 'J':
			case 'K':
			case 'L':
				time += 6;
				break;
				
			case 'M':
			case 'N':
			case 'O':
				time += 7;
				break;
				
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
				time += 8;
				break;
				
			case 'V':
			case 'T':
			case 'U':
				time += 9;
				break;
				
			case 'X':
			case 'Z':
			case 'W':
			case 'Y':
				time += 10;
				break;
			}
		}
		System.out.println(time);
	}

}
