package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj5543 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int top = Integer.parseInt(br.readLine());
		int mid = Integer.parseInt(br.readLine());
		int bot = Integer.parseInt(br.readLine());
		int cok = Integer.parseInt(br.readLine());
		int cid = Integer.parseInt(br.readLine());		
		int[] set = new int[6];
		
		set[0] = cok + top - 50;
		set[1] = cok + mid - 50;
		set[2] = cok + bot - 50;
		set[3] = cid + top - 50;
		set[4] = cid + mid - 50;
		set[5] = cid + bot - 50;
		
		Arrays.sort(set);
		sb.append(set[0]);
		System.out.println(sb.toString());
	}

}
