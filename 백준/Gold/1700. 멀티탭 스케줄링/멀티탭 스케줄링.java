import java.util.*;
import java.io.*;
public class Main {
    static int N,K;
    static Set<Integer> tap;
    static int[] board;
    static int[] getNeverUsed(int startIdx){
        int[] rst = new int[2];
        rst[0] = -1;
        rst[1] = 0;
        Set<Integer> temp = new HashSet<>();
        for(int i = startIdx; i<K; i++){
            temp.add(board[i]);
        }
        for(int item : tap){
            if(!temp.contains(item)){
                rst[0] = item;
                rst[1] = 1;
                return rst;
            }
        }

        return rst;
    }

    static int getLatestUsed(int cur){
        int maxLen = 0;
        int latestItem = -1;
        for(int item : tap){
            int len = 0;
            for(int i = cur; i<K; i++){
                if(item == board[i]){
                    break;
                }
                len++;
            }
            if(maxLen < len){
                maxLen = len;
                latestItem = item;
            }
        }
        return latestItem;
    }

    public static void main(String[] args) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 멀티탭 set 크기
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[K];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<K; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        tap = new HashSet<>();
        for(int i = 0; i<K; i++){
            if(tap.size() < N){
                tap.add(board[i]);
            }
            else{
                if(tap.contains(board[i]))
                    continue;
                else{
                    // 미래에 아예 사용되지 않을 물건 찾은 경우 먼저 뺌
                    int[] temp = getNeverUsed(i);
                    if(temp[1] == 1)
                        tap.remove(temp[0]);

                    // 탭에 있는 물건이 전부 미래에 언젠가는 사용된다면
                    // 다음으로 등장하는 텀이 가장 긴 아이템 제거해야함
                    else
                        tap.remove(getLatestUsed(i));

                    // 그리고 뺀 자리에 삽입함
                    tap.add(board[i]);
                    ans++;
                }
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
