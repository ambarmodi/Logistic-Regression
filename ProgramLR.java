/**
 * @author ambar
 *
 */
public class ProgramLR {

	/**
	 *  This function starts the NB algorithm.
	 * @param args has list on arguments.
	 */
	public static void main(String[] args) {
/*		String trainingHamDir = "D:\\Courses\\ML\\HW3\\train\\ham";
		String trainingSpamDir = "D:\\Courses\\ML\\HW3\\train\\spam";
		String testingHamDir = "D:\\Courses\\ML\\HW3\\test\\ham";
		String testingSpamDir ="D:\\Courses\\ML\\HW3\\test\\spam";
		String stopWord = "D:\\Courses\\ML\\HW3\\stopWords.txt";
*/		
		double accuracy,accuracySW;
		if(args.length != 6) {
			System.out.println("Invalid Arguments: Please refer the README.txt for instructions");
			return;
		}

		String trainingHamDir = args[0];
		String trainingSpamDir = args[1];
		String testingHamDir = args[2];
		String testingSpamDir = args[3];
		String stopWord = args[4];
		LogisticRegression.eta = Double.parseDouble(args[5]);
	/*	LogisticRegression.lambda = Double.parseDouble(args[6]);
		LogisticRegression.iterations = Integer.parseInt(args[7]);	
		LogisticRegression.eta=0.002; */
		LogisticRegression.lambda=0.05;
		LogisticRegression.iterations = 15;
		
		try {
			System.out.println("===Training LR model started without using stopWords===");
			LogisticRegression logisticReg = new LogisticRegression(trainingHamDir, trainingSpamDir);
			logisticReg.trainLR();
			System.out.println("Training successful!");
			
			accuracy = logisticReg.calculateAccuracy(testingHamDir,testingSpamDir);
			System.out.println("Overall accuracy of LR without using stopwords = "
							+ accuracy + "%\n\n");

			System.out.println("===Training LR model started using stopWords===");
			LogisticRegression stopWordLogisticReg = new LogisticRegression(trainingHamDir, trainingSpamDir, stopWord);
			stopWordLogisticReg.trainLR();
			System.out.println("Trained successful!");
			
			//As the previous model has acquired too much heap space to avoid HeapSpace Error
			System.gc();
		
			accuracySW = stopWordLogisticReg.calculateAccuracy(testingHamDir,testingSpamDir);
			System.out.println("Overall Accuracy of LR using stopwords = "
					+ accuracySW + "%");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
