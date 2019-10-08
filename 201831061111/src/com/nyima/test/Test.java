package com.nyima.test;

import com.nyima.count.impl.CountMethodImpl;

import java.util.List;

/**
 * @author Chen Panwen
 * @data 2019/10/8 9:18
 */
public class Test {
	@org.junit.Test
	public void testMain() {
		//获得单词技术类对象
		CountMethodImpl method = new CountMethodImpl();
		//获得输入对象，输入文件名
		String path = "E:\\软工作业\\第四次结对编程\\input.txt";
		//用于保存各种数据
		int characters, words, lines;
		//用于保存单词集
		List<String> strings;
		characters = method.countCharacterNumber(path);
		strings = method.countWordNumber(path);
		words = strings.size();
		lines = method.countLine(path);
		System.out.println("characters "+characters);
		System.out.println("words "+words);
		System.out.println("lines "+lines);
	}
}
