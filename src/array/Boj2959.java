package array;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2959 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int line[] = new int[4];
		
		for(int i = 0;i < line.length; i++){
			line[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(line);
		
		int rectangle = line[0] * line[2];
		
		System.out.println(rectangle);
		
	}

}
