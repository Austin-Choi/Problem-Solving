/*
배열로 넣고 하나씩 클래스로 빼서 토큰화하고
클래스에 정렬 만들어주고 idx-값 매칭하는거 만들어서
idx 배열 클래스data 기준으로 정렬해서
idx 값으로 원래 값 순서대로 읽어오기

비교
1) 문자 vs 문자
AaBbCc...YyZz 로 비교
-> 원래 문자 따로 저장해놓고 둘다 소문자로 내려서 비교함
소문자로 내린게 같지 않다면 그거 대소 비교해주고
같으면 원래 문자 저장해둔거에서 누가 대문자인지 비교해서 대소 비교함
2) 문자 vs 숫자
숫자가 먼저 감
3) 숫자 vs 숫자
문자열로 읽어서 더 작은게 먼저 감
->여기서 앞에 연속된 0 세서 저장하고 제거함(trim)
같으면 앞의 0 갯수 작은게 먼저 감
-> 클래스로 빼주기
 */
import java.util.*;
import java.io.*;
public class Main {
    static class Token implements Comparable<Token>{
        boolean isNum;
        String data;
        String num;
        int zeroCount;
        Token(char c){
            this.isNum = false;
            this.data = String.valueOf(c);
            this.num = null;
            this.zeroCount = 0;
        }
        Token(String numstring){
            this.isNum = true;
            this.data = numstring;
            int i = 0;
            while(i<numstring.length() && numstring.charAt(i) == '0')
                i++;
            if(i == numstring.length()){
                this.num = "0";
                this.zeroCount = numstring.length()-1;
            }
            else{
                this.num = numstring.substring(i);
                this.zeroCount = i;
            }
        }

        @Override
        public int compareTo(Token o){
            if(this.isNum != o.isNum){
                return this.isNum ? -1 : 1;
            }
            // 둘다 isNum 상태가 같은데 한쪽이 숫자가 아님
            // -> 둘다 문자임
            if(!this.isNum){
                char a = Character.toLowerCase(this.data.charAt(0));
                char b = Character.toLowerCase(o.data.charAt(0));
                if(a != b)
                    return Character.compare(a,b);
                boolean ua = Character.isUpperCase(this.data.charAt(0));
                boolean ub = Character.isUpperCase(o.data.charAt(0));
                if(ua != ub)
                    return ua ? -1 : 1;
                return 0;
            }
            else{
                if(this.num.length() != o.num.length())
                    return Integer.compare(this.num.length(), o.num.length());
                int cmp = this.num.compareTo(o.num);
                if(cmp != 0)
                    return cmp;
                if(this.zeroCount != o.zeroCount)
                    return Integer.compare(this.zeroCount, o.zeroCount);
                return 0;
            }
        }
    }

    static List<Token> tokenize(String s){
        List<Token> tokens = new ArrayList<>();
        int n  = s.length();
        for(int i = 0; i<n; ){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int j = i;
                while(j<n && Character.isDigit(s.charAt(j)))
                    j++;
                tokens.add(new Token(s.substring(i,j)));
                i = j;
            }
            else {
                tokens.add(new Token(c));
                i++;
            }
        }
        return tokens;
    }

    static int compareTokens(List<Token> a, List<Token> b){
        int i = 0;
        int j = 0;
        while(true){
            if(i == a.size() && j == b.size())
                return 0;
            // a는 b의 접두사임
            if(i == a.size())
                return -1;
            if(j == b.size())
                return 1;

            int cmp = a.get(i).compareTo(b.get(j));
            if(cmp != 0)
                return cmp;
            i++;
            j++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = new String[N];
        List<List<Token>> tokensList = new ArrayList<>();

        for(int i = 0; i<N; i++){
            String s = br.readLine();
            input[i] = s;
            tokensList.add(tokenize(s));
        }

        Integer[] idx = new Integer[N];
        for(int i = 0; i<N; i++)
            idx[i] = i;

        Arrays.sort(idx, (i1, i2)-> compareTokens(tokensList.get(i1), tokensList.get(i2)));

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++)
            sb.append(input[idx[i]]).append("\n");
        System.out.println(sb);
    }
}
