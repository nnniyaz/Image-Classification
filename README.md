# Image Classifier

P.S.: Image Classification is a machine learning project idea from Clark University.

Machine learning image classification project has two parts: **Training** and **Testing**.

## Training

In the training phase, the algorithm learns a function that maps an input to an output (or a label) using training data consisting of known input-output pairs. For the handwritten digit application, the training data comprise 60,000 grayscale images (inputs) and associated digits (labels).

## Testing

In the testing phase, the algorithm uses the learned function to predict labels for unseen inputs. The algorithm makes some prediction errors (e.g., predicts 9 when the handwritten digit is 6). An important quality metric is the **test error rate** – the fraction of testing inputs that the algorithm misclassifies. It measures how well the learning algorithm generalizes from the training data to new data.

### Here are some sample executions:

```
$ java-introcs ImageClassifier digits-training20.txt digits-testing10.txt 
digits/testing/1/46.png
digits/testing/7/36.png
digits/testing/7/80.png
digits/testing/1/40.png
digits/testing/1/39.png
digits/testing/7/79.png
digits/testing/9/20.png
digits/testing/9/58.png
test error rate = 0.8

$ java-introcs ImageClassifier digits-training60K.txt digits-testing10K.txt 
jar:file:digits.jar!/testing/5/9428.png
jar:file:digits.jar!/testing/6/4814.png
jar:file:digits.jar!/testing/5/4915.png
...
jar:file:digits.jar!/testing/5/7870.png
jar:file:digits.jar!/testing/4/1751.png
jar:file:digits.jar!/testing/5/6043.png
test error rate = 0.136

$ java-introcs ImageClassifier fashion-training60K.txt fashion-testing10K.txt 

$ java-introcs ImageClassifier Kuzushiji-training60K.txt Kuzushiji-testing10K.txt

$ java-introcs ImageClassifier fruit-training30K.txt fruit-testing6K.txt

$ java-introcs ImageClassifier animals-training60K.txt animals-testing12K.txt

$ java-introcs ImageClassifier music-training50K.txt music-testing10K.txt
```
