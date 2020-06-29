import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj17613 {
	private static HashMap<Long, Long> dp = new HashMap<>();
	private static final String NEW_LINE = "\n";

	public static void main (String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());

			sb.append(recursion(x, y)).append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}

	private static long recursion(long start, long end){
		long current = shifting(start, 30, end);
		if(dp.containsKey(current)) return dp.get(current);

		long result = 0;

		for(long i = 1; i < 40; i++){
			long s = shifting(1L, i, -1);
			long e = shifting(2L, i, -2);

			s = Math.max(s, start);
			e = Math.min(e, end);

			if(s > e) continue;

			long shift = shifting(1L, i, -1);
			s -= shift;
			e -= shift;
			result = Math.max(result, recursion(s, e) + i);
		}

		dp.put(current, result);
		return dp.get(current);
	}

	private static long shifting(long init, long push, long diff) {
		return (init << push) + diff;
	}
}
