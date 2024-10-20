import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String burger = "(1)";
        String input = br.readLine();
        if(input.equals(burger)){
            bw.write(0+"");
        }
        else if(input.equals(")1(")){
            bw.write(2+"");
        }
        else
            bw.write(1+"");
        bw.flush();
        bw.close();
        br.close();
    }
}