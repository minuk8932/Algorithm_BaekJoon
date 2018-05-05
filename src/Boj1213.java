import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1213 {
	private static final String WRONG = "I'm Sorry Hansoo";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int[] name = new int[26];
		boolean isPossible = true;
		
		for(char word : line.toCharArray()){
			name[word - 65]++;
		}
		
		int cnt = 0;
		
		for(int i = 0; i < name.length; i++){			// 홀수개가 몇개여야 불가능인가...
			if(name[i] % 2 != 0){
				isPossible = false;
				
				break;
			}
			
			name[i] /= 2;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(!isPossible){
			sb.append(WRONG);
		}
		else{
			for(int i = 0; i < name.length; i++){
				if(name[i] != 0){
					for(int j = 0; j < name[i]; j++){
						sb.append((char) (i + 65));
					}
				}
			}
			String head = sb.toString();
			
			int rLeng = head.length();
			for(int i = rLeng - 1; i >= 0; i--){
				sb.append(head.charAt(i));
			}
		}
		
		System.out.println(sb.toString());
	}
}
