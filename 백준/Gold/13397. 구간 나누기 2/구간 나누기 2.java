/*
결정 문제 : M개 이하의 구간으로 나눠서 구간의 최댓값 - 최솟값이 X(mid) 이상이 되게 할 수 있는가
왼쪽부터 순서대로 뽑으면서 현재 구간의 min/max를 갱신함
만약 현재 max - min > X가 되는 순간, 바로 이전 원소까지를 하나의 구간으로 확정하고 새 구간을 시작함
이렇게 끝까지 갔을 때 필요한 구간 수가 M 이하이면 X로 가능, 아니면 불가능

가능하면 오른쪽 범위를 줄이고 r=mid
불가능하면 왼쪽을 올림 l=mid+1

min = 0 (1개로 이뤄져있을 경우)
max = 전체의 max - 전체의 min
저 범위 안에서 이분 탐색 돌리기

주의점
구간은 최소 1임 count = 1;
구간 새로 시작할때 min, max 초기화해야함

while(l<r) 이면 r = mid;
루프 후 right이 답
 */
import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int[] arr;
    // [ start, end ]
    static boolean can(int x){
        int max = arr[0];
        int min = arr[0];
        int count = 1;
        for(int i = 0; i<N; i++){
            if(arr[i] > max)
                max = arr[i];
            if(arr[i] < min)
                min = arr[i];
            if(max - min > x) {
                count++;
                max = min = arr[i];
            }
            if(count > M)
                break;
        }
        return count <= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int l = Integer.MAX_VALUE;
        int r = 0;
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] > r)
                r = arr[i];
            if(arr[i] < l)
                l = arr[i];
        }
        r -= l;
        l = 0;
        while(l < r){
            int mid = (l+r) / 2;
            if(can(mid)) {
                r = mid;
            }
            else
                l = mid+1;
        }

        System.out.println(r);
    }
}
