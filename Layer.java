import java.util.List;
import java.util.ArrayList;

public class Layer {
    private List<Neuron> neurons;

    public Layer(int nin, int nout) {
        this.neurons = new ArrayList<>();

        for (int i = 0; i < nout; i++) {
            this.neurons.add(new Neuron(nin));
        }
    }

    public List<Value> call(List<Value> inputs) {
        List<Value> outputs = new ArrayList<>();

        for (Neuron neuron : this.neurons) {
            outputs.add(neuron.call(inputs));
        }

        return outputs;
    }

    public List<Value> parameters() {
        List<Value> params = new ArrayList<>();

        for (Neuron neuron : this.neurons) {
            params.addAll(neuron.parameters());
        }

        return params;
    }
}