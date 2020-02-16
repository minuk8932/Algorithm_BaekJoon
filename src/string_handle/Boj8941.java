package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author exponential-e
 * 백준 8941번: Molar mass
 *
 * @see https://www.acmicpc.net/problem/8941/
 *
 */
public class Boj8941 {
    private static final char SPACE = ' ';
    private static final String NEW_LINE = "\n";
    private static final HashMap<Character, Double> MOLES = new HashMap<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        init();

        while(T-- > 0){
            char[] mole = br.readLine().toCharArray();
            sb.append(String.format("%.3f", getMolarMass(mole))).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        MOLES.put('C', 12.01);
        MOLES.put('H', 1.008);
        MOLES.put('O', 16.00);
        MOLES.put('N', 14.01);
    }

    private static double getMolarMass(char[] inputs){
        double result = 0.0;
        char m = SPACE;
        int val = 0;

        for(char c: inputs){
            if(c >= 'A' && c <= 'Z'){
                if(m != SPACE){
                    if(val == 0) val = 1;

                    result += MOLES.get(m) * val;       // total moles
                    val = 0;
                }

                m = c;
                continue;
            }

            if(val != 0){           // make cipher
                val *= 10;
                val += c - '0';
            }
            else{
                val = c - '0';
            }
        }

        result += MOLES.get(m) * (val == 0 ? 1: val);
        return result;
    }
}
