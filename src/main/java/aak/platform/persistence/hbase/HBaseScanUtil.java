package aak.platform.persistence.hbase;

import java.util.Iterator;
import java.util.Map;


import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseScanUtil {
	/*
	 * more versons to come
	 */
	public static Scan prepareScan(Map<String, String> cfAndColumnNamesMap, String whereRowKeyLike) throws Exception {
		Scan scan = new Scan();
		Filter filter = new PrefixFilter(Bytes.toBytes(whereRowKeyLike));
		scan.setFilter(filter);
		Iterator<Map.Entry<String, String>> cfAndColumnNamesIter = cfAndColumnNamesMap.entrySet().iterator();
		String [] columns = null;
		Map.Entry<String, String> anEntry = null;
		
		while(cfAndColumnNamesIter.hasNext()) {
			anEntry = cfAndColumnNamesIter.next();
			columns = anEntry.getValue().split(",");
			for(String aColumn : columns) {
				
				scan.addColumn(Bytes.toBytes(anEntry.getKey()), Bytes.toBytes(aColumn.trim()));
			}
		}
		return scan;
	}
}
