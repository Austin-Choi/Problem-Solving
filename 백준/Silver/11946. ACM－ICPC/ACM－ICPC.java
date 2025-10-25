import java.util.*;
import java.io.*;
public class Main {
    static int N,M,Q;
    static Team[] teams;
    static class Team implements Comparable<Team>{
        int id;
        int totalAC;
        int totalT;
        int[] quest;
        Team(int id){
            this.id = id;
            this.totalAC = 0;
            this.totalT = 0;
            this.quest = new int[M];
        }

        // 0시간에 AC 받을때 -1 처리가 안됨
        public void update(int qn, int time, String cmd){
            if(quest[qn] < 0)
                return;
            if(cmd.equals("AC")){
                // AC 받은건 음수로 표기 (total 계산시 abs해줄거임)
                quest[qn] = -(quest[qn]+time+1);
            }
            else{
                quest[qn] +=  20;
            }
        }

        public void calcTotal(){
            for(int t : quest){
                if(t < 0){
                    totalAC++;
                    totalT += (-t-1);
                }
            }
        }

        @Override
        public int compareTo(Team o){
            if(this.totalAC == o.totalAC){
                if(this.totalT == o.totalT){
                    return this.id - o.id;
                }
                else
                    return this.totalT - o.totalT;
            }
            else
                return o.totalAC - this.totalAC;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        teams = new Team[N];
        for(int i = 0; i<N; i++){
            teams[i] = new Team(i);
        }

        for(int q = 0; q<Q; q++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int id = Integer.parseInt(st.nextToken())-1;
            int qn = Integer.parseInt(st.nextToken())-1;
            String cmd = st.nextToken();
            teams[id].update(qn,t,cmd);
        }
        for(Team t : teams){
            t.calcTotal();
        }

        Arrays.sort(teams);
        StringBuilder sb = new StringBuilder();
        for(Team t : teams){
            sb.append(t.id+1).append(" ")
                    .append(t.totalAC).append(" ")
                    .append(t.totalT).append("\n");
        }
        System.out.println(sb);
    }
}
