package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14487번: 욱제는 효도쟁이야!!
 *
 *	@see https://www.acmicpc.net/problem/14487
 *
 */
public class Boj14487{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0, max = 0;
        for(int i = 0; i < N; i++){
            int value = Integer.parseInt(st.nextToken());
            sum += value;
            
            if(value > max) max = value;
        }
        
        System.out.println(sum - max);		// 가장 비싼 경로 제외
    }
}