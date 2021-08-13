package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 6568번: 귀도 반 로썸은 크리스마스날 심심하다고 파이썬을 만들었다
 *
 * @see https://www.acmicpc.net/problem/6568
 *
 */
public class Boj6568 {

    private static int[] memory;

    private static final int SIZE = 1 << 5;
    private static final String NEW_LINE = "\n";

    private static class Package {
        String input;
        int cmd = 0;
        int index;
        int head;

        public Package(String input, int index, int head) {
            this.input = input;
            this.index = index;
            this.head = head;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;

        while((input = br.readLine()) != null) {
            memory = ARRAY.apply(SIZE);
            memory[0] = UNPACKING.apply(new Package(input, 0,  1 << 7));

            for(int i = 1; i < memory.length; i++) {
                input = br.readLine();
                memory[i] = UNPACKING.apply(new Package(input, 0,  1 << 7));
            }

            int pc = 0;
            int adder = 0;

            while (true) {
                int cmd = memory[pc] / SIZE;
                int x = memory[pc] % SIZE;

                pc++;
                pc &= 31;

                if (cmd == 0) memory[x] = adder;
                else if (cmd == 1) adder = memory[x];
                else if (cmd == 2) pc = adder == 0 ? x: pc;
                else if (cmd == 3) continue;
                else if (cmd == 4) adder = (adder - 1) & 255;
                else if (cmd == 5) adder = (adder + 1) & 255;
                else if (cmd == 6) pc = x;
                else if (cmd == 7) break;
            }

            sb.append(TO_BINARY.apply(STACKING.apply(adder))).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static final Function<Integer, Deque<Integer>> STACKING = ten -> {
        Deque<Integer> stack = new ArrayDeque<>();

        while(ten > 0) {
            stack.push(ten % 2);
            ten /= 2;
        }

        return stack;
    };

    private static final Function<Deque<Integer>, String> TO_BINARY = stack -> {
        StringBuilder builder = new StringBuilder();
        int max = 8;
        int loop = max - stack.size();

        while (loop-- > 0) {
            stack.push(0);
        }

        while(max-- > 0) {
            builder.append(stack.pop());
        }

        return builder.toString();
    };

    private static final Function<Package, Integer> UNPACKING = pack -> {
        while(pack.head > 0) {
            pack.cmd += ((pack.input.charAt(pack.index++) - '0') * pack.head);
            pack.head >>= 1;
        }

        return pack.cmd;
    };

    private static final Function<Integer, int[]> ARRAY = int[]::new;
}
