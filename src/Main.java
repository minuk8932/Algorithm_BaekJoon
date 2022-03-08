import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final char[] WOLF = {'w', 'o', 'l', 'f'};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        System.out.println(isWolf(input.toCharArray()));
    }

    private static int isWolf(char[] words) {
        int index = 0;

        while(index < words.length) {
            int[] count = new int[4];

            for(int i = 0; i < 4; i++) {
                for (; index < words.length; index++) {
                    if (words[index] != WOLF[i]) break;
                    count[i]++;
                }
            }

            for(int i = 1; i < count.length; i++) {
                if(count[i - 1] != count[i]) return 0;
            }
        }

        return 1;
    }
}
