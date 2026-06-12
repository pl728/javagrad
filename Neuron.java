import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Neuron {
    private List<Value> weights;
    private Value bias;

    public Neuron(int nin) {
        Random random = new Random();

        this.weights = new ArrayList<>();
        for (int i = 0; i < nin; i++) {
            double w = random.nextDouble() * 2 - 1; // random in [-1, 1]
            this.weights.add(new Value(w));
        }

        this.bias = new Value(0.0);
    }

    public Value call(List<Value> inputs) {
        if (inputs.size() != this.weights.size()) {
            throw new IllegalArgumentException("inputs size must match weights size");
        }
        Value out = this.bias;
        for (int i = 0; i < inputs.size(); i++) {
            Value x = inputs.get(i); Value w = this.weights.get(i);
            out = out.add(x.mul(w));
        }
        return out.tanh();
    }

    public List<Value> parameters() {
        List<Value> params = new ArrayList<>();
        params.addAll(this.weights);
        params.add(this.bias);
        
        return params;
    }
}