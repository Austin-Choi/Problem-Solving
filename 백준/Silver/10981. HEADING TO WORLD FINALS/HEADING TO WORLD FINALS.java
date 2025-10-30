/*
대학별로 최고팀들 만들고
최고팀만 모아서 정렬 한번 더 해서 K개 출력
--
해싱해서 풀되, 따로 value값 또 연결하지 말고 바로 pq 연결하기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,K;
    static Map<String, PriorityQueue<Team>> schools = new HashMap<>();
    static PriorityQueue<Team>[] teams;
    static class Team implements Comparable<Team>{
        String name;
        int solved;
        int penalty;
        Team(String n, int s, int p){
            this.name = n;
            this.solved = s;
            this.penalty = p;
        }
        @Override
        public int compareTo(Team o){
            if(o.solved == this.solved){
                return this.penalty - o.penalty;
            }
            return o.solved - this.solved;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        teams = new PriorityQueue[100001];
        for(int i = 0; i<100001; i++){
            teams[i] = new PriorityQueue<>();
        }
        int id = 0;
        while(N-->0){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            String n = st.nextToken();
            int sol = Integer.parseInt(st.nextToken());
            int pen = Integer.parseInt(st.nextToken());
            PriorityQueue<Team> cur = null;
            if(!schools.containsKey(s))
                schools.put(s,new PriorityQueue<>());
            cur = schools.get(s);
            cur.add(new Team(n,sol,pen));
        }

        PriorityQueue<Team> bests = new PriorityQueue<>();
        for(PriorityQueue<Team> pq : schools.values()){
            Team cur = pq.poll();
            bests.add(cur);
        }

        StringBuilder sb = new StringBuilder();
        for(int i= 0; i<K && !bests.isEmpty(); i++){
            Team cur = bests.poll();
            sb.append(cur.name).append("\n");
        }

        System.out.println(sb);
    }
}
