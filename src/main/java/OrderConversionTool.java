import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.util.*;

public class OrderConversionTool {

    public static List<List<String>> CreateLists = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入xml文件路径 ：" );
        String XmlPath = sc.next();
        System.out.println("请输入Excel文件路径 ：" );
        String ExcelPath = sc.next();
        System.out.println("请输入要输出文件夹路径 ：" );
        String Output = sc.next();

        ArrayList<String> xmlList =  Dom4JDemo.ParsingXml(XmlPath);
        String OutputPath = Output + File.separator + xmlList.get(0) + ".xls";

        List<Map<String, String>> ExcelMaps =  ExcelData.ParsingExcel(ExcelPath);
//        ExcelMaps.forEach(System.out::println);

        matchData(xmlList,ExcelMaps);
        OutputExcel.output(CreateLists,OutputPath);

        System.out.println("文件将输出到：" + OutputPath);
        System.out.println("转换成功！");
    }


        public static void matchData(ArrayList<String> xmlList , List<Map<String, String>> ExcelMaps){
            List<String> nameList = new ArrayList<>();
            for(int k = 1 ; k < xmlList.size(); k+=3){
                String CreateName = xmlList.get(k);
                nameList.add(CreateName);
            }
//            System.out.println(nameList);
            CreateLists.add(nameList);

            for(int i = 0 ; i < ExcelMaps.size(); i++){
                Map<String, String> message = ExcelMaps.get(i);

                boolean match = true;
                String symbol = null;
                String ExcelName = null;
                List<String> messageList = new ArrayList<>();
                for(int j = 1 ; j < xmlList.size(); j+=3){
                    symbol = xmlList.get(j+1);
                    ExcelName = xmlList.get(j+2);

                    if(symbol.equals("=")){
                        if(!message.containsKey(ExcelName)){
//                            System.out.println("未找到对应项！！！");
                        }
//                        System.out.println("对应项：" + CreateName + symbol + message.get(ExcelName));
                        messageList.add(message.get(ExcelName));
                    }
//                    else if(symbol.equals("<")){
//                        System.out.println("对应项：" + CreateName + "=" + message.get(CreateName) + " 是否 " + symbol + ExcelName);
//                        int a = Integer.parseInt(message.get(CreateName));
//                        int b = Integer.parseInt(ExcelName);
//                        if(a<b){
//                            messageList.add(message.get(ExcelName));
//                        }else {
//                            match = false;
//                        }
//                    }
                    //else ..................
                }
                if(match){
                    CreateLists.add(messageList);
                }
            }
//            System.out.println(CreateLists);
        }
}
