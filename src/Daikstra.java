import java.util.*;


public class Daikstra {

    private Stack<String> stackOfOperator = new Stack<String>();
    private Stack<String> output = new Stack<String>();
    private Stack<Float> calcOfoutp = new Stack<Float>();

    public static byte getMainStat(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 2;
    }

    public static boolean isOpenS(String token) {
        return token.equals("(");
    }

    public static boolean isCloseS(String token) {
        return token.equals(")");
    }

    public static boolean isNum(String token) {
        try {
            Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isOperator(String str) {

        switch (str) {
            case "+":
            case "-":
            case "/":
            case "*":
                return true;
            default:
                return false;
        }
    }






    public static void main(String[] args) {
        Float output_a = null, output_b = null, output = null;

        Daikstra deikstra = new Daikstra();
        deikstra.stackOfOperator.clear();
        deikstra.output.clear();

        Scanner input = new Scanner(System.in);
        System.out.print("Input a expression: \n");
        String exp = input.nextLine();
        exp = exp.replaceAll("", " ");
        StringTokenizer tockinizer = new StringTokenizer(exp, " ");
        while (tockinizer.hasMoreTokens()) {
            String token = tockinizer.nextToken();

            if (isOpenS(token)) {
                deikstra.stackOfOperator.push(token);
            }
            if (isCloseS(token)) {
                while (!deikstra.stackOfOperator.empty()
                        && !isOpenS(deikstra.stackOfOperator.lastElement())) {
                    deikstra.output.push(deikstra.stackOfOperator.pop());
                }
                deikstra.stackOfOperator.pop();
            }

            if (isNum(token)) {
                deikstra.output.push(token);
            }

            if (isOperator(token)) {
                while (!deikstra.stackOfOperator.empty()
                        && isOperator(deikstra.stackOfOperator.lastElement())
                        && getMainStat(token) <= getMainStat(deikstra.stackOfOperator
                        .lastElement())) {
                    deikstra.output.push(deikstra.stackOfOperator.pop());
                }
                deikstra.stackOfOperator.push(token);
            }
        }
        while (!deikstra.stackOfOperator.empty()) {
            deikstra.output.push(deikstra.stackOfOperator.pop());
        }
        System.out.println(deikstra.output);
        for(String x:deikstra.output){
            if (isNum(x)){
                deikstra.calcOfoutp.push(Float.valueOf(x));
            }
            else if (isOperator(x)){
                output = null;
                output_b = null;
                output_a = null;
                output_b = Float.valueOf(deikstra.calcOfoutp.peek());
                deikstra.calcOfoutp.pop();
                output_a  = Float.valueOf(deikstra.calcOfoutp.peek());
                deikstra.calcOfoutp.pop();
                if (x.equals("+")){output = output_a+output_b; deikstra.calcOfoutp.push(output);}
                else if (x.equals("-")) {output = output_a-output_b; deikstra.calcOfoutp.push(output);}
                else if(x.equals("/")) {output = output_a/output_b; deikstra.calcOfoutp.push(output);}
                else if (x.equals("*")) {output = output_a*output_b; deikstra.calcOfoutp.push(output);}
                }

            }

        System.out.println("Done : "+deikstra.calcOfoutp);



    }
}


