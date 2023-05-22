package Utility;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Utility {

	FileInputStream fis = null;
	public Properties prop = null;
	
	//read Data Property File
	public Properties readPropertyFile() {

		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resource/data.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (Exception fnfe) {
			fnfe.printStackTrace();
		}
		return prop;
	}

	public Properties readFromTestPropertyFile() {
		Properties testProp = new Properties();

		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resource/testdata.properties");
			testProp.load(fis);
			fis.close();
		} catch (Exception fnfe) {
			fnfe.printStackTrace();
		}
		return testProp;
	}

	public Properties writeToTestPropertyFile(String key, String value) throws Exception {
		Properties testProp = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resource/testdata.properties");
		testProp.load(fis);
		Map<String, String> map = new HashMap<String, String>();
		for (Map.Entry<Object, Object> entry : testProp.entrySet()) {
			map.put(entry.getKey().toString(), entry.getValue().toString());
		}
		map.put(key, value);

		try {
			testProp.putAll(map);
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.dir") + "/src/main/java/resource/testdata.properties");
			testProp.store(fos, "Test property file updated");
			fos.close();
		} catch (Exception fnfe) {
			fnfe.printStackTrace();

		}
		return testProp;
	}

	public int getScreenWidth() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		// System.out.println(width +"\t");
		return width;
	}

	public int getScreenHieght() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int hieght = (int) screenSize.getHeight();
		return hieght;
	}
	
	

}
