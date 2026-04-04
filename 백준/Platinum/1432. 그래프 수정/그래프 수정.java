import java.util.*;
import java.io.*;
/*
사이클이 있으면 수정 안됨
v1-v2 간선에서 v2의 번호는 v1보다 커야한다
-> 위상정렬

00001
00010
00000
00001
00100

1->5
2->4
4->5
5->3
정방향 그래프 입력
outdegree =0인거 중에 제일 큰거 우선으로 선택
거꾸로니까 거꾸로 숫자가 부여됨

 */

public class Main {
    static int N;
    static ArrayList<Integer>[] g;
    static int[] outdeg;
    static int num;
    static int[] ans;

    static boolean topologySort(){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i<N; i++){
            if(outdeg[i] == 0)
                pq.add(i);
        }
        // 사이클 검출용
        int count = 0;

        while(!pq.isEmpty()){
            int cur = pq.poll();

            // 여기서 번호 부여하던가
            // num이 N회 초과로 돌면 사이클임
            ans[cur] = num--;
            count++;

            // 역방향으로 입력받음
            for(int n : g[cur]){
                if(--outdeg[n] == 0){
                    pq.add(n);
                }
            }
        }

        // 위상 정렬 스텝이 정점수보다 많으면 사이클
        return count == N;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        outdeg = new int[N];
        ans = new int[N];
        num = N;
        g = new ArrayList[N];
        for(int i = 0; i<N; i++){
            g[i] = new ArrayList<>();
        }
        for(int i = 0; i<N; i++){
            char[] t = br.readLine().toCharArray();
            for(int j = 0; j<N; j++){
                if(t[j] == '1') {
                    g[j].add(i);
                    outdeg[i]++;
                }
            }
        }

        boolean rst = topologySort();

        if(!rst){
            System.out.print(-1);
        }
        else{
            StringBuilder sb = new StringBuilder();
            for(int i : ans){
                sb.append(i).append(" ");
            }
            System.out.print(sb);
        }
    }
}
