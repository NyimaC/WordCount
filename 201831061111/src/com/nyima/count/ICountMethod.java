package com.nyima.count;

import java.util.List;
import java.util.Map;

/**
 * @author Chen Panwen
 * @data 2019/10/8 9:01
 */
public interface ICountMethod {

	/**
	 * 得到文件路径，用于计算字符数
	 * @param filePath
	 * @return
	 */
	int countCharacterNumber(String filePath);

	/**
	 * 得到文件路径，用于计算有效行数
	 * @param filePath
	 * @return
	 */
	int countLine(String filePath);

	/**
	 * 得到文件路径，用于计算有效单词数，并返回单词集合
	 * @param filePath
	 * @return
	 */
	List<String> countWordNumber(String filePath);

	/**
	 * 判断是否为字母
	 * @param c
	 * @return
	 */
	boolean judgeCharacter(int c);

	/**
	 * 判断是否为分隔符
	 * @param c
	 * @return
	 */
	boolean judgeBreak(int c);

	/**
	 * 用于统计指定长度单词的词频
	 * @param strings
	 * @param length
	 * @return
	 */
	List<String> statisticsWord(List<String> strings, int length);

	/**
	 * 用于统计单词出现的频率
	 * @param wordList
	 * @return
	 */
	List<Map.Entry<String,Integer>> countWordsFrequency(List<String> wordList);
}
