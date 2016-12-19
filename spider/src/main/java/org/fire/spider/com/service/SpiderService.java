package org.fire.spider.com.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.fire.spider.com.entity.DatasPool;
import org.fire.spider.com.thread.SpiderThread;
import org.fire.spider.com.util.ListUtils;
import org.fire.spider.com.util.RegexUtils;
import org.fire.spider.com.util.ResourcesUtils;

public class SpiderService {

	public static int num = 0;
	public static final String PATH = "D://dataMining";
	public static final String CHASET = "UTF-8";
	public static final int THREAD_MAX = 4000;
	
	public void dataMiningService(String urlPath){
		try{
			String content = ResourcesUtils.readResources(urlPath);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(content);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			List<String> list = RegexUtils.parseURL(content);
			//将挖掘出的url添加到单例
			ListUtils.addAll(DatasPool.getInstance().getDatasList(), list);
			//落地保存
			write(urlPath, content);
			execute();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void execute(){
		boolean flag = false;
		//记录下当前单例list中的条数
		int size = DatasPool.getInstance().getDatasList().size();
		num++;
		
		//线程个数是20个，超过则排队
		int merchant = DatasPool.getInstance().getDatasList().size()/THREAD_MAX;
		int module = DatasPool.getInstance().getDatasList().size()%THREAD_MAX;
		int cnt = module==0?merchant:(merchant+1);
		for(int i=0;i<cnt;i++){
			List<String> tempList = new ArrayList<String>();
			if(i!=cnt-1){
				for(int j=0;j<THREAD_MAX;j++){
					tempList.add(DatasPool.getInstance().getDatasList().get(j+i*THREAD_MAX));
				}
			}else{
				int tempModule = module;
				if(module==0) tempModule=THREAD_MAX;
				for(int j=0;j<tempModule;j++){
					tempList.add(DatasPool.getInstance().getDatasList().get(j+i*THREAD_MAX));
				}
			}
			Vector<Thread> threads = new Vector<Thread>();
			for(String url : tempList){
				SpiderThread thread = new SpiderThread(url, num);
				threads.add(thread);
				thread.start();
			}
			for(Thread thread : threads){
				try {
					thread.join();
				} catch (InterruptedException e) {
				}
			}
		}
		
		/*Vector<Thread> threads = new Vector<Thread>();
		for(String url : DatasPool.getInstance().getDatasList()){
			SpiderThread thread = new SpiderThread(url, num);
			threads.add(thread);
			thread.start();
		}
		for(Thread thread : threads){
			try {
				thread.join();
			} catch (InterruptedException e) {
			}
		}*/
		
		ListUtils.deletePart(DatasPool.getInstance().getDatasList(), size);
		if(DatasPool.getInstance().getDatasList().size()==0){
			flag = true;
		}else if(num==1){
			flag = true;
		}
		while(!flag){
			execute();
		}
	}
	
	/**
	 * 写数据
	 * @throws UnsupportedEncodingException 
	 * */
	public static void write(String urlPath, String content) throws UnsupportedEncodingException{
		if(urlPath.contains("/")){
			urlPath = "local";
		}
		if(urlPath.contains("\\")){
			urlPath = "local";
		}
		System.out.println(Thread.currentThread().getName()+" -- urlPath = "+urlPath);
		ResourcesUtils.writeResources(content.getBytes(CHASET), PATH+File.separator+urlPath+".txt");
	}
	
	public static void write(String urlPath, String content, int hierarchy) throws UnsupportedEncodingException{
		String tempPath = "";
		tempPath = "["+hierarchy+"]"+DatasPool.getNum()+"";
		write(tempPath, content);
	}
	
}
