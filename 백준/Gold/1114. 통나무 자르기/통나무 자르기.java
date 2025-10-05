/*
이분탐색 + 그리디
1) 최대 조각 길이 mid 1로 가능한지? L이 엄청 큼
2) 최대 조각 길이가 고정되었을때 C번 자른 결과물 중
가장 긴 조각이 고정된 최대 조각 길이가 나오게 할 수 있는지?

-> 가능하면 어디가 가장 왼쪽에 있는 자르는 위치인지?
-> 단조성 만드려면 항상 뒤쪽이 mid이하로 보장되야함
-> 오른쪽 끝[K-1]부터 진행 (현재 조각의 시작점)
------------------------------------------
입력 중복 제거, 정렬
 */
import java.io.*;
import java.util.*;
public class Main {
    static int L,K,C;
    static int[] cuts;
    // 최대 조각이 x라는 걸 만족하면서 C번 자를수 있는지
    // 가능하면 첫번째 자르는 위치 반환
    static int can1(int x){
        int last = L;
        int count = 0;
        int first = 0;
        for(int i = K-1; i >= 0; i--){
            if(last - cuts[i] > x){
                if(i == K-1)
                    return -1;
                last = cuts[i+1];
                if(last - cuts[i] > x)
                    return -1;
                count++;
                first = cuts[i+1];
            }
            if(count >= C)
                break;
        }
        if(count < C)
            first = cuts[0];
        if(first > x)
            return -1;
        return first;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cuts = new int[K];

        st = new StringTokenizer(br.readLine());
        for(int k = 0; k<K; k++){
            cuts[k] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cuts);
        int uc = 1;
        for(int i = 1; i<K; i++){
            if(cuts[i] != cuts[i-1]){
                cuts[uc++] = cuts[i];
            }
        }
        K = uc;

        int l1 = 1;
        int r1 = L;
        int al = L;
        int ap = 0;
        while(l1<=r1){
            int mid = (l1+r1) / 2;
            int rst = can1(mid);
            if(rst != -1){
                al = mid;
                ap = rst;
                r1 = mid -1;
            }
            else{
                l1 = mid + 1;
            }
        }
        System.out.println(al+" "+ap);
    }
}