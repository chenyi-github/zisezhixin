package com.zisezhixin.util;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.csource.fastdfs.StorageClient1;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	/**
	* <p>Title: thumbnailImage</p>
	* <p>Description: 根据图片路径生成缩略图 </p>
	* @param imagePath    原图片路径
	* @param w            缩略图宽
	* @param h            缩略图高
	* @param prevfix    生成缩略图的前缀
	* @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	*/
	public static void thumbnailImage(File imgFile, int w, int h, String dstFile, boolean force){
		if(imgFile.exists()){
			try {
				// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
	        	String types = Arrays.toString(ImageIO.getReaderFormatNames());
	        	String suffix = null;
	        	if(imgFile.getName().indexOf(".") > -1) {
	        		// 获取图片后缀
	        		suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
	        	}// 类型和图片后缀全部小写，然后判断后缀是否合法
		        if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0){
		        	return ;
		     	}
		        //获取图片信息
		        Image img = ImageIO.read(imgFile);
		      	if(!force){
		          	//根据原图与要求的缩略图比例，找到最合适的缩略图比例
		      		int width = img.getWidth(null);
		          	int height = img.getHeight(null);
		          	if((width*1.0)/w < (height*1.0)/h){
		             	if(width > w){
		             		h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
		              	}
		           	} else {
		              	if(height > h){
		                 	w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
		               	}
		           	}
		      	}
		      	BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		      	Graphics g = bi.getGraphics();
		      	g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
		       	g.dispose();
		       	imgFile.getPath();
		       	// 将图片保存在原目录并加上前缀
		       	//判断文件是否存在
                File dstFileimg =new File(dstFile);
                //如果文件夹不存在则创建
                if  (!dstFileimg.exists())
                {
                	dstFileimg.createNewFile();
                }
		       	ImageIO.write(bi, suffix, dstFileimg);
			}
			catch (IOException e) {
		    	//log.error("generate thumbnail image failed.",e);
			}
	   	}else{
	     	//log.warn("the image is not exist.");
	  	}
	}
	/**
	 *
	 * @param imagePath
	 * @param w
	 * @param h
	 * @param prevfix
	 * @param force
	 */
	public static void thumbnailImage(String srcimagePath, int w, int h, String dstFilePath, boolean force){
		File imgFile = new File(srcimagePath);
	 	thumbnailImage(imgFile, w, h, dstFilePath, force);
	}
	/**
	 * 获取文件的缩略图信息
	 * @param imagePath
	 * @param w
	 * @param h
	 * @param force
	 */
	public static void thumbnailImage_s(String imagePath, int w, int h,String dstFilePath, boolean force){
		thumbnailImage(imagePath, w, h, dstFilePath, force);
	}
	/**
	 * 获取文件的压缩图信息
	 * @param imagePath
	 * @param w
	 * @param h
	 * @param force
	 */
	public static void thumbnailImage_m(String imagePath, int w, int h,String dstFilePath, boolean force){
		thumbnailImage(imagePath, w, h, dstFilePath, force);
	}
	
	/**
	 * 获取上传图片信息
	 * @param uploadImage
	 * @param MultipartFile
	 * @return String
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	public static String uploadImage(MultipartFile multipartFile,StringBuilder path,StringBuilder fileNameSb,StringBuilder extNameSb) throws IOException{
		String status = "";
        if (multipartFile != null) {
            String fileName = multipartFile.getOriginalFilename();
            fileNameSb.append(fileName);
            //得到文件扩展名
            String extName = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();	//得到文件扩展名，并统一转换成小写
            extNameSb.append(extName);
            
            InputStream is = multipartFile.getInputStream();
            BufferedImage  bufferedImage = null;
            try{
            	bufferedImage = ImageIO.read(is); 	//用于获得文件宽和高用
            }catch(Exception e){
            	status = "-1";	//图片格式有问题
            }
            
            long pictSize = multipartFile.getSize();
            
            String fieId = ImageUtil.uploadImageToServer(multipartFile.getInputStream(), extName, pictSize);		//上传图片
            
            path.append(fieId.substring(0, fieId.lastIndexOf(".")));
        }
        return status;
	}
	
	/**
     * 上传图片到FastDFS分布式文件系统
     * @param inStream
     * @param uploadFileName
     * @param pictSize
     * @return String 图片路径
     * @throws IOException
     */
    private static String uploadImageToServer(InputStream inStream, String extName, long pictSize) throws IOException{
    	String fileId = "";
    	if(inStream==null){
    		return fileId;
    	}else{
			byte[] fileBuff = getFileBuffer(inStream, pictSize);  
			
			//上传文件
			StorageClient1 client = null;
			try {
				//从连接池中获取连接
				client = ConnectionPool.getPoolInstance().checkout(10); 
			    fileId = client.upload_file1(fileBuff, extName, null);
			    ConnectionPool.getPoolInstance().checkin(client);
			} catch (InterruptedException e) {
				//确实没有空闲连接,并不需要删除与fastdfs连接
			} catch (Exception e) { 
				//发生io异常等其它异常，默认删除这次连接重新申请
				ConnectionPool.getPoolInstance().drop(client);
				e.printStackTrace();
			}  
    	}
    	
		return fileId; 
	}
    
    /** 
     * Transfer java.io.InpuStream to byte array. 
     * @param inStream, input stream of the uploaded file. 
     * @param fileLength, the length of the file. 
     * @return the byte array transferred from java.io.Inputstream. 
     * @throws IOException occurred by the method read(byte[]) of java.io.InputStream. 
     */  
    private static byte[] getFileBuffer(InputStream inStream, long fileLength) throws IOException {  
          
        byte[] buffer = new byte[256 * 1024];  
        byte[] fileBuffer = new byte[(int) fileLength];  
      
        int count = 0;  
        int length = 0;  
      
        while((length = inStream.read(buffer)) != -1){  
            for (int i = 0; i < length; ++i){  
                fileBuffer[count + i] = buffer[i];  
            }  
            count += length;  
        }  
        return fileBuffer;  
    }
	
}




