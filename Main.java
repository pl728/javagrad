import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Start here. Then try {100, 100, 100, 1} after confirming it works.
        MLP mlp = new MLP(2, new int[] {4, 4, 1});

        double[][] X = {
            {-1.0, -1.0},
            {-1.0,  1.0},
            { 1.0, -1.0},
            { 1.0,  1.0},
            {-0.5, -0.75},
            {-0.5,  0.75},
            { 0.5, -0.75},
            { 0.5,  0.75},
            {-1.5,  0.5},
            { 1.5,  0.5}
        };

        // Target: +1 if signs are same, -1 if signs differ.
        // This is XOR-ish / nonlinear.
        double[] Y = {
             1.0,
            -1.0,
            -1.0,
             1.0,
             1.0,
            -1.0,
            -1.0,
             1.0,
            -1.0,
             1.0
        };

        double learningRate = 0.05;

        for (int iter = 0; iter < 100; iter++) {
            // 1. zero parameter gradients
            for (Value p : mlp.parameters()) {
                p.zeroGrad();
            }

            // 2. total loss over all examples
            Value totalLoss = new Value(0.0);

            for (int i = 0; i < X.length; i++) {
                List<Value> xs = List.of(
                    new Value(X[i][0]),
                    new Value(X[i][1])
                );

                Value prediction = mlp.call(xs).get(0);
                Value target = new Value(Y[i]);

                Value loss = prediction.sub(target).pow(2.0);
                totalLoss = totalLoss.add(loss);
            }

            // 3. backward
            totalLoss.backward();

            // 4. update params
            for (Value p : mlp.parameters()) {
                p.step(learningRate);
            }

            if (iter % 10 == 0 || iter == 99) {
                System.out.println("iter=" + iter + " loss=" + totalLoss.getData());
            }
        }

        System.out.println("\nFinal predictions:");
        for (int i = 0; i < X.length; i++) {
            List<Value> xs = List.of(
                new Value(X[i][0]),
                new Value(X[i][1])
            );

            Value prediction = mlp.call(xs).get(0);

            System.out.println(
                "x=(" + X[i][0] + ", " + X[i][1] + ")" +
                " target=" + Y[i] +
                " pred=" + prediction.getData()
            );
        }
    }
}