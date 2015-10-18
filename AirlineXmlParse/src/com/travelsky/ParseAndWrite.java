package com.travelsky;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class ParseAndWrite {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws DocumentException,
			IOException {
		SAXReader reader = new SAXReader();
		ReadProperties readProperties = new ReadProperties();
		Document doc = reader.read(new File(readProperties
				.getValueBykey("filename")));
		XPath mycode = doc.createXPath("//CODE");
		XPath cn_name = doc.createXPath("//CN_NAME");
		XPath airline_type = doc.createXPath("//AIRLINE_TYPE");
		XPath en_name = doc.createXPath("//EN_NAME");
		XPath country = doc.createXPath("//COUNTRY");
		List l1 = mycode.selectNodes(doc);
		List l2 = cn_name.selectNodes(doc);
		List l3 = airline_type.selectNodes(doc);
		List l4 = en_name.selectNodes(doc);
		List l5 = country.selectNodes(doc);
		FileWriter fw = null;
		fw = new FileWriter("HSD-iPNR-H.txt");
		for (int i = 0; i < l1.size(); i++) {
			String str = ((Element) l1.get(i)).getText();
			String str2 = ((Element) l2.get(i)).getText();
			String str3 = ((Element) l3.get(i)).getText();
			String str4 = ((Element) l4.get(i)).getText();
			String str5 = ((Element) l5.get(i)).getText();
			//System.out.println(str + "," + str2 + "," + str3 + "," + str4 + ","
			//		+ str5 + "\n");
			fw.write(str + "," + str2 + "," + str3 + "," + str4 + "," + str5
					+ "\r\n");

		}
		fw.close();
	}

}
