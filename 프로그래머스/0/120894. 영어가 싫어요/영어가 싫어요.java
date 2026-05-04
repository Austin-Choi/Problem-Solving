class Solution {
    public long solution(String numbers) {
        String[] stn = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i =0; i<10; i++){
            String n = stn[i];
            numbers = numbers.replaceAll(n, String.valueOf(i));
        }
        return Long.parseLong(numbers);
    }
}