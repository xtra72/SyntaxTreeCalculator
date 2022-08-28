public class TokenOperandVariable extends TokenOperandSingle {
    public TokenOperandVariable(String item) {
        super(item);
    }

    public TokenOperandVariable(char item) {
        super(item);
    }

    @Override
    public TokenOperandVariable clone() {
        return  new TokenOperandVariable(this.item) ;
    }
}

