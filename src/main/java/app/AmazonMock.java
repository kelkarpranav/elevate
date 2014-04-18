package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.alchemyapi.api.AlchemyAPI;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AmazonMock {
    public static String getAmazonMock(String s) throws XPathExpressionException, IOException, SAXException, ParserConfigurationException{
    	
    	String nouse = s;
        List<String> reviewlist1 = new ArrayList<String>();
        List<String> reviewlist2 = new ArrayList<String>();
        List<String> reviewlist3 = new ArrayList<String>();


        reviewlist1.add("I have been using Microsoft's basic optical USB wired mouse for years. Everytime I try something different I end up going back to MS mouse. The thumbwheel usually goes first in my experience.");
        reviewlist1.add("I have had this mouse for a month now. This mouse feels and performs exactly the same as the MS basic optical mouse. Pointer movement is accurate and scroll wheel feel/operation is very good. Button clicks are good. Not too hard or too soft. I have med-lg hands and never had a problem with this size mouse.");
        reviewlist2.add("Installation was plug and play with Windows 7. No drivers to install. Wireless operation has been flawless. The top of the mouse is rubberized synthetic that does absorb oil from your hand and fingers.. Easy to clean with windex though. I got the mouse on sale. I really like it. I will keep using it until stops working.. Oh. almost forgot it tracks well on my sofa as well. Considering sale price -$15 and smooth operation. 5 stars");
        reviewlist2.add("The product is not bad. As far as its function as a mouse, it works flawlessly. It looks great, fits nicely in the hand, and moves across a laminate mouse pad with ease. The optical tracker is receptive and accurate without skipping. Like I said, it works flawlessly.xtremely competitive price, but I believe in this case that there are some better options out there that might even be a little cheaper than the current $16 price tag.");
        reviewlist3.add("Amazon Basics are almost always great products at an extremely competitive price, but I believe in this case that there are some better options out there that might even be a little cheaper than the current $16 price tag.");
        reviewlist3.add(" In an effort to reduce the size of the mouse, the battery housing was designed in such a way that the batteries have to be inserted at a steep angle. Because of this, and to ensure that one could remove the batteries at some point, an awkward piece of plastic had to be included in the design that lies across the bottom of both battery slots and protrudes on one side. The protrusion can be pulled to eject both batteries. The system is necessary, as the batteries are nearly impossible to get out without the awkward plastic, but it makes the process of inserting batteries a little clumsy.");
        List  l1 = new LinkedList();
        List  l2 = new LinkedList();
        List  l3 = new LinkedList();


        for (String e : reviewlist1)  
        {  
        	Map map1 = new HashMap();
     	    map1.put("review", e);
     	   map1.put("sentiment", "positive");
     	  map1.put("score", "5");
     	  l1.add(map1);
        }

        for (String e : reviewlist2)  
        {  
        	Map map2 = new HashMap();

     	    map2.put("review", e);
     	   map2.put("sentiment", "negative");
     	  map2.put("score", "-3.33");
     	  l2.add(map2);
        }
    	
        for (String e : reviewlist3)  
        {  
        	Map map3 = new HashMap();

     	    map3.put("review", e);
     	   map3.put("sentiment", "positive");
     	  map3.put("score", "2.334");
     	  l3.add(map3);
        }
    	
        JSONObject obj1 = new JSONObject();
        obj1.put("product1",l1);
        obj1.put("product2",l2);
        obj1.put("product3",l3);
        String answer = obj1.toJSONString();
		return answer;

    	    	
    }
    
}
