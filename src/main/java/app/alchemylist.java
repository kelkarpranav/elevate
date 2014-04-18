package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.alchemyapi.api.AlchemyAPI;

public class alchemylist {
    public static void main(String[] args) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
        List<String> reviewlist1 = new ArrayList<String>();
        

        reviewlist1.add("I have been using Microsoft's basic optical USB wired mouse for years. Everytime I try something different I end up going back to MS mouse. The thumbwheel usually goes first in my experience.");
        reviewlist1.add("I have had this mouse for a month now. This mouse feels and performs exactly the same as the MS basic optical mouse. Pointer movement is accurate and scroll wheel feel/operation is very good. Button clicks are good. Not too hard or too soft. I have med-lg hands and never had a problem with this size mouse.");
        AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromString("xxx");

        
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
     	 
        }

    }

}
