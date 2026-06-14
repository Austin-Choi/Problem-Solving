import java.util.*;
import java.io.*;

/*
앉을수 있는 곳이 맨앞, 맨뒤 중간에 제일 큰 곳 중간이긴 한데
그냥 앞뒤로 0,0 달아놓고
다 앉혀보면서 가까운 거리 구하기?
*/

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] seat = br.readLine().toCharArray();
        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (seat[i] == '1') 
                continue;

            seat[i] = '1';

            int prev = -1;
            int minDist = Integer.MAX_VALUE;

            for (int j = 0; j < N; j++) {
                if (seat[j] == '1') {
                    if (prev != -1) {
                        minDist = Math.min(minDist, j - prev);
                    }
                    prev = j;
                }
            }

            answer = Math.max(answer, minDist);

            seat[i] = '0';
        }

        System.out.print(answer);
    }
}