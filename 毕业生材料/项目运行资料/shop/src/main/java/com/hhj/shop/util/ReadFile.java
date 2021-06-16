package com.hhj.shop.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile
{
    /**
     * Delete
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean deleteFile(String path) throws FileNotFoundException,IOException {
        try
        {
            File file = new File(path);
            if (!file.isDirectory())
            {
                file.delete();
            }else{
                String filelist[] = file.list();
                for(int i=0; i< filelist.length;i++){
                    File delfile = new File(path +"\\"+filelist[i]);
                    if(delfile.isDirectory()){
                        System.out.println("FileName:"+delfile.getName());
                        System.out.println("AbsolutePath:"+delfile.getAbsolutePath());
                        delfile.delete();
                    }else{
                        deleteFile(path+"\\"+filelist[i]);
                    }
                }
                file.delete();
            }
        }
        catch (Exception e)
        {
            System.out.println("deletFile() Exception:"+e.getMessage());
        }
        return true;
    }



    /**
     * Read
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String readFile(String path) throws FileNotFoundException,IOException{
        try
        {
            File file = new File(path);
            if (!file.isDirectory())
            {

            }else{
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++)
                {
                    File readFile = new File(path+"\\"+filelist[i]);
                    if(!readFile.isDirectory()){
                        System.out.println("read file Name:" + readFile.getName());
                        if(readFile.getName().equals("goods.xlsx")){
                            return readFile.getPath();
                        }
                        System.out.println(readFile.getPath());
                        //ExcelFile.readExl(readFile.getPath());
                        System.out.println("------------------------");
                        //System.out.println("path=" + readFile.getPath());
                        //System.out.println("absolutepath=" + readFile.getAbsolutePath());
                        //System.out.println("name=" + readFile.getName());
                    }else{
                        readFile(path+"\\"+filelist[i]);
                    }
                }

            }
        }
        catch (Exception e)
        {
            System.out.println("readFile() Exception:"+e.getMessage());
        }
        return "true";
    }

//    public static void main(String[] args)
//    {
//
//        try
//        {
//            readFile("C:/Documents and Settings/Developer/Desktop/All-01012012-05312012");
//        }
//        catch (FileNotFoundException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        //deleteFile("C:/Documents and Settings/Developer/Desktop/All-01012012-05312012/All-01012012-05312012/ok");
//
//    }
}

