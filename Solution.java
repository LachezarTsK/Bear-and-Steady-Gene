import java.util.Scanner;

public class Solution {

	private static int totalFrequency_A;
	private static int totalFrequency_C;
	private static int totalFrequency_G;
	private static int totalFrequency_T;
	private static final int totalChars_ASCII_Table = 256;

	public static void main(String[] args) throws Exception {
		Scanner reader = new Scanner(System.in);
		int lengthOfGene = reader.nextInt();
		reader.nextLine();
		String gene = reader.nextLine();
		reader.close();

		if (gene.length() != lengthOfGene) {
			throw new Exception("Actual gene length is different from the input value!");
		}

		int results = getLength_MinimumSubstringToBeReplaced(gene);
		System.out.println(results);
	}

	/**
	 * The method calculates the frequency of each letter in the gene.
	 */
	public static void findOccurancesOfLetters(String gene) {
		totalFrequency_A = gene.length() - gene.replaceAll("A", "").length();
		totalFrequency_C = gene.length() - gene.replaceAll("C", "").length();
		totalFrequency_G = gene.length() - gene.replaceAll("G", "").length();
		totalFrequency_T = gene.length() - gene.replaceAll("T", "").length();
	}

	/**
	 * The method creates the string of letters that must be replaced in order to
	 * make a steady gene.
	 */
	public static String getLettersToBeReplaced(String gene) {

		findOccurancesOfLetters(gene);
		int steadyGenesNumber = gene.length() / 4;
		StringBuilder lettersToBeReplaced = new StringBuilder();

		for (int i = 0; i < totalFrequency_A - steadyGenesNumber; i++) {
			lettersToBeReplaced.append("A");
		}
		for (int i = 0; i < totalFrequency_C - steadyGenesNumber; i++) {
			lettersToBeReplaced.append("C");
		}
		for (int i = 0; i < totalFrequency_G - steadyGenesNumber; i++) {
			lettersToBeReplaced.append("G");
		}
		for (int i = 0; i < totalFrequency_T - steadyGenesNumber; i++) {
			lettersToBeReplaced.append("T");
		}
		return lettersToBeReplaced.toString();
	}

	/**
	 * The method calculates the minimum substring to be replaced so that the gene
	 * is modified to a steady one.
	 */
	private static int getLength_MinimumSubstringToBeReplaced(String gene) {

		String lettersToBeReplaced = getLettersToBeReplaced(gene);
		if (lettersToBeReplaced.length() == 0) {
			return 0;
		}

		int[] chars_gene = new int[totalChars_ASCII_Table];
		int[] chars_toBeReplaced = new int[totalChars_ASCII_Table];

		for (int i = 0; i < lettersToBeReplaced.length(); i++) {
			chars_toBeReplaced[lettersToBeReplaced.charAt(i)]++;
		}

		int counterReplacements = 0;
		int length_MinimumSubstring = Integer.MAX_VALUE;
		int startIndex_Subsequence = 0;

		for (int i = 0; i < gene.length(); i++) {
			chars_gene[gene.charAt(i)]++;

			if (chars_toBeReplaced[gene.charAt(i)] != 0
					&& chars_gene[gene.charAt(i)] <= chars_toBeReplaced[gene.charAt(i)]) {
				counterReplacements++;
			}

			if (counterReplacements == lettersToBeReplaced.length()) {
				while (chars_gene[gene.charAt(startIndex_Subsequence)] > chars_toBeReplaced[gene
						.charAt(startIndex_Subsequence)]
						|| chars_toBeReplaced[gene.charAt(startIndex_Subsequence)] == 0) {
					if (chars_gene[gene.charAt(startIndex_Subsequence)] > chars_toBeReplaced[gene
							.charAt(startIndex_Subsequence)]) {
						chars_gene[gene.charAt(startIndex_Subsequence)]--;
					}
					startIndex_Subsequence++;
				}
				int currentLength_Substring = i - startIndex_Subsequence + 1;
				length_MinimumSubstring = Math.min(length_MinimumSubstring, currentLength_Substring);
			}
		}
		return length_MinimumSubstring;
	}
}
