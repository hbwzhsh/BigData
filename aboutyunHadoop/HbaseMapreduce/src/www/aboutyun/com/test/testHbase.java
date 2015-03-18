package www.aboutyun.com.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class testHbase {

	private static Configuration conf = null;
	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "master");// ʹ��eclipseʱ�����������������޷���λmaster��Ҫ����hosts
		conf.set("hbase.zookeeper.property.clientPort", "2181");
	}

	public static void main(String[] args) throws IOException {
		String[] cols = new String[1];
		String[] colsValue = new String[1];
		cols[0] = "title";
		colsValue[0] = "AboutYunArticle";

		// ������
		createTable();
		// ���ֵ
		addData("www.aboutyun.com", "blog", cols, colsValue);
	}

	private static void createTable() throws MasterNotRunningException,
			ZooKeeperConnectionException, IOException {

		HBaseAdmin admin = new HBaseAdmin(conf);// �½�һ�����ݿ����Ա//��api
		if (!admin.tableExists(TableName.valueOf("LogTable"))) {
			System.out.println("�����ڣ����ȴ�����!");
			System.exit(0);
		}  
		admin.close();
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
	 * 
	 * @column2 �ڶ��������б�
	 * 
	 * @value2 �ڶ����е�ֵ���б�
	 */
	private static void addData(String rowKey, String tableName,
			String[] column1, String[] value1) throws IOException {
		Put put = new Put(Bytes.toBytes(rowKey));// ����rowkey
		HTable table = new HTable(conf, Bytes.toBytes(tableName));// HTabel�������¼��صĲ�������ɾ�Ĳ��//
																	// ��ȡ��
		HColumnDescriptor[] columnFamilies = table.getTableDescriptor() // ��ȡ���е�����
				.getColumnFamilies();

		for (int i = 0; i < columnFamilies.length; i++) {
			String familyName = columnFamilies[i].getNameAsString(); // ��ȡ������
			if (familyName.equals("article")) { // article����put����
				for (int j = 0; j < column1.length; j++) {
					put.add(Bytes.toBytes(familyName),
							Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));
				}
			}

		}
		table.put(put);
		System.out.println("add data Success!");
	}

}
