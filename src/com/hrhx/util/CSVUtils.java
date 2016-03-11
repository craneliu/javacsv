package com.hrhx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.UUID;

import com.csvreader.CsvReader;

public class CSVUtils {
	/**
	 * 此函数用于辅助CSVBeans，由于此开源包有一个缺陷，就是不能解析以tab为分隔符的csv,因此此函数专门用于将tab转换成逗号,并覆盖原有文件
	 * 
	 * @param fromFile
	 *            需要转换的文件
	 * @throws Exception
	 */
	public void parseFromTabToComma(File fromFile) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(fromFile)));
		File tmpFile = new File(UUID.randomUUID().toString()); // 生成临时文件，不过最后不会存在
		PrintWriter writer = new PrintWriter(tmpFile);
		String line = null;
		while ((line = reader.readLine()) != null) {
			String ss[] = line.split("\\t"); // 以tab分隔
			for (int i = 0; i < ss.length; i++) {
				writer.write(ss[i]);
				if (i < (ss.length - 1)) {
					writer.write(",");
				}
			}
			writer.write("\n");
		}
		writer.close();
		reader.close();
		if (fromFile.exists()) {
			fromFile.delete();
			tmpFile.renameTo(fromFile);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String data_dir = "data/Meteologica_SSPSS-Aikangjinghe_201601011500_weather.csv";
		//生成CsvReader对象，以，为分隔符，GBK编码方式
        CsvReader r = new CsvReader(data_dir, ',',Charset.forName("GBK"));
        //读取表头
        r.readHeaders();
        //逐条读取记录，直至读完
        while (r.readRecord()) {
            //读取一条记录
            System.out.println(r.getRawRecord());
            //按列名读取这条记录的值
            System.out.println(r.get(0));
            System.out.println(r.get(1));
            System.out.println(r.get(2));
            System.out.println(r.get(3));
            System.out.println(r.get(4));
            System.out.println(r.get(5));
            System.out.println(r.get(6));
            System.out.println(r.get(7));
            System.out.println(r.get(50)+"================NONE=================");
        }
        r.close();
	}
}