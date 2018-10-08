import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj2042 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int size = (int) (Math.pow(2, N - 1) - 1);
		
		TreeSet<Integer> tSet = new TreeSet<>();
		for(int i = 0; i < size; i++) {
			
			tSet.add(0);
		}
	}
}
