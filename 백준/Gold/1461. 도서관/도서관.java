/*
정렬해서 M개씩 그룹핑해서
그 그룹 왔다 가는건 (최소거리+최대거리)
이동은 (M개 요소 사의의 거리 m-1개)

그리고 M개씩 처리하는데 마지막에
M보다 적은 갯수가 남아있을 수 있는데
그중에 최소 최대 뽑고 똑같이 처리함

마지막에는 가기만 하면 됨(최소거리 + 구간)
----------------------------------
음수와 양수로 2개 그룹으로 나누고
abs() 거리순으로 내림차순 정렬한 뒤
그룹내 최대 구간을 * 2 하면 되고
가장 먼 위치 하나만 구해서 그건 편도 처리함
-> 그 값만큼 빼 주기
------------------------
 */
import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer> negative = new ArrayList<>();
        ArrayList<Integer> positive = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int maxNum = 0;
        for(int i = 0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(num < 0)
                negative.add(num);
            else
                positive.add(num);
            if(Math.abs(num)>maxNum)
                maxNum = Math.abs(num);
        }

        Collections.sort(negative, Comparator.comparingInt((Integer o)->Math.abs(o)).reversed());
        Collections.sort(positive, Collections.reverseOrder());

        int ans = 0;
        for(int i = 0; i<negative.size(); i+=M){
            ans += Math.abs(negative.get(i)) * 2;
        }
        for(int i = 0; i<positive.size(); i+=M){
            ans += Math.abs(positive.get(i)) * 2;
        }
        ans -= maxNum;

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
