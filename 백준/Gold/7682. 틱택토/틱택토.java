/*
1) x갯수 , O갯수
2) x 승리 라인 수
3) o 승리 라인 수

x==o 또는 x==o+1이어야 함
x win = x == o+1
o win -> x == o
both win -> invalid
no win -> full board일때만 valid
 */
import java.util.*;
import java.io.*;
public class Main {
    static boolean win(char[] b, char p){
        return
                (b[0]==p && b[1]==p && b[2]==p) ||
                        (b[3]==p && b[4]==p && b[5]==p) ||
                        (b[6]==p && b[7]==p && b[8]==p) ||
                        (b[0]==p && b[3]==p && b[6]==p) ||
                        (b[1]==p && b[4]==p && b[7]==p) ||
                        (b[2]==p && b[5]==p && b[8]==p) ||
                        (b[0]==p && b[4]==p && b[8]==p) ||
                        (b[2]==p && b[4]==p && b[6]==p);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){

            String s = br.readLine();
            if(s.equals("end")) break;

            char[] b = s.toCharArray();

            int x=0,o=0;
            for(char c : b){
                if(c=='X') x++;
                if(c=='O') o++;
            }

            boolean xWin = win(b,'X');
            boolean oWin = win(b,'O');

            boolean valid = false;

            if(xWin && !oWin && x==o+1)
                valid=true;
            else if(oWin && !xWin && x==o)
                valid=true;
            else if(!xWin && !oWin && x+o==9 && x==o+1)
                valid=true;

            sb.append(valid ? "valid\n" : "invalid\n");
        }

        System.out.print(sb);
    }
}
