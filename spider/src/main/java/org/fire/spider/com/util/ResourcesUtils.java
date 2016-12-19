package org.fire.spider.com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class ResourcesUtils {

	/**
	 * 读取指定静态资源占位符的源代码信息
	 * @throws IOException 
	 * */
	public static String readResources(String urlPath) throws IOException{
		if(StringUtils.isEmpty(urlPath)) return null;
		URL url = new URL(urlPath);
		URLConnection conn = url.openConnection();
		conn.setUseCaches(false);
		conn.setAllowUserInteraction(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer content = new StringBuffer();
		String temp = "";
		while((temp=reader.readLine()) != null){
			content.append(temp).append("\n");
		}
		reader.close();
//		System.out.println("urlPath = "+urlPath);
//		System.out.println("content = "+content.toString());
		return content.toString();
	}
	
	/**
	 * 将挖掘出的数据写到指定路径
	 * */
	public static void writeResources(byte[] bys, String path){
		if(bys==null || StringUtils.isEmpty(path)) return;
		File file = new File((String) path.substring(0, path.lastIndexOf(File.separator)));
		if(!file.exists())
			file.mkdirs();
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(path));
			os.write(bys);
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
