package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14544번: Vote
 *
 * @see https://www.acmicpc.net/problem/14544/
 *
 */
public class Boj14544 {
    private static final String V = "VOTE ";
    private static final String WIN = ": THE WINNER IS ";
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final String DRAW = ": THERE IS A DILEMMA\n";

    private static ArrayList<String> candidate;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int vote = 1;

        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            HashMap<String, BigInteger> hm = new HashMap<>();
            candidate = new ArrayList<>();

            while(N-- > 0){
                String name = br.readLine();
                candidate.add(name);
                hm.put(name, new BigInteger("0"));
            }

            BigInteger max = new BigInteger("0");

            while(K-- > 0){
                st = new StringTokenizer(br.readLine());
                String trg = st.nextToken();
                String val = st.nextToken();
                st.nextToken();

                if(hm.containsKey(trg)){
                    hm.put(trg, hm.get(trg).add(new BigInteger(val)));
                    max = max.max(hm.get(trg));
                }
            }

            int count = 0;
            String res = "";
            BigInteger resCount = new BigInteger("0");

            for(String names: candidate){
                if(max.equals(hm.get(names))){
                    count++;
                    if(count == 2) break;

                    res = names;
                    resCount = hm.get(names);
                }
            }

            sb.append(V).append(vote++);
            if(count == 2) sb.append(DRAW);
            else sb.append(WIN).append(res).append(SPACE).append(resCount).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}
