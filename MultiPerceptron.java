public class MultiPerceptron {
    private int n; // number of inputs
    private int m; // number of perceptrons
    private Perceptron[] storage; // storage of perceptrons

    // Creates a multi-perceptron object with m classes and n inputs.
    public MultiPerceptron(int m, int n) {
        this.n = n;
        this.m = m;
        this.storage = new Perceptron[this.m];
        for (int i = 0; i < storage.length; i++) {
            this.storage[i] = new Perceptron(this.n);
        }
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return this.m;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        return this.n;
    }

    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        double max = this.storage[0].weightedSum(x);
        int index = 0;
        for (int i = 0; i < this.storage.length; i++) {
            if (max < this.storage[i].weightedSum(x)) {
                max = this.storage[i].weightedSum(x);
                index = i;
            }
        }
        return index;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < this.storage.length; i++) {
            if (i == label) this.storage[i].train(x, 1);
            else this.storage[i].train(x, -1);
        }
    }

    // Returns a string representation of this MultiPerceptron.
    public String toString() {
        String res = "(";
        for (int i = 0; i < this.storage.length; i++) {
            res += this.storage[i] + ", ";
        }
        return res.substring(0, res.length() - 2) + ")";
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int m = 2;
        int n = 3;

        double[] training1 = { 5.0, -4.0, 3.0 };
        double[] training2 = { 2.0, 3.0, -2.0 };
        double[] training3 = { 4.0, 3.0, 2.0 };
        double[] training4 = { -6.0, -5.0, 7.0 };

        MultiPerceptron perceptron = new MultiPerceptron(m, n);
        StdOut.println(perceptron);
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);
    }
}
