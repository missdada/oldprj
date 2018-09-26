package manytag.framework.util;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBUtil {
	@SuppressWarnings("unchecked")
	public static <T extends Object> T getObject(String xml, Class<T> type) throws JAXBException, UnsupportedEncodingException {
		JAXBContext jc = JAXBContext.newInstance(type);
		ByteArrayInputStream xmlStream = new ByteArrayInputStream(xml.getBytes("utf-8"));
		T obj = (T) jc.createUnmarshaller().unmarshal(xmlStream);

		return obj;
	}

	public static <T extends Object> String getXml(T obj) throws JAXBException {
		JAXBContext jcRet = JAXBContext.newInstance(obj.getClass());

		Marshaller marshaller = jcRet.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		StringWriter sw = new StringWriter();
		marshaller.marshal(obj, sw);

		String xml = sw.toString();

		return xml;
	}
}