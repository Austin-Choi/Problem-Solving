import java.util.*;
import java.io.*;

/*
만약 여러번 한다면 빼기만 하니까 인덱스를 그만큼 끌어오면 되는거 아닐까
-> 그러면 그때마다 temp 써야해서 어차피 안될듯 한꺼번에 계산은

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    // 값
    static int[] A;

    // 1 2 3 1 1 5
    // 1 x x x 1 5
    // 1 1 5
    static void remove(int s, int e){
        s--;
        e--;
        List<Integer> li = new ArrayList<>();
        for(int i = 0; i<A.length; i++){
            if(s <= i && i <= e)
                continue;
            else
                li.add(A[i]);
        }
        int[] temp = new int[li.size()];
        for(int i = 0; i<temp.length; i++){
            temp[i] = li.get(i);
        }
        A = temp;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }

        int Q = 2;
        while(Q-->0){
            remove(read(), read());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(A.length).append("\n");
        for(int i = 0; i<A.length; i++){
            sb.append(A[i]).append("\n");
        }
        System.out.print(sb);
    }
}