/*
현재 순서 그대로 K개의 그룹으로 나눈뒤
맞은 문제 개수 합의 최소값(mid)을 최대로 하는 것

결정 함수
처음부터 쭉 문제 개수 합을 더하고 mid 초과면
그룹을 +1을 하고
그렇게 해서 그룹 수가 K 이상인지 반환

최소 : 요소의 최솟값
최대 : 전체 더한 것
l<r
l+r+1/2
l = mid
r = mid - 1

주의점 count 조기반환 안됨
total이 너무 작아서 그룹 못만드는 경우 있을 수 있음.
해야 하는건 최댓값 탐색임 l = mid; r = mid - 1;
최솟값 탐색이면 r = mid; l = mid + 1;
최댓값 상한탐색이면 l+r+1 / 2를 해야함
 */

import java.util.*;
import java.io.*;
public class Main {
    static int N, K;
    static int[] arr;
    static boolean can(int x){
        int count = 0;
        int total = 0;
        for(int i = 0; i<N; i++){
            total += arr[i];
            if(total >= x){
                count++;
                total = 0;
            }
        }
        return count >= K;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int l = 0;
        int r = 0;
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            r += arr[i];
        }

        while(l<r){
            int mid = (l+r+1) / 2;
            if(can(mid)){
                l = mid;
            }
            else
                r = mid-1;
        }

        System.out.println(l);
    }
}