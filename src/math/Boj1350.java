package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1350번: 진짜 공간
 *
 *	@see https://www.acmicpc.net/problem/1350/
 *
 */
public class Boj1350 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		double[] file = new double[N];
		long sum = 0;
		
		for(int i = 0; i < N; i++){
			file[i] = Double.parseDouble(st.nextToken());		// 파일 크기를 하나씩 받음
		}
		
		double size = Double.parseDouble(br.readLine());		// 클러스터 사이즈를 받아옴
		
		for(int i = 0; i < N; i++){
			double val = file[i] / size;					// 나눈 값을 가져와서
			sum += (long) Math.ceil(val) * size;		// 값을 올림한 후 사이즈 크기를 곱해서 사용한 사이즈를 총합에 더해줌
		}
		
		System.out.println(sum);		// 결과값 출력
	}
}
