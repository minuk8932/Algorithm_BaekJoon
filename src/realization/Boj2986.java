package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2986 { // 시간복잡도 감소.

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int sqrtN = (int) Math.sqrt(N);
		int max = 1;
		for(int i = sqrtN; i >= 2; i--){
			if(N % i == 0){
				max = Math.max(i, N / i); // 제일 작은 약수로 나누어서 나온 몫은 제일 큰 약수. 
			}			
		}
		System.out.println(N - max); // 가장 큰 약수가 나올때까지 값을 뺀 것이 counter 
	}

}
//
//public class Boj2986 { // 기본적인 풀이   
//
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine());
//		
//		int counter = 0;
//LOOP:		for(int i = N - 1; i > 0; i--){
//			counter += +1;
//			if(N % i == 0){
//				break LOOP;
//			}
//		}
//		System.out.println(counter);
//	}
//
//}
