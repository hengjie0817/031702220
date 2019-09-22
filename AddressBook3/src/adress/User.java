package adress;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.json.*;
import java.io.*;
import java.util.HashMap;  
import java.util.Map;
public class User 
{
	private String num;
	private String name;
	private String phone;
	private String addr;
	private String pro="";
	private String ci="";
	private String di="";
	private String to="";
	private String els="";
	private String []jason;
	public static String jun=new String();
	private static Map<String,Object> map = new HashMap<String,Object>();
	public User(String a)
	{
		String [] arr1=a.split("!");
		num=arr1[0];
		Pattern rr=Pattern.compile("\\d");
		Matcher mm=rr.matcher(num);
		if(mm.find()) 
		{
			num=mm.group();
		}
		a=arr1[1];
		arr1=a.split(",");
		a=arr1[1];
		name=arr1[0];
		Pattern r= Pattern.compile("[0-9]{11}");
		Matcher m=r.matcher(a);
		if(m.find()) phone=m.group(0);
		arr1=a.split("[0-9]{11}");
		addr=arr1[0];
		if(arr1.length==2) addr=addr+arr1[1];
	}
	private boolean judge(String arr,String a)
	{
		String str=new String();
		str=arr;
		str=str.substring(0,str.length()-1);
		String pattern=".*"+str+".*";
		return Pattern.matches(pattern, a);
	}
	private String qie(String a)
	{
		String [] crr=new String[3];
		if(di.length()!=0)  
		{
			if(Pattern.matches(".*"+di+".*", a)) crr=a.split(di,2);
			else
			{
				String str=new String();
				str=di;
				str=str.substring(0,str.length()-1);
				crr=a.split(str,2);
			}
		}
		else 
		{
			if(Pattern.matches(".*"+ci+".*", a)) crr=a.split(ci,2);
			else
			{
				String str=new String();
				str=ci;
				str=str.substring(0,str.length()-1);
				crr=a.split(str,2);
			}
		}
		if(crr.length==2) a=crr[1];
	
		else 
		{
			if(pro.length()!=0) a="";
		}
		return a;
	}
	private void AddressSolution(String a) throws JSONException 
	{
		
		Pattern r= Pattern.compile(".*县|.*镇|.*乡|.*区|.*街道|.*路|.*巷|.*街|.*市|.*");
		Matcher m=r.matcher(a);
		String b=new String();
		if(m.find()) b=m.group(0);
		if(b.length()>20)
		{
			r= Pattern.compile(".*市");
			m=r.matcher(b);
			if(m.find()) b=m.group(0);
		}
		User2 uu=new User2();
		String[] arr=uu.webhelp(b);
		for(int i=0;i<3;i++)
		{
			if(arr[i].length()!=0&&judge(arr[i],a))
			{
				if(i==0) 
				{
					pro=arr[i];
					if(pro.equals("北京市")||pro.equals("天津市")||
							pro.equals("上海市")||pro.equals("重庆市"))
						pro=pro.substring(0,pro.length()-1);
				}
				else if(i==1) ci=arr[i];
				else di=arr[i];
			}
		}
		//System.out.println(pro+ci+di);
		if(Pattern.matches(".*"+di+".*", a)||Pattern.matches(".*"+ci+".*", a)) 
		{
			a=qie(a); 
		}
		//System.out.println(num+pro+ci+di);
		if(num.equals("1"))
		{
			Pattern rr= Pattern.compile("(.*?区|.*?街道|.*?镇|.*乡)?(.*)");
			Matcher mm=rr.matcher(a);
			if(mm.find())
			{
				for(int i=1;i<=mm.groupCount();i++)
					if(mm.group(i)!=null) 
					{
						if(i==1) to=mm.group(i);
						if(i==2) els=mm.group(i);
					}
			}
			get1();
		}
		else 
		{
			jason=new String[7];
			if(num.equals("2")) 	get2();
			else if(num.equals("3")) 
			{
				jason[0]=arr[0];
				jason[1]=arr[1];
				jason[2]=arr[2];
			}
			r= Pattern.compile("(.*?街道|.*?镇|.*乡)?(.*?路|.*?巷|.*?街)?([\\d]+?号|[\\d]+.?道)?(.*)");
			m=r.matcher(a);
			if(m.find());
			{
				for(int i=1;i<=m.groupCount();i++)
				{
					if(m.group(i)!=null) 
						jason[i+2]=m.group(i);//System.out.println(m.group(i));
					else 
						jason[i+2]="";//System.out.println("");
				}
			}
		}
		/*if(m.find())
		{
			r= Pattern.compile("(.*?省|.*?自治区|.*行政区)(.+市)?(.*?区|.*?县|.+?市)?(.*?街道|.*?镇|.*乡)?(.*?路|.*?巷|.*?街)?(.*号)(.*)");
			m=r.matcher(a);
			int i;
			if(m.find());
			for(i=1;i<=m.groupCount();i++)
			{
				if(m.group(i)!=null) System.out.println(m.group(i));
			}
		}*/
	}
	public void get1()
	{
		//System.out.println(num+pro+ci+di);
		jason=new String [5];
		if(pro.length()!=0) jason[0]=pro;//System.out.println(pro);
		else  jason[0]="";//System.out.println("");
		if(ci.length()!=0) jason[1]=ci;//System.out.println(ci);
		else  jason[1]="";//System.out.println("");
		if(di.length()!=0) jason[2]=di;//System.out.println(di);
		else  jason[2]="";//System.out.println("");
		if(to.length()!=0) jason[3]=to;//System.out.println(to);
		else  jason[3]="";//System.out.println("");
		if(els.length()!=0) jason[4]=els;//System.out.println(els);
		else  jason[4]="";//System.out.println("");
	}
	public void get2()
	{
		//System.out.println(num+pro+ci+di);
		if(pro.length()!=0) jason[0]=pro;//System.out.println(pro);
		else  jason[0]="";//System.out.println("");
		if(ci.length()!=0)  jason[1]=ci;//System.out.println(ci);
		else  jason[1]="";//System.out.println("");
		if(di.length()!=0) jason[2]=di;//System.out.println(di);
		else  jason[2]="";//System.out.println("");
	}
	public void getall() throws JSONException
	{
		map.put("姓名", name);
		//System.out.println("姓名："+name);
		map.put("手机", phone);
		//System.out.println("手机："+phone);
		AddressSolution(addr);
		map.put("地址", jason);
		JSONObject jj = new JSONObject(map);
		System.out.println(jj.toString());
		jun=jun+jj.toString()+"\n";
	}
	public static String txt2String(File file,String a){
        //StringBuilder result = new StringBuilder();
		String s=null;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                a=a+s+"\n";
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return a;
    }
	public static void txtin(String jj) throws IOException
	{
		try {
			String pathname ="2.txt";
			File writename=new File(pathname);
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			String []str=jj.split("\n");
			out.write("[");
			for(int i=0;i<str.length;i++) 
			{
				out.write(str[i]);
				if(i!=str.length-1) out.write(",\r\n");
				else out.write("]");
			}
			out.flush();
			out.close();
			} catch(Exception e){
				e.printStackTrace();
		}
	}

	public static void main(String[] args) throws JSONException, IOException
	{
		File file = new File("1.txt");//在D盘放上TXT文件
		String a=new String();
		a=txt2String(file,a);
		String[] arr=a.split("\n");
		String line;
		for(int i=0;i<arr.length;i++) 
		{
			line=arr[i];
			line=line.substring(0,line.length()-1);
			User one=new User(line);
			one.getall();
		}
		txtin(jun);
		//String path="D:/1.txt";
		//readFile(path);
	}
}
