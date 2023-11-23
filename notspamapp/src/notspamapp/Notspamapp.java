/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notspamapp;

import com.mysql.jdbc.Connection;
import isp.ispBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.CustomException;
import utility.DBConnect;

/**
 *
 * @author rakeshv
 */
public class Notspamapp {

    private static int MYTHREADS = 0;
    public static Object object = new Object();

    /**
     * @param args the command line arguments
     */
    public static final int getInstanceAtATime() throws Exception {
        int defaultInstance = 10;
        try {
            String path = System.getProperty("user.dir");
            path = path + "\\InstanceAtATime.txt";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                return defaultInstance;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String fileContent = bufferedReader.readLine();
            if (fileContent.trim().length() == 0) {
                return defaultInstance;
            }
            defaultInstance = Integer.parseInt(fileContent);
        } catch (Exception e) {
            throw new Exception("Instance File Not Availabel");
        }
        return defaultInstance;
    }
    
    public static void main(String[] args) {
        WarmUp warmUp = null;
        try {
            while (true) {
                int activeCount = WarmUp.getActiveCount();
                System.out.println("Thread Count " + MYTHREADS);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //get current date time with Date()
                Date date = new Date();
                System.out.println(dateFormat.format(date));
                if (MYTHREADS < getInstanceAtATime()) {
                    //warmUp = new WarmUp(); 
                    //warmUp.startWarming();                    
                    new Thread(new Runnable() {
                        public void run() {
                            synchronized (object) {
                                MYTHREADS++;
                            }
                            try {
                                new WarmUp().startWarming();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            } finally {
                                MYTHREADS--;
                            }
                        }
                    }).start();
                    Thread.sleep(3000);
                } else {
                    Thread.sleep(3000);
                    WarmUp.decrementActiveCount();
                    System.out.println("--------------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* 
     public void startExecution(int id) {
     try {

     ispBean bean = new ispBean();
     bean.setEmailUsername("23victoria@att.net");
     bean.setEmailPassword("23victoria23");
     bean.setProxyIP("192.161.6.10");
     bean.setProxyPort("3129");
     bean.setEmailSubject("Suggests");
     bean.setBodyDomain("aviationwings.org");
     Att att = new Att();
     att.setBean(bean);
     att.startProcess();
     } catch (Exception e) {
     e.printStackTrace();
     }
     }
     */
}
