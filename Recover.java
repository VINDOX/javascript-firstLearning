/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unipharma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Comptoir
 */
public class Recover {

    String connectionURL;
    Connection conn;
    Statement st;
final String csv_SDC_DATA_HEADER="GENERAL_COUNTER,RT_COUNTER,RTYPE,TTYPE,MRC,TIN,RNUM"
                        + ",TAXE_RATE_A,TAXE_RATE_B,TAXE_RATE_C,TAXE_RATE_D,"
                        + "TOTAL_A,TOTAL_B,TOTAL_C,TOTAL_D,"
                        + "TAXE_A,TAXE_B,TAXE_C,TAXE_D,CLIENTSTIN,DATE_TIME_SDC,"
                        + "RECEIPT_SIGNATURE,EJ,ISAHA,DATE_TIME_CIS,INTERNAL_DATA\n";

SimpleDateFormat formatterSdcDataRecovery = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
 SimpleDateFormat formatterSdcDataBackup = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");; 
    public Recover(String server,String dataBase) {
         connectionURL = "jdbc:derby://" + server + ":1527/" + dataBase;
        System.out.println(connectionURL);
        connecting();
        
        
        kururaData();
    }
    
    void kururaData(){
        String hera="2021-01-20 00:00:00";
        String geza="2021-01-20 23:59:59.999";
        int minInvoice=0;
        int minRefund=0;
        int maxInvoice=0;
        int maxRefund=0;
        try{
            String sql="select max(id_invoice),min(id_invoice) from app.invoice where " +
"APP.INVOICE.HEURE>='"+hera+"' and APP.INVOICE.HEURE<='"+geza+"' ";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            minInvoice=rs.getInt(2);
            maxInvoice=rs.getInt(1);
        }rs.close();
         sql="select max(id_refund),min(id_refund) from app.refund where " +
"APP.refund.HEURE>='"+hera+"' and APP.refund.HEURE<='"+geza+"' ";
             rs = st.executeQuery(sql);
            while(rs.next()){
            minRefund=rs.getInt(2);
            maxRefund=rs.getInt(1);
        }rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"failed fetch minmax "+e);
        }
        
        System.out.println(minInvoice+" min max"+maxInvoice);
        String day="200121";
        korafileInvoice(day,minInvoice,maxInvoice);
        korafileRefund(day,minRefund,maxRefund);
        
    }
    void korafileRefund(String day,int min,int max){          
StringBuilder sb = new StringBuilder();
        sb.append(csv_SDC_DATA_HEADER);
        day=day+"_RECOVER_CIS_REFUND";
       
        String rtype="N";
String ttype="R";
String table="refund";
            String sql="select id_n,TIN,TOTAL_A,TOTAL_B,TOTAL_C,TOTAL_D,TAXE_A,TAXE_B,TAXE_C,TAXE_D," +
"EXTERNAL_DATA,MRC,EXPORT,app.refund.heure,ID_EXTERNAL from app."+table+",APP.RRA_N where " +
"APP.refund.ID_refund=APP.RRA_N.ID_EXTERNAL  AND " +
"APP.refund.ID_refund>="+min+" and APP.refund.ID_refund<="+max+" and num_client is not null";
            try{
            System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
            sb = result(rs,sb, rtype, ttype);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"failed fetch minmax "+e);
        }
        if(backupFileSdcData(day, sb)){
            JOptionPane.showMessageDialog(null,"REFUND RECOVERED SUCCESSFULLY");
        } else {
             JOptionPane.showMessageDialog(null,"REFUND RECOVER FAIL");
        }
        
    }
    void korafileInvoice(String day,int min,int max){
              
StringBuilder sb = new StringBuilder();
       sb.append(csv_SDC_DATA_HEADER);
        day=day+"_RECOVER_CIS_INVOICE";
        try{
            
String rtype="N";
String ttype="S";
String table="invoice";
            String sql="select id_n,TIN,TOTAL_A,TOTAL_B,TOTAL_C,TOTAL_D,TAXE_A,TAXE_B,TAXE_C,TAXE_D," +
"EXTERNAL_DATA,MRC,EXPORT,app.invoice.heure,ID_EXTERNAL from app."+table+",APP.RRA_N where " +
"APP.INVOICE.ID_INVOICE=APP.RRA_N.ID_EXTERNAL  AND " +
"APP.INVOICE.ID_INVOICE>="+min+" and APP.INVOICE.ID_INVOICE<="+max+" and num_client is not null";
            
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            sb = result(rs,sb, rtype, ttype);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"failed fetch minmax "+e);
        }
        if(backupFileSdcData(day, sb)){
            JOptionPane.showMessageDialog(null,"INVOICE RECOVERED SUCCESSFULLY");
        } else {
             JOptionPane.showMessageDialog(null,"INVOICE RECOVER FAIL");
        }
        
    }
    
    boolean backupFileSdcData(String day, StringBuilder builder){
        String dirName = "SDC_DATA";
        makeFolder(dirName);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(dirName+"/DATA_"+day.replaceAll("-", "")+".csv"));
            
            pw.write(builder.toString());
            pw.close();
            System.out.println("done!!!!");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
        return false;
    }
     boolean makeFolder(String directoryName) {
        try {
            File directory = new File(String.valueOf(directoryName));
            if (!directory.exists()) {
                directory.mkdir();
            }
            return true;
        } catch (Throwable t) {
            return false;
        }
    }
    private String cleanEJ(String ej) {
        if(ej==null){
            return "";
        }
        ej = ej.replaceAll("\\R", " ");
        if (ej.contains(",") || ej.contains("\"") 
                || ej.contains("'")) {
            ej = ej.replaceAll("\"", "\"\"");
            ej = "\"" + ej + "\"";
            ej = ej.replaceAll(",","");
        }
        return ej;
    }
    void connecting(){
        
        try {
            conn=DriverManager.getConnection(connectionURL);
            st=conn.createStatement();
            System.out.println(" 1  connection etablie TO  " + connectionURL);
        }catch(SQLException ex){
            System.err.println("SQLException "+ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String server="172.16.0.17";
        String dataBase="speranza";
      
        
        new Recover(server,dataBase);
        
    }

    private StringBuilder result(ResultSet rs, StringBuilder sb,String rtype,String ttype) throws SQLException {
        
        while (rs.next()) {
String ext = rs.getString("EXTERNAL_DATA");
//SDC008000095,34261,34583,NS,20/01/2021 08:16:52,35CMPXSEIDPPEOVX,NRFGCISANUV5MSSCZ4WBPZWFMQ
System.out.println(rs.getInt("ID_EXTERNAL")+"ext "+ext);
String sp[]=ext.split(",");
String sdc=sp[0];
int tcount=Integer.parseInt(sp[1]);
int gc=Integer.parseInt(sp[2]);
String isaha=sp[4];
try{
        Date dIsaha = formatterSdcDataRecovery.parse(isaha);
        isaha= formatterSdcDataBackup.format(dIsaha);
        }catch(ParseException ex){
            
        }
String signature=sp[5];
String internal_data=sp[6];

                sb.append(""+gc
                        + ","+tcount+","
                        + ""+rtype+","
                        + ""+ttype+","
                        + ""+rs.getString("MRC")+","
                        + "101587911,"
                        + ""+rs.getInt("id_n")+","
                                 + "0.0,"
                                 + "18,"
                                 + "0.0,"
                                 + "0.0,"
                                 + ""+rs.getDouble("TOTAL_A")+","
                                 + ""+rs.getDouble("TOTAL_B")+","
                                 + ""+rs.getDouble("TOTAL_C")+","
                                 + ""+rs.getDouble("TOTAL_D")+","
                                 + ""+rs.getDouble("TAXE_A")+","
                                 + ""+rs.getDouble("TAXE_B")+","
                                 + ""+rs.getDouble("TAXE_C")+","
                                 + ""+rs.getDouble("TAXE_D")+","
                                 + ""+rs.getString("tin")+","
                                 + ""+rs.getString("heure")+","
                                 + ""+signature+","
                                 + ""+cleanEJ(rs.getString("EXPORT"))+","
                                 + ""+isaha+","
                                 + ""+rs.getString("heure")+","
                                 + ""+internal_data+""
                        + "\n");
                
            }
            rs.close();
        return sb;
    }
    
    
}
