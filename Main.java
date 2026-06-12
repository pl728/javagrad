public class Main {
    public static void main(String[] args) {
        Value x1 = new Value(2.0);
        Value x2 = new Value(0.0);

        Value w1 = new Value(-3.0);
        Value w2 = new Value(1.0);

        Value b = new Value(6.8813735870195432);
        Value n = x1.mul(w1).add(x2.mul(w2)).add(b);
        Value o = n.tanh();

        o.backward();

        System.out.println("x1 = " + x1);
        System.out.println("x2 = " + x2);
        System.out.println("w1 = " + w1);
        System.out.println("w2 = " + w2);
        System.out.println("b = " + b);
        System.out.println("n = " + n);
        System.out.println("o = " + o);
    }
}