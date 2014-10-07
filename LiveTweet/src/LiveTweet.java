import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/*
 * Program Name : LiveTweet
 * Created by : Abhishek Ghag
 * Program Description : This program fetches live tweets through tweeter rest api 
 * and stored the data to ta file in an organised fashion
 */
public class LiveTweet {
	
	public static void main(String args[]) throws TwitterException{
		Twitter twitter = TwitterFactory.getSingleton();
	    List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }
	}

}
