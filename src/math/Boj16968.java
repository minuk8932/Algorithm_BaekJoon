package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16968번: 차량 번호판1
 *
 *	@see https://www.acmicpc.net/problem/16968/
 *
 */
public class Boj16968{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] value = {26, 10};
        int result = 1;
        
        char prev = ' ';
        int count = 0;
        
        for(char c: br.readLine().toCharArray()){            
           if(prev == c) count = 1;
           else count = 0;
        	   
           prev = c;
           result *= (value[c - 'c'] - count);			// 2자리 중복 제거 ex) dddd의 경우: 10 * 9 * 9 * 9
        }
        
        System.out.println(result);
    }
}