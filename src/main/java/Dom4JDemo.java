import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.*;

/**
 * Dom4j解析xml
 */
public class Dom4JDemo {

    private static String FileName;
    private static String Format;
    private static ArrayList<String> List = new ArrayList<>();

    public static ArrayList<String> ParsingXml(String XmlPath) throws Exception {

        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.加载xml
        Document document = reader.read(new File(XmlPath));
        //3.获取根节点
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()){
            Element stu = (Element) iterator.next();
//            System.out.println("======获取ExportFileName和Format======");
            Iterator iterator1 = stu.elementIterator();
            while (iterator1.hasNext()){
                Element stuChild = (Element) iterator1.next();
                if(stuChild.getName().equals("ExportFileName")){
                    FileName = stuChild.getStringValue();
//                    System.out.println("ExportFileName：" + FileName);
                    List.add(FileName);
                }
                if(stuChild.getName().equals("Format")){
                    Format = stuChild.getStringValue().replaceAll("\\s*", "");
//                    System.out.println("Format：" + Format);
                    String s[] = Format.split(",");
                    for(int i = 0 ; i < s.length ; i++){
                        String name[] = s[i].split("\"");
//                        System.out.println(Arrays.toString(name));
                        List.add(name[1]);
                        List.add(name[2]);
                        if(name.length<4){
                            List.add(null);
                        }else {
                            List.add(name[3]);
                        }
                    }
                }
            }
        }
//        System.out.println(List);
        return List;
    }
}
