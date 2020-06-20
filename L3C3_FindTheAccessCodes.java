public class L3C3_FindTheAccessCodes {

    public static int solution2(int[] l) {
        int length = l.length;

        int[] countOfDivisor = new int[length];
        int[] isExists = new int[l[length-1]];
        int count = 0, countOfOnes = 0;

        for(int i = 0 ; i<l.length ; i++){
            if(isExists[l[i]-1] == 0) isExists[l[i]-1] = i+1 ;
            if(l[i] == 1) countOfOnes++;
        }

        for(int i = 0 ; i<l.length ; i++){
            for(int j = 1 ; j <= Math.sqrt(l[i] ) ; j++){
                if(isExists[j-1] > 0){
                    if(l[i] % j == 0){
                        if(!(i==0 && l[i] == 1)) countOfDivisor[i]++;
                        int result = l[i] / j;
                        if(isExists[result - 1] > 0){
                            if(result != l[i]){
                                if(result == j) count += countOfDivisor[isExists[result-1] - 1];
                                else{
                                    countOfDivisor[i]++;
                                    count += countOfDivisor[isExists[j-1]-1] + countOfDivisor[isExists[result-1] - 1];
                                }
                            }
                        }
                    }
                }
            }
        }
        if(countOfOnes == 2) count += (length - 2);
        else if(countOfOnes > 2) count += (length - countOfOnes) + 1;

        return count;
    }
    /*
    Solution in O(n^2) time, O(n) space, where n is the input length
    - For all couples of numbers (z,w), if z / w -> z is dividend, and w is divisor.
    - if a number is dividend and also a divisor, so it can appear (dividend * divisor) times in "triple"
    Params: list
    Return: count of all "lucky triples"
    */
    public static int solution(int[] l) {
        int length = l.length;
        int[] dividendCount  = new int[length];
        int[] divisorCount = new int[length];

        for(int i = 0 ; i<length ; i++){
            for(int j = i+1 ; j<length ; j++){
                if(l[j]%l[i]==0){
                    dividendCount[j] += 1;
                    divisorCount[i] += 1;
                }
            }
        }
        int result = 0;
        for(int i = 0; i<length ; i++){
            result += dividendCount[i] * divisorCount[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] test1 = new int[]{1, 2, 3, 5, 7, 9, 10};
        int[] test2 = new int[]{1, 1, 1};
        int[] test3 = new int[]{1, 3, 5, 7, 11, 13, 17, 19, 23};
        int result1 = solution(test1);
        int result2 = solution(test2);
        int result3 = solution(test3);
        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);
        System.out.println("result3: " + result3);
    }
}
