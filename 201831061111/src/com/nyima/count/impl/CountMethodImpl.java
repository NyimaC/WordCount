package com.nyima.count.impl;

import com.nyima.count.ICountMethod;

import java.io.*;
import java.util.*;

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
			while ((judge = in.read()) != -1) {
				buffer.append((char) judge);
			}
			return buffer.length();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.length();
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
				if (!"".equals(line)) {
					lineNumber++;
				}
			}
			return lineNumber;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineNumber;
	}

	@Override
	public List<String> countWordNumber(String filePath) {
		//保存满足要求的单词
		List<String> strings = new ArrayList<>();
		//先读一个单词，再判断他是否满足要求：即：单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
		try {
			InputStream in = new FileInputStream(filePath);
			//空格
			int space = 32;
			//回车
			int enter = 13;
			//末尾
			int end = -1;
			//存放一个读到的字符
			int judge;
			//用于记录该单词是否满足要求
			StringBuffer tempWord = new StringBuffer();
			while (true) {
				//读到末尾跳出循环
				if ((judge = in.read()) == -1) {
					break;
				}
				//有空格或回车则一直读，读到为字符为止
				while (judgeBreak(judge)) {
					judge = in.read();
				}
				//如果打头的字符是字母，则获取整个字母
				if (judgeCharacter(judge)) {
					//将整个单词存入tempWord
					while (!judgeBreak(judge)) {
						//存入wordTemp
						tempWord.append((char) judge);
						//读下一个字符
						judge = in.read();
					}
					//得到整个单词来判断
					//单词长度太小，不满足要求
					int minLength = 4;
					if (tempWord.length() < minLength) {
						//清零
						tempWord.delete(0, tempWord.length());
						continue;
					} else {

						for (int i = 1; i < minLength; i++) {
							//读到第四个字符的时候分开判断
							if (i == minLength-1) {
								if (judgeCharacter(tempWord.charAt(i))) {
									//转化成String类型存入strings集合中
									String string = tempWord.toString();
									//将单词存入字母集
									strings.add(string);
									break;
								} else {
									break;
								}
							}
							if (judgeCharacter(tempWord.charAt(i))) {
								continue;
							} else {
								break;
							}
						}
						//单词清零
						tempWord.delete(0, tempWord.length());
					}
				} else {
					while (judge != space && judge != enter && judge != end) {
						judge = in.read();
					}
				}
			}
			//得到了所有单词的集合
			return strings;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strings;
	}

	@Override
	public boolean judgeCharacter(int c) {
		char a = 'a';
		char z = 'z';
		char A = 'A';
		char Z = 'Z';
		if (c >= a && c <= z) {
			return true;
		} else if (c >= A && c <= Z) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean judgeBreak(int c) {
		char a = 'a';
		char z = 'z';
		char A = 'A';
		char Z = 'Z';
		char one = '1';
		char nine = '9';
		if (c >= a && c <= z) {
			return false;
		} else if (c >= A && c <= Z) {
			return false;
		} else if (c >= one && c <= nine) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<String> statisticsWord(List<String> strings, int length) {
		//保存满足长度的单词
		List<String> lengthWord = new ArrayList<>();
		for (int i = 0; i < strings.size(); i++) {
			//取出集合中的单词
			String word = strings.get(i);
			//如果该单词长度满足需求，则存入lengthWord中
			if (word.length() == length) {
				lengthWord.add(word);
			}
		}
		//返回词频
		return lengthWord;
	}

	@Override
	public List<Map.Entry<String, Integer>> countWordsFrequency(List<String> wordList) {
		//map 作为中间转换记录 wordList 中单词出现的次数
		//map 中的 key 保存的是单词，value 保存的是出现的次数
		Map<String,Integer> wordMap = new HashMap<String,Integer>(20);

		//遍历 wordList 将 wordLis 中的单词放入 worMap中，做统计
		for(String word: wordList){

			//判断 wordMap 中是否有这个单词,如果有 value 值加一，
			if(wordMap.containsKey(word)){
				wordMap.put(word,wordMap.get(word).intValue() + 1);
			}
			else{ //如果没有这个单词，则把这个单词放进去
				wordMap.put(word,1);
			}
		}

		//再将 wordMap 中的单词按照词频从大到小排列
		//由于 Collections.sort 方法默认是无法给 map 排序的，
		// 所以需要用到比较器 Comparator 来自定义比较
		List<Map.Entry<String,Integer>> resultList = new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());

		//此方法完成后，将单词按照词频次序由大到小排列，但是次序相同的单词未能按字典顺序排列, 根据 value 值比较
		Collections.sort(resultList,new Comparator<Map.Entry<String,Integer>>() {

			//重写 compare 方法，来自定义比较
			@Override
			public int compare(Map.Entry<String,Integer> wordMap1 ,Map.Entry<String,Integer>  wordMap2){

				//降序排列, compareTo 方法作比较时，当 wordMap1 小于，等于，大于 wordMap2 时，返回 -1，0，1
				return (wordMap2.getValue().compareTo(wordMap1.getValue()));
			}
		});

		//将单词按照字典序排列，根据 key 值比较，
		Collections.sort(resultList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> wordMap1, Map.Entry<String, Integer> wordMap2) {
				if(wordMap1.getValue().compareTo(wordMap2.getValue()) == 0){

					//compareTo 方法比较两个字符串时，wordMap1 的 key 等于 wordMap2 的 key 返回 0
					// 前面的字符串小于后面的字符串返回正值，大于返回负值，Collections.sort 方法根据不同的返回值进行不同的排序
					return (wordMap1.getKey().compareTo(wordMap2.getKey()));
				}
				return 0;
			}
		});
		return resultList;
	}
}
