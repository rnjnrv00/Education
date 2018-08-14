package aak.platform.persistence.hbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;


/*
 *  lot of work to be done, just for an interface/abstraction
 *  
 */
public class HBaseConnectionPool {
	
	private static HBaseConnectionPool onePoolForFeaturesOneCluster = null; 
	private static HashMap<String, Configuration> featureWiseCongigs = null;
	private static HashMap<String, List<Connection>> featureWiseConnections = null;
	
	private HBaseConnectionPool () {
		super();
	}
	
	/*
	 * 
	 */
	public static Connection getConnection(String featureKey) throws Exception {
		if(onePoolForFeaturesOneCluster == null) {
			init();
		}
		List<Connection> temp = featureWiseConnections.get(featureKey);
		if(temp == null) {
			throw new Exception("FEATURE NOT REGISTERED IN ENTERPRISE INFRASTRUCTURE REGISTRY!");
		}
		return temp.get(0);
		
	}
	
	
	/*
	 * 
	 */
	public static synchronized void init() throws Exception {
		
		if(onePoolForFeaturesOneCluster == null) {
			
				//read enterprise wide policy/registry and decide
				// what to do etc. will need URL of registry/policy server and config
				// inputs to init 
				featureWiseCongigs = new HashMap<String, Configuration>();
				
				Configuration config = HBaseConfiguration.create();
				config.set("hbase.zookeeper.property.clientPort", "2181");
				config.set("hbase.zookeeper.quorum", "192.168.0.22");
				
				featureWiseCongigs.put("READ_PROFILE_OVERVIEW", config);
				featureWiseCongigs.put("READ_PROFILE_EDUCATION", config);
				featureWiseCongigs.put("READ_PROFILE_LANGUAGES", config);
				featureWiseCongigs.put("READ_PROFILE_PERSONALITY", config);
				//
				featureWiseCongigs.put("WRITE_PROFILE_SUMMARY", config);
				featureWiseCongigs.put("WRITE_PROFILE_EDUCATION", config);
				featureWiseCongigs.put("WRITE_PROFILE_LANGUAGES", config);
				featureWiseCongigs.put("WRITE_PROFILE_PERSONALITY", config);
				
				ArrayList<Connection> tempList= new ArrayList<Connection>();
				tempList.add(ConnectionFactory.createConnection(config));
				
				featureWiseConnections = new HashMap<String, List<Connection>>();
				
				featureWiseConnections.put("READ_PROFILE_OVERVIEW", tempList);
				featureWiseConnections.put("READ_PROFILE_EDUCATION", tempList);
				featureWiseConnections.put("READ_PROFILE_LANGUAGES", tempList);
				featureWiseConnections.put("READ_PROFILE_PERSONALITY", tempList);
				//
				featureWiseConnections.put("WRITE_PROFILE_SUMMARY", tempList);
				featureWiseConnections.put("WRITE_PROFILE_EDUCATION", tempList);
				featureWiseConnections.put("WRITE_PROFILE_LANGUAGES", tempList);
				featureWiseConnections.put("WRITE_PROFILE_PERSONALITY", tempList);
				
				onePoolForFeaturesOneCluster = new HBaseConnectionPool();
				
		}
		
	}
}
