import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1475 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int len = line.length();
		
		int[] num = new int[len];
		
		for(int i = 0; i < len; i++){	
			num[i] = Character.getNumericValue(line.charAt(i));
		}
		
		boolean[] nums = new boolean[10];
		int  cnt = 0;
		
		for(int i = 0; i < num.length; i++){
			switch(num[i]){
			case '0':
				if(!nums[num[i]]){
					nums[num[i]] = true;
				}
				break;
				
			case '1':
				break;
			
			case '2':
				break;
			
			case '3':
				break;
			
			case '4':
				break;
			
			case '5':
				break;
			
			case '6':
				break;
			
			case '7':
				break;
			
			case '8':
				break;
			
			case '9':
				break;

			}
		}
	}

}
