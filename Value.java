import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Value {
    private double data;
    private double grad;
    private String op;
    private Set<Value> prev;
    private Runnable backFn;

    public Value(double data) {
        this.data = data;
        this.grad = 0.0;
        this.op = "";
        this.prev = new HashSet<>();
        this.backFn = () -> {};
    }

    private Value(double data, String op, Set<Value> prev) {
        this.data = data;
        this.grad = 0.0;
        this.op = op;
        this.prev = prev;
        this.backFn = () -> {};
    }

    public double getData() {
        return this.data;
    }

    public Value add(Value other) {
        Set<Value> prev = new HashSet<>();
        prev.add(this);
        prev.add(other);
        Value out = new Value(this.data + other.data, "+", prev);

        out.backFn = () -> {
            this.grad += out.grad;
            other.grad += out.grad;
        };

        return out;
    }

    public Value mul(Value other) {
        Set<Value> prev = new HashSet<>();
        prev.add(this);
        prev.add(other);

        Value out = new Value(this.data * other.data, "*", prev);
        
        out.backFn = () -> {
            this.grad += out.grad * other.data;
            other.grad += out.grad * this.data;
        };

        return out;
    }

    public void backward() {
        List<Value> topo = new ArrayList<>();
        Set<Value> visited = new HashSet<>();

        buildTopological(this, topo, visited);

        this.grad = 1.0;

        for(Value v : topo.reversed()) {
            v.backFn.run();
        }
    }

    public Double getGrad() {
        return this.grad;
    }

    private static void buildTopological(Value v, List<Value> topo, Set<Value> visited) {
        if (visited.contains(v)) {
            return;
        }

        visited.add(v);

        for(Value _prev : v.prev) {
            buildTopological(_prev, topo, visited);
        }

        topo.add(v);
    }

    @Override
    public String toString() {
        return "Value(data=" + data + ", grad=" + grad + ", op='" + op + "', prevCount=" + prev.size() + ")";
    }
}
