package org.fire.spider.com.util;

import java.util.List;

public class ListUtils {

	/**
	 * 将一个list无重复的添加到另一个list
	 * */
	public static void addAll(List<String> targetList, List<String> sourceList){
		if(targetList==null || sourceList==null) return;
		for(String data : sourceList){
			if(!targetList.contains(data))
				targetList.add(data);
		}
	}
	
	/**
	 * 删除指定个数前排数据
	 * */
	public static void deletePart(List<String> targetList, int num){
		if(num<1) return;
		if(targetList.size()<num) return;
		for(int i=0;i<num;i++){
			targetList.remove(0);
		}
	}
	
}
