package bestway;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

/**
 * Created by cjs on 2017/4/21.
 * 参考https://my.oschina.net/zhhzhfya/blog/735050
 * JAR_ADDRESS 填入你要转换jar为maven依赖的路径
 * 执行后将在JAR_ADDRESS路径下生成一个jar_to_pom.xml
 */
public class MakePomFromJars {
    private static final String JAR_ADDRESS = "F:\\My_Training\\Hibernate\\demo2\\lib";
    
    public static void main(String[] args) throws IOException, DocumentException {
        Element dependencys = new DOMElement("dependencies");
        File dir = new File(JAR_ADDRESS);
        File file = new File(JAR_ADDRESS + "\\jar_to_pom.xml");
        if(file.exists()) {
            System.out.println(JAR_ADDRESS + "\\jar_to_pom.xml已存在,请删除后再试");
            return;
        }
        for (File jar : dir.listFiles()) {
            JarInputStream jis = new JarInputStream(new FileInputStream(jar));
            Manifest mainmanifest = jis.getManifest();
            jis.close();
            String bundleName = mainmanifest.getMainAttributes().getValue("Bundle-Name");
            String bundleVersion = mainmanifest.getMainAttributes().getValue("Bundle-Version");
            Element ele = null;
            System.out.println(jar.getName());

            StringBuffer sb = new StringBuffer(jar.getName());
            if (bundleName != null) {
                bundleName = bundleName.toLowerCase().replace(" ", "-");
                sb.append(bundleName+"\t").append(bundleVersion);
                ele = getDependices(bundleName, bundleVersion);
                System.out.println(sb.toString());
                System.out.println(ele.asXML());
            }
            if (ele == null || ele.elements().size() == 0) {
                bundleName = "";
                bundleVersion = "";
                String[] ns = jar.getName().replace(".jar", "").split("-");
                for (String s : ns) {
                    if (Character.isDigit(s.charAt(0))) {
                        bundleVersion += s + "-";
                    } else {
                        bundleName += s + "-";
                    }
                }
                if (bundleVersion.endsWith("-")) {
                    bundleVersion = bundleVersion.substring(0, bundleVersion.length() - 1);
                }
                if (bundleName.endsWith("-")) {
                    bundleName = bundleName.substring(0, bundleName.length() - 1);
                }
                ele = getDependices(bundleName, bundleVersion);
                sb.setLength(0);
                sb.append(bundleName+"\t").append(bundleVersion);
                System.out.println(sb.toString());
                System.out.println(ele.asXML());
            }
            ele = getDependices(bundleName, bundleVersion);
            if (ele.elements().size() == 0) {
                ele.add(new DOMElement("groupId").addText("not find"));
                ele.add(new DOMElement("artifactId").addText(bundleName));
                ele.add(new DOMElement("version").addText(bundleVersion));
            }
            dependencys.add(ele);
            System.out.println();
        }
        String resultDependencies = dependencys.asXML();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>groupId</groupId>\n" +
                "    <artifactId>artifactId</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "    <build>\n" +
                "        <plugins>\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-compiler-plugin</artifactId>\n" +
                "                <configuration>\n" +
                "                    <source>1.7</source>\n" +
                "                    <target>1.7</target>\n" +
                "                </configuration>\n" +
                "            </plugin>\n" +
                "        </plugins>\n" +
                "    </build>");
        stringBuffer.append(resultDependencies);
        stringBuffer.append("</project>");
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.print(formatXml(stringBuffer.toString()));
        //System.out.println(resultpom);
    }

    public static Element getDependices(String key, String ver) {
        Element dependency = new DOMElement("dependency");
        // 设置代理
        // System.setProperty("http.proxyHost", "127.0.0.1");
        // System.setProperty("http.proxyPort", "8090");
        try {
            String url = "http://search.maven.org/solrsearch/select?q=a%3A%22" + key + "%22%20AND%20v%3A%22" + ver + "%22&rows=3&wt=json";
            org.jsoup.nodes.Document doc = Jsoup.connect(url).ignoreContentType(true).timeout(30000).get();
            String elem = doc.body().text();
            JSONObject response = JSONObject.parseObject(elem).getJSONObject("response");
            if (response.containsKey("docs") && response.getJSONArray("docs").size() > 0) {
                JSONObject docJson = response.getJSONArray("docs").getJSONObject(0);
                Element groupId = new DOMElement("groupId");
                Element artifactId = new DOMElement("artifactId");
                Element version = new DOMElement("version");
                groupId.addText(docJson.getString("g"));
                artifactId.addText(docJson.getString("a"));
                version.addText(docJson.getString("v"));
                dependency.add(groupId);
                dependency.add(artifactId);
                dependency.add(version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dependency;
    }

    //格式化一整个String字符串 为整齐的xml格式
    public static String formatXml(String str) throws DocumentException, IOException {
        SAXReader reader=new SAXReader();
        StringReader in=new StringReader(str);
        Document doc=reader.read(in);
        OutputFormat formater= OutputFormat.createPrettyPrint();
        formater.setEncoding("UTF-8");
        StringWriter out=new StringWriter();
        XMLWriter writer=new XMLWriter(out,formater);
        writer.write(doc);
        writer.close();
        return out.toString();
    }
}
