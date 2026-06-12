public class Main {
    public static void main(String[] args) {
        Value a = new Value(2.0);
        Value b = new Value(-3.0);
        Value c = new Value(10.0);

        Value e = a.mul(b); // e = -6
        Value d = e.add(c); // d = 4

        d.backward();

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("e = " + e);
        System.out.println("d = " + d);
    }
}