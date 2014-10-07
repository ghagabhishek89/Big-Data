/*
 * Program Name : LiveTweet
 * Created by : Abhishek Ghag
 * Created Date : Oct 08 2014
 * Updated Date : Oct 08 2014
 * Latest update : Write the recent tweets to the file
 * Program Description : This program fetches live tweets through tweeter rest api 
 * and stored the data to ta file in an organised fashion
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class LiveTweet {
	
	public static void main(String args[]) throws TwitterException, NumberFormatException, IOException{
		
		Twitter twitter = TwitterFactory.getSingleton();
	    List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline.");
	    
	    //create a local file to write the tweets
	    FileOutputStream fos = new FileOutputStream("recentTweetList.txt");
	    
	    for (Status status : statuses) {
	    	// read user name and status line from each tweet and write it to a file
	        fos.write(Byte.parseByte((status.getUser().getName() + ":" +status.getText())));
	    }
	    //closing file
	    fos.close();
	}

}
