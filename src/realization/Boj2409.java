package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2409 {
	public static final int THROW = 3;
	public static final int COUNT = 4;
	public static final String NEW_LINE = "\n";
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < THROW; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int val = 0;
			for(int j = 0; j < COUNT ; j++){
				val += Integer.parseInt(st.nextToken());
			}
			switch(val){
			case 0:
				sb.append("D").append(NEW_LINE);
				break;
			
			case 1:
				sb.append("C").append(NEW_LINE);
				break;
			
			case 2:
				sb.append("B").append(NEW_LINE);
				break;
			
			case 3:
				sb.append("A").append(NEW_LINE);
				break;
			
			case 4:
				sb.append("E").append(NEW_LINE);
				break;
			}
		}
		System.out.println(sb.toString());
	}
}
