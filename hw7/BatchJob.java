
package org.example;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.util.Collector;

public class BatchJob {

	public static void main(String[] args) throws Exception {
		// set up the batch execution environment
		String input = null;
		String output = null;

		ParameterTool params = ParameterTool.fromArgs(args);

		try {
			input = params.getRequired("input");
			output = params.getRequired("output");
		}catch (Exception e){
			System.out.println("Argument Error");
			e.printStackTrace();

			return;
		}

		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		env.setParallelism(1);

		DataSet<String> text = env.readTextFile(input);

		DataSet<Tuple2<String, Integer>> zimucounts = text.flatMap(new Tokenizer()).groupBy(0).sum(1);

		zimucounts.writeAsText(output, FileSystem.WriteMode.OVERWRITE);

		env.execute("Flink Batch Java API Skeleton");
	}

	public static class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>>{

		@Override
		public void flatMap(String s, Collector<Tuple2<String, Integer>> out) throws Exception {
	
			StringBuffer s2 = new StringBuffer();
			String s3 = s.toLowerCase();
			for(int i = 0;i<s3.length();i++){
				if(s3.charAt(i)>='a' && s3.charAt(i)<='z'){
					s2.append(s3.charAt(i));
				}
			}
	
			String[] tokens = s2.toString().split("");

			for (String token : tokens){
				if(token.length() > 0){
					System.out.println(token);
					out.collect(new Tuple2<String,Integer>(token,1));
				}
			}
		}
	}
}
