
/*
0<1번<=2 : 회복량은 입력으로 변화
2<2번<=4
4<3번<=6
6<4번<=8
8<5번<=10
10<6번<12

L = 행동수
S.T초에 뭔가 행동하는데 1분이 지나면 회복하고 끝나니까 S.T초는 신경 x
매번 하기 전에 모든 칸이 봉인되었는지 보기 -> 그때 끝남
-> S.T초에서 S가 60이상인건 볼필요없음

 */
import java.util.*;
import java.io.*;
public class Main {
    static class Time{
        // 0~12
        int hh;
        // 0~59
        int mm;
        Time(int h, int m){
            this.hh = h;
            this.mm = m;
        }
        void addMinute(int min){
            int carry = 0;
            int tm = this.mm + min;
            if(tm > 60)
                carry = 1;
            this.mm = tm % 60;
            addHour(0,carry);
        }
        void addHour(int hour, int carry){
            this.hh = (this.hh + hour + carry)% 12;
        }
        void addTime(String cmd){
            if(cmd.equals("10MIN")){
                addMinute(10);
            }
            else if(cmd.equals("30MIN")){
                addMinute(30);
            }
            else if(cmd.equals("50MIN")){
                addMinute(50);
            }
            else if(cmd.equals("2HOUR")){
                addHour(2,0);
            }
            else if(cmd.equals("4HOUR")){
                addHour(4,0);
            }
            else if(cmd.equals("9HOUR")){
                addHour(9,0);
            }
        }
        int getHHarea(){
            if(0<=this.hh && this.hh <2)
                return 0;
            if(2<=this.hh && this.hh <4)
                return 1;
            if(4<=this.hh && this.hh <6)
                return 2;
            if(6<=this.hh && this.hh <8)
                return 3;
            if(8<=this.hh && this.hh <10)
                return 4;
            if(10<=this.hh)
                return 5;
            return -1;
        }
    }
    static int[] regen = new int[6];
    static boolean[] isPressed = new boolean[6];
    static boolean isAllPressed(){
        boolean rst = true;
        for(boolean b : isPressed) {
            rst = b;
            if(!rst)
                return rst;
        }
        return rst;
    }
    static int L= 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] curin = br.readLine().split(":");
        Time cur = new Time(Integer.parseInt(curin[0]), Integer.parseInt(curin[1]));

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<6; i++){
            regen[i] = Integer.parseInt(st.nextToken());
        }
        L = Integer.parseInt(br.readLine());
        while(L-->0){
            st = new StringTokenizer(br.readLine());
            String[] temp = st.nextToken().split("\\.");
            if(Integer.parseInt(temp[0]) >= 60)
                break;
            if(isAllPressed())
                break;
            String cmd = st.nextToken();
            if(cmd.equals("^")){
                isPressed[cur.getHHarea()] = true;
            }
            else{
                cur.addTime(cmd);
            }
        }

        int tot = 0;
        for(int i = 0; i<6; i++){
            if(!isPressed[i]){
                tot += regen[i];
                if(tot > 100) {
                    System.out.println(100);
                    return;
                }
            }
        }
        System.out.println(tot);
    }
}