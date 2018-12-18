package com.luo.myhotupdate.controller;

import com.luo.myhotupdate.entity.UpdateInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RestController
public class VersionController {

  private static String path = "C:\\ServerConfig\\serverConfig.properties";
  public static Map<String,Object> map = new HashMap<>();
  private static String versionCodeStr = "versionCode";
  private static  String versionNameStr ="versionName";
  private static  String needAll ="needAll";
  private static  String between= "between";
  private static  String needRepair= "needRepair";
  /*needAll=5
    between=6
    needRepair=7*/

    public VersionController() {
        if (map.size() == 0) {
            Properties p = new Properties();
            InputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(path));
                p.load(in);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            Iterator<Map.Entry<Object, Object>> it = p.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Object, Object> entry = it.next();
               String keyStr =  String.valueOf(entry.getKey());
               String valueStr = String.valueOf(entry.getValue());
                map.put(keyStr, valueStr);
                System.out.println("map 添加键值对  key=" + keyStr);
                System.out.println("map 添加键值对  value=" +valueStr);
            }
        }
    }
    @RequestMapping(value = "getInfo",method = RequestMethod.GET)
    public UpdateInfo getInfo(@RequestParam (value = "" ,required = false) int versionCode,
                           @RequestParam(value = "", required = false) float versionName) {
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.setAppName("爱小说");
        updateInfo.setDescription("app更新");
        int  verCode = Integer.valueOf((String)map.get(versionCodeStr));
        float  verName = Float.valueOf((String)map.get(versionCodeStr));
        int  verneedAll = Integer.valueOf((String)map.get(needAll));
        int  verbetween = Integer.valueOf((String)map.get(between));
        int  verneedRepair = Integer.valueOf((String)map.get(needRepair));
        if (verCode > versionCode){
            updateInfo.setVersionCode(verCode);
            if (versionCode <= verneedAll){
                updateInfo.setDownUrl("Weibo5.5.apk");
            }else if (versionCode == verbetween){
                updateInfo.setDownUrl("weibo.patch");
            }else  if (versionCode >= verneedRepair){
                updateInfo.setDownUrl("Weibo5.6.apk");
            }
        }
        return updateInfo;
    }


    @RequestMapping(value = "getApk")
    public void getApk(String path, HttpServletResponse response, HttpServletRequest request){

            response.setContentType("text/html;charset=utf-8");
          String basePath = request.getServletContext().getRealPath("/")+"WEB-INF\\classes\\static\\"+path;
            try {
                request.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            java.io.BufferedInputStream bis = null;
            java.io.BufferedOutputStream bos = null;

            String downLoadPath = basePath;  //注意不同系统的分隔符
            //	String downLoadPath =filePath.replaceAll("/", "\\\\\\\\");   //replace replaceAll区别 *****
            System.out.println(downLoadPath);

            try {
                long fileLength = new File(downLoadPath).length();
                response.setContentType("application/x-msdownload;");
                response.setHeader("Content-disposition", "attachment; filename=" + new String(path.getBytes("utf-8"), "ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(fileLength));
                bis = new BufferedInputStream(new FileInputStream(downLoadPath));
                bos = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null)
                    try {
                        bis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                if (bos != null)
                    try {
                        bos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }




    };


}


