package com.aboutyun.mr.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class ReadFromHbase{
	
	public static class ReadMap extends TableMapper<Text, Text>{
		public void map(ImmutableBytesWritable row, Result value, Context context)
				throws IOException, InterruptedException {
			
			String rowkey=new String(row.get());
			String ipaddress=new String(value.getValue(Bytes.toBytes("info"), Bytes.toBytes("ipaddress")));
			context.write(new Text(rowkey),new Text(ipaddress));	
		}
	}
	
	public static void main(String args[]) throws Exception{
		
		Configuration hbaseconf=new Configuration();
		hbaseconf.set("hbase.zookeeper.quorum", "hadoop");
		Configuration config=HBaseConfiguration.create(hbaseconf);
//		Configuration config = HBaseConfiguration.create();
		Job job = new Job(config, "Read Hbase");
		job.setJarByClass(ReadFromHbase.class);     // class that contains mapper
			
		Scan scan = new Scan();
		scan.setCaching(500);        // 1 is the default in Scan, which will be bad for MapReduce jobs
		scan.setCacheBlocks(false);  // don't set to true for MR jobs
		  
		TableMapReduceUtil.initTableMapperJob(
		  "LogTable",       // input HBase table name
		  scan,             // Scan instance to control CF and attribute selection
		  ReadMap.class,    // mapper
		  Text.class,       // mapper output key 
		  Text.class,       // mapper output value
		  job);
		job.setOutputFormatClass(TextOutputFormat.class);   // because we aren't emitting anything from mapper
		FileOutputFormat.setOutputPath(job, new Path(args[0]));    
		boolean b = job.waitForCompletion(true);
		if (!b) {
		  throw new IOException("error with job!");
		}
	}


}
