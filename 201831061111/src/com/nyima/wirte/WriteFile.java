package com.nyima.wirte;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Panwen
 * @data 2019/10/8 20:08
 */
public class WriteFile {
	public void writeFile(String outFile, int characters, int words, int lines, List<String> lengthWords, List<Map.Entry<String, Integer>> neededFrequency) {
		try{
		File file = new File(outFile);
		Writer out = new FileWriter(file);
		out.write("字符总数是："+characters+"\n");
		out.write(" 有效单词数是："+words+"\n");
		out.write("有效行数是："+lines+"\n");
		if(lengthWords.size()>0) {
			out.write(" 定长为 "+lengthWords.get(0).length()+" 的单词是："+lengthWords+"\n");
		}
		if(neededFrequency.size()>0) {
			out.write("出现频率最高的前"+neededFrequency.size()+"个单词依次是："+neededFrequency+"\n");
		}
		out.close();
	}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
