package com.nyima.main;

import com.nyima.count.impl.CountMethodImpl;
import com.nyima.translate.impl.TranslateCommandImpl;
import com.nyima.wirte.WriteFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Chen Panwen
 * @data 2019/10/8 9:18
 */
public class Main {
	public static void main(String[] args) {
		//输入命令行
		Scanner scanner = new Scanner(System.in);
		String cmd = scanner.nextLine();
		//创建需要的对象
		TranslateCommandImpl translate = new TranslateCommandImpl();
		CountMethodImpl method = new CountMethodImpl();
		WriteFile writeFile = new WriteFile();
		//得到需要的参数
		String inFile = translate.returnInFile(cmd);
		String outFile = translate.returnOutFile(cmd);
		int numberM = translate.returnNumberM(cmd);
		int numberN = translate.returnNumberN(cmd);
		//用于保存各种数据
		int characters, words, lines;
		//词频Map中的长度
		int length;
		//判断有无-i, -o指令
		if("null".equals(inFile) || "null".equals(outFile)) {
			System.out.println("输入或者输出不能为空！");
			return;
		}
		//用于保存单词集
		List<String> strings;
		//定长单词集
		List<String> lengthWords;
		characters = method.countCharacterNumber(inFile);
		strings = method.countWordNumber(inFile);
		words = strings.size();
		lines = method.countLine(inFile);
		lengthWords = method.statisticsWord(strings, numberM);
		//得到词频的Map
		List<Map.Entry<String, Integer>> frequency = method.countWordsFrequency(strings);
		//保存定长词频
		List<Map.Entry<String, Integer>> neededFrequency = new ArrayList<>();
		//得到其长度，方便遍历
		length = frequency.size();
		//如果长度大于10，则把长度变成10
		if(length > 10) {
			length = 10;
		}
		//打印结果
		System.out.println("字符总数是："+characters);
		System.out.println("有效单词数是："+words);
		System.out.println("有效行数是："+lines);
		System.out.println("单词的出现频率从高到低依次是：");
		for(int i=0; i<length; i++) {
			System.out.println(frequency.get(i));
		}
		if(numberM > 0) {
			System.out.println("定长为 "+numberM+" 的单词是："+lengthWords);
		}
		//如果参数n大于Map中的大小，则打印有效个。
		if(numberN > length) {
			numberN = length;
		}
		if(numberN > 0) {
			System.out.println("出现评率最高的前"+numberN+"个单词依次是：");
			for(int i=0; i<numberN; i++) {
				System.out.println(frequency.get(i));
				neededFrequency.add(frequency.get(i));
			}
		}
		//写入文件
		writeFile.writeFile(outFile, characters, words, lines, lengthWords, neededFrequency);
	}
}

