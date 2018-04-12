package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj6321 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++){
			String line = br.readLine();
			int length = line.length();
			char[] com = new char[length];
			
			com = line.toCharArray();
			sb.append("String #"+(i+1)).append(NEW_LINE);
			for(int j = 0; j < length; j++){
				
				if(com[j] == 'Z'){
					com[j] = 'A';
				}
				else{
					com[j] += 1;
				}
				
				sb.append(com[j]);
			}
			sb.append(NEW_LINE).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}

}
