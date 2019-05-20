package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12787번: 지금 밥이 문제냐
 *
 *	@see https://www.acmicpc.net/problem/12787/
 *
 */
public class Boj12787 {
	private static final String NEW_LINE = "\n", DOT = ".";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			if(cmd == 1) {										// IPv8 -> 10
				st = new StringTokenizer(st.nextToken(), DOT);
				String[] bin = new String[8];
				
				for(int i = 0; i < bin.length; i++) {
					bin[i] = makeBinary(new BigInteger(st.nextToken()));		// 각 주소의 값을 .기준으로 끊어서
					
					while(bin[i].length() < 8) {								// 2진수 문자열로 변경, 길이가 8이 안되면 0으로 채움
						bin[i] = "0" + bin[i];
					}
				}
				
				sb.append(getBigInteger(bin)).append(NEW_LINE);
			}
			else {												// 10 -> IPv8
				BigInteger big = new BigInteger(st.nextToken());
				String bin = makeBinary(big);					// 10진수를 2진수 문자열로 변경
				
				sb.append(getOctal(bin)).append(NEW_LINE);
			}
		}
		
		System.out.print(sb);
	}
	
	private static String makeBinary(BigInteger b) {
		BigInteger two = new BigInteger("2");
		BigInteger zero = new BigInteger("0");
		
		String str = "";
		
		while(!b.equals(zero)) {
			if(b.mod(two).equals(zero)) str = "0" + str;
			else str = "1" + str;
			
			b = b.divide(two);
		}

		return str;
	}
	
	private static String getOctal(String b) {
		StringBuilder sb = new StringBuilder();
		long[] address = new long[8];
		
		int index = 7, count = 0;
		char[] value = b.toCharArray();
		
		for(int i = value.length - 1; i >= 0; i--) {
			address[index] += (value[i] - '0') * Math.pow(2, count);		// 파트별로 나누어서 각 부분에 들어갈 주솟값을 구함
			count++;
			
			if(count == 8) {
				index--;
				count = 0;
			}
		}
		
		for(int i = 0; i < address.length; i++) {
			if(i < address.length - 1) sb.append(address[i]).append(DOT);
			else sb.append(address[i]);
		}
		
		return sb.toString();
	}
	
	private static String getBigInteger(String[] arr) {
		BigInteger res = new BigInteger("0");
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++) {		// 나눠진 이진수를 하나로 붙여줌
			sb.append(arr[i]);
		}

		BigInteger two = new BigInteger("2");
		String bin = sb.toString();
		int leng = bin.length();
		
		for(int i = leng - 1; i >= 0; i--) {
			if(bin.charAt(i) == '0') continue;				
			res = res.add(two.pow(leng - i - 1));	// 각 자리가 1인 경우에만 해당 위치의 인덱스만큼 지수승 해서 결과를 구해줌
		}
		
		return res.toString();
	}
}
