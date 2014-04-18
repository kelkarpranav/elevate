package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.*;


public class TwitterSentiment {
public static String getTwitterSentiment (String s) throws IOException, InterruptedException
{
	String brand = s;	
	StringBuffer output1 = new StringBuffer();

	try{
	Process p1 = Runtime.getRuntime().exec("python analyze.py " + brand + " 30");
	//p1.waitFor();

	BufferedReader reader = new BufferedReader(new InputStreamReader(p1.getInputStream()));
	String line = "";			
	while ((line = reader.readLine())!= null) {
		output1.append(line + "\n");
	}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
    	

	
	String str1 = output1.toString();
	//System.out.print(str1);
	
    String[] tweetset = str1.split("\n");
    List<String> tweetlist = Arrays.asList(tweetset); 
    for (String e : tweetlist) 
    {
    	System.out.println(e);
    	
    } 
	Map map = new LinkedHashMap();

	int i,a,b,c;
	int j=1;
    for(i=0;i<(tweetlist.size()/3);i++)  
    {  
      a=i;
      b=i+1;
      c=i+2; 
      map.put("tweet"+j, tweetlist.get(a));
 	  map.put("sentiment"+j, tweetlist.get(b));
 	  map.put("score"+j, tweetlist.get(c));
 	  j++;
 	  i=i+2;
 	  
    }  
    
   String jsonText = JSONValue.toJSONString(map);
   System.out.print(jsonText);	
    return s;
	
}
}
