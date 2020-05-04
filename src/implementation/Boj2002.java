package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author exponential-e
 * 백준 2002번: 추월
 *
 * @see https://www.acmicpc.net/problem/2002/
 *
 */
public class Boj2002 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashMap<String, Integer> car = new HashMap<>();
        for(int i = 0; i < N; i++) {
            car.put(br.readLine(), i);
        }

        String[] comp = new String[N];
        for(int i = 0; i < N; i++) {
            comp[i] = br.readLine();
        }

        System.out.println(compare(N, car, comp));
    }

    private static int compare(int n, HashMap<String, Integer> car, String[] comp) {
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(car.get(comp[i]) < car.get(comp[j])) continue;

                result++;
                break;
            }
        }

        return result;
    }
}
