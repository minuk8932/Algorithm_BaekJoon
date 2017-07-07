package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5586 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chars = br.readLine().toCharArray();
		br.close();
		
		int last = chars.length - 2;
		int joi = 0;
		int ioi = 0;
		
		for(int i = 1; i <= last; i++){
			if(chars[i] == 'O' && chars[i+1] == 'I'){
				if(chars[i - 1] == 'J'){
					joi++;	
				}
				else if(chars[i - 1] == 'I'){
					ioi++;
				}
			}
		}
		System.out.println(new StringBuilder().append(joi).append("\n").append(ioi));
	}

}

