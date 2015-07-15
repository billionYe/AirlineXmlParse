package com.travelsky;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import com.travelsky.ReadProperties;

import redis.clients.jedis.Jedis;

public class airxml {
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		ReadProperties readProperties = new ReadProperties();
		Document doc = reader.read(new File(readProperties
				.getValueBykey("filename")));
		HashMap<String, String> map = new HashMap<String, String>();
		List l1;
		List l2;
		List l3;
		List l4;
		List l5;
		Element e;
		Element e2;
		Element e3;
		Element e4;
		Element e5;
		XPath mycode = doc.createXPath("//CODE");
		XPath cn_name = doc.createXPath("//CN_NAME");
		XPath airline_type = doc.createXPath("//AIRLINE_TYPE");
		XPath en_name = doc.createXPath("//EN_NAME");
		XPath country = doc.createXPath("//COUNTRY");
		mycode.setNamespaceURIs(map);
		cn_name.setNamespaceURIs(map);
		airline_type.setNamespaceURIs(map);
		en_name.setNamespaceURIs(map);
		country.setNamespaceURIs(map);
		l1 = mycode.selectNodes(doc);
		l2 = cn_name.selectNodes(doc);
		l3 = airline_type.selectNodes(doc);
		l4 = en_name.selectNodes(doc);
		l5 = country.selectNodes(doc);
		// HashMap<String, HashMap<String, String>> airlineMap = new
		// HashMap<String, HashMap<String, String>>();
		HashMap<String, String> valueMap = new HashMap<String, String>();
		Jedis jedis = new Jedis("172.27.18.82", 6379);
		for (int i = 0; i < l1.size(); i++) {
			e = (Element) l1.get(i);
			e2 = (Element) l2.get(i);
			e3 = (Element) l3.get(i);
			e4 = (Element) l4.get(i);
			e5 = (Element) l5.get(i);
			String str = e.getText();
			String str2 = e2.getText();
			String str3 = e3.getText();
			String str4 = e4.getText();
			String str5 = e5.getText();

			valueMap.put("CN_NAME", str2);
			valueMap.put("AIRLINE_TYPE", str3);
			valueMap.put("EN_NAME", str4);
			valueMap.put("COUNTRY", str5);
			// airlineMap.put(e.getText(), valueMap);
			// System.out.println(valueMap.get("CN_NAME"));
			// System.out.println(e.getText());
			// System.out.println(e.getName() + ":" +
			// str+"     "+e2.getName()+":"+str2+"     "+e3.getName()+":"+str3+"     "+e4.getName()+":"+str4+"     "+e5.getName()+":"+str5);
			// 连接redis
			jedis.hmset(str, valueMap);
			List<String> rsmap = jedis.hmget("OJ", "CN_NAME", "AIRLINE_TYPE",
					"EN_NAME");
			System.out.println(rsmap);
		}
	}
}
