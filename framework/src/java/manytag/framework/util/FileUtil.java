package manytag.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
	/**
	 * 创建指定的目录<br>
	 * 
	 * @param sDir ：目录
	 * @return 成功：true；失败：false。
	 */
	public static boolean createDirectory(String sDir) {
		boolean bResult = false;
		File destDir = new File(sDir);
		if (!destDir.exists() || !destDir.isDirectory()) {
			bResult = destDir.mkdirs();
		} else {
			bResult = true;
		}

		return bResult;
	}

	/**
	 * 复制文件夹<br>
	 * 
	 * @param sDirSrc ：源文件夹
	 * @param sDirDst ：目标文件夹
	 * @return 成功：true； 失败：false。
	 */
	public static boolean copyDirectory(String sDirSrc, String sDirDst) {
		File in = new File(sDirSrc);
		File out = new File(sDirDst);
		if (!in.exists()) {
			System.out.println(in.getAbsolutePath() + "源文件路径错误！！！");
			return false;
		} else {
			System.out.println("源文件路径" + in.getAbsolutePath());
			System.out.println("目标路径" + out.getAbsolutePath());
		}
		if (!out.exists()) {
			out.mkdirs();
		}
		File[] file = in.listFiles();
		FileInputStream fin = null;
		FileOutputStream fout = null;
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				try {
					fin = new FileInputStream(file[i]);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println("in.name=" + file[i].getName());
				try {
					fout = new FileOutputStream(new File(sDirDst + "/" + file[i].getName()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println(sDirDst);
				int c;
				byte[] b = new byte[1024 * 5];
				try {
					while ((c = fin.read(b)) != -1) {
						fout.write(b, 0, c);
						System.out.println("复制文件中！");
					}
					fin.close();
					fout.flush();
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				copyDirectory(sDirSrc + "/" + file[i].getName(), sDirDst + "/" + file[i].getName());
			}
		}

		return true;
	}

	/**
	 * 判断文件或文件夹是否存在<br>
	 * 
	 * @param sFilePath ：文件或文件夹的路径
	 * @return true：存在；false：不存在
	 */
	public static boolean isExist(String sFilePath) {
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
	public static String getDirectory(String sFileName) {
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

	/**
	 * 写文件<br>
	 * 
	 * @param sContent ：内容
	 * @param sFileName ：文件名
	 * @param charsetName ：字符集名称
	 * @return true：成功；false：失败
	 */
	public static boolean writeFile(String sContent, String sFileName, String charsetName) {
		boolean bResult = false;
		try {
			// 如果目标文件夹不存在，则先创建文件夹。
			String sDestDir = getDirectory(sFileName);
			createDirectory(sDestDir);

			FileOutputStream os = new FileOutputStream(sFileName);
			os.write(sContent.getBytes(charsetName));
			os.close();
			bResult = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bResult;
	}

	public static boolean writeFile(String sContent, FileOutputStream os, String charsetName) {
		boolean bResult = false;
		try {
			os.write(sContent.getBytes(charsetName));
			os.close();
			bResult = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bResult;
	}

	public static boolean writeFile(String sContent, String sFileName) {
		return writeFile(sContent, sFileName, "UTF-8");
	}

	public static boolean writeFile(String sContent, FileOutputStream os) {
		return writeFile(sContent, os, "UTF-8");
	}

	public static boolean writeFile(InputStream is, String sFileName) {
		boolean bResult = false;
		try {
			// 如果目标文件夹不存在，则先创建文件夹。
			String sDestDir = getDirectory(sFileName);
			createDirectory(sDestDir);

			FileOutputStream os = new FileOutputStream(sFileName);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				os.write(buf, 0, len);
			}

			os.close();
			bResult = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bResult;
	}

	/**
	 * 读文件<br>
	 * 
	 * @param sFileName
	 * @param charsetName
	 * @return
	 * @throws Exception
	 */
	public static String read(String sFileName, String charsetName) throws IOException {
		String result = null;
		FileInputStream fos = null;
		try {
			fos = new FileInputStream(sFileName);
			result = read(fos, charsetName);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}

		return result;
	}

	public static String read(InputStream fis, String charsetName) throws IOException {
		StringBuilder sb = new StringBuilder();
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(fis, charsetName);
			char[] buffer = new char[512];
			int nLen = 0;
			while ((nLen = isr.read(buffer)) > 0) {
				String str = new String(buffer, 0, nLen);
				sb.append(str);
			}
		} finally {
			if (isr != null) {
				isr.close();
			}
		}

		return sb.toString();
	}

	public static String read(String fileName) throws IOException {
		return read(fileName, "UTF-8");
	}

	public static String read(InputStream fis) throws IOException {
		return read(fis, "UTF-8");
	}

	/**
	 * 获得路径下所有的文件名绝对路径<br>
	 * 
	 * @param sPath ：路径
	 * @param oFiles ：Key:输出的文件名绝对路径, value：文件名
	 * @param bRecursive ：是否递归标记。如果false，表示不搜索子目录，反之亦然。
	 */
	public static void getFiles(String sPath, Hashtable<String, String> oFiles, boolean bRecursive) {
		File in = new File(sPath);
		if (!in.exists()) {
			System.out.println(sPath + "源文件路径错误！！！");
			return;
		}

		File[] file = in.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				String sAbsoluteFileName = file[i].getAbsolutePath();
				String sFileName = file[i].getName();
				if (!oFiles.containsKey(sAbsoluteFileName)) {
					oFiles.put(sAbsoluteFileName, sFileName);
				}
			} else {
				if (bRecursive) {
					getFiles(file[i].getAbsolutePath(), oFiles, bRecursive);
				}
			}
		}
	}

	/**
	 * 复制文件<br>
	 * 
	 * @param sFileSrc ：原文件
	 * @param sFileDest ：目标文件
	 */
	public static boolean copyFile(String sFileSrc, String sFileDest) {
		boolean bResult = false;
		try {
			int byteread = 0;
			File oldfile = new File(sFileSrc);
			if (oldfile.exists()) { // 文件存在时
				// 如果目标文件夹不存在，则先创建文件夹。
				String sDestDir = getDirectory(sFileDest);
				createDirectory(sDestDir);

				InputStream inStream = new FileInputStream(sFileSrc); // 读入原文件
				FileOutputStream fs = new FileOutputStream(sFileDest);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.flush();
				fs.close();

				bResult = true;
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}

		return bResult;
	}

	/**
	 * 删除指定的文件<br>
	 * 
	 * @param sFileName
	 * @return
	 */
	public static boolean deleteFile(String sFileName) {
		boolean bResult = false;

		try {
			File oFile = new File(sFileName);
			if (oFile.exists()) {
				bResult = oFile.delete();
			} else {
				bResult = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bResult;
	}

	/**
	 * 删除指定的文件夹<br>
	 * 
	 * @param sPath
	 * @return
	 */
	public static boolean deleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static String getURLDirectory(String sUrl) {
		String sRet = "";

		String sPattern = ".*\\:\\/\\/[^\\/].*$";
		Pattern pattern = Pattern.compile(sPattern);
		Matcher matcher = pattern.matcher(sUrl);
		if (matcher.find()) {
			sUrl = matcher.group(1);
		}

		sUrl = sUrl.substring(8);

		if (sUrl != null) {
			int nIndex = sUrl.lastIndexOf('/');
			if (nIndex == -1) {
				nIndex = sUrl.lastIndexOf('\\');
			}
			if (nIndex != -1) {
				sRet = sUrl.substring(0, nIndex + 1);
			}
		}

		return sRet;
	}

	public static long getFileSize(String fileName) {
		long size = 0;

		if (isExist(fileName)) {
			File file = new File(fileName);
			if (file.isFile()) {
				size = file.length();
			}
		}
		return size;
	}
}