package org.fire.spider.com.entity;

import java.util.ArrayList;
import java.util.List;

public class DatasPool {

	public static int num = 0;
	private List<String> datasList = new ArrayList<String>();
	private static DatasPool datasPool = new DatasPool();
	private DatasPool(){}
	
	public static DatasPool getInstance(){
		return datasPool;
	}
	
	public synchronized void setDatasList(String data){
		if(!datasList.contains(data))
			datasList.add(data);
	}
	
	public List<String> getDatasList(){
		return datasList;
	}
	
	public synchronized static int getNum(){
		return num++;
	}
	
}
