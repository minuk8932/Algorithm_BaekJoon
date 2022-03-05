package implementation;

import common.Time;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 24465번: 데뷔의 꿈
 *
 * @see https://acmicpc.net/problem/24465
 *
 */
public class Boj24465 {

    private static Map<Integer, Integer> constellations = new HashMap<>();

    private static final String NO_DAP = "ALL FAILED";
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static final int DIV = 10_000;
    private static final int MONTH = 100;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();

        boolean[] sections = new boolean[constellations.size()];
        for(int i = 0; i < 7; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int month = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            for(Map.Entry<Integer, Integer> entry: constellations.entrySet()) {
                int left = entry.getKey() / DIV;
                int right = entry.getKey() % DIV;

                if(month == getMonth(left)) {
                    if(day < getDay(left)) continue;
                    sections[entry.getValue()] = true;
                    break;
                }

                if(month == getMonth(right)) {
                    if(day > getDay(right)) continue;
                    sections[entry.getValue()] = true;
                    break;
                }
            }
        }

        List<Time<Integer, Integer>> answer = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int month = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            for(Map.Entry<Integer, Integer> entry: constellations.entrySet()) {
                if(sections[entry.getValue()]) continue;
                int left = entry.getKey() / DIV;
                int right = entry.getKey() % DIV;

                if(month == getMonth(left)) {
                    if(day < getDay(left)) continue;
                    answer.add(new Time.Builder(month, day).build());
                    break;
                }

                if(month == getMonth(right)) {
                    if(day > getDay(right)) continue;
                    answer.add(new Time.Builder(month, day).build());
                    break;
                }
            }
        }

        System.out.println(printer(answer));
    }

    private static String printer(List<Time<Integer, Integer>> answer) {
        if(answer.size() == 0) return NO_DAP;

        StringBuilder sb = new StringBuilder();
        Collections.sort(answer, Comparator
                .comparingInt(Time<Integer, Integer>::getStart)
                .thenComparingInt(Time::getEnd));

        for(Time t: answer) {
            sb.append(t.getStart()).append(SPACE).append(t.getEnd()).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static int getDay(int data) {
        return data % MONTH;
    }

    private static int getMonth(int data) {
        return data / MONTH;
    }

    private static void init() {
        constellations.put(1200218, 0);
        constellations.put(2190320, 1);
        constellations.put(3210419, 2);
        constellations.put(4200520, 3);
        constellations.put(5210621, 4);
        constellations.put(6220722, 5);
        constellations.put(7230822, 6);
        constellations.put(8230922, 7);
        constellations.put(9231022, 8);
        constellations.put(10231122, 9);
        constellations.put(11231221, 10);
        constellations.put(12220119, 11);
    }
}
