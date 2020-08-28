package com.zy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class LoadUtil {
	
	/**
	 * 上传视频，返回的是视频的访问路径
	 * @param request
	 * @param response
	 * @return   图片的访问路径
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String uploadVideo(MultipartFile video) {
		String showPath = null;
		//将文件复制到指定的目录
		try {
			//获取原始文件名
			String name = video.getOriginalFilename();
			//对文件名进行截取从/后面一位开始截取子字符串
			String name2 = name.substring(name.lastIndexOf("/")+1);
			name2 = name2.replaceAll("[\u4e00-\u9fa5]*", "");
			//对名字进行更改防止重名
			Long time = System.currentTimeMillis();
			String newName = time+name2;
			//定义需要存储的路径 存储路径每日一个
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String day = format.format(new Date());
			String uploadpath = "c:/video/" + day;
			
			//判断文件夹是否存在，如果不存在则创建目录
			File dayFile = new File(uploadpath);
			if(!dayFile.exists()){
				dayFile.mkdirs();
			}
			
			//上传文件到服务器之后的文件名
			String newFileName = uploadpath +"/"+ newName;
			File newFile = new File(newFileName);
			video.transferTo(newFile);
			
			showPath = "/video/"+day+"/"+ newName;;
		} catch (Exception e) {
			showPath = null;
		}
		return showPath;
	}
	
	/**
	 * 上传视频，返回的是视频的访问路径
	 * @param request
	 * @param response
	 * @return   图片的访问路径
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String uploadImage(MultipartFile image) {
		String showPath = null;
		//将文件复制到指定的目录
		try {
			//获取原始文件名
			String name = image.getOriginalFilename();
			//对文件名进行截取从/后面一位开始截取子字符串
			String name2 = name.substring(name.lastIndexOf("/")+1);
			name2 = name2.replaceAll("[\u4e00-\u9fa5]*", "");
			//对名字进行更改防止重名  uuid
			Long time = System.currentTimeMillis();
			String newName = time+name2;
			//定义需要存储的路径 存储路径每日一个
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String day = format.format(new Date());
			String uploadpath = "c:/image/" + day;
			
			//判断文件夹是否存在，如果不存在则创建目录
			File dayFile = new File(uploadpath);
			if(!dayFile.exists()){
				dayFile.mkdirs();
			}
			
			//上传文件到服务器之后的文件名
			String newFileName = uploadpath +"/"+ newName;
			File newFile = new File(newFileName);
			image.transferTo(newFile);
			
			showPath = "/image/"+day+"/"+ newName;;
		} catch (Exception e) {
			showPath = null;
		}
		return showPath;
	}
	
	
	//页面上需要发起一个请求  请求后面带一个下载的参数   需要下载的文件对应的数据库的id   通过这个id 到数据库去获取到对应的  下载地址
	//download.do?id=1
	public static void download(HttpServletRequest req, HttpServletResponse resp, String downloadPath) throws ServletException, IOException {

		//获取原来的文件名。去掉系统加上的22位数据
		String fileName = downloadPath.substring(downloadPath.lastIndexOf("/")+23);


		//设置响应头  ，  定义了文件名
		resp.setHeader("content-disposition",  "attachment;filename=" +
				URLEncoder.encode(fileName, "UTF-8"));


		//下载流程
		InputStream input = new FileInputStream(downloadPath);
		OutputStream output = resp.getOutputStream();

		IOUtils.copy(input, output);

		input.close();
		output.close();
	}
}
