package aak.profile.read.education.capability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.codehaus.jackson.map.ObjectMapper;

import aak.common.vo.Org;
import aak.common.vo.education.Education;
import aak.common.vo.education.ScoreDetailsVO;
import aak.java.json.Aakson;
import aak.platform.persistence.hbase.HBaseConnectionPool;
import aak.platform.persistence.hbase.HBaseScanUtil;
import aak.profile.vo.ProfileInfoRequestContext;

/*
 * 
 */
public class EducationDataReadCapability {

	/*
	 * 
	 */
	public static List<Education> getEducationInfo(ProfileInfoRequestContext requestContext) throws Exception {
		
		List<Education> resultList = null;
		
		Connection connection = HBaseConnectionPool.getConnection("READ_PROFILE_EDUCATION");
		
		HashMap<String, String> cfAndColumnNamesMap = new HashMap<String, String>(1);
		cfAndColumnNamesMap.put("edu", "PED_SEQ, PED_EDU, PED_START, PED_END, PED_ORG_DOC, PED_SCORE_DOC, PED_SPECIALIZATION, PED_SUBJECTS");
		
		Table table = connection.getTable(TableName.valueOf("ppl_master_DEV", "PROFILE_EDUCATION"));  
		Scan scan = HBaseScanUtil.prepareScan(cfAndColumnNamesMap, requestContext.getWhose().trim());
		ResultScanner scanner = table.getScanner(scan);
		
		if(scanner != null) {
			
			byte [] colFamily = Bytes.toBytes("edu");
			ObjectMapper mapper = new ObjectMapper();
			
			resultList = new ArrayList<Education>();
			
			Iterator<Result> results = scanner.iterator();
			
			while(results.hasNext()){
					
				resultList.add(buildObjectFromResult(colFamily, results.next(), mapper));
			}
			scanner.close();
		}
		table.close();
		return resultList;
	}
	
	/*
	 * 
	 */
	private static Education buildObjectFromResult(byte [] colFamily, Result aResult, ObjectMapper mapper) throws Exception{
		Education oneEduInfoObj = new Education();
		
		oneEduInfoObj.setSeq(Integer.parseInt(Bytes.toString(aResult.getValue(colFamily, Bytes.toBytes("PED_SEQ")))));
		oneEduInfoObj.setEdu(Bytes.toString(aResult.getValue(colFamily, Bytes.toBytes("PED_EDU"))));
		//oneEduInfoObj.setType(Bytes.toString(aResult.getValue(colFamily, Bytes.toBytes("PED_TYPE"))));
		oneEduInfoObj.setStart(Bytes.toString(aResult.getValue(colFamily, Bytes.toBytes("PED_START"))));
		oneEduInfoObj.setEnd(Bytes.toString(aResult.getValue(colFamily, Bytes.toBytes("PED_END"))));
		
		String jsonString = Bytes.toString(aResult.getValue(colFamily, Bytes.toBytes("PED_ORG_DOC")));
		if(jsonString != null) {
			oneEduInfoObj.setOrg(mapper.readValue(jsonString, Org.class));  //to do
		}
		
		jsonString = Bytes.toString(aResult.getValue(colFamily, Bytes.toBytes("PED_SCORE_DOC")));
		if(jsonString != null) {
			oneEduInfoObj.setScore(mapper.readValue(jsonString, ScoreDetailsVO.class));
		}
		
		oneEduInfoObj.setSpecialization(Aakson.getOrderedSet(Bytes.toString(aResult.getValue(colFamily, Bytes.toBytes("PED_SPECIALIZATION")))));
		oneEduInfoObj.setSubjects(Aakson.getOrderedSet(Bytes.toString(aResult.getValue(colFamily, Bytes.toBytes("PED_SUBJECTS")))));
		return oneEduInfoObj;
	}
}
