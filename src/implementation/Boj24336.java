package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24336번: 가희와 무궁화호
 *
 * @see https://www.acmicpc.net/problem/24336
 *
 */
public class Boj24336 {

    private static Schedule24336[] schedules;

    private static final Map<String, Integer> DISTANCE = new HashMap<>();
    private static final Map<String, Integer> INDEX = new HashMap<>();

    private static final double PREFIX_DOUBLE = 1_000_000.0;
    private static final int A_HOUR_TO_MINUTE = 60;
    private static final String NEW_LINE = "\n";
    private static final String DELIMITER = ":";
    private static final char EMPTY = '-';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init();

        schedules = new Schedule24336[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int d = hourToMinute(st.nextToken());
            int a = hourToMinute(st.nextToken());

            schedules[i] = new Schedule24336(INDEX.get(name), d, a);
        }

        StringBuilder sb = new StringBuilder();
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String from = st.nextToken();
            String to = st.nextToken();

            sb.append(rapid(from, to) / PREFIX_DOUBLE).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int getTime(int from, int to) {
        return to - from + (from > to ? 24 * A_HOUR_TO_MINUTE: 0);
    }

    private static double rapid(String f, String t) {

        int time = 0;
        int current = -1;

        for(int i = 0; i < schedules.length; i++) {
            if(schedules[i].getIndex() == INDEX.get(f)) {
                current = i;
                continue;
            }

            if(schedules[i].getIndex() == INDEX.get(t)) {
                time += getTime(schedules[i - 1].getArrived(), schedules[i].getDeparture());
                break;
            }

            if(current != -1) {
                time += getTime(schedules[i - 1].getArrived(), schedules[i].getDeparture());
                time += getTime(schedules[i].getDeparture(), schedules[i].getArrived());
            }
        }

        double distance = Math.abs(DISTANCE.get(t) - DISTANCE.get(f));
        return distance / (time / (double) A_HOUR_TO_MINUTE);
    }

    private static int hourToMinute(String input) {
        StringTokenizer st = new StringTokenizer(input, DELIMITER);
        String hour = st.nextToken();
        String minute = st.nextToken();

        if(hour.charAt(0) == EMPTY) return -1;

        return Integer.parseInt(hour) * A_HOUR_TO_MINUTE + Integer.parseInt(minute);
    }

    private static void init() {
        DISTANCE.put("Seoul", 0); DISTANCE.put("Yeongdeungpo", 9_100_000); DISTANCE.put("Anyang", 23_900_000);
        DISTANCE.put("Suwon", 41_500_000); DISTANCE.put("Osan", 56_500_000); DISTANCE.put("Seojeongri", 66_500_000);
        DISTANCE.put("Pyeongtaek", 75_000_000); DISTANCE.put("Seonghwan", 84_400_000);
        DISTANCE.put("Cheonan", 96_600_000); DISTANCE.put("Sojeongni", 107_400_000);
        DISTANCE.put("Jeonui", 114_900_000); DISTANCE.put("Jochiwon", 129_300_000);
        DISTANCE.put("Bugang", 139_800_000); DISTANCE.put("Sintanjin", 151_900_000);
        DISTANCE.put("Daejeon", 166_300_000); DISTANCE.put("Okcheon", 182_500_000);
        DISTANCE.put("Iwon", 190_800_000); DISTANCE.put("Jitan", 196_400_000);
        DISTANCE.put("Simcheon", 200_800_000); DISTANCE.put("Gakgye", 204_600_000);
        DISTANCE.put("Yeongdong", 211_600_000); DISTANCE.put("Hwanggan", 226_200_000);
        DISTANCE.put("Chupungnyeong", 234_700_000); DISTANCE.put("Gimcheon", 253_800_000);
        DISTANCE.put("Gumi", 276_700_000); DISTANCE.put("Sagok", 281_300_000);
        DISTANCE.put("Yangmok", 289_500_000); DISTANCE.put("Waegwan", 296_000_000);
        DISTANCE.put("Sindong", 305_900_000); DISTANCE.put("Daegu", 323_100_000);
        DISTANCE.put("Dongdaegu", 326_300_000); DISTANCE.put("Gyeongsan", 338_600_000);
        DISTANCE.put("Namseonghyeon", 353_100_000); DISTANCE.put("Cheongdo", 361_800_000);
        DISTANCE.put("Sangdong", 372_200_000); DISTANCE.put("Miryang", 381_600_000);
        DISTANCE.put("Samnangjin", 394_100_000); DISTANCE.put("Wondong", 403_200_000);
        DISTANCE.put("Mulgeum", 412_400_000); DISTANCE.put("Hwamyeong", 421_800_000);
        DISTANCE.put("Gupo", 425_200_000); DISTANCE.put("Sasang", 430_300_000);
        DISTANCE.put("Busan", 441_700_000);

        INDEX.put("Seoul", 0); INDEX.put("Yeongdeungpo", 1); INDEX.put("Anyang", 2);
        INDEX.put("Suwon", 3); INDEX.put("Osan", 4); INDEX.put("Seojeongri", 5);
        INDEX.put("Pyeongtaek", 6); INDEX.put("Seonghwan", 7); INDEX.put("Cheonan", 8);
        INDEX.put("Sojeongni", 9); INDEX.put("Jeonui", 10); INDEX.put("Jochiwon", 11);
        INDEX.put("Bugang", 12); INDEX.put("Sintanjin", 13); INDEX.put("Daejeon", 14);
        INDEX.put("Okcheon", 15); INDEX.put("Iwon", 16); INDEX.put("Jitan", 17);
        INDEX.put("Simcheon", 18); INDEX.put("Gakgye", 19); INDEX.put("Yeongdong", 20);
        INDEX.put("Hwanggan", 21); INDEX.put("Chupungnyeong", 22); INDEX.put("Gimcheon", 23);
        INDEX.put("Gumi", 24); INDEX.put("Sagok", 25); INDEX.put("Yangmok", 26); INDEX.put("Waegwan", 27);
        INDEX.put("Sindong", 28); INDEX.put("Daegu", 29); INDEX.put("Dongdaegu", 30); INDEX.put("Gyeongsan", 31);
        INDEX.put("Namseonghyeon", 32); INDEX.put("Cheongdo", 33); INDEX.put("Sangdong", 34);
        INDEX.put("Miryang", 35); INDEX.put("Samnangjin", 36); INDEX.put("Wondong", 37);
        INDEX.put("Mulgeum", 38); INDEX.put("Hwamyeong", 39); INDEX.put("Gupo", 40);
        INDEX.put("Sasang", 41); INDEX.put("Busan", 42);
    }
}

class Schedule24336 {
    private int index;
    private int departure;
    private int arrived;

    public Schedule24336(int index, int departure, int arrived) {
        this.index = index;
        this.departure = departure;
        this.arrived = arrived;
    }

    public int getIndex() {
        return index;
    }

    public int getDeparture() {
        return departure;
    }

    public int getArrived() {
        return arrived;
    }
}
