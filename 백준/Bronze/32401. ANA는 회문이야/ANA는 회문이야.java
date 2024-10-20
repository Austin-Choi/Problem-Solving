import java.io.*;

/*
입력 문자열의 부분 문자열 중에
substring으로 첫번째, 마지막 문자 떼어내고 이게 A인지 확인
나머지 문자열에서 N이랑 A 갯수 카운트

121 121 121 121 121 12

12121212112112

N입력될때 앞뒤 1인지 체크해서 count
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        StringBuilder ada = new StringBuilder();
        for(String str : s.split("")){
            if(str.equals("A") || str.equals("N")){
                ada.append(str);
            }
        }
        String[] ls = ada.toString().split("");
        int ans = 0;
        for(int i = 0; i<ls.length; i++){
            if(ls[i].equals("N")){
                if(i != 0 && i != ls.length-1){
                    if(ls[i-1].equals("A") && ls[i+1].equals("A"))
                        ans++;
                }
            }
        }
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
