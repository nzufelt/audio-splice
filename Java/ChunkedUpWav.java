/**
* Splits a .wav file apart into "numNewWaves" new, smaller
* wav files and saves these arrays in a two dimensional array of arrays called "chunks".
* Stores the following information about each of these new arrays:
* - maximum value
* - minimum value
* - average value
*/
public class ChunkedUpWav {
  private double maximum = 0;
  private double minimum = 0;
  private double average = 0;
  private double[][] chunks;
  private Processor wholeWave;

  public ChunkedUpWav(String fileName, int numNewWaves) {
    wholeWave = new Processor(fileName);
    this.getInformation(numNewWaves);
    this.makeMiniWaves(numNewWaves);
  }
  /**
  * Calculates the average value, minimum value, and maximum of a segment of the double array.
  * The segments generated by this method are sliced apart
  * at even intervals. The "factor" parameter determines how often to split the file.
  */
  public double[] getInformation(int factor) {
    int arrSizes = wholeWave.getOriginalSamples().length/factor; // the size of the arrays made
    double[] smallWav = chunks[arrSizes];
    double[] oldWave = wholeWave.getOriginalSamples();
    for(int i = 0; i < arrSizes; i++) {
      smallWav[i] = oldWave[i];
    }
    double sum = 0;
    for(int f = 0; f < arrSizes; f++) {
      sum += smallWav[f];
      if(smallWav[f] > maximum) {
        maximum = smallWav[f];
      }
      if(smallWav[f] < minimum) {
        minimum = smallWav[f];
      }
    }
    average = sum/smallWav.length;
    System.out.println("avg val: " + average);
    System.out.println("max val: " + maximum);
    System.out.println("min val: " + minimum);
    return smallWav;
  }
  public void makeMiniWaves(int factor) { // where factor is the number of new arrays made
    double[] oldWave = wholeWave.getOriginalSamples();
    int arrSizes = oldWave.length/factor; // the size of the new arrays
    chunks = new double[factor][arrSizes];

    int moveThrough = 0;
    for(int i = 0; i < factor; i++) {
      for(int j = 0; j < arrSizes; j++) {
        chunks[i][j] = oldWave[moveThrough];
      }
      moveThrough += arrSizes;
    }
  }
}
