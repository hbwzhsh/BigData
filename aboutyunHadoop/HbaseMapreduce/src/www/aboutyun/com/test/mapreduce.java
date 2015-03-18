package www.aboutyun.com.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class mapreduce {

	static final String INPUT_PATH = "hdfs://master:8020/test.txt";
	static final String OUT_PATH = "hdfs://master:8020/Output";

	public static void main(String[] args) throws Exception {
		// 主类
		Configuration conf = new Configuration();
		final Job job = new Job(conf, mapreduce.class.getSimpleName());
		job.setJarByClass(mapreduce.class);
		// 寻找输入
		FileInputFormat.setInputPaths(job, INPUT_PATH);
		// 1.2对输入数据进行格式化处理的类
		job.setInputFormatClass(TextInputFormat.class);
		job.setMapperClass(MyMapper.class);

		// 1.2指定map输出类型<key,value>类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);

		// 1.3指定分区
		job.setPartitionerClass(HashPartitioner.class);
		job.setNumReduceTasks(1);

		// 1.4排序分组省略，使用默认
		// 1.5规约省略，使用默认
		job.setReducerClass(MyReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		// 指定输出路径
		FileOutputFormat.setOutputPath(job, new Path(OUT_PATH));
		// 指定输出的格式或则类
		job.setOutputFormatClass(TextOutputFormat.class);

		// 把作业提交给jobtracer
		job.waitForCompletion(true);

	}

	// map类
	static class MyMapper extends
			Mapper<LongWritable, Text, Text, LongWritable> {
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			final String[] splited = value.toString().split("\t");
			for (String word : splited) {
				context.write(new Text(word), new LongWritable(1L));

			}

		}

	}

	// reduce类
	static class MyReduce extends
			Reducer<Text, LongWritable, Text, LongWritable> {
		@Override
		protected void reduce(Text k2, java.lang.Iterable<LongWritable> v2s,
				Context ctx) throws java.io.IOException, InterruptedException {
			long times = 0L;
			for (LongWritable count : v2s) {
				times += count.get();
				ctx.write(k2, new LongWritable(times));
			}

		}

	}

}