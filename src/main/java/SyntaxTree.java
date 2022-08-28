import javax.naming.OperationNotSupportedException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SyntaxTree<T extends MyNumber> {
    String expression;
    Tokenizer tokenizer;
    List<Token> tokens;
    Node<T> root;
    Function<String, T> creator;

    public SyntaxTree(String expression, BiFunction<String, Integer, String> operandParser, Function<String, T> creator) {
        this.expression = expression;
        this.tokenizer = new Tokenizer();
        this.tokenizer.setOperandParser(operandParser);
        this.creator = creator;

        this.tokens = this.tokenizer.evaluation(this.expression);
        this.root = makeTree(this.tokens.iterator());
    }

    public SyntaxTree(String expression, Tokenizer tokenizer, Function<String, T> creator) {
        this.expression = expression;
        this.tokenizer = tokenizer;
        this.creator = creator;

        this.tokens = this.tokenizer.evaluation(this.expression);
        this.root = makeTree(this.tokens.iterator());
    }

    public List<Token> getTokens() {
        return  this.tokens;
    }

    public T getResult() {
        return  this.root.getValue();
    }

    public boolean evaluation() {
        try {
            this.tokens = this.tokenizer.evaluation(this.expression);
            this.root = makeTree(this.tokens.iterator());

            return  true;
        }
        catch(Exception ignore) {
            return  false;
        }
    }

    public Node<T> makeTree(Iterator<Token> iterator) {
        Stack<Node<T>> operandStack = new Stack<>();
        Stack<Token> operatorStack = new Stack<>();

        while(iterator.hasNext()) {
            Token token = iterator.next();

            if (token instanceof TokenOperand) {
                operandStack.push(new NodeLeaf<T>(this.creator.apply(token.toString())));
            }
            else if (token instanceof TokenOperator) {
                if (operatorStack.isEmpty()) {
                    operatorStack.push(token);
                }
                else if (((TokenOperator) token).getItem().equals("(")) {
                    operandStack.push(this.makeTree(iterator));
                }
                else if (((TokenOperator) token).getItem().equals(")")) {
                    break;
                }
                else if (((TokenOperator) token).getItem().equals("*")
                        ||((TokenOperator) token).getItem().equals("/")){
                    if (((TokenOperator)operatorStack.peek()).getItem().equals("*")
                            || ((TokenOperator)operatorStack.peek()).getItem().equals("/")) {
                        Node<T> rightNode = operandStack.pop();
                        Node<T> leftNode = operandStack.pop();
                        if (((TokenOperator) token).getItem().equals("*")) {
                            operandStack.push(new NodeMultiplication<>(leftNode, rightNode));
                        }
                        else {
                            operandStack.push(new NodeDivision<>(leftNode, rightNode));
                        }
                        operatorStack.pop();
                        operatorStack.push(token);
                    }
                    else {
                        operatorStack.push(token);
                    }
                }
                else if (((TokenOperator) token).getItem().equals("+")
                        ||((TokenOperator) token).getItem().equals("-")){
                    Node<T> rightNode = operandStack.pop();
                    Node<T> leftNode = operandStack.pop();
                    if (((TokenOperator) token).getItem().equals("+")) {
                        operandStack.push(new NodeAddition<>(leftNode, rightNode));
                    }
                    else {
                        operandStack.push(new NodeSubtraction<>(leftNode, rightNode));
                    }
                    operatorStack.pop();
                    operatorStack.push(token);
                }
            }
        }

        while(!operatorStack.isEmpty()) {
            Node<T> rightNode = operandStack.pop();
            Node<T> leftNode = operandStack.pop();
            if (((TokenOperator)operatorStack.peek()).getItem().equals("+"))  {
                operandStack.push(new NodeAddition<>(leftNode, rightNode));
            }
            else if (((TokenOperator)operatorStack.peek()).getItem().equals("-"))  {
                operandStack.push(new NodeSubtraction<>(leftNode, rightNode));
            }
            else if (((TokenOperator)operatorStack.peek()).getItem().equals("*"))  {
                operandStack.push(new NodeMultiplication<>(leftNode, rightNode));
            }
            else if (((TokenOperator)operatorStack.peek()).getItem().equals("/"))  {
                operandStack.push(new NodeDivision<>(leftNode, rightNode));
            }
            operatorStack.pop();
        }
        return  operandStack.pop();
    }

    @Override
    public String toString() {
        if (this.root == null) {
            return  "";
        }

        return  this.root.toString();
    }

}
