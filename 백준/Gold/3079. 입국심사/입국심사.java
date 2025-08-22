/*
최적화 문제를 결정 문제로 변환
어떤시간 T가 되었을때 모든 사람이 심사가 완료 되는가?

T에서 각 입국 심사대의 시간으로 나눈 몫이 사람 수에 비해서 작은지 큰지

T 최대값은 입국심사의 가장 오래걸리는거 * 인원수
 */

import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static List<Long> li = new ArrayList<>();

    static boolean canFinish(long total){
        long people = 0;
        for(long time : li){
            people += total / time;
            if(people >= M)
                return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 심사대 N, 사람수 M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i<N; i++){
            li.add(Long.parseLong(br.readLine()));
        }
        Collections.sort(li);
        long start = 0;
        long end = li.get(0) * M;

        while(start < end){
            long mid = (start + end) / 2;
            if(canFinish(mid))
                end = mid;
            else
                start = mid+1;
        }

        System.out.println(start);
    }
}
