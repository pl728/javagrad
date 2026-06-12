import java.util.List;
import java.util.ArrayList;

public class MLP {
    private List<Layer> layers;

    public MLP(int nin, int[] nouts) {
        this.layers = new ArrayList<>();

        int currentInputSize = nin;

        for (int nout : nouts) {
            Layer layer = new Layer(currentInputSize, nout);
            this.layers.add(layer);

            currentInputSize = nout;
        }
    }

    public List<Value> call(List<Value> inputs) {
        List<Value> activations = inputs;

        for (Layer layer : this.layers) {
            activations = layer.call(activations);
        }

        return activations;
    }

    public List<Value> parameters() {
        List<Value> params = new ArrayList<>();

        for (Layer layer : this.layers) {
            params.addAll(layer.parameters());
        }

        return params;
    }
}