package org.fire.spider.com.thread;

import java.io.IOException;
import java.util.List;

import org.fire.spider.com.entity.DatasPool;
import org.fire.spider.com.service.SpiderService;
import org.fire.spider.com.util.RegexUtils;
import org.fire.spider.com.util.ResourcesUtils;

public class SpiderThread extends Thread {

	private String urlPath;
	private int hierarchy;
	
	public SpiderThread(String urlPath, int hierarchy){
		this.urlPath = urlPath;
		this.hierarchy = hierarchy;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			String content = ResourcesUtils.readResources(urlPath);
			List<String> urlList = RegexUtils.parseURL(content); 
			List<String> datasList = DatasPool.getInstance().getDatasList();
			for(String url : urlList){
				if(!datasList.contains(url))
					datasList.add(url);
			}
			SpiderService.write(urlPath, content,hierarchy);
			System.out.println("层级 :"+hierarchy);
//			System.out.println(content);
			System.out.println("---------------------------------------------------------------------------");
		} catch (IOException e) {
			System.out.println(Thread.currentThread().getName()+" - is wrong urlPath = "+urlPath+" hierarchy = "+hierarchy);
		}
	}
	
}
