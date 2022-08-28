import java.util.Arrays;

public class TokenizerTest {
    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        String expression = "X = [1/2] + 2 * (3 - -1 / 4)";
        tokenizer.setOperandParser((x, index)-> {
            String operand = "";
            int start = x.indexOf('[', index);
            if (start == index) {
                int end  = x.indexOf(']', start);
                if (0 <= end) {
                    String substring = x.substring(start, end+1);
                    if(substring.matches("\\[\\b*\\-?\\d+\\b*[,/]\\b*\\-?\\d+\\b*\\]")) {
                        operand = x.substring(start, end+1);
                    }
                }
            }
            else {
                StringBuilder builder = new StringBuilder();
                if (Character.isAlphabetic(x.charAt(index))){
                    while(index < x.length() && Character.isAlphabetic(x.charAt(index))) {
                        builder.append(x.charAt(index++));
                    }

                    if (builder.toString().matches("\\D+")) {
                        operand = builder.toString();
                    }
                }
                else {
                    if (x.charAt(index) == '-') {
                        builder.append(x.charAt(index++));
                    }

                    while(Character.isDigit(x.charAt(index))) {
                        builder.append(x.charAt(index++));
                    }

                    if (builder.toString().matches("-?\\d+")) {
                        operand = builder.toString();
                    }
                }
            }

            return operand;
        });
        System.out.println(tokenizer.evaluation(expression));

    }
}
