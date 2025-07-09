import java.io.*;
import java.util.*;

public class Main {
    private static int T, N,M;
    private static int[] rank;
    private static ArrayList<Integer>[] board; // 간선 그래프 저장
    private static int[] indegree; // 작년 i등 팀의 진입차수 기록

    /*
    위상 정렬 결과가 정확히 하나로 나오면 -> 출력
    중간에 indegree 0이 되는게 2개 이상이 q에 들어가면 -> ?
    결과 list의 길이가 원본하고 맞지 않을 경우 -> 사이클(impossible)
     */
    private static String topologySort(){
        StringBuilder sb = new StringBuilder();
        List<Integer> output = new ArrayList<>();

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i<indegree.length; i++){
            if(indegree[i] == 0)
                q.add(i);
        }

        for(int j = 0; j<N; j++){
            if(q.isEmpty())
                return "IMPOSSIBLE";

            if(q.size()>=2)
                return "?";

            int cur = q.poll();
            output.add(cur);

            for(int n : board[cur]){
                if(--indegree[n] == 0)
                    q.add(n);
            }
        }

        for(int i : output){
            sb.append(i).append(" ");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            board = new ArrayList[N+1];
            for(int i = 1; i<=N; i++){
                board[i] = new ArrayList<>();
            }
            indegree = new int[N+1];
            rank = new int[N+1]; // 인덱스가 팀, 값이 작년 등수

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int n = 1; n<=N; n++){
                int team = Integer.parseInt(st.nextToken());
                rank[n] = team;
            }

            // 기본 간선 만들기
            // 그냥 다음거에 연결하는게 아니라
            // 위상정렬의 정의를 생각해서
            // 만약 5가 맨앞이면 5-4 5-3 5-2 5-1 이렇게 넣고
            // 4가 다음이면 4-3 4-2 4-1 이렇게 넣어야함
            for(int i = 1; i<=N; i++){
                for(int j = i+1; j<=N; j++){
                    board[rank[i]].add(rank[j]);
                    indegree[rank[j]]++;
                }
            }

            M = Integer.parseInt(br.readLine());
            for(int m = 0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                // 주의점 remove에 그냥 넣으면 인덱스 지움
                // Integer.valueOf로 명시할것
                if(board[s].contains(e)){
                    board[s].remove(Integer.valueOf(e));
                    board[e].add(s);
                    indegree[e]--;
                    indegree[s]++;
                }
                else if(board[e].contains(s)){
                    board[e].remove(Integer.valueOf(s));
                    board[s].add(e);
                    indegree[s]--;
                    indegree[e]++;
                }
            }

            bw.write(topologySort());
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
