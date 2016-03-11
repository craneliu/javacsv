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
	 * �˺������ڸ���CSVBeans�����ڴ˿�Դ����һ��ȱ�ݣ����ǲ��ܽ�����tabΪ�ָ�����csv,��˴˺���ר�����ڽ�tabת���ɶ���,������ԭ���ļ�
	 * 
	 * @param fromFile
	 *            ��Ҫת�����ļ�
	 * @throws Exception
	 */
	public void parseFromTabToComma(File fromFile) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(fromFile)));
		File tmpFile = new File(UUID.randomUUID().toString()); // ������ʱ�ļ���������󲻻����
		PrintWriter writer = new PrintWriter(tmpFile);
		String line = null;
		while ((line = reader.readLine()) != null) {
			String ss[] = line.split("\\t"); // ��tab�ָ�
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
		//����CsvReader�����ԣ�Ϊ�ָ�����GBK���뷽ʽ
        CsvReader r = new CsvReader(data_dir, ',',Charset.forName("GBK"));
        //��ȡ��ͷ
        r.readHeaders();
        //������ȡ��¼��ֱ������
        while (r.readRecord()) {
            //��ȡһ����¼
            System.out.println(r.getRawRecord());
            //��������ȡ������¼��ֵ
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