import java.io.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] temp = br.readLine().toCharArray();
        int rst = 0;
        int star = -1;
        for(int i = 0; i<13; i++){
            if(!Character.isDigit(temp[i])) {
                star = i;
                continue;
            }
            int n = temp[i]-'0';
            if(i%2 == 0){
                rst += n;
            }
            else
                rst += 3*n;
        }

        int x = (10 - (rst%10)) % 10;
        if(star % 2 == 1)
            x = (x*7) % 10;
        System.out.print(x);
    }
}