package www.aboutyun.com;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HbaseMain {

	static final String INPUT_PATH = "hdfs://master:8020/test.txt";
	static final String OUT_PATH = "hdfs://master:8020/Output";

	public static void main(String[] args) throws IOException,
			InterruptedException, ClassNotFoundException {

		// ����
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, HbaseMain.class.getSimpleName());
		job.setJarByClass(HbaseMain.class);
		// Ѱ������
		FileInputFormat.setInputPaths(job, INPUT_PATH);
		// 1.2���������ݽ��и�ʽ���������
		job.setInputFormatClass(TextInputFormat.class);
		job.setMapperClass(HbaseMap.class);
		// 1.2ָ��map�������<key,value>����
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		job.setNumReduceTasks(0);
		// ָ�����·��
		FileOutputFormat.setOutputPath(job, new Path(OUT_PATH));
		
		job.waitForCompletion(true);

	}
}
