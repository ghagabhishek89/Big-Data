
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class PageRankReducer extends MapReduceBase
implements Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, Text> output, Reporter reporter)
					throws IOException {

		String outLink = null;
		double totalPR = 0.0;

		while (values.hasNext()) {

			String value = values.next().toString();

			if (value.contains(",")){
				totalPR = totalPR + Double.parseDouble(value.substring(8).trim());
			}
			else{
				outLink = value.substring(6);
			}
		}
		output.collect(key, new Text(outLink + " " +totalPR));
	}
}
