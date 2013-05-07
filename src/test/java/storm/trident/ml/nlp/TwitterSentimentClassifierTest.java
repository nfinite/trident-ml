package storm.trident.ml.nlp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import storm.trident.ml.preprocessing.EnglishTokenizer;

public class TwitterSentimentClassifierTest {

	@Test
	public void testWithSomeTwitterSentiments() {
		TwitterSentimentClassifier classifier = new TwitterSentimentClassifier();

		test(false, "RT @JazminBianca: I hate Windows 8. I hate Windows 8. I hate Windows 8.", classifier);
		test(false, "I don't like Windows 8, I think it's overrated =))", classifier);
		test(false, "Windows 8 is stupid as fuck ! Shit is confusing <<", classifier);
		test(false, "not a big fan of Windows 8", classifier);
		test(false, "Forever hating apple for changing the chargers #wanks", classifier);
		test(false, "#CSRBlast #CSRBlast That moment you pull out a book because the customer service at apple is horrible and takes wa... http://t.co/WxqyGR9a85", classifier);

		test(true, "Windows 8 is awesome :D", classifier);
		test(true, "God Windows 8 is amazing. Finally", classifier);
		test(true, "Register for the AWESOME Windows 8 western US regional events all in the next few weeks! http://t.co/7lfqaHSxfs #w8appfactor @w8appfactor", classifier);
		test(true, "Windows 8 is fun to use. I like it better then mac lion.", classifier);
		test(true, "Good morning loves 😁😁 apple jacks doe http://t.co/nOfi42enoQ", classifier);
		test(true, "@Saad_khan33 No i prefer apple anyday", classifier);
	}

	protected void test(boolean expected, String text, TwitterSentimentClassifier classifier) {
		boolean actual = classifier.classify(new EnglishTokenizer(2, 2).tokenize(text));
		assertEquals("Expecting " + expected + " but was " + actual + " for " + text, expected, actual);
	}
}
