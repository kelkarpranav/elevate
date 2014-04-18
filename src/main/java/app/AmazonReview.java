package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.*;
import com.alchemyapi.api.AlchemyAPI;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import amazon.advertising.api.sample.ItemLookupSample;

public class AmazonReview {
public static String getAmazonReview(String s) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException
{
	String itemDescription = s;
	String[] asinList = ItemLookupSample.searchItem(itemDescription);

   /* Process perlcmd = Runtime.getRuntime().exec("pwd");
    BufferedReader rpmStdout = new BufferedReader(new InputStreamReader(perlcmd.getInputStream()));
	System.out.println(rpmStdout); */
	StringBuffer output1 = new StringBuffer();
	StringBuffer output2 = new StringBuffer();
	StringBuffer output3 = new StringBuffer();
	Map m2 = new HashMap();
	Map m3 = new HashMap();


	try {
	Process p1 = Runtime.getRuntime().exec("./downloadAmazonReviews1.pl com " + asinList[0]);
	p1.waitFor();
	p1 = Runtime.getRuntime().exec("./downloadAmazonReviews2.pl com " + asinList[1]);
	p1.waitFor();
	p1 = Runtime.getRuntime().exec("./downloadAmazonReviews3.pl com " + asinList[2]);
	p1.waitFor();

	p1 = Runtime.getRuntime().exec("./extractAmazonReviews1.pl 1");

	BufferedReader reader = new BufferedReader(new InputStreamReader(p1.getInputStream()));
	String line = "";			
	while ((line = reader.readLine())!= null) {
		output1.append(line + "\n");
	}
	//System.out.println(output1.toString());
	
	p1 = Runtime.getRuntime().exec("./extractAmazonReviews2.pl 2");
	reader = new BufferedReader(new InputStreamReader(p1.getInputStream()));
	line = "";			
	while ((line = reader.readLine())!= null) {
		output2.append(line + "\n");
	}
	//System.out.println(output2.toString());
	
	p1 = Runtime.getRuntime().exec("./extractAmazonReviews3.pl 3");
	reader = new BufferedReader(new InputStreamReader(p1.getInputStream()));
	line = "";			
	while ((line = reader.readLine())!= null) {
		output3.append(line + "\n");
	}
	//System.out.println(output3.toString());


	//p1 = Runtime.getRuntime().exec("./extractAmazonReviews1.pl ./1 > reviews1.csv" );
	//p1.waitFor();


	}
	catch (Exception e) {
		e.printStackTrace();
	}
	/* String str1 = output1.toString();
    String[] reviewset1 = str1.split("\n");
    List<String> reviewlist1 = Arrays.asList(reviewset1);  
	String str2 = output2.toString();
    String[] reviewset2 = str2.split("\n");
    List<String> reviewlist2 = Arrays.asList(reviewset1);  
    String str3 = output3.toString();
    String[] reviewset3 = str3.split("\n");
    List<String> reviewlist3 = Arrays.asList(reviewset1);   */
    
    String str1 = output1.toString();
    String[] reviewset1 = str1.split("\n");
    List<String> reviewlist1 = Arrays.asList(reviewset1); 
    
    String str2 = output2.toString();
    String[] reviewset2 = str2.split("\n");
    List<String> reviewlist2 = Arrays.asList(reviewset2); 
    
    String str3 = output3.toString();
    String[] reviewset3 = str3.split("\n");
    List<String> reviewlist3 = Arrays.asList(reviewset3); 

	
	
    AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromString("xxx");

	//System.out.println("Sentiment information for Product 1: " + ItemLookupSample.title[0] );

   List<String> finalset = new ArrayList<String>();	
   List  l1 = new LinkedList();
   List  l2 = new LinkedList();

   List  l3 = new LinkedList();


	Document doc;

    for (String e : reviewlist1)  
    {  
    	Map map1 = new HashMap();

    	doc = alchemyObj.TextGetTextSentiment(e);
    	String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
 	    String score = doc.getElementsByTagName("score").item(0).getTextContent();
 	    map1.put("review", e);
 	   map1.put("sentiment", sentiment);
 	  map1.put("score", score);
 	  l1.add(map1);
 	    
 	    
 	    //finalset.add(e);
 	   // finalset.add(sentiment);
 	    //finalset.add(score);

 	    // print results
 	   // System.out.println("Sentiment: " + sentiment);
 	    //System.out.println("Score: " + score);
       //System.out.println(e);  
    }  
    for (String e : reviewlist2)  
    {  
    	Map map1 = new HashMap();

    	doc = alchemyObj.TextGetTextSentiment(e);
    	String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
 	    String score = doc.getElementsByTagName("score").item(0).getTextContent();
 	    map1.put("review", e);
 	   map1.put("sentiment", sentiment);
 	  map1.put("score", score);
 	  l2.add(map1);
    }  
    for (String e : reviewlist3)  
    {  
    	Map map1 = new HashMap();

    	doc = alchemyObj.TextGetTextSentiment(e);
    	String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
 	    String score = doc.getElementsByTagName("score").item(0).getTextContent();
 	    map1.put("review", e);
 	   map1.put("sentiment", sentiment);
 	  map1.put("score", score);
 	  l3.add(map1);
 	
    }  
    
    JSONObject obj = new JSONObject();
obj.put("product1", l1);
obj.put("product2", l2);

obj.put("product3", l3);
String answer = obj.toJSONString();
System.out.println(answer);
try {
	 
	FileWriter file = new FileWriter("test.json");
	file.write(obj.toJSONString());
	file.flush();
	file.close();

} catch (IOException e) {
	e.printStackTrace();
}


    
/*	System.out.println("Sentiment information for Product 2: " + ItemLookupSample.title[1] );

    for (String f : reviewlist2)  
    {  
    	doc = alchemyObj.TextGetTextSentiment(f);
    	String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
 	    String score = doc.getElementsByTagName("score").item(0).getTextContent();
 	   finalset.add(f);
	    finalset.add(sentiment);
	    finalset.add(score);

 	    // print results
 	   // System.out.println("Sentiment: " + sentiment);
 	   // System.out.println("Score: " + score);
      //System.out.println(f);  
    }  
    
	System.out.println("Sentiment information for Product 3: " + ItemLookupSample.title[2] );

    
    for (String g : reviewlist3)  
    {  
    	doc = alchemyObj.TextGetTextSentiment(g);
    	 String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
 	    String score = doc.getElementsByTagName("score").item(0).getTextContent();
 	   finalset.add(g);
	    finalset.add(sentiment);
	    finalset.add(score);

 	    // print results
 	    //System.out.println("Sentiment: " + sentiment);
 	   // System.out.println("Score: " + score);
      System.out.println(g);  
    }  */
    
 /* for (String h : finalset)  
    {  
    	
      System.out.println(h);  
    } */
return answer;  
   
    
    
    
	/* while(!reviewlist1.isEmpty()) {
		doc = alchemyObj.TextGetTextSentiment(reviewlist1.remove(a1));

	    // parse XML result
	    String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
	    String score = doc.getElementsByTagName("score").item(0).getTextContent();

	    // print results
	    System.out.println("Sentiment: " + sentiment);
	    System.out.println("Score: " + score);
	    a1++;
	} */
	
 /*  for(int a=0; a<10; a++)
   {
	doc = alchemyObj.TextGetTextSentiment(reviewset1[a]);

    // parse XML result
    String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
    String score = doc.getElementsByTagName("score").item(0).getTextContent();

    // print results
    System.out.println("Sentiment: " + sentiment);
    System.out.println("Score: " + score);
   } */

 /*  for(int b=0; b<10; b++)
   {
	doc = alchemyObj.TextGetTextSentiment(reviewset2[b]);

    // parse XML result
    String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
    String score = doc.getElementsByTagName("score").item(0).getTextContent();

    // print results
    System.out.println("Sentiment: " + sentiment);
    System.out.println("Score: " + score);
   }

   for(int c=0; c<10; c++)
   {
	doc = alchemyObj.TextGetTextSentiment(reviewset3[c]);

    // parse XML result
    String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
    String score = doc.getElementsByTagName("score").item(0).getTextContent();

    // print results
    System.out.println("Sentiment: " + sentiment);
    System.out.println("Score: " + score);
   } */
	
	/*try {
		
		Process p2 = Runtime.getRuntime().exec(new String[] {"./extractAmazonReviews1.pl", "./1 > reviews1.csv"});
		p2.waitFor();


		}
		catch (Exception e) {
			e.printStackTrace();
		} */
	//System.out.println(output1.toString());
  
  
  
	
	}
}

