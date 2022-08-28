public class SyntaxTreeTest {
    public static void main(String[] args) {

        String expression = "1+2/(3*-2)";
        SyntaxTree<MyRationalNumber> syntaxTree = new SyntaxTree<>(expression, MyRationalNumber::parser, MyRationalNumber::creator);

        syntaxTree.evaluation();
        System.out.println("Input expression : " + expression);
        System.out.println("Syntax tree : " + syntaxTree);
        System.out.println("Tokens : " + syntaxTree.getTokens());
        System.out.println("Result : " + syntaxTree.getResult());

        String expression2 = "[1/2]+2/(3*-2)";
        SyntaxTree<MyRationalNumber> syntaxTree2 = new SyntaxTree<>(expression2,
        (x, index)-> {
            String operand = "";
            int start = x.indexOf('[', index);
            if (start == index) {
                int end  = x.indexOf(']', start);
                if (0 <= end) {
                    String substring = x.substring(start, end+1);
                    if(substring.matches("\\[\\b*-?\\d+\\b*[,/]\\b*-?\\d+\\b*]")) {
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
        },
        x-> {
            if (x.matches("\\[\\b*-?\\d+\\b*[,/]\\b*-?\\d+\\b*]")) {
                String [] fields  = x.replace('[', ' ')
                                        .replace(']',' ')
                                        .strip()
                                        .split("[/,]") ;
                if (fields.length != 2) {
                    throw new IllegalArgumentException("Value is not rational number format");
                }
                return  new MyRationalNumber(Integer.valueOf(fields[0]) , Integer.valueOf(fields[1]));
            }
            else {
                return  new MyRationalNumber(Integer.valueOf(x));
            }
        });


        syntaxTree2.evaluation();
        System.out.println("Input expression : " + expression2);
        System.out.println("Syntax tree : " + syntaxTree2);
        System.out.println("Tokens : " + syntaxTree2.getTokens());
        System.out.println("Result : " + syntaxTree2.getResult());
    }
}
