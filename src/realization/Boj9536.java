package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Boj9536 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		
		while (T-- > 0) {
			String[] sounds = br.readLine().split(" ");
			
			String line;
			while(!(line = br.readLine()).equals("what does the fox say?")){
				String sound = line.split(" ")[2];
				
				for(int i = 0; i < sounds.length; i++){
					if(sounds[i].equals(sound)){
						sounds[i] = "";
					}
				}
				
			}
			for(String res : sounds){
				if(!res.equals("")){
					sb.append(res).append(SPACE);
				}
			}
		}
		
		br.close();
		System.out.println(sb.toString());
	}

}
