package app;

import yelp.Yelp;

public class YelpReview {
	
	String consumerKey = "";
    String consumerSecret = "";
    String token = "";
    String tokenSecret = "";

    Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
    String response = yelp.search("tandoori", "San Jose");

}
