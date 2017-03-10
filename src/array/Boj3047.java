package array;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Boj3047 {
	public static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] num = new int[st.nextToken().length()];
		
		for(int i = 0; i < num.length; i++){
			num[i] = Integer.parseInt(st.nextToken());
		}		
		
		Arrays.sort(num);
		
	}

}
