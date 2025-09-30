/*
전부다 하나에 넣고 정렬함(Info class)
그리고 모든 학생의 능력치는 unique 하므로 빠진 학급이 없게
학급이 다 포함되었으면
최댓값과 최소값의 차이를 적어지게 하려면 l을 증가시키고
포함 x면
coverd == N을 만족시키기 위해 r++

isIn[classId] 값이 0이면 포함X
>0 이면 포함
 */
import java.util.*;
import java.io.*;
public class Main {
    static class Info implements Comparable<Info>{
        int power;
        int id;
        Info(int p, int i){
            this.power = p;
            this.id = i;
        }

        @Override
        public int compareTo(Info o){
            return this.power - o.power;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Info[] arr = new Info[N*M];
        int id = 0;
        for(int i = 0; i<N*M;){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m<M; m++, i++){
                int p = Integer.parseInt(st.nextToken());
                arr[i] = new Info(p,id);
            }
            id++;
        }
        int[] isIn = new int[id];
        int l = 0;
        int r = 0;
        int ans = Integer.MAX_VALUE;
        Arrays.sort(arr);
        // isIn에서 0아닌 학급 종류 갯수
        int covered = 0;

        while(l < N*M){
            // 학급 다 안들어가서 오른쪽으로 늘림
            if(covered < N){
                if(r == N*M)
                    break;
                int ri = arr[r].id;
                if(isIn[ri] == 0)
                    covered++;
                isIn[ri]++;
                r++;
            }
            // 학급 다들어왔으니까 차이 최소화함
            else {
                int li = arr[l].id;
                ans = Math.min(ans, arr[r-1].power - arr[l].power);
                isIn[li]--;
                if(isIn[li] == 0)
                    covered--;
                l++;
            }
        }

        System.out.println(ans);
    }
}