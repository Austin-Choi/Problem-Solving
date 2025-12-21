import java.util.*;
import java.io.*;

public class Main {
    static int N, K, Q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        HashSet<Character>[] read = new HashSet[K];
        for (int i = 0; i < K; i++) {
            read[i] = new HashSet<>();
            read[i].add('A');
        }

        int[] unread = new int[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            unread[i] = Integer.parseInt(st.nextToken());
            char sender = st.nextToken().charAt(0);

            // sender가 A가 아니면
            // 메시지를 보내기 위해 이전 메시지를 모두 읽었어야 함
            if (sender != 'A') {
                for (int j = 0; j < i; j++)
                    read[j].add(sender);
            }

            // 안 읽은 사람 수가 그대로면 상태 복사
            if (i > 0 && unread[i] == unread[i - 1]) {
                read[i] = new HashSet<>(read[i - 1]);
            }

            // 현재 메시지를 읽은 사람에 sender 추가
            read[i].add(sender);
        }

        if (unread[Q - 1] == 0) {
            System.out.println(-1);
            return;
        }

        // 읽지 않았을 가능성이 있는 사람 출력
        HashSet<Character> readSet = read[Q - 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char p = (char) ('A' + i);
            if (!readSet.contains(p))
                sb.append(p).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}