package com.ceye;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class readStringXML {

	// 设置数据存储格式Map

		private static Map<String, String> map = new HashMap<String, String>();
		public static Map parseXml(String xml) throws DocumentException {
			Map<String, String> mapEle = new HashMap<String, String>();
			SAXReader reader = new SAXReader();
			Document document = DocumentHelper.parseText(xml);
			//Document document = reader.read(filePath);// 读取xml文档
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
				
				map.put(ele.getName(), ele.getText());
			} else {
				List<Element> elementList = ele.elements();
				Iterator<Element> it = elementList.iterator();
				while (it.hasNext()) {
					printEle(it.next());
				}
			}
			return map;
		}
	
	public static void main(String[] args) {
		String file = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
				"<IMFRoot xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
				"    <SysInfo>\r\n" + 
				"        <MessageSequenceID>3</MessageSequenceID>\r\n" + 
				"        <MessageType>Operation</MessageType>\r\n" + 
				"        <ServiceType>FSS1</ServiceType>\r\n" + 
				"        <OperationMode>SYS</OperationMode>\r\n" + 
				"        <SendDateTime>2018-12-12T12:44:00.869</SendDateTime>\r\n" + 
				"        <CreateDateTime>2018-12-12T12:45:56.243</CreateDateTime>\r\n" + 
				"        <OriginalDateTime>2018-12-12T12:45:56.243</OriginalDateTime>\r\n" + 
				"        <Receiver>BBS</Receiver>\r\n" + 
				"        <Sender>IMF</Sender>\r\n" + 
				"        <Owner>IMF</Owner>\r\n" + 
				"    </SysInfo>\r\n" + 
				"    <Operation>\r\n" + 
				"        <Subscription>\r\n" + 
				"            <SubscribeResponse>\r\n" + 
				"                <SubscriptionStatus>Accept</SubscriptionStatus>\r\n" + 
				"                <SubscriptionRequestID>3</SubscriptionRequestID>\r\n" + 
				"            </SubscribeResponse>\r\n" + 
				"        </Subscription>\r\n" + 
				"    </Operation>\r\n" + 
				"</IMFRoot> ";
		Map<String , String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = readStringXML.parseXml(file);
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

	}

}
