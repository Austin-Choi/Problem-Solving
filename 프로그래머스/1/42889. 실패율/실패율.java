/*
사용자가 멈춰있는 stage는 stages에 나온 값이고 그 전꺼는 다 통과를 한거임
-> 각 스테이지에 멈춘 사람 수만 구하고 
뒤에서 누적해서 도달 인원 계산하기
*/
import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        // 멈춘 스테이지
        int[] stop = new int[N+2];
        for(int s : stages){
            stop[s]++;
        }
        
        double[] fail = new double[N+2];
        
        // stages 길이는 총 사용인원 수
        int total = stages.length;
        for(int i = 1; i<=N; i++){
            if(total == 0)
                fail[i] = 0;
            else
                fail[i] = (double) stop[i] / total;
            
            //다음 스테이지로 넘어간 사람 수
            total -= stop[i];
        }
        
        // 스테이지 번호, 실패율
        // 0-based
        double[][] arr = new double[N][2];
        for(int i = 0; i<N; i++){
            arr[i][0] = i+1;
            arr[i][1] = fail[i+1];
        }
        
        Arrays.sort(arr, (a,b)->{
            if(b[1] != a[1])
                return Double.compare(b[1], a[1]);
            return (int) a[0] - (int) b[0];
        });
        
        int[] rst = new int[N];
        for(int i = 0; i<N; i++){
            rst[i] = (int) arr[i][0];
        }
        return rst;
    }
}