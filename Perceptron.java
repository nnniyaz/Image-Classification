public class Perceptron {
    private int n; // number of inputs
    private double[] m; // storage of weights

    // Creates a perceptron with n inputs.
    public Perceptron(int n) {
        this.n = n;
        this.m = new double[this.n];
    }

    // Returns the number of inputs n.
    public int numberOfInputs() {
        return this.n;
    }

    // Returns the weighted sum of the weight vector and x.
    public double weightedSum(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < m.length; i++) {
            sum += this.m[i] * x[i];
        }
        return sum;
    }

    // Predict the label (+1 or -1) of input x.
    public int predict(double[] x) {
        if (weightedSum(x) > 0) return 1;
        else return -1;
    }

    // Trains this perceptron on the labeled (+1 or -1) input x.
    public void train(double[] x, int label) {
        int predicted = predict(x);
        if (predicted != label) {
            if (predicted == 1) {
                for (int i = 0; i < this.m.length; i++) {
                    this.m[i] -= x[i];
                }
            }
            else if (predicted == -1) {
                for (int i = 0; i < this.m.length; i++) {
                    this.m[i] += x[i];
                }
            }
        }
    }

    // Returns a string representation of this perceptron.
    public String toString() {
        String res = "(";
        for (int i = 0; i < this.m.length; i++) {
            res += this.m[i] + ", ";
        }
        return res.substring(0, res.length() - 2) + ")";
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        double[] training1 = { 5.0, -4.0, 3.0 };  // yes
        double[] training2 = { 2.0, 3.0, -2.0 };  // no
        double[] training3 = { 4.0, 3.0, 2.0 };  // yes
        double[] training4 = { -6.0, -5.0, 7.0 };  // no

        int n = 3;
        Perceptron perceptron = new Perceptron(n);
        StdOut.println(perceptron);
        perceptron.train(training1, +1);
        StdOut.println(perceptron);
        perceptron.train(training2, -1);
        StdOut.println(perceptron);
        perceptron.train(training3, +1);
        StdOut.println(perceptron);
        perceptron.train(training4, -1);
        StdOut.println(perceptron);
    }
}
