package hbase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import com.jscape.inet.scp.Scp;
import com.jscape.inet.scp.ScpException;
import com.jscape.inet.scp.events.*;
import com.jscape.inet.ssh.util.SshParameters;

@Path("generic")
public class test {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public test() {
        // TODO Auto-generated constructor stub
    }

    
   

  
    @GET
    @Produces("application/x-javascript")
    @Path("hbaseCreate/{tablename:.+}/{columnFamilies:.+}")
    public String hbaseCreate(@QueryParam("callback") String callback, @PathParam("tablename") String tablename,
    		@PathParam("columnFamilies") String columnFamilies) {
        String line="";
    	
        Configuration config = HBaseConfiguration.create();		 
		config.clear();
         config.set("hbase.zookeeper.quorum", "134.193.136.147");
         config.set("hbase.zookeeper.property.clientPort","2181");
         config.set("hbase.master", "134.193.136.147:60010");
		
         HBaseAdmin hba = null;
		try {
			// HBaseConfiguration hc = new HBaseConfiguration(new Configuration());
			
			  HTableDescriptor ht = new HTableDescriptor(tablename); 
			  
			  //String cf1 = columnFamilies.split(":")[0];
			  //String cf2 = columnFamilies.split(":")[1];
			  //String cf3 = columnFamilies.split(":")[2];
			  
			  
		//	  ht.addFamily( new HColumnDescriptor(cf1));

			  ht.addFamily( new HColumnDescriptor("coordinates"));
			  
			  ht.addFamily( new HColumnDescriptor("accelerometer"));
			  
			  ht.addFamily( new HColumnDescriptor("humidity"));
			  
			  ht.addFamily( new HColumnDescriptor("Date"));
			  
			  ht.addFamily( new HColumnDescriptor("Temp"));
			  
			//  System.out.println( "connecting" );

			  
			try {
				hba = new HBaseAdmin( config );
				// System.out.println( "Creating Table" );

				  hba.createTable( ht );

				 line = "Create Success";
			} catch (MasterNotRunningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ZooKeeperConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 
			  
			  	
        } finally {
            try {
				hba.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		return line;
    }
  
    
    
    @GET
    @Produces("application/x-javascript")
    @Path("hbaseInsert/{tablename:.+}/{filepath:.+}/{columnFamilies:.+}")
    public String hbaseInsert(@QueryParam("callback") String callback,@PathParam("tablename") String tablename, 
    		@PathParam("filepath") String filepath,@PathParam("columnFamilies") String columnFamilies) {
        String line="insert success";
    	
        Configuration config = HBaseConfiguration.create();		 
		config.clear();
         config.set("hbase.zookeeper.quorum", "134.193.136.147");
         config.set("hbase.zookeeper.property.clientPort","2181");
         config.set("hbase.master", "134.193.136.147:60010");
         
         
         String latitude="",longitude="",Date="",x="",y="",z="";
         

		  HTable table = null;
		try {
			table = new HTable(config, tablename);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		  //Put p = new Put(Bytes.toBytes("row1"));
		  
		  int count=1;
		  int timestamp=10000;
         
        BufferedReader br = null;
        
        
        String finalpath = filepath.replace("-", "/");
      
        
         
 		try {
  
 			String sCurrentLine;
  
 			br = new BufferedReader(new FileReader(finalpath));
  
 			while ((sCurrentLine = br.readLine()) != null) {
 				
 				 Put p = new Put(Bytes.toBytes("row"+count),timestamp);
 				
 				if(sCurrentLine.equals(""))
 				{
 					continue;
 				}
 				
 				String[] array = sCurrentLine.split("\t");
 				latitude = array[0];
 				longitude=array[1];
 				Date=array[2];
 				x=array[3];
 				y=array[4];
 				z=array[5];
 				String h=array[6];
 				String t=array[7];
 				
 				 String cf1 = columnFamilies.split(":")[0];
 				 String cf2 = columnFamilies.split(":")[1];
 				 String cf3 = columnFamilies.split(":")[2];
 				 String cf4=columnFamilies.split(":")[3];
 				 String cf5=columnFamilies.split(":")[4];

 				
 				
 				 p.add(Bytes.toBytes(cf1), Bytes.toBytes("latitude"),Bytes.toBytes(latitude));
 				 p.add(Bytes.toBytes(cf1), Bytes.toBytes("longitude"),Bytes.toBytes(longitude));

 				 //p.add(Bytes.toBytes("long), Bytes.toBytes("col"+(count+1)),Bytes.toBytes(longitude));
 				 
 				 p.add(Bytes.toBytes(cf2), Bytes.toBytes("D"),Bytes.toBytes(Date));
 				 
 				 p.add(Bytes.toBytes(cf3), Bytes.toBytes("x"),Bytes.toBytes(x));
 				 
 				p.add(Bytes.toBytes(cf3), Bytes.toBytes("y"),Bytes.toBytes(y));
 		

 				p.add(Bytes.toBytes(cf3), Bytes.toBytes("z"),Bytes.toBytes(z));

 				p.add(Bytes.toBytes(cf4), Bytes.toBytes("T"),Bytes.toBytes(t));

 				p.add(Bytes.toBytes(cf5), Bytes.toBytes("h"),Bytes.toBytes(h));

 				
 			      table.put(p);
 			      
 			      count=count+1;
 			      timestamp=timestamp+1;
 				
 			}
  
 		} catch (IOException e) {
 			e.printStackTrace();
 			line = e.toString();
 		} finally {
 			try {
 				if (br != null)br.close();
 			} catch (IOException ex) {
 				ex.printStackTrace();
 				line = ex.toString();
 			}
 		}
		
		return line;
    }
    
    
    
    @GET
    @Produces("application/x-javascript")
    @Path("hbaseRetrieveAll/{tablename:.+}")
    public String hbaseRetrieveAll(@QueryParam("callback") String callback,@PathParam("tablename") String tablename) {
        String line="";
    	
        Configuration config = HBaseConfiguration.create();		 
		config.clear();
         config.set("hbase.zookeeper.quorum", "134.193.136.147");
         config.set("hbase.zookeeper.property.clientPort","2181");
         config.set("hbase.master", "134.193.136.147:60010");
         
      
 		try{
             HTable table = new HTable(config, tablename);
             Scan s = new Scan();
             ResultScanner ss = table.getScanner(s);
             for(Result r:ss){
                 for(KeyValue kv : r.raw()){
                	 line = line+ new String(kv.getRow()) + " ";
                	 line = line + new String(kv.getFamily()) + ":";
                	 line = line + new String(kv.getQualifier()) + " ";
                	 line = line + kv.getTimestamp() + " ";
                	 line = line + new String(kv.getValue());
                	 line = line + "/n";
                   /* System.out.print(new String(kv.getRow()) + " ");
                    System.out.print(new String(kv.getFamily()) + ":");
                    System.out.print(new String(kv.getQualifier()) + " ");
                    System.out.print(kv.getTimestamp() + " ");
                    System.out.println(new String(kv.getValue()));*/
                 }
             }
        } catch (IOException e){
            e.printStackTrace();
        }
 		
		return line;
    }
    
    
    
    @GET
    @Produces("application/x-javascript")
    @Path("hbasedeletel/{tablename:.+}")
    public String hbasedeletel(@QueryParam("callback") String callback,@PathParam("tablename") String tablename) {
        String line="delete success";
    	
        Configuration config = HBaseConfiguration.create();		 
		config.clear();
         config.set("hbase.zookeeper.quorum", "134.193.136.147");
         config.set("hbase.zookeeper.property.clientPort","2181");
         config.set("hbase.master", "134.193.136.147:60010");
         
      
       
         try {
        	  HBaseAdmin admin = new HBaseAdmin(config);
			admin.disableTable(tablename);
			admin.deleteTable(tablename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			line = e.toString();
		}
         
		return line;
    }


    /**
     * PUT method for updating or creating an instance of HDFRestWSJar
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}