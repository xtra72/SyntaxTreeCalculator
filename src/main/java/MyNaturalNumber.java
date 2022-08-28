import org.jetbrains.annotations.NotNull;

public class MyNaturalNumber extends MyInteger {
    public MyNaturalNumber() {
        super();
    }

    public MyNaturalNumber(int number) {
        super(number);
        if (number <= 0) {
            throw new IllegalArgumentException("The initial value must be a natural number.");
        }
    }

    public MyNaturalNumber plus(@NotNull MyNaturalNumber number) {
        return  new MyNaturalNumber(this.getValue() + number.getValue()) ;
    }

    public MyNaturalNumber minus(@NotNull MyNaturalNumber number) {
        return  new MyNaturalNumber(this.getValue() - number.getValue()) ;
    }

    public MyNaturalNumber multipliedBy(@NotNull MyNaturalNumber number) {
        return  new MyNaturalNumber(this.getValue() * number.getValue()) ;
    }

    public MyNaturalNumber dividedBy(@NotNull MyNaturalNumber number) {
        return  new MyNaturalNumber(this.getValue() / number.getValue()) ;
    }
}
