public class L3C1_FuelInjectionPerfection {

    /*
    Function solution:  Greedy Algorithm - for each number x, if x is even divide by 2.
                        if x is odd: check if (x+1)/2 is even, if yes choose it, else choose x-1
    Params: String x
    Returns: int result - number of steps
     */
    public static int solution(String x) {
        int result = 0;
        if(x == null || x.length()>309) return -1;

        while(x.length() > 1) {
            char c = x.charAt(x.length() - 1);
            int lastCharOfX = convertCharToInteger(c);
            // check if x is even
            if (lastCharOfX % 2 == 0) {
                result++;
                x = divideStringBy2(x);
            }
            // if x is odd
            else {
                String addedOne = addOne(x);
                String tempDivide = divideStringBy2(addedOne);
                char lastCharOfTemp = tempDivide.charAt(tempDivide.length() - 1);
                int lastDigit = convertCharToInteger(lastCharOfTemp);
                result += 2;
                // if (x+1)/2 is even: choose x+1
                if (lastDigit % 2 == 0) {
                    x = tempDivide;
                }
                else x = divideStringBy2(subOne(x));
            }
        }
        int lastDigit = convertCharToInteger(x.charAt(0));

        // All cases when x is only one digit
        if(lastDigit == 9 || lastDigit == 7) result += 4;
        else if(lastDigit == 8 || lastDigit == 6 || lastDigit == 5) result += 3;
        else if(lastDigit == 4 || lastDigit == 3) result += 2;
        else if(lastDigit == 2) result += 1;

        return result;
    }
    /*
        Function divideStringBy2: Manual division by 2 (works only for even numbers)
        Param: String x
        Return: String newString - x divided by 2
     */
    public static String divideStringBy2(String x){
        int asciiZero = 48;
        boolean isEven, carry = false;
        String newString ="";

        for(int i = 0 ; i < x.length() ; i++){
            char c = x.charAt(i);
            int digit = convertCharToInteger(c);

            if(digit % 2 == 0) isEven = true;
            else isEven = false;

            digit = digit / 2;
            if(carry) digit += 5;
            c = (char)(digit + asciiZero);

            if(isEven) carry = false;
            else carry = true;

            if(i == 0 && c == '0') continue;
            newString += c;
        }
        return newString;
    }
    /*
    Function addOne:    Adding 1 to string number (working only with odd numbers)
                        Special case : last digit is 9 - need to update digits before
    Param: String x
    Return: String newString - x+1
 */
    public static String addOne(String x){
        String newString ="";
        int length = x.length();
        char c = x.charAt(length - 1);
        // when last digit is not 9 - add 1 to last digit
        if(c != '9') newString = x.substring(0,length -1 )+(char)(c+1);
        // special case when last digit is 9
        else{
            int i = length - 1;
            while(c == '9' && i > 0){
                i--;
                c = x.charAt(i);
            }
            if(i==0 && c == '9') {
                newString += '1' ;
                for(int j = 0 ; j < (length - i) ; j++){
                    newString += '0';
                }
            }
            else {
                newString = x.substring(0,i)+(char)(c+1);
                for(int j = 0 ; j < (length - i - 1) ; j++){
                    newString += '0';
                }
            }
        }
        return newString;
    }
    /*
    Function subOne:    Subtraction 1 from string number (working only with odd numbers)
    Param: String x
    Return: String newString - (x-1)
 */
    public static String subOne(String x){
        char c = x.charAt(x.length() - 1);
        return (x.substring(0,x.length() -1 )+(char)(c-1));
    }

    public static int convertCharToInteger(char c){
        switch (c){
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            default: return -1;
        }
    }
    public static void main(String[] args) {
        String[] myTests = new String[]{"15","999","169999", "111199", "9", "19", "52999", "99", "9999", "1999",
                "10", "9", "8",
                "0123456789999999999", "8888888888888", "1234569998",
                "4", "136", "1112224", "6", "0", "2", "33336", "4", "6", "8", "92",
                "950","1019", "100000000000000000000008000000000000000000000004"
        };
        for(int i=0 ; i< myTests.length ; i++){
            int result = solution(myTests[i]);
            System.out.println(myTests[i] + ": " + result);
        }

    }
}
