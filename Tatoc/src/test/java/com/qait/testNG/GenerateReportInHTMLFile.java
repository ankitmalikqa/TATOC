package com.qait.testNG;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class GenerateReportInHTMLFile extends Reporting
{
	
public static void report() 
{int i=0,j=0,k=0,l=0;
	String str[]=new String[4];
	String str1[]=new String[4];
	String str2[]=new String[4];
	String str3[]=new String[4];
	System.out.println(success); 
	StringTokenizer st = new StringTokenizer(success,"-");  
     while (st.hasMoreTokens()) {  
         //System.out.println(st.nextToken()+"malik");
         str[i]=st.nextToken();
         i++;
}
     StringTokenizer st1 = new StringTokenizer(failure,"-");  
     while (st1.hasMoreTokens()) {  
         System.out.println(st1.nextToken());
         str2[j]=st1.nextToken();
         j++;
}
     StringTokenizer st2 = new StringTokenizer(skipped,"-");  
     while (st2.hasMoreTokens()) {  
         System.out.println(st2.nextToken());
         str2[k]=st2.nextToken();
         k++;
}
     for(int l1=0;l1<i;l1++)
     {
    	 str3[l1]=str[l1];
     }
     for(int l1=i;l1<(i+j);l1++)
     {
    	 str3[l1]=str1[l];
    	 l++;
     }
     l=0;
     for(int l1=j;l1<(i+j+k);l1++)
     {
    	 str3[l1]=str2[l];
    	 l++;
     }
     String s= "<!DOCTYPE html>\r\n" + 
     		"<html>\r\n" + 
     		"<head>\r\n" + 
     		"<meta charset=\"UTF-8\">\r\n" + 
     		"<title>Insert title here</title>\r\n" + 
     		"<style>\r\n" + 
     		"table, th, td {\r\n" + 
     		"    border: 1px solid black;\r\n" + 
     		"}\r\n" + 
     		"</style>\r\n" + 
     		"</head>\r\n" + 
     		"<body>\r\n" + 
     		" <div>\r\n" + 
     		"    <table>\r\n" + 
     		"       <tr>\r\n" + 
     		"        <th>Test</th>\r\n" + 
     		"        <th>#Passed</th>\r\n" + 
     		"        <th>#Skipped</th>\r\n" + 
     		"        <th>Failed</th>\r\n" + 
     		"        <th>Time(ms)</th>\r\n" + 
     		"        <th>Included Groups</th>\r\n" + 
     		"        <th>Excluded Groups</th>\r\n" + 
     		"       </tr>\r\n" + 
     		"       <tr>\r\n" + 
     		"         <td colspan=\"7\" ><center>Default Suite</center></td>\r\n" + 
     		"       </tr>\r\n" + 
     		"       <tr>\r\n" + 
     		"         <td>Default Set</td>\r\n" + 
     		"         <td>"+ i +"</td>\r\n" + 
     		"         <td>"+ j +"</td>\r\n" + 
     		"         <td>"+ k +"</td>\r\n" + 
     		"         <td>11385</td>\r\n" + 
     		"         <td></td>\r\n" + 
     		"         <td></td>\r\n" + 
     		"       </tr>\r\n" + 
     		"    </table>\r\n" + 
     		" </div>\r\n" + 
     		" <div style=\"margin-top:20px;\">\r\n" + 
     		"    <table>\r\n" + 
     		"       <tr>\r\n" + 
     		"        <th>Class</th>\r\n" + 
     		"        <th>Method</th>\r\n" + 
     		"        <th>Start</th>\r\n" + 
     		"        <th>Time</th>\r\n" + 
     		"       </tr>\r\n" + 
     		"       <tr>\r\n" + 
     		"         <td colspan=\"4\" ><center>Default Suite</center></td>\r\n" + 
     		"       </tr>\r\n" + 
     		"       <tr>\r\n" + 
     		"         <td colspan=\"4\" ><center>Default Test Passed</center></td>\r\n" + 
     		"       </tr>\r\n" + 
     		"       <tr>\r\n" + 
     		"         <td rowspan=\"4\">com.qait.tatoc.ADVANCEDTATOC</td>\r\n" + 
     		"         <td>"+ str[0] +"</td>\r\n" + 
     		"         <td>0</td>\r\n" + 
     		"         <td>0</td>\r\n" + 
     		"       </tr>\r\n" + 
     		"       <tr>\r\n" + 
     		"         \r\n" + 
     		"         <td>"+ str[1] +"</td>\r\n" + 
     		"         <td>0</td>\r\n" + 
     		"         <td>0</td>\r\n" + 
     		"       </tr>\r\n" + 
     		"       <tr>\r\n" + 
     		"         \r\n" + 
     		"         <td>"+ str[2] +"</td>\r\n" + 
     		"         <td>0</td>\r\n" + 
     		"         <td>0</td>\r\n" + 
     		"       </tr>\r\n" + 
     		"       <tr>\r\n" + 
     		"         \r\n" + 
     		"         <td>"+ str[3] +"</td>\r\n" + 
     		"         <td>0</td>\r\n" + 
     		"         <td>0</td>\r\n" + 
     		"       </tr>\r\n" + 
     		"    </table>\r\n" + 
     		" </div>\r\n" + 
     		"</body>\r\n" + 
     		"</html>";
     //System.out.println(s);
     
     try
     {
    	 BufferedWriter  writer = new BufferedWriter( new FileWriter("C:\\Users\\ankitmalik\\workspace\\Tatoc\\report.html"));
         writer.write( s);
         writer.close();
     }
     catch(Exception e) {
     System.out.println(e);}
     
}
}