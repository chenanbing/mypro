package com.cab.common.framework.utils;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/5/21.
 */
public class PrintUtil {
    public static void main(String[] args) throws Exception{
        PrintUtil.print("D:\\myworkspace\\mypro-parent\\mypro-web\\src\\main\\webapp\\template\\generator\\doc\\i087j.doc");
    }

    public static int print(String path) throws Exception {
        File file = new File(path); //获取选择的文件
        //构建打印请求属性集
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        //设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
        //定位默认的打印服务
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        //构造待打印的文件流
        InputStream fis = null;
        if(defaultService != null){
            try {
                DocPrintJob job = defaultService.createPrintJob(); //创建打印作业
                fis = new FileInputStream(file);
                DocAttributeSet das = new HashDocAttributeSet();
                Doc doc = new SimpleDoc(fis, flavor, das);  //指定打印内容
//                job.print(doc, pras);
                System.out.println(1);
            } catch (Exception e) {
                e.printStackTrace();
            }  finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return 1;
    }
}
