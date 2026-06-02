
/*
c가 메시지를 올림 -> 해당 시점 이하의 메시지는 c가 읽음 
빈 set 하나를 두고 ArrayList[M]으로 해서 set에 c 추가하면서 list[m] = new list<>(set) 하면?
뒤에서부터 누적해야 함. 
-> 일단 입력을 받야아 함

-> unread 갯수가 유지되는 구간은 안읽은 사람 집합이 달라지지 않았다는 것.
거기까지 읽어서 안읽은 사람 집합 구하기?
*/

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        char[] sender = new char[M];
        int[] unread = new int[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            sender[i] = st.nextToken().charAt(0);
            unread[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] check = new boolean[26];

        // P번째 메시지 이후에 채팅한 사람들
        for (int i = P - 1; i < M; i++) {
            check[sender[i] - 'A'] = true;
        }

        // 이미 모두 읽은 경우
        if (unread[P - 1] == 0) {
            System.out.println();
            return;
        }

        // 같은 unread 구간을 왼쪽으로 확장
        int temp = P;

        while (temp > 1 && unread[temp - 1] == unread[temp - 2]) {
            check[sender[temp - 2] - 'A'] = true;
            temp--;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                sb.append((char) ('A' + i)).append(' ');
            }
        }

        System.out.print(sb);
    }
}