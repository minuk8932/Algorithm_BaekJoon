package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		
		for(int i = 0; i < N; i++){
			words[i] = br.readLine();
		}
		br.close();
		
		StringBuilder sb = new StringBuilder();
		
		MAIN_LOOP: for(int i = 0; i < N; i++){
			int len = words[i].length();
			int half = len / 2;
			boolean isPal = true;
			
			for(int j = 0; j < half; j++){
				if(words[i].charAt(j) != words[i].charAt(len -1 -j)){
					isPal = false;
					
					break;
				}
				
				if(isPal){
					sb.append(len).append(" ").append(words[i].charAt(half));
				}
			}
			
			for(int k = i; k < N; k++){
				if(len == words[k].length()){
					boolean isRev = true;
					
					for(int l = 0; l < half; l++){
						if(words[i].charAt(l) != words[k].charAt(len - 1 - l)){
							isRev = false;
							break;
							
						}
						
						if(isRev){
							sb.append(len).append(" ").append(words[i].charAt(half));
							break MAIN_LOOP;
						}
					}
				}
			}
		}
		System.out.println(sb.toString());
	}

}
