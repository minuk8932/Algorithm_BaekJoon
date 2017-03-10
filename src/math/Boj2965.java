package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2965 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] kanga = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < kanga.length; i++){
			kanga[i] = Integer.parseInt(st.nextToken());
		}
		
		int chk1 = kanga[2] - kanga[1];
		int chk2 = kanga[1] - kanga[0];
		
		if(chk1 < chk2){
			sb.append(chk2-1).append(NEW_LINE);
		}
		else {
			sb.append(chk1-1).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		
	}

}
