/*
 * Program Name : LiveTweet
 * Created by : Abhishek Ghag
 * Created Date : Oct 08 2014
 * Updated Date : Oct 08 2014
 * Latest update : Write the recent tweets to the file
 * Program Description : This program fetches live tweets through tweeter rest api 
 * and stored the data to ta file in an organised fashion
 */

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class LiveTweet {

	public static void main(String args[]) throws IOException{
		try {
			// get Twitter singleton instance with default credentials
			Twitter twitter = TwitterFactory.getSingleton();
			List<Status> statuses;

			statuses = twitter.getHomeTimeline();

			System.out.println("Showing home timeline.");

			//create a local file to write the tweets
			BufferedWriter buffWritter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("recentTweetList.txt"), "utf-8"));

			for (Status status : statuses) {
				// read user name and status line from each tweet and write it to a file
				buffWritter.write(((status.getUser().getName() +
						":\n" +status.getText()+"\nCreated on : "+status.getCreatedAt()+"\n\n")));
			}
			//closing file
			buffWritter.close();

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to get timeline: " + e.getMessage());
            System.exit(-1);
		}
	}
}
