package com.nyima.translate;

/**
 * @author Chen Panwen
 * @data 2019/10/8 19:45
 */
public interface ITranslateParameter {
	/**
	 * 返回命令中的输入文件名
	 * @param cmd
	 * @return
	 */
	String returnInFile(String cmd);

	/**
	 * 返回命令中的输出文件名
	 * @param cmd
	 * @return
	 */
	String returnOutFile(String cmd);

	/**
	 * 返回命令中的参数m
	 * @param cmd
	 * @return
	 */
	int returnNumberM(String cmd);

	/**
	 * 返回命令中的参数n
	 * @param cmd
	 * @return
	 */
	int returnNumberN(String cmd);
}
