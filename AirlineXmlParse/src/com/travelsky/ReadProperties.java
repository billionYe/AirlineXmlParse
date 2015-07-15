package com.travelsky;

import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

	private static Properties properties = new Properties();
	
	/**
	 * <p>
	 * 方法描述:读取config配置信息
	 * </p>
	 * 
	 * @param key
	 * @return<br><br>
	 * @author yyf
	 * @date
	 * @remark <br>
	 */
	public String getValueBykey(String key) {
		
		try {
			InputStream inputStream=getClass().getResourceAsStream("/config.propertites");
			properties.load(inputStream);
			inputStream.close();
			return properties.getProperty(key);
		} catch (Exception e1) {
			System.out.println("没有找到这个文件!");
		}
		return null;
	}
}
