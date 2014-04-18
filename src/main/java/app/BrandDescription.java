package app;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class BrandDescription {
  private String brandDescription;

  public BrandDescription() {
  }

  public BrandDescription(String brandDescription) {
    this.brandDescription = brandDescription;
    
  }

  public String getbrandDescription() {
    return brandDescription;
  }

  public void setbrandDescription(String brandDescription) throws XPathExpressionException, IOException, SAXException, ParserConfigurationException {
    this.brandDescription = brandDescription;
    AmazonReview.getAmazonReview(brandDescription);
  }

 }
