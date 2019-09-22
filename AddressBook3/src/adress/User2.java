package adress;
import java.io.*;
import java.net.*;
import org.json.*;
class User2 {
	public String getResponse(String serverUrl){
        //��JAVA����http���󣬲�����json��ʽ�Ľ��
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while((line = in.readLine()) != null){
                result.append(line);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();

}
	
	
	public String getposition(String serverUrl)
	{
		StringBuffer result =new StringBuffer();
		try 
		{
			URL url=new URL(serverUrl);
			URLConnection conn= url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while((line = in.readLine()) != null){
                result.append(line);
            }
            in.close();
		}
		catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result.toString();
	}
	
	
	public String[] webhelp(String a) throws JSONException
	{
		//String address="�����ж����������ڶ����1�ű����ж���������Ժ";
		String ak="VueoOZkzhcX7lW397bBsUlpgk1nDg6nq";
		String url="http://api.map.baidu.com/geocoding/v3/?address=" + a + "&output=json&ak=" +ak+"&callback=showLocation";
		String jason=getResponse(url);
		int lngStart = jason.indexOf("lng\":");  
        int lngEnd = jason.indexOf(",\"lat");  
        int latEnd = jason.indexOf("},\"precise");
        //System.out.println(jason);
        //if(lngStart > 0 && lngEnd > 0 && latEnd > 0)
            String lng = jason.substring(lngStart+5, lngEnd);  
            String lat = jason.substring(lngEnd+7, latEnd);  
            //System.out.println(lng);
           // System.out.println(lat);
            url="http://api.map.baidu.com/reverse_geocoding/v3/?ak="+ak+"&output=json&coordtype=wgs84ll&location="+ lat +"," +lng;
            String zason=getposition(url);
            int addStart = zason.indexOf("province\":\"");
            int addEnd = zason.indexOf("\",\"city");
            String province = zason.substring(addStart+11, addEnd);
          
          // System.out.println(zason);  
            addStart=zason.indexOf("city\":\"");
            addEnd=zason.indexOf("\",\"city_level");
            String city=zason.substring(addStart+7,addEnd);
            addStart=zason.indexOf("district\":\"");
            addEnd=zason.indexOf("\",\"town");
            String district=zason.substring(addStart+11,addEnd);
            
            String[] arr=new String[] {province,city,district};
            return arr;
	}
}