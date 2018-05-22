package com.cab.common.framework.utils;

import freemarker.template.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/5/21.
 */
public class DocUtil {

    public static String html2doc(Map<String, Object> outData ,String id) throws Exception {
        List<String> result = new ArrayList<>();
        String templatePath = "H:\\development\\ideaworkspace\\mypro\\mypro-web\\src\\main\\webapp\\template";
        String templateName = "fiction.html";
        String outFilePath = "H:\\development\\ideaworkspace\\mypro\\mypro-web\\src\\main\\webapp\\template\\generator\\html";
        String outFileName = id + ".html"; //生成的文件名

        FileInputStream fileInputStream = generateHtml(templatePath,templateName,outFilePath, outFileName, outData);

        String outFileDoc = "H:\\development\\ideaworkspace\\mypro\\mypro-web\\src\\main\\webapp\\template\\generator\\doc\\"+id+".doc";
        OutputStream os = new FileOutputStream(outFileDoc);
        POIFSFileSystem fs = new POIFSFileSystem();
        //对应于org.apache.poi.hdf.extractor.WordDocument
        fs.createDocument(fileInputStream, "WordDocument");
        fs.writeFilesystem(os);
        os.close();
        fileInputStream.close();
        return outFileDoc;
    }


    public static FileInputStream generateHtml(String templatePath, String templateName, String outFilePath, String outFileName, Map<?, ?> outData) {
        Writer out = null;
        FileOutputStream fos = null;
        try {
            //初使化FreeMarker配置
            Version version = new Version("2.3.23");
            Configuration config = new Configuration(version);
            // 设置要解析的模板所在的目录，并加载模板文件
            config.setDirectoryForTemplateLoading(new File(templatePath));
            // 设置包装器，并将对象包装为数据模型
            config.setObjectWrapper(new DefaultObjectWrapper(version));
            // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
            // 否则会出现乱码
            Template template = config.getTemplate(templateName, "UTF-8");
            // 合并数据模型与模板
            String fullFileName = outFilePath + "\\" + outFileName;
            fos = new FileOutputStream(fullFileName);
            out = new OutputStreamWriter(fos, "UTF-8");
            template.process(outData, out);
            return new FileInputStream(new File(fullFileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (null != out){
                    out.flush();
                    out.close();
                }
                if (null != fos){
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        Map<String, Object> outData = new HashMap<String, Object>();
        outData.put("article", "dfdf");
        DocUtil.html2doc(outData,"dfdfdffd");
    }


}
