import java.util.*;
import java.io.*;
public class Main {
    // x, l, c(r,y,b)
    static class Info{
        int idx;
        int pos;
        int len;
        int color;
        Info(){};
        Info(int i, int x, int l, int c){
            this.idx = i;
            this.pos = x;
            this.len = l;
            this.color = c;
        }
    }
    static int N;
    static Info[] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new Info[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            board[i] = new Info();
            board[i].idx = i+1;
            board[i].pos = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            board[i].len = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            String color = st.nextToken();
            if(color.equals("R"))
                board[i].color = 0;
            else if(color.equals("Y"))
                board[i].color = 1;
            else
                board[i].color = 2;
        }
        Arrays.sort(board, Comparator.comparingInt(o->o.pos));
        int ans1 = -1;
        int ans2 = -1;
        for(Info a : board){
            for(Info o : board){
                if(a.color != o.color && Math.abs(a.pos - o.pos)<= a.len + o.len){
                    ans1 = a.idx;
                    ans2 = o.idx;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if(ans1 != -1){
            sb.append("YES\n");
            sb.append(ans1).append(" ").append(ans2);
        }
        else{
            sb.append("NO");
        }
        System.out.println(sb);
    }
}
