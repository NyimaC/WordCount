package com.nyima.translate.impl;

import com.nyima.translate.ITranslateParameter;

/**
 * @author Chen Panwen
 * @data 2019/10/8 19:44
 */
public class TranslateCommandImpl implements ITranslateParameter {
	@Override
	public String returnInFile(String cmd) {
		//设置初始输入文件名
		String inFile = "null";
		StringBuffer command = new StringBuffer();
		//命令行指令
		command.append(cmd);
		//字符串长度
		int length = command.length();
		//遍历指令
		for(int i=0; i<length; i++) {
			char c = command.charAt(i);
			//如果是指令,则读出指令内容
			if (c == '-') {
				c = command.charAt(++i);
				if (c == 'i') {
					System.out.println("读取文件");
					//初始化输入文件名
					inFile = "";
					//跳过空格，读出文件名
					++i;
					c = command.charAt(++i);
					//读出文件名
					while (c != ' ') {
						inFile += c;
						if (i == length - 1) {
							return inFile;
						}
						c = command.charAt(++i);
					}
				}
			}
		}
		return inFile;
	}

	@Override
	public String returnOutFile(String cmd) {
		//设置初始输入文件名
		String outFile = "null";
		StringBuffer command = new StringBuffer();
		//命令行指令
		command.append(cmd);
		//字符串长度
		int length = command.length();
		for(int i=0; i<length; i++) {
			char c = command.charAt(i);
			//如果是指令,则读出指令内容
			if(c == '-') {
				c = command.charAt(++i);
				if(c == 'o') {
					System.out.println("写入文件");
					//初始化输出文件名
					outFile = "";
					//跳过空格，读出文件名
					++i;
					c = command.charAt(++i);
					while (c != ' ') {
						outFile += c;
						if(i == length-1){
							return outFile;
						}
						c = command.charAt(++i);
					}
				}
			}
		}
		return outFile;
	}

	@Override
	public int returnNumberM(String cmd) {
		//设置初始输入文件名
		int numberM = 0;
		StringBuffer command = new StringBuffer();
		//命令行指令
		command.append(cmd);
		//字符串长度
		int length = command.length();
		for(int i=0; i<length; i++) {
			char c = command.charAt(i);
			//如果是指令,则读出指令内容
			if(c == '-') {
				c = command.charAt(++i);
				if (c == 'm') {
					++i;
					c = command.charAt(++i);
					numberM = c;
					numberM -= '0';
					return numberM;
				}
			}
		}
		return numberM;
	}

	@Override
	public int returnNumberN(String cmd) {
		//设置初始输入文件名
		int numberN = 0;
		StringBuffer command = new StringBuffer();
		//命令行指令
		command.append(cmd);
		//字符串长度
		int length = command.length();
		for(int i=0; i<length; i++) {
			char c = command.charAt(i);
			//如果是指令,则读出指令内容
			if(c == '-') {
				c = command.charAt(++i);
				if (c == 'n') {
					++i;
					c = command.charAt(++i);
					numberN = c;
					numberN -= '0';
					return numberN;
				}
			}
		}
		return numberN;
	}
}
