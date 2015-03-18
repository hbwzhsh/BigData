package www.aboutyun.com;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HbaseMap extends Mapper<LongWritable, Text, Text, IntWritable> {
	private static Configuration conf = null;
	/**
	 * ��ʼ������
	 */

	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "master");// ʹ��eclipseʱ�����������������޷���λ
		conf.set("hbase.zookeeper.property.clientPort", "2181");
	}

	/**************************************************************************/
	public void map(LongWritable key, Text line, Context context)
			throws IOException, InterruptedException {

		try {
			StringResolves(line.toString(), context);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**************************************************************************/
	// �ַ�������

	public static void StringResolves(String line, Context context)
			throws ParseException {
		String ipField, dateField, urlField, browserField;

		// ��ȡip��ַ
		ipField = line.split("- -")[0].trim();

		// ��ȡʱ��,��ת����ʽ
		int getTimeFirst = line.indexOf("[");
		int getTimeLast = line.indexOf("]");
		String time = line.substring(getTimeFirst + 1, getTimeLast).trim();
		Date dt = null;
		DateFormat df1 = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG);
		dt = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.US)
				.parse(time);
		dateField = df1.format(dt);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMM");
		String dateField1 = sdf.format(dt);
		// ��ȡurl
		String[] getUrl = line.split("\"");

		String firtGeturl = getUrl[1].substring(3).trim();

		String secondGeturl = getUrl[3].trim();
		urlField = firtGeturl + "�ָ���" + secondGeturl;

		// ��ȡ�����
		String[] getBrowse = line.split("\"");
		String strBrowse = getBrowse[5].toString();
		String str = "(KHTML, like Gecko)";
		int i = strBrowse.indexOf(str);
		strBrowse = strBrowse.substring(i);
		String strBrowse1[] = strBrowse.split("\\/");
		strBrowse = strBrowse1[0].toString();
		String strBrowse2[] = strBrowse.split("\\)");
		browserField = strBrowse2[1].trim();

		// ��ӵ����ݿ�

		String rowKey = ipField + dateField1 + urlField
				+ new Random().nextInt();
		String[] cols = new String[] { "IpAddress", "AccressTime", "Url",
				"UserBrowser", };
		String[] colsValue = new String[] { ipField, dateField, urlField,
				browserField };

		try {
			addData(rowKey, "LogTable", cols, colsValue, context);
			context.write(new Text("1"), new IntWritable(1));

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Ϊ��������ݣ��ʺ�֪���ж�������Ĺ̶���
	 * 
	 * @rowKey rowKey
	 * 
	 * @tableName ����
	 * 
	 * @column1 ��һ�������б�
	 * 
	 * @value1 ��һ���е�ֵ���б�
	 */
	public static void addData(String rowKey, String tableName,
			String[] column1, String[] value1, Context context)
			throws IOException {

		Put put = new Put(Bytes.toBytes(rowKey));// ����rowkey
		HTable table = new HTable(conf, Bytes.toBytes(tableName));// HTabel�������¼��صĲ�������ɾ�Ĳ��//
																	// ��ȡ��
		HColumnDescriptor[] columnFamilies = table.getTableDescriptor() // ��ȡ���е�����
				.getColumnFamilies();

		for (int i = 0; i < columnFamilies.length; i++) {
			String familyName = columnFamilies[i].getNameAsString(); // ��ȡ������
			if (familyName.equals("Info")) { // info����put����
				for (int j = 0; j < column1.length; j++) {
					put.add(Bytes.toBytes(familyName),
							Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));
				}
			}

		}
		table.put(put);
		// context.write(new Text(rowKey), null);
		System.out.println("add data Success!");
	}

}
