import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] A = br.readLine().toCharArray();

        ArrayList<Integer> pos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (A[i] == '1')
                pos.add(i);
        }

        int originMin = Integer.MAX_VALUE;

        for (int i = 1; i < pos.size(); i++) 
            originMin = Math.min(originMin, pos.get(i) - pos.get(i - 1));
        
        int answer = 0;
        // 맨 앞 구간
        int frontGap = pos.get(0);
        if (frontGap > 0) 
            answer = Math.max(answer,Math.min(originMin, frontGap));

        // 맨 뒤 구간
        int backGap = N - 1 - pos.get(pos.size() - 1);
        if (backGap > 0) 
            answer = Math.max(answer,Math.min(originMin, backGap));

        // 중간 구간
        for (int i = 1; i < pos.size(); i++) {
            int gap = pos.get(i) - pos.get(i - 1) - 1;
            if (gap > 0) {
                int candidate = (gap + 1) / 2;
                answer = Math.max(answer,Math.min(originMin, candidate));
            }
        }
        System.out.println(answer);
    }
}