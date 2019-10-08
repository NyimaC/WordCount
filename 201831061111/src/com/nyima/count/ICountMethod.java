package com.nyima.count;

import java.util.List;

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
	public int countCharacterNumber(String filePath);

	/**
	 * 得到文件路径，用于计算有效行数
	 * @param filePath
	 * @return
	 */
	public int countLine(String filePath);

	/**
	 * 得到文件路径，用于计算有效单词数，并返回单词集合
	 * @param filePath
	 * @return
	 */
	public List<String> countWordNumber(String filePath);

	/**
	 * 判断是否为字母
	 * @param c
	 * @return
	 */
	public boolean judgeCharacter(int c);

	/**
	 * 判断是否为分隔符
	 * @param c
	 * @return
	 */
	public boolean judgeBreak(int c);
}
