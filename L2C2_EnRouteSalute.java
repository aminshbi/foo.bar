public class L2C2_EnRouteSalute {
    public static int solution(String s) {
        // check invalid input
        if(s == null || s.length() < 1 || s.length() > 100) return -1;

        int result = 0 , right = 0 ;

        // count employees walking to right, when we meet a left walking one - add to result
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '>'){
                right ++;
            }
            else if(s.charAt(i) == '<'){
                result += right * 2;
            }
            else if(s.charAt(i) != '-') return -1;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String[] myTests = new String[]{"<<>><",
                "--->->-><-->--",
                "--->-><-><-->-",
                null,
                "",
                "---<>a",
                ">>>>>>>>>>>>>>>>",
                ">",
                "<>",
                "><"
        };
        for(int i=0 ; i< myTests.length ; i++){
            int result = solution(myTests[i]);
            System.out.println(myTests[i] + ": " + result);
        }

    }
}
