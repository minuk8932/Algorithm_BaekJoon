package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13133번: Aurora Princess
 *
 * @see https://www.acmicpc.net/problem/13133/
 *
 */
public class Boj13133 {
    private static boolean[] unhappy;

    private static class Family {
        int mother;
        int father;

        public Family(int mother, int father) {
            this.mother = mother;
            this.father = father;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Family[] f = new Family[N];
        unhappy = new boolean[N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int mo = Integer.parseInt(st.nextToken()) - 1;
            int fa = Integer.parseInt(st.nextToken()) - 1;
            f[i] = new Family(mo, fa);

            if(f[i].mother == -1|| f[i].father == -1) unhappy[i] = true;
        }

        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(M-- > 0){
            int dead = Integer.parseInt(st.nextToken()) - 1;
            unhappy[dead] = true;

            for(int i = 0; i < N; i++){
                if(f[i].mother == -1 || f[i].father == -1) continue;        // find son who parents dead or gone to america
                if(f[i].mother == dead) unhappy[i] = true;
                if(f[i].father == dead) unhappy[i] = true;
            }
        }

        int count = 0;
        for(int i = 0; i < N; i++){
            if(!unhappy[i]) count++;
        }

        System.out.println(count);
    }
}
