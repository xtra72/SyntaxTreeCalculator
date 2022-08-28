import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Tokenizer {

    BiFunction<String, Integer, String> getOperand = (expression, index)-> {
        StringBuilder operand = new StringBuilder();
        if (expression.charAt(index) == '-') {
            operand.append(expression.charAt(index++));
        }

        while(index < expression.length()) {
            if (!Character.isDigit(expression.charAt(index))) {
                break;
            }

            operand.append(expression.charAt(index++));
        }

        return  operand.toString();
    };

    public void setOperandParser(BiFunction<String, Integer, String> parser) {
        this.getOperand = parser;
    }

    public List<Token> evaluation(String expression) {
        return  this.evaluation(expression, 0, expression.length());
    }

    public List<Token> evaluation(String expression, int startIndex, int endIndex) {
        List<Token> tokens = new ArrayList<>();
        int index = startIndex;

        while (index < endIndex) {
            if (expression.charAt(index) == '-') {
                if (tokens.isEmpty()) {
                    String item = this.getOperand.apply(expression, index);
                    tokens.add(new TokenOperandSingle(item));
                    index += item.length();
                } else {
                    Token previousToken = tokens.get(tokens.size() - 1);
                    if (previousToken instanceof TokenOperator && !((TokenOperator)previousToken).getItem().equals(")")){
                        String item = this.getOperand.apply(expression, index);
                        tokens.add(new TokenOperandSingle(item));
                        index += item.length();
                    } else {
                        tokens.add(new TokenOperator(expression.charAt(index++)));
                    }
                }
            } else if ("=+-*/()".indexOf(expression.charAt(index)) >= 0) {
                tokens.add(new TokenOperator(expression.charAt(index++)));
            }
            else if (Character.isSpaceChar(expression.charAt(index))) {
                while(Character.isSpaceChar(expression.charAt(index))) {
                    index++;
                }
            }
            else if (Character.isAlphabetic(expression.charAt(index))) {
                String item = this.getOperand.apply(expression, index);
                if (item.length() != 0) {
                    tokens.add(new TokenOperandVariable(item));
                    index += item.length();
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            }
            else {
                String item = this.getOperand.apply(expression, index);
                if (item.length() != 0) {
                    tokens.add(new TokenOperandSingle(item));
                    index += item.length();
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            }
        }

        return  tokens;
    }

}
