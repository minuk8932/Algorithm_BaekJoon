package math;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;

/**
 * 
 * 	@author minchoba
 *	백준 2587번: 대표값2
 *
 *	@see https;//www.acmicpc.net/problem/2587/
 *
 */
public class Boj2587{
    private static final String NEW_LINE = "\n";
    
    public static void main(String[] args)throws Exception{
    	// 버퍼를 통한 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int avg = 0;
        int[] nums = new int[5];
        
        for(int i = 0; i < 5; i++){
           nums[i] = Integer.parseInt(br.readLine());
           avg += nums[i];
        }
        
        Arrays.sort(nums);
        System.out.println((avg / 5) + NEW_LINE + nums[2]);			// 평균, 대표값 출력
    }
}