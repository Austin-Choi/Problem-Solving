/*
세로가 한 세트 0123
N E S W
0,2 / 1,3이 같은팀

2 3 4 5 6 7 8 9 T(10) J(11) Q(12) K(13) A(14)
 */
import java.util.*;
import java.io.*;
public class Main {
    static char[][][] deal;
    static String debug1(int idx){
        if(idx == 0)
            return "north";
        if(idx == 1)
            return "east";
        if(idx == 2)
            return "south";
        return "west";
    }
    static int cardToNum(char[] card){
        char rank = card[0];
        if(Character.isDigit(rank))
            return rank-'0';
        if(rank == 'T')
            return 10;
        if(rank == 'J')
            return 11;
        if(rank == 'Q')
            return 12;
        if(rank == 'K')
            return 13;
        else
            return 14;
    }

    static char trump;
    static int[] scores;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> m =new HashMap<>();
        while(true){
            trump = br.readLine().charAt(0);
            if(trump == '#')
                break;
            deal = new char[4][13][2];
            scores = new int[2];
            for(int i = 0; i<4; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j<13; j++)
                    deal[i][j] = st.nextToken().toCharArray();
            }

            int lead = 0;
            char suit;
            for(int j = 0; j<13; j++){
                char[][] trick = new char[4][2];
                int[] rank = new int[4];
                boolean[] isTrump = new boolean[4];
                boolean isAllTrump = true;
                for(int i = 0; i<4; i++){
                    trick[i] = deal[i][j];
                    rank[i] = cardToNum(trick[i]);
                    if(trick[i][1] == trump)
                        isTrump[i] = true;
                    else
                        isAllTrump = false;
                }
                suit = trick[lead][1];

                boolean isAllNotTrump = true;
                for(int i = 0; i<4; i++){
                    if(isTrump[i]){
                        isAllNotTrump = false;
                    }
                }

                // 모두가 트럼프일때, -> 앞에 숫자만 판단
                // 모두가 트럼프가 아닐때 -> leadsuit인거 중에서만 숫자판단
                // 한두개만 트럼프가 아닐때 -> 트럼프 아닌거 제외하고 숫자판단
                int max = 0;
                int maxIdx = -1;
                if(isAllTrump){
                    for(int k = 0; k<4; k++){
                        int i = (lead+k)%4;
                        if(max < rank[i]){
                            max = rank[i];
                            maxIdx = i;
                        }
                    }
                } else if(isAllNotTrump){
                    for(int k = 0; k<4; k++){
                        int i = (lead+k)%4;
                        if(trick[i][1] == suit){
                            if(max < rank[i]){
                                max = rank[i];
                                maxIdx = i;
                            }
                        }
                    }
                } else{
                    for(int k = 0; k<4; k++){
                        int i = (lead+k)%4;
                        if(!isTrump[i])
                            continue;
                        if(max < rank[i]){
                            max = rank[i];
                            maxIdx = i;
                        }
                    }
                }
                scores[maxIdx%2]++;
                lead = maxIdx;
            }

            // 0 = NS, 1 = EW
            if(scores[0] > scores[1]){
                sb.append("NS ");
                sb.append(scores[0]-6).append("\n");
            }
            else{
                sb.append("EW ");
                sb.append(scores[1]-6).append("\n");
            }
        }
        System.out.print(sb);
    }
}
