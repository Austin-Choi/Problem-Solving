/*
구간의 시작점 i, 끝점 j를 잡고
j=i+1에서 끝까지 늘려봄
그리고 하나 할때마다 max, min계산하는데 
하나 추가되면 max는 바로 체크 가능하고 -> cnt[c-'a']++이니까
min은 음 26개 순회?

*/

class Solution {
    final int INF = 501;
    public int beautySum(String s) {
        int ans = 0;
        char[] in = s.toCharArray();
        int N = in.length;
        
        for(int i = 0; i<N; i++){
            int[] cnt = new int[26];
            cnt[in[i] - 'a']++;
            int max = cnt[in[i] - 'a'];
            for(int j = i+1; j<N; j++){
                cnt[in[j]-'a']++;
                max = Math.max(max, cnt[in[j]-'a']);

                int min = INF;
                for(int n : cnt){
                    if(n == 0)
                        continue;
                    min = Math.min(min, n);
                }
                ans += (max-min);
            }
        }
        return ans;
    }
}