import java.util.*;
import java.io.*;

/*
  1
 2 3
4 5

Q 이동중에 과거에 도착 완료된 노드를 만나지 않았으면
계속 이동하고 루트에 도달하면 해당 도착 노드를 칠함

근데 이동을 부모쪽으로 해야해서 루트->목적지로 하면 경우가 2개가 되지만
목적지->루트로 하면 경우 1개(부모)로 상태압축되서 역으로 하고 
역으로 해도 경로는 같으니까 경로 저장해주고
경로 거꾸로 해서 루트에서 시작했을때 처음으로 막히는 (used.contains)인 노드 반환
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,Q;
    static Set<Integer> used = new HashSet<>();

    static int setRoute(int dest){
        ArrayList<Integer> path = new ArrayList<>();

        while(dest > 0){
            path.add(dest);
            dest /= 2;
        }
        for(int i = path.size()-1; i>=0; i--){
            if(used.contains(path.get(i)))
                return path.get(i);
        }
        return 0;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        Q = read();

        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            int dest = read();
            int rst = setRoute(dest);
            if(rst == 0){
                used.add(dest);
            }
            sb.append(rst).append("\n");
        }
        System.out.print(sb);
    }
}