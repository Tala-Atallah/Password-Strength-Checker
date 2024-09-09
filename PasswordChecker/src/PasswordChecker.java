import java.util.Iterator;
import java.util.Scanner;

public class PasswordChecker {

	public static void main(String[] args) {
		String password;
		int space;
		int length;
		double entropy;
		double variance;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter your password to check its strength:");
		System.out.println(
				"Hint: A strong password should be uncommon and has a mix between digits, upper case, lower case, and special characters");
		
		password = s.nextLine();
		length = password.length();
		space = calculateSpace(password);
		entropy = calculateEntropy(space, length); // space and password length is passed to the function
		variance = calculateVariance(password, length); // the password and its length is passed to the function
		System.out.println("Entropy:" + entropy);
		System.out.println("Variance:" + variance);

		if (entropy >= 75 && variance > 700 && length >= 8 && space == 94)  // space is calculated using the function calculateSpace
			System.out.println("Your password is Strong");
		else if (entropy >= 50 && variance >= 400 && space >= 52)
			System.out.println("Your password is medium");
		else {
			System.out.println("Your password is weak");  // if your password is weak a suggestion is provided to improve your password's strength
			if (length < 8)
				System.out.println("Make your password longer");
			if (space == 26)
				System.out.println("You should use a mix of upper and lower case alphabets and add digits");
			else if (space == 10)
				System.out.println("Using digits only is weak, try using alphabets with digits");
			else if (space == 36)
				System.out.println("Try using upper case and special charcters along with your password");
			else if (space == 62)
				System.out.println("Try adding special characters");
			else if (space == 94)
				System.out.println("Your Password is common, try uncommon passwords");
			else
				System.out.println("Use characters and digits along with special characters");

		}
	}

	private static int calculateSpace(String password) {
		int space = 0;
		boolean upperCase = false, lowerCase = false, specialChar = false, digit = false;
		// checks if each flag is presented in the entered password
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isUpperCase(c)) {
				upperCase = true;
			} else if (Character.isLowerCase(c)) {
				lowerCase = true;
			}

			else if (Character.isDigit(c)) {
				digit = true;
			} else {
				specialChar = true;
			}
		}
		// the space is set after checking if flags are true
		if (upperCase && lowerCase && digit && specialChar)
			space = 94;
		else if (upperCase && lowerCase && digit)
			space = 62;
		else if (upperCase && lowerCase)
			space = 52;
		else if ((upperCase || lowerCase) && digit)
			space = 36;
		else if (upperCase || lowerCase)
			space = 26;
		else if (digit)
			space = 10;
		else if(specialChar)
			space=32;

		return space;
	}

	private static double calculateEntropy(int space, int length) {
		double entropy;
		entropy = length * (Math.log(space) / Math.log(2)); // entropy is calculated using this formula
		return entropy;

	}

	private static double calculateVariance(String password, int length) {
		int sum = 0;
		char c;
		double mean;
		double variance = 0;
		// the sum of the ascii values of the password is calculated
		for (int i = 0; i < length; i++) {
			c = password.charAt(i);
			sum += c;
		}
		mean = (double) sum / length; // the sum is divided by the length to find the mean
		for (int i = 0; i < length; i++) {
			c = password.charAt(i);
			variance += Math.pow((c - mean), 2); // this formula is to find the variance

		}
		return variance;

	}

}
