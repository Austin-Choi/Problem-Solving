import java.util.*;
import java.io.*;

/*
폭발시킬 것 선정
0으로 만들고
한번에 당기기
-> 순차적으로 하면 한꺼번에 터뜨리지 않아도 같을 줄 알았음
-> 근데 아럐 반례를 보면 처음 11 클러스터를 전부 같은 레벨에서 터뜨리지 않으면
나중에 앞의 다른 숫자때문에 이상하게 터져서 뒤의 21212로 끝나버림
-> find를 하나만 찾을게 아니라 int[클러스터 수][2]로 전부 찾아서 돌려야 함.

반례 : 
입력
15 2
2 1 2 1 1 2 1 2 1 1 2 1 2 1 2
---------
2 1 2 0 0 2 1 2 1 1 2 1 2 1 2
2 1 0 0 0 0 1 2 1 1 2 1 2 1 2
2 0 0 0 0 0 0 2 1 1 2 1 2 1 2
0 0 0 0 0 0 0 0 1 1 2 1 2 1 2
0 0 0 0 0 0 0 0 0 0 2 1 2 1 2
-> 2 1 2 1 2 오답(내 풀이)
-------------
2 1 2 0 0 2 1 2 0 0 2 1 2 1 2
2 1 0 0 0 0 1 0 0 0 0 1 2 1 2
2 0 0 0 0 0 0 0 0 0 0 0 2 1 2
0 0 0 0 0 0 0 0 0 0 0 0 0 1 2
-> 2 1 2 정답

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] A;

    // 폭발시킬 구간 s,e 반환
    static ArrayList<int[]> find(){
        ArrayList<int[]> li = new ArrayList<>();

        int i = 0;
        while(A[i] == 0){
            if(i == N-1){
                return li;
            }
            i++;
        }

        int s = i;
        int e = i;
        int curType = A[i++];
        int curCnt = 1;

        for(; i<N; i++){
            if(curType == A[i]){
                curCnt++;
                e = i;
            }
            else{
                if(A[i] == 0)
                    continue;
                if(curCnt >= M)
                    li.add(new int[]{s,e});
                
                curType = A[i];
                curCnt = 1;
                s = i;
                e = i;
            }
        }

        if(curCnt >= M)
            li.add(new int[]{s,e});
        return li;
    }

    static void bomb(List<int[]> li){
        for(int[] l : li){
            int s = l[0];
            int e = l[1];
            for(int i = s; i<=e; i++){
                A[i] = 0;
            }
        }
    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i = 0; i<N; i++){
            if(A[i] != 0){
                cnt++;
                sb.append(A[i]).append("\n");
            }
        }
        String ss = sb.toString();
        System.out.print(cnt+"\n"+ss);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        A = new int[N];
        for(int i= 0; i<N; i++)
            A[i] = read();
        
        while(true){
            ArrayList<int[]> li = find();
            //System.out.println(rst[0] +" "+rst[1]);
            if(li.isEmpty())
                break;
            bomb(li);
        }
        print();
    }
}