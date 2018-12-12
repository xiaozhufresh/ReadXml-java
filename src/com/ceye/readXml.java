package com.ceye;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class readXml {

	// 设置数据存储格式Map

	private static Map<String, String> map = new HashMap<String, String>();
	public static Map parseXml(File filePath) throws DocumentException {
		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 读取xml文档
		Element root = document.getRootElement();// 得到xml文档根节点元素，即最上层的"<xml>"
		//System.out.println("输出根节点元素的名字===" + root.getName());
		// 循环遍历出更节点下面的子节点
		List<Element> elementList = root.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			//System.err.println("1级子节点："+element.getName());
			mapEle = printEle(element);
		}
		return mapEle;
	}
	public static Map printEle(Element ele) {
		if (ele.elements().size() == 0) {
			//System.out.println(ele.getName() + ":" + ele.getText());
			map.put(ele.getName(), ele.getText());
			/*if(ele.attributes().size()==0) {
			System.out.println(ele.getName() + ":" + ele.getText());
			map.put(ele.getParent().getName(), ele.getText());}
			else {
				System.out.println(ele.getName()+"_"+ele.attribute(0).getName()+":"+ele.attribute(0).getText()+"_"+ele.getText());
			}*/
		} else {
			//System.out.println(""+ele.elements().size());
			List<Element> elementList = ele.elements();
			Iterator<Element> it = elementList.iterator();
			while (it.hasNext()) {
				printEle(it.next());
			}
		}
		return map;
	}
	public static void main(String[] args) {
		
		File file = new File("FlightInformation.xml");
		Map<String , String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = readXml.parseXml(file);
			//遍历map
			Iterator<Entry<String, String>> it = mapEleList.entrySet().iterator();
			while(it.hasNext()){
			Entry<String , String> entry = it.next();
			System.out.println(entry.getKey() +":"+entry.getValue());
			}
			System.out.println("____________________________");
			} catch (DocumentException e) {
			e.printStackTrace();
			}

			

		/*Document document = null;
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(new File("FlightInformation.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("文件读取失败！");
		}
		Element root = document.getRootElement();*/
		/*
		 * Iterator it = root.elementIterator(); while(it.hasNext()) { Element element =
		 * (Element) it.next(); Iterator attrIt = element.attributeIterator(); while
		 * (attrIt.hasNext()) { Attribute a = (Attribute) attrIt.next();
		 * System.out.println(a.getValue()); } Iterator eleIt =
		 * element.elementIterator(); while (eleIt.hasNext()) { Element e = (Element)
		 * eleIt.next(); System.out.println(e.getName() + ": " + e.getText()); Iterator
		 * eleIt1 = e.elementIterator(); while(eleIt1.hasNext()) { Element e1 =
		 * (Element) eleIt1.next(); System.out.println(e1.getName()+":"+e1.getText());
		 * Iterator eleIt2 = e1.elementIterator(); while(eleIt2.hasNext()) { Element e2
		 * = (Element) eleIt2.next(); System.out.println(e2.getName()+":"+e2.getText());
		 * Iterator eleIt3 = e2.elementIterator(); while(eleIt3.hasNext()) { Element e3
		 * = (Element) eleIt3.next(); System.out.println(e3.getName()+":"+e3.getText());
		 * }
		 * 
		 * } } } System.out.println(); }
		 */
	}

}
