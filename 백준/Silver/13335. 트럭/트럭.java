/*
덱이 비는것 목표로 하고
0을 채워넣음으로써 덱의 공간을 '설정'하고
현재 다리 총 무게 tw와 다음 넣을 트럭 idx를 보고
시간의 흐름에 따라 항상 맨 앞을 poll하고 (쭉쭉 진행)
tw가 L보다 적으면 트럭을 넣고 아니면 0을 넣기
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N, W, L;
    static int[] trucks;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        trucks = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> dq = new ArrayDeque<>();
        // 다리를 0의 칸으로 초기화
        for(int i = 0; i<W; i++)
            dq.addLast(0);

        int ans = 0;
        int tw = 0;
        int idx = 0;

        while(!dq.isEmpty()){
            ans++;
            tw -= dq.pollFirst();

            if(idx < N){
                if(tw + trucks[idx] <= L){
                    dq.addLast(trucks[idx]);
                    tw += trucks[idx];
                    idx++;
                }
                else{
                    dq.addLast(0);
                }
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
