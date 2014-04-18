package app;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.xml.sax.SAXException;

import amazon.advertising.api.sample.ItemLookupSample;


public class check {
    public static void main(String[] args) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException, InterruptedException {
    	/* int i = 0;
    	String s = "wireless mouse";
    	String[] asinList = ItemLookupSample.searchItem(s);
    	for (i=0; i<3; i++)
    		System.out.println(asinList[i]); */
    	//String s = AmazonReview.getAmazonReview("SATA SSD");
    	String t = TwitterSentiment.getTwitterSentiment("pizza");
    	//TwitterSentiment.getTwitterSentiment();
    	// for (String h : s)  
    	//    {  
    	//System.out.println(s);  
    	// System.out.println(AmazonMock.getAmazonMock("hi"));  
    	
    	//    }
    /*	Map data = AmazonReview.getAmazonReview("wireless mouse");
    	 List<String> keys = new ArrayList<String>(data.keySet());
    	 for (String key: keys) {
    		    System.out.println(key + ": " + data.get(key));
    		}
    	/* for (String h : data)  
    	    {  
    	    	
    	      System.out.println(h);  
    	    } 
           
        // Convert to JSON
         JSONArray arr = new JSONArray();
         arr.add(data);
        JSONObject obj = new JSONObject();
      
        obj.put("versioninformation", arr); */

       // System.out.println(JSONValue.toJSONString(obj));

    	
    }
  
}
