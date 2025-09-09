
/*
N*N으로 받고
이전 좌표에 +K+count한거 0으로 만들고 출력함 (0 count = 0)
근데 오면서 0이 있었으면 0 count ++??

bi = -1;
7 3
1 2 - 4 5 6 7  [2] -1+K
1 2 - 4 5 - 7  [5] 2+3
1 - 3 4 5 6 7  [1] 5+3 % N
1 - - 4 5 - -  [6] 1+3+2
1 - - 4 - 6 7  [4] 6+3+2 % N (bi + K + 0count) % N
- 2 3 4 5 6 7  [0]
1 2 3 - 5 6 7  [3]
(bi + K + 0count) % N 

------------------------------------
시간 복잡도 N*N
list.remove는 해당 인덱스 값을 없애줌
-> 계속 count ++ 하다가 count == K면
remove 하고 ans.add하고
count = 0 break;
 */
import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i<N; i++){
            arr.add(i+1);
        }
        // before
        int bi = 0;
        // K count
        int count = 0;

        for(int i = 0; i<N; i++){
            while(count < K){
                bi%=arr.size();
                count++;
                if(count == K){
                    ans.add(arr.get(bi));
                    arr.remove(bi);
                    count = 0;
                    break;
                }
                bi++;
            }
        }

        StringBuilder sb = new StringBuilder("<");
        for(int i = 0; i<N; i++){
            if(i != N-1)
                sb.append(ans.get(i)).append(", ");
            else
                sb.append(ans.get(i)).append(">");
        }
        System.out.println(sb);
    }
}
