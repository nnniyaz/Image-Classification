import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ImageClassifier {

    // // Creates a feature vector (1D array) from the given picture.
    public static double[] extractFeatures(Picture picture) {
        int height = picture.height();
        int width = picture.width();
        double[] res = new double[width * height];

        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = picture.get(j, i);
                res[index] = color.getRed();
                index++;
            }
        }
        return res;
    }

    // See below.
    public static void main(String[] args) {
        // reading files
        In training = new In(args[0]);
        In testing = new In(args[1]);

        int m = training.readInt();
        int width = training.readInt();
        int height = training.readInt();

        // ---------------------------------------------------------------------
        // Initialized in order to skip them during the reading testing file.
        int mTesting = testing.readInt();
        int widthTesting = testing.readInt();
        int heightTesting = testing.readInt();
        // ---------------------------------------------------------------------

        MultiPerceptron robbot = new MultiPerceptron(m, width * height);

        // training
        for (int i = 0; !training.isEmpty(); i++) {
            Picture picture = new Picture(training.readString());
            double[] extractedTraining = extractFeatures(picture);
            robbot.trainMulti(extractedTraining, training.readInt());
        }

        // testing
        int errorsSum = 0;
        int instancesNumber = 0;
        List<Integer> labels = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            labels.add(0);
        }
        List<List<Integer>> labelsPredicted = new ArrayList<List<Integer>>();
        for (int i = 0; i < 10; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < 10; j++) {
                row.add(0);
            }
            labelsPredicted.add(row);
        }

        for (int i = 0; !testing.isEmpty(); i++) {
            String imageName = testing.readString();
            int label = testing.readInt();
            Picture picture = new Picture(imageName);
            double[] extractedTesting = extractFeatures(picture);
            int predicted = robbot.predictMulti(extractedTesting);
            if (predicted != label) {
                errorsSum++;

                int valueLabels = labels.get(label);
                valueLabels += 1;
                labels.set(label, valueLabels);

                int valueLabelsPredicted = labelsPredicted.get(label).get(predicted);
                valueLabelsPredicted += 1;
                labelsPredicted.get(label).set(predicted, valueLabelsPredicted);

                System.out.println(
                        imageName + " label: " + label + " predicted: " + predicted
                );
            }

            instancesNumber++;
        }

        /*
        Visualisation of error rate and which label is predicted misclassified
        mostly, and for this misclassified digit what are the top two digits
        predicted incorrectly.
        */
        System.out.println("test error rate = " + (double) errorsSum / instancesNumber);
        System.out.println();
        for (int i = 0; i < labels.size(); i++) {
            System.out.println(i + ": " + labels.get(i));
        }
        System.out.println();

        for (int i = 0; i < labelsPredicted.size(); i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < labelsPredicted.size(); j++) {
                System.out.printf("[%d: %d] ", j, labelsPredicted.get(i).get(j));
            }
            System.out.print("\n");
        }
    }
}
