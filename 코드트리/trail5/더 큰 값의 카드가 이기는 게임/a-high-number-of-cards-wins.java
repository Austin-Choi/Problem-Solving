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

    // 다음에 사용할 idx 저장해놓고 find로 건너뛰는 방식
    static int[] parent;
    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    // p < arr[idx]를 만족하는 최초 idx
    static int ub(int p, int[] arr){
        int l = 0;
        int r = arr.length-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(arr[mid] <= p){
                l = mid+1;
            }
            else
                r = mid-1;
        }
        return l;
    }

    public static void main(String[] args) throws IOException{
        //TreeSet<Integer> ts = new TreeSet<>();
        int N = read();
        boolean[] bb = new boolean[2*N+1];
        int[] bcard = new int[N];
        parent = new int[N+1];
        for(int i = 0; i<N; i++){
            int cur = read();
            bb[cur] = true;
            bcard[i] = cur;
            parent[i+1] = i+1;
        }

        int[] acard = new int[N];
        int ii = 0;
        for(int i =1 ; i<=2*N; i++){
            if(!bb[i]){
                //ts.add(i);
                acard[ii++] = i;
            }
        }

        Arrays.sort(acard);
        int ans = 0;
        for(int i = 0; i<N; i++){
            int cur = bcard[i];
            // TreeSet
            // Integer found = ts.higher(cur);
            // if(found != null){
            //     ans++;
            //     ts.remove(found);
            // }

            // union-find
            // 현재 b카드를 이기는 최소의 acard 찾기
            int idx = ub(cur, acard);
            // 가능한 최소 acard idx 찾기
            idx = find(idx);
            // 이기는 카드 없음
            if(idx == N)
                idx = find(0);
            else
                ans++;
            parent[idx] = find(idx+1);
        }

        System.out.print(ans);
    }
}