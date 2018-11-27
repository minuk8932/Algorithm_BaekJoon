import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author minchoba
 *	3, abcabadbbadbca
 */
public class Boj16472 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] word = br.readLine().toCharArray();
		
		System.out.println(getCatLangLeng(N, word));
	}
	
	private static int getCatLangLeng(int limitSize, char[] meow) {
		int max = 0;
		
		return max;
	}
	
}
