package com.nyima.test;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Panwen
 * @data 2019/10/7 17:12
 */
public class WordCount {
	public static void main(String[] args) {
		try {
			InputStream in = new FileInputStream("E:\\软工作业\\第四次结对编程\\text.txt");
			StringBuffer buffer = new StringBuffer();
			//判断是否读到末尾，末尾标识为-1
			int judge = -1;
			while((judge=in.read())!=-1) {
				System.out.println((char)judge);
				buffer.append((char)judge);
			}
			System.out.println(buffer.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadLine() {
		try {
			File file = new File("E:\\软工作业\\第四次结对编程\\input.txt");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			int lineNumber = 0;
			java.lang.String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if(!"".equals(line)) {
					lineNumber++;
				}
			}
			System.out.println(lineNumber);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void countWords() {
		//先读一个单词，再判断他是否满足要求：即：单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
		try{
			InputStream in = new FileInputStream("E:\\软工作业\\第四次结对编程\\input.txt");
			//空格
			int space = 32;
			//回车
			int enter = 13;
			//存放一个读到的字符
			int judge;
			//保存满足要求的单词
			List<String> strings = new ArrayList<>();
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
			System.out.println(strings);
			System.out.println(strings.size());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否为字母
	 * @param c
	 * @return
	 */
	public boolean judgeCharacter(int c) {
		if(c>=65 && c<=90) {
			return true;
		}else if(c>=97 && c<=122) {
			return true;
		}else {
			return false;
		}
	}


	/**
	 * 判断是否为分隔符
	 * @param c
	 * @return
	 */
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


