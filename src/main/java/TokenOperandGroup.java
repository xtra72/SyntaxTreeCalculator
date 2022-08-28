import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TokenOperandGroup extends TokenOperand {
    public List<Token>  tokenList;

    public TokenOperandGroup(List<Token> tokenList) {
        this.tokenList = new ArrayList<>();
        Iterator<Token> iterator = tokenList.iterator();
        while(iterator.hasNext()) {
            Token token = iterator.next();
            this.tokenList.add(token.clone());
        }
    }

    @Override
    public TokenOperandGroup clone() {
        return  new TokenOperandGroup(tokenList);
    }

    @Override
    public String toString() {
        return  tokenList.toString();
    }
}
