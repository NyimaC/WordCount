package com.nyima.test;

import com.nyima.count.impl.CountMethodImpl;
import com.nyima.translate.impl.TranslateCommandImpl;
import java.util.List;
import java.util.Scanner;

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
		String path = "E:\\软工作业\\第四次结对编程\\text.txt";
		//用于保存各种数据
		int characters, words, lines;
		//用于保存单词集
		List<String> strings;
		//定长单词集
		List<String> lengthWords;
		characters = method.countCharacterNumber(path);
		strings = method.countWordNumber(path);
		words = strings.size();
		lines = method.countLine(path);
		//需要统计长度为6的单词的词频
		lengthWords = method.statisticsWord(strings, 6);
		System.out.println("characters "+characters);
		System.out.println("words "+words);
		System.out.println("lines "+lines);
		System.out.println("wordList"+strings);
		System.out.println("Fixed length word frequency "+lengthWords.size());
		System.out.println("Fixed length words are "+lengthWords);
	}

	@org.junit.Test
	public void getCommandTest() {
		//输入文件名
		String inFile = "null";
		String outFile = "null";
		//保存数字
		int numberM = 0;
		int numberN = 0;
		StringBuffer command = new StringBuffer();
		//命令行指令
		command.append("wordCount.exe -m 5 -n 3");
		//字符串长度
		int length = command.length();
		//遍历指令
		for(int i=0; i<length; i++) {
			char c = command.charAt(i);
			//如果是指令,则读出指令内容
			if(c == '-') {
				c = command.charAt(++i);
				if(c == 'i') {
					System.out.println("读取文件");
					//初始化输入文件名
					inFile = "";
					//跳过空格，读出文件名
					++i;
					c = command.charAt(++i);
					//读出文件名
					while(c != ' ') {
						inFile += c;
						if(i == length-1){
							break;
						}
						c = command.charAt(++i);
					}
					System.out.println(inFile);
				}else if(c == 'o') {
					System.out.println("读取文件");
					//初始化输出文件名
					outFile = "";
					//跳过空格，读出文件名
					++i;
					c = command.charAt(++i);
					while (c != ' ') {
						outFile += c;
						if(i == length-1){
							break;
						}
						c = command.charAt(++i);
					}
					System.out.println(outFile);
				}else if (c == 'm') {
					++i;
					c = command.charAt(++i);
					numberM = c;
					numberM -= '0';
					System.out.println(numberM);
				}else if (c == 'n') {
					++i;
					c = command.charAt(++i);
					numberN = c;
					numberN -= '0';
					System.out.println(numberN);
				}
			}
		}
	}

	@org.junit.Test
	public void testMethod() {
		TranslateCommandImpl translate = new TranslateCommandImpl();
		String cmd = "wordCount.exe -i E:\\软工作业\\第四次结对编程\\text.txt -m 6 -n 3 -o E:\\软工作业\\第四次结对编程\\output.txt";
		String inFile = translate.returnInFile(cmd);
		String outFile = translate.returnOutFile(cmd);
		int m = translate.returnNumberM(cmd);
		int n = translate.returnNumberN(cmd);
		System.out.println(inFile);
		System.out.println(outFile);
		System.out.println(m);
		System.out.println(n);
	}

	/**
	 * 测试所有项目集成
	 */
	@org.junit.Test
	public void testAll() {
		//输入命令行
		Scanner scanner = new Scanner(System.in);
		String cmd = scanner.nextLine();
		//创建需要的对象
		TranslateCommandImpl translate = new TranslateCommandImpl();
		CountMethodImpl method = new CountMethodImpl();
		//得到需要的参数
		String inFile = translate.returnInFile(cmd);
		String outFile = translate.returnOutFile(cmd);
		int numberM = translate.returnNumberM(cmd);
		int numberN = translate.returnNumberN(cmd);
		//用于保存各种数据
		int characters, words, lines;
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
		//需要统计长度为6的单词的词频
		lengthWords = method.statisticsWord(strings, numberM);
		System.out.println(characters);
		System.out.println(words);
		System.out.println(lines);
		System.out.println(lengthWords);
	}
}
