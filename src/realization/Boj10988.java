package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj10988 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chars = br.readLine().toCharArray();
		
		br.close();
		
		int half = chars.length / 2 ;
		boolean isFel = true;
		
		for(int i = 0; i < half; i++){
			if(chars[i] != chars[chars.length -1 -i]){
				isFel = false;
				break;
			}
		}
		System.out.println(isFel ? 1 : 0);
	}

}
