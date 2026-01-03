/*
1) 일정 정렬
2) 일정 하나 처리한뒤 바로 시작할수 있는 가장 가까운 일정 찾기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    // [N][min][dur]
    static int[][] works;
    static String[] animals
            = {"rat", "cow", "tiger"
            , "rabbit", "dragon", "snake"
            , "horse", "sheep", "monkey"
            , "chicken", "dog", "pig"
            , "lion", "giraffe", "cat"};

    static String timeFormat(int t){
        return String.format("%02d:%02d", t / 60, t%60);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        works = new int[N][2];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            works[i] = new int[]{h*60 + m, d};
        }

        Arrays.sort(works, (o1, o2) -> {
            if(o1[0] == o2[0]){
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        StringBuilder sb = new StringBuilder();
        int cur = 0;
        int done = 0;

        for(int[] work : works){
            int sCand = work[0];
            int dur = work[1];

            int s = Math.max(sCand, cur);

            if(s + dur > 1439)
                break;

            int e = s + dur;
            int ss = s / 96;
            int ee = Math.min(14, e / 96);

            for(int i = ss; i<=ee; i++){
                sb.append(animals[i]);
                if(i<ee)
                    sb.append(" ");
            }
            sb.append("\n");
            sb.append(timeFormat(s)).append(" ").append(timeFormat(e)).append("\n");

            cur = e;
            done++;
        }

        System.out.print(sb);
        if(done < N)
            System.out.print(N - done);
    }
}
