/*
40분
웅덩이들을 시작 위치로 정렬을 하고
웅덩이는 시작 포함 끝 -1까지임

판자를 덮을때 다음 시작점에 닿으면 다음 웅덩이 처리는 어떻게?
------------------------
판자 길이 L에 대해서 물웅덩이 길이를 올림처리하면
길이보다 넘어가더라도 필요한 판자 갯수가 나오고
cover는 지금까지 덮인 마지막 좌표를 말하는 거니까
다음 것을 처리할 때 cover하고 다음 start중 큰 좌표부터 처리하면 됨

 */

import java.util.*;
import java.io.*;
class Puddle implements Comparable<Puddle>{
    int s;
    int e;

    public Puddle(int s, int e){
        this.s = s;
        this.e = e;
    }

    @Override
    public int compareTo(Puddle o){
        return this.s - o.s;
    }
}
public class Main {
    static int N;
    static int L;
    static Puddle[] puddles;

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        puddles = new Puddle[N];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            puddles[i] = new Puddle(s,e);
        }

        Arrays.sort(puddles);

        int cover = 0;
        int ans = 0;

        // L 판자 길이
        for(Puddle p : puddles){
            if(cover >= p.e)
                continue;
            int start = Math.max(p.s, cover);
            int length = p.e - start;

            // length에 대해 L 올림 계산을 해서
            // 갯수 구함
            int need = (length + L-1) / L;
            ans += need;
            cover = start + need * L;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}