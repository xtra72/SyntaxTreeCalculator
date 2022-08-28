public class TokenOperator extends Token {
    String item;

    public TokenOperator(String item) {
        this.item = item;
    }

    public TokenOperator(char item) {
        this.item = String.valueOf(item);
    }

    public String getItem() {
        return  this.item;
    }

    @Override
    public TokenOperator  clone() {
        return  new TokenOperator(this.item) ;
    }

    @Override
    public String toString() {
        return  item;
    }
}

