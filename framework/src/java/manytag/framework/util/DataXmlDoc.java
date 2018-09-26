package manytag.framework.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataXmlDoc {
	protected String _sFileName = "";
	protected Document _xDoc = null;
	protected XPath xpath = null;

	public String getFileName() {
		return _sFileName;
	}

	public void setFileName(String sFileName) {
		_sFileName = sFileName;
	}

	public Document oDoc() {
		return _xDoc;
	}

	public Element oData() {
		return GetDataNode();
	}

	public DataXmlDoc() {
		Init();
	}

	public int Init() {
		return RefreshInfo();
	}

	public int RefreshInfo() {
		int nRet = 0;

		// ...

		return nRet;
	}

	public boolean Save() {
		return SaveAs(_sFileName);
	}

	public boolean SaveAs(String sFileName) {
		boolean bResult = false;

		if (_xDoc != null) {
			OutputStream outputStream = null;
			try {
				String sDir = getDirectory(sFileName);
				if (!isExist(sDir)) {
					createDirectory(sDir);
				}
				outputStream = new FileOutputStream(sFileName);
				bResult = output(_xDoc.getDocumentElement(), outputStream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return bResult;
	}

	public boolean Load(InputStream stream) {
		boolean bRet = false;
		try {
			InputStream is = stream;
			_xDoc = getDocumentBuilder().parse(is);
			XPathFactory xPathfactory = XPathFactory.newInstance();
			xpath = xPathfactory.newXPath();
			bRet = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bRet;
	}

	public boolean Load(String sFileName) {
		boolean bRet = false;
		_sFileName = sFileName;
		try {
			InputStream is = new FileInputStream(sFileName);
			_xDoc = getDocumentBuilder().parse(is);
			XPathFactory xPathfactory = XPathFactory.newInstance();
			xpath = xPathfactory.newXPath();
			bRet = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bRet;
	}

	public boolean LoadXml(String xml) {
		boolean bRet = false;
		try {
			InputStream is = String2InputStream(xml, "utf-8");
			_xDoc = getDocumentBuilder().parse(is);
			XPathFactory xPathfactory = XPathFactory.newInstance();
			xpath = xPathfactory.newXPath();
			bRet = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bRet;
	}

	public int SetInfo(String sInfo) {
		int nRet = 0;

		Node oNode = getNode("/root/info");
		if (oNode != null) {
			Element newChild = _xDoc.createElement("info");
			newChild.setNodeValue(sInfo);
			Node oParent = oNode.getParentNode();
			oParent.removeChild(oNode);
			oParent.appendChild(newChild);
			nRet = 1;
		}

		return nRet;
	}

	public int SetData(String sData) {
		int nRet = 0;

		Node oNode = getNode("/root/data");
		if (oNode != null) {
			Element newChild = _xDoc.createElement("data");
			newChild.setNodeValue(sData);
			Node oParent = oNode.getParentNode();
			oParent.removeChild(oNode);
			oParent.appendChild(newChild);
			nRet = 1;
		}

		return nRet;
	}

	public Element GetDataNode() {
		Element oNode = (Element) getNode("/root/data");

		return oNode;
	}

	public NodeList getNodeList(String xPath) {
		NodeList nodes = null;
		try {
			XPathExpression expr = xpath.compile(xPath);
			Object result = expr.evaluate(_xDoc, XPathConstants.NODESET);
			nodes = (NodeList) result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nodes;
	}

	public Node getNode(String xPath) {
		Node node = null;
		try {
			XPathExpression expr = xpath.compile(xPath);
			Object result = expr.evaluate(_xDoc, XPathConstants.NODE);
			node = (Node) result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return node;
	}

	public boolean output(Node node, OutputStream outputStream) {
		boolean bResult = false;

		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty("encoding", "utf-8");
			transformer.setOutputProperty("indent", "yes");

			DOMSource source = new DOMSource();
			source.setNode(node);
			StreamResult result = new StreamResult();
			result.setOutputStream(outputStream);

			transformer.transform(source, result);
			bResult = true;
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return bResult;
	}

	public static InputStream String2InputStream(String str, String charset) throws Exception {
		ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes(charset));
		return stream;
	}

	protected DocumentBuilder getDocumentBuilder() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = factory.newDocumentBuilder();

		return builder;
	}

	public static String getNodeXmlString(Node oNode) {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		StringWriter buffer = new StringWriter();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		try {
			transformer.transform(new DOMSource(oNode), new StreamResult(buffer));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		String s = buffer.toString();

		return s;
	}

	/**
	 * 创建指定的目录<br>
	 * 
	 * @param sDir ：目录
	 * @return 成功：true；失败：false。
	 */
	private static boolean createDirectory(String sDir) {
		boolean bResult = false;
		File destDir = new File(sDir);
		if (!destDir.exists()) {
			bResult = destDir.mkdirs();
		} else {
			bResult = true;
		}

		return bResult;
	}

	/**
	 * 判断文件或文件夹是否存在<br>
	 * 
	 * @param sFilePath ：文件或文件夹的路径
	 * @return true：存在；false：不存在
	 */
	private static boolean isExist(String sFilePath) {
		boolean bResult = false;

		File oFile = new File(sFilePath);
		bResult = oFile.exists();

		return bResult;
	}

	/**
	 * 根据文件名获取其路径<br>
	 * 
	 * @param sFileName ：文件名（要使用绝对路径）
	 * @return 此文件所在的文件绝对路径。最后一位字符为/
	 */
	private static String getDirectory(String sFileName) {
		String sDir = sFileName;
		if (sFileName != null) {
			int nIndex = sFileName.lastIndexOf('/');
			if (nIndex == -1) {
				nIndex = sFileName.lastIndexOf('\\');
			}
			if (nIndex != -1) {
				sDir = sFileName.substring(0, nIndex + 1);
			}
		}

		return sDir;
	}
}