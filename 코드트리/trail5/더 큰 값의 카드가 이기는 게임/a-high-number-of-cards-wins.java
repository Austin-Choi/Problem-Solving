import java.util.*;
import java.io.*;

/*
게임 이론?
B 카드 내는 순서에서 보고 2N 카드 나눠가졌으니까 boolean으로 체크
카드 대소 유무로 최대 1점 획득 가능
-> 최대한 많은 큰 경우를 가져가야 하므로 A가 현재 낸 것보다 큰 최소 번호의 카드를 내야 함 
-> 없으면 아직 안쓴것중 최소값 내기 

treeSet 쓰거나 A카드 정렬해서 union-find + binarySearch
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        TreeSet<Integer> ts = new TreeSet<>();
        int N = read();
        boolean[] bb = new boolean[2*N+1];
        int[] bcard = new int[N];
        for(int i = 0; i<N; i++){
            int cur = read();
            bb[cur] = true;
            bcard[i] = cur;
        }

        for(int i =1 ; i<=2*N; i++){
            if(!bb[i])
                ts.add(i);
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            int cur = bcard[i];
            Integer find = ts.higher(cur);

            if(find != null){
                ans++;
                ts.remove(find);
            }
        }
        System.out.print(ans);
    }
}