
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class PageRankMapper extends MapReduceBase
implements Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> output, Reporter reporter)
					throws IOException {

		if(value != null){
			String line = value.toString();
			String[] data = line.trim().split(" ");

			if(data.length>2){

				//retrieving source
				String keyName = data[0];

				//retrieving current PageRank and calculating new Page Rank
				double pR = Double.parseDouble(data[data.length-1]);
				double newPR = pR/(data.length-2);

				//string to track outgoing links
				String outLink = "value=";

				//mapping outgoing links and PR to source key
				for(int i=1 ; i<data.length -1 ; i++){
					output.collect(new Text(data[i]), new Text("value="+keyName+", "+newPR));
					outLink = outLink + data[i] + " ";
				}
				
				//mapping outgoing links with source
				output.collect(new Text(keyName), new Text(outLink));
			}

		}
		else{
			System.err.println("Mapper value is null");
		}
	}

}