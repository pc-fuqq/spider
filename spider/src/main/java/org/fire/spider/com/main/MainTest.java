package org.fire.spider.com.main;

import java.io.IOException;

import org.fire.spider.com.service.SpiderService;

public class MainTest {

	private static final String CHARSET = "UTF-8";
	
	public static void main(String[] args) throws IOException {
		
//		String urlPath = "http://www.baidu.com";
		//首页
//		String urlPath = "http://www.sh.10086.cn/sh/wsyyt/x2013/100000000115.jsp?WT.srch=1WT.mc_id=SE160706_baidu_4GTC";
		//明细
//		String urlPath = "http://www.sh.10086.cn/sh/wsyyt/busi/2002_14.jsp";
		String urlPath = "http://www.sh.10086.cn/sh/service/beload2014/2014/07/2014-07-119029.html";
		SpiderService service = new SpiderService();
		service.dataMiningService(urlPath);
		
	}
	
}
