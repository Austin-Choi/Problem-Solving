import java.util.*;
import java.io.*;

//이거 태그가뭐지;
/*
관찰한건 시간순서니까 일단 입력받고
처음꺼랑 비교해서 달라지면 cnt++
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        ArrayList<Integer>[] A = new ArrayList[11];
        for(int i = 0; i<=10; i++)
            A[i] = new ArrayList<>();

        for(int i = 1; i<=N; i++){
            int u = read();
            int v = read();
            A[u].add(v);
        }

        int ans = 0;
        for(List li : A){
            if(li.size()<2)
                continue;
            int cur = (int) li.get(0);
            for(int i = 1; i<li.size(); i++){
                if(!li.get(i).equals(cur)){
                    cur = (int) li.get(i);
                    ans++;
                }
            }
        }
        System.out.print(ans);
    }
}