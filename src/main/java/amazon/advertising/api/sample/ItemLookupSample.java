/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

package amazon.advertising.api.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/*
 * This class shows how to make a simple authenticated ItemLookup call to the
 * Amazon Product Advertising API.
 * 
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class ItemLookupSample {
    public static String[] title = null;

   
    private static final String AWS_ACCESS_KEY_ID = "xxx";

    private static final String AWS_SECRET_KEY = "xxx";

    private static final String ENDPOINT = "ecs.amazonaws.com";

    private static final String ITEM_ID = "B001UEBN42";

   
    
    public static String[] searchItem(String userInput) {
		String usr = userInput;
    	
		/* InputStreamReader ISR = new InputStreamReader(System.in);
        BufferedReader BR = new BufferedReader(ISR);
        System.out.println("--------------------------------------------");
        System.out.println("Describe your brand type in very few words?");
        System.out.println("--------------------------------------------");

        String userInput = BR.readLine();
        
        System.out.println("Your brand description is : "+userInput);
        System.out.println("--------------------------------------------");

        
        BR.close();
        ISR.close(); */
        SignedRequestsHelper helper;
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            String[] cached = {"stacktrace"};
			return cached;
        }
        
        String requestUrlForSearch = null;
        String[] asin = null;
       
        /* The helper can sign requests in two forms - map form and string form */
        
        /*
         * Here is an example in map form, where the request parameters are stored in a map.
         */
      //  System.out.println("ItemSearch:");
        Map<String, String> itemsearch = new HashMap<String, String>();
        itemsearch.put("Service", "AWSECommerceService");
        itemsearch.put("Version", "2011-08-01");
        itemsearch.put("Operation", "ItemSearch");
        itemsearch.put("Keywords", usr);
        itemsearch.put("SearchIndex", "Electronics");
        itemsearch.put("AssociateTag", "pranavkelkar-20");
      //  itemsearch.put("IdType", "ASIN");
        requestUrlForSearch = helper.sign(itemsearch);
    //    System.out.println("Signed Request is \"" + requestUrlForSearch + "\"");
    //    System.out.println();
        asin = fetchASIN(requestUrlForSearch);
       // title = fetchTitle(requestUrlForSearch);
       // reviews = fetchReviews(requestUrlForSearch);
       // tech = fetchTech(requestUrlForSearch);
	   /* for (int i = 0; i < asin.length; i++) {
	    	
	         System.out.println(asin[i]);
	      

	         
	         System.out.println();
	    } */
    return asin;
    }
    
    
    /*
     * Utility function to fetch the response from the service and extract the
     * title from the XML.
     */
  /*  private static String[] fetchTitle(String requestUrl) {
        String[] title = new String[3];
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            Node[] titleNode = new Node[3];
            titleNode[0]=doc.getElementsByTagName("Title").item(0);
            titleNode[1]=doc.getElementsByTagName("Title").item(1);
            titleNode[2]=doc.getElementsByTagName("Title").item(2);
            title[0] = titleNode[0].getTextContent();
            title[1] = titleNode[1].getTextContent();
            title[2] = titleNode[2].getTextContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return title;
    } */
    
    private static String[] fetchASIN(String requestUrl) {
        String[] asin = new String[6];
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            Node[] asinNode = new Node[100];
            asinNode[0]=doc.getElementsByTagName("ASIN").item(0);
            asinNode[1]=doc.getElementsByTagName("ASIN").item(1);
            asinNode[2]=doc.getElementsByTagName("ASIN").item(2);
            asinNode[3]=doc.getElementsByTagName("Title").item(0);
            asinNode[4]=doc.getElementsByTagName("Title").item(1);
            asinNode[5]=doc.getElementsByTagName("Title").item(2);
          
            
            asin[0] = asinNode[0].getTextContent();
            asin[1] = asinNode[1].getTextContent();
            asin[2] = asinNode[2].getTextContent();
            asin[3] = asinNode[3].getTextContent();
            asin[4] = asinNode[4].getTextContent();
            asin[5] = asinNode[5].getTextContent();
           
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return asin;
    }

   /* private static String[] fetchReviews(String requestUrl) {
        String[] reviews = new String[3];
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            Node[] reviewNode = new Node[3];
            reviewNode[0]=doc.getElementsByTagName("URL").item(5);
            reviewNode[1]=doc.getElementsByTagName("URL").item(12);
            reviewNode[2]=doc.getElementsByTagName("URL").item(19);
            reviews[0] = reviewNode[0].getTextContent();
            reviews[1] = reviewNode[1].getTextContent();
            reviews[2] = reviewNode[2].getTextContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }
    private static String[] fetchTech(String requestUrl) {
        String[] tech = new String[3];
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            Node[] techNode = new Node[3];
            techNode[0]=doc.getElementsByTagName("URL").item(0);
            techNode[1]=doc.getElementsByTagName("URL").item(7);
            techNode[2]=doc.getElementsByTagName("URL").item(14);
            tech[0] = techNode[0].getTextContent();
            tech[1] = techNode[1].getTextContent();
            tech[2] = techNode[2].getTextContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tech;
    }'*/
}
