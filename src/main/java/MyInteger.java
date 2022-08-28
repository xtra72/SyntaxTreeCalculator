import org.jetbrains.annotations.NotNull;

public class MyInteger extends MyRationalNumber {
    public MyInteger() {
        super();
    }

    public MyInteger(int number) {
        super(number);
    }

    int getValue() {
        return  this.getNumerator();
    }

    public MyInteger plus(@NotNull MyInteger number) {
        return  new MyInteger(this.getValue() + number.getValue());
    }

    @Override
    public MyInteger plus(@NotNull MyNumber number) {
        if (!(number instanceof MyInteger)) {
            throw new IllegalArgumentException("Number is not integer");
        }

        return  new MyInteger(this.getValue() + ((MyInteger)number).getValue());
    }

    @Override
    public MyInteger minus(@NotNull MyNumber number) {
        if (!(number instanceof MyInteger)) {
            throw new IllegalArgumentException("Number is not integer");
        }

        return  new MyInteger(this.getValue() - ((MyInteger)number).getValue());
    }

    public MyInteger multipliedBy(@NotNull MyNumber number) {
        if (!(number instanceof MyInteger)) {
            throw new IllegalArgumentException("Number is not integer");
        }

        return  new MyInteger(this.getValue() * ((MyInteger)number).getValue());

    }

    public MyInteger dividedBy(@NotNull MyNumber number) {
        if (!(number instanceof MyInteger)) {
            throw new IllegalArgumentException("Number is not integer");
        }

        if (((MyInteger)number).getValue() == 0) {
            throw new ArithmeticException("Divide by 0") ;
        }

        return  new MyInteger(this.getValue() / ((MyInteger)number).getValue());
    }
}
