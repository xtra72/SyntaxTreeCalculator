import org.jetbrains.annotations.NotNull;

/*
 *
 */
public class MyRationalNumber extends MyNumber {
    int numerator;
    int denominator;

    public MyRationalNumber() {
        this.numerator = 0;
        this.denominator = 1;
    }

    public MyRationalNumber(int number) {
        this.numerator = number;
        this.denominator = 1;
    }

    public MyRationalNumber(int numerator, int denominator)  {
        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator cannot be 0.");
        }
        else if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        int gcd = getGCD(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator /gcd;
    }

    public MyRationalNumber(@NotNull MyRationalNumber number) {
        this.numerator = number.getNumerator();
        this.denominator = number.getDenominator();
    }

    public int getNumerator() {
        return  this.numerator;
    }

    public int getDenominator() {
        return  this.denominator;
    }

    @Override
    public MyRationalNumber plus(@NotNull MyNumber number) {
        if (!(number instanceof MyRationalNumber)) {
            throw new IllegalArgumentException("Number is not rational number");
        }

        return  new MyRationalNumber(
                this.numerator *  ((MyRationalNumber)number).getDenominator()
                        + this.denominator * ((MyRationalNumber)number).getNumerator(),
                this.denominator * ((MyRationalNumber)number).getDenominator()
        );
    }


    @Override
    public MyRationalNumber minus(@NotNull MyNumber number) {
        if (!(number instanceof MyRationalNumber)) {
            throw new IllegalArgumentException("Number is not rational number");
        }

        return  new MyRationalNumber(
                this.numerator * ((MyRationalNumber)number).getDenominator()
                        - this.denominator * ((MyRationalNumber)number).getNumerator(),
                this.denominator * ((MyRationalNumber)number).getDenominator()
        );
    }

    @Override
    public MyRationalNumber multipliedBy(@NotNull MyNumber number) {
        if (!(number instanceof MyRationalNumber)) {
            throw new IllegalArgumentException("Number is not rational number");
        }

        return  new MyRationalNumber(
                this.numerator * ((MyRationalNumber)number).getNumerator(),
                this.denominator * ((MyRationalNumber)number).getDenominator()
        );
    }


    @Override
    public MyRationalNumber dividedBy(@NotNull MyNumber number) {
        if (!(number instanceof MyRationalNumber)) {
            throw new IllegalArgumentException("Number is not rational number");
        }

        if (((MyRationalNumber) number).getDenominator() == 0) {
            throw new ArithmeticException("Divide by 0") ;
        }

        return  new MyRationalNumber(
                this.numerator * ((MyRationalNumber) number).getDenominator(),
                this.denominator * ((MyRationalNumber) number).getNumerator()
        );
    }

    private int getGCD(int a, int b) {
        if (b == 0) {
            return  a;
        }

        return  getGCD(b, a % b);
    }

    @Override
    public String toString() {
        if (this.denominator == 1) {
            return  String.valueOf(this.numerator);
        }
        else {
            return  this.numerator + "/" + this.denominator;
        }
    }

    public static MyRationalNumber creator(String value) {
        if (value.contains("/")){
            String [] fields  = value.split("/") ;
            if (fields.length != 2) {
                throw new IllegalArgumentException("Value is not rational number format");
            }
            return  new MyRationalNumber(Integer.valueOf(fields[0]) , Integer.valueOf(fields[1]));
        }
        else {
            return  new MyRationalNumber(Integer.valueOf(value));
        }

    }

    public static String parser(String expression, int startIndex) {
        return  MyRationalNumber.parser(expression, startIndex, expression.length()) ;
    }
    
    public static String parser(String expression, int startIndex, int endIndex) {
        StringBuilder operand = new StringBuilder();
        if (expression.charAt(startIndex) == '-') {
            operand.append(expression.charAt(startIndex++));
        }

        while(startIndex < endIndex) {
            if (!Character.isDigit(expression.charAt(startIndex))) {
                break;
            }

            operand.append(expression.charAt(startIndex++));
        }

        return  operand.toString();
    }
}
