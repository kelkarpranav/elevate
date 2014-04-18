package app;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class ImplementAmazon {

	public static void main(String args[]) throws XPathExpressionException, IOException, SAXException, ParserConfigurationException{
		AmazonReview.getAmazonReview("wireless keyboard");
	}
	
}
