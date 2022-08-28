public class TokenOperandSingle extends TokenOperand {
    String item;

    public TokenOperandSingle(String item) {
        this.item = item;
    }

    public TokenOperandSingle(char item) {
        this.item = String.valueOf(item);
    }

    public String getItem() {
        return  this.item;
    }

    @Override
    public TokenOperandSingle clone() {
        return  new TokenOperandSingle(this.item) ;
    }

    @Override
    public String toString() {
        return item;
    }
}

