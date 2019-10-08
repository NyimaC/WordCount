package com.nyima.count.impl;

import com.nyima.count.ICountMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Panwen
 * @data 2019/10/8 9:01
 */
public class CountMethodImpl implements ICountMethod {
	@Override
	public int countCharacterNumber(String filePath) {
		StringBuffer buffer = new StringBuffer();
		try {
			int judge = -1;
			InputStream in = new FileInputStream(filePath);
			//判断是否读到末尾，末尾标识为-1
			while((judge=in.read())!=-1) {
				buffer.append((char)judge);
			}
			return buffer.length();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			return buffer.length();
		}
	}

	@Override
	public int countLine(String filePath) {
		//用于记录行数
		int lineNumber = 0;
		try {
			File file = new File(filePath);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			//该行为空行
			java.lang.String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if(!"".equals(line)) {
					lineNumber++;
				}
			}
			return lineNumber;
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			return lineNumber;
		}
	}

	@Override
	public List<String> countWordNumber(String filePath) {
		//保存满足要求的单词
		List<String> strings = new ArrayList<>();
		//先读一个单词，再判断他是否满足要求：即：单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
		try{
			InputStream in = new FileInputStream(filePath);
			//空格
			int space = 32;
			//回车
			int enter = 13;
			//存放一个读到的字符
			int judge;
			//用于记录该单词是否满足要求
			StringBuffer tempWord = new StringBuffer();
			while(true) {
				//读到末尾跳出循环
				if((judge=in.read())==-1) {
					break;
				}
				//有空格或回车则一直读，读到为字符为止
				while(judgeBreak(judge)) {
					judge = in.read();
				}
				//如果打头的字符是字母，则获取整个字母
				if(judgeCharacter(judge)) {
					//将整个单词存入tempWord
					while(!judgeBreak(judge)) {
						//存入wordTemp
						tempWord.append((char)judge);
						//读下一个字符
						judge = in.read();
					}
					//得到整个单词来判断
					//单词长度太小，不满足要求
					if(tempWord.length()<4) {
						//清零
						tempWord.delete(0, tempWord.length());
						continue;
					}else {

						for(int i=1; i<4; i++) {
							//读到第四个字符的时候分开判断
							if(i == 3) {
								if(judgeCharacter(tempWord.charAt(i))){
									//转化成String类型存入strings集合中
									String string = tempWord.toString();
									//将单词存入字母集
									strings.add(string);
									break;
								}else {
									break;
								}
							}
							if(judgeCharacter(tempWord.charAt(i))){
								continue;
							}else {
								break;
							}
						}
						//单词清零
						tempWord.delete(0, tempWord.length());
					}
				}else {
					while(judge!=space && judge!=enter && judge!=-1) {
						judge = in.read();
					}
				}
			}
			//得到了所有单词的集合
			return strings;
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			return strings;
		}
	}

	@Override
	public boolean judgeCharacter(int c) {
		if(c>=65 && c<=90) {
			return true;
		}else if(c>=97 && c<=122) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean judgeBreak(int c) {
		if(c>=65 && c<=90) {
			return false;
		}else if(c>=97 && c<=122) {
			return false;
		}else if (c>48 && c<57) {
			return false;
		}else {
			return true;
		}
	}
}
