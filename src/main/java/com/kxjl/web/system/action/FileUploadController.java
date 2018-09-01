package com.kxjl.web.system.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;
import com.kxjl.tool.common.Md5EncryptFile;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.ImageUtil;
import com.kxjl.tool.utils.JsonUtil;
import com.kxjl.tool.utils.StringUtil;
import com.kxjl.web.system.model.SvrFileInfo;
import com.kxjl.web.system.service.SvrFileInfoService;


/**
 * 文件服务器实现
 * 
 * 
 * @date 2016-7-12
 * @author zj
 * 
 */
@Controller
@RequestMapping(value = "/FileSvr")
public class FileUploadController {

	// 日志记录对象
	private Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	private SvrFileInfoService fileService;

	/**
	 * 下载文件，计算下载数+1
	 * 
	 * @param request
	 * @param response
	 * @date 2016-7-12
	 * @author zj
	 */
	@RequestMapping(value = "/downFile")
	public void downFile(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String md5 = request.getParameter("m5");

			SvrFileInfo query = new SvrFileInfo();
			query.setFile_md5(md5);

			SvrFileInfo sInfo = fileService.getFileInfo(query);
			if (sInfo != null) {
				File file = new File(sInfo.getFull_path());

				if (file.exists()) {

					if (file != null) {
						response.setCharacterEncoding("UTF-8");
						response.setContentType("application/octet-stream");
						response.setHeader("Content-Disposition",
								"attachment;filename=" + sInfo.getOld_name());// mod
																				// by
																				// pengqp
																				// at
																				// 2012/8/29
																				// 下载文件乱码
						ServletOutputStream out = response.getOutputStream();

						FileInputStream stream = new FileInputStream(file);
						int size = 2048;
						byte[] temp = new byte[size];
						int len = 0;

						while ((len = stream.read(temp, 0, size)) != -1) {
							out.write(temp, 0, len);
						}
						// stream.read( temp,0, len)
						// while( )
						// wb.write(out);

						out.close();

						stream.close();
						fileService.addFileDonwLoadNums(sInfo);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件服务器接收上传文件
	 * 
	 * @param receiveFiles
	 * @param request
	 *            path属性指定文件存储的第二级目录，不指定则默认存放至tmp目录
	 * @param response
	 * @date 2016-7-12
	 * @author zj
	 */
	@RequestMapping(value = "/UploadFile")
	public void upload(
			@RequestParam(value = "file", required = false) MultipartFile[] receiveFiles,
			HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonOut = new JSONObject();

		String curDir = "tmp"; // 服务器文件存储目录-默认 tmp

		String path = request.getParameter("path");
		if (!StringUtil.isNull(path))
			curDir = path;

		// 设置图片类型
		String extension = ".png";
		// 获取 服务器上文件存储绝对路径
		String uploadPath = ConfigReader.getInstance().getProperty(
				"FILE_SAVE_PATH");
		// web访问相对路径
		String http_path = ConfigReader.getInstance().getProperty("HTTP_PATH");

		String relativeURL = ""; // 文件相对路径
		String httpURL = ""; // 文件http完整路径

		String downURL = "";
		String httpDownURL = "";
		String httpURL2 = "";
		String md5 = "";
		String fileName = "";
		String oriName="";
		String os = System.getProperty("os.name");
		SvrFileInfo efile = null;
		try {


			//增加月份分级
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String formattedDate = formatter.format(new Date());  

			
			String relativePath=  curDir + "/"+formattedDate+"/";
			logger.info("uploadPath:  " +uploadPath+ relativePath);

			
			
			File dir = new File(uploadPath+ relativePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// int sizeThreshold = 1024 * 1024; // 写满该大小的缓存后，存入硬盘中。
			// // repositoryFile.mkdirs(); 第三方
			// DiskFileItemFactory factory = new DiskFileItemFactory();
			// // 设置文件的缓存路径
			// factory.setRepository(dir);
			// // 设置缓存大小，如果上传文件超过缓存大小，将使用临时目录做为缓存。
			// factory.setSizeThreshold(sizeThreshold);
			// ServletFileUpload upload = new ServletFileUpload(factory);
			//
			// upload.setHeaderEncoding("utf-8");
			// upload.setSizeMax(10 * 1024 * 1024); // set every upload

			for (int i = 0; i < receiveFiles.length; i++) {

				MultipartFile mfile = receiveFiles[i];

				// 文件
				// itemFile = item;

				// String name =mfile.getOriginalFilename();//
				// item.getFieldName();

				String value = mfile.getOriginalFilename();// .getName();//
															// item.getName();

				extension = value.substring(value.lastIndexOf("."),
						value.length());

				int picMax = ConfigReader.getInstance().getIntProperty(
						"PIC_MAXSIZE", 10);
				int vidMax = ConfigReader.getInstance().getIntProperty(
						"VID_MAXSIZE", 50);

				if (extension.equalsIgnoreCase("jpg")
						|| extension.equalsIgnoreCase("png")
						|| extension.equalsIgnoreCase("bmp")
						|| extension.equalsIgnoreCase("jpeg")) {
					if (mfile.getSize() > picMax * 1024 * 1024) {
						jsonOut.put("ResponseCode", "201");
						jsonOut.put("ResponseMsg", "FAILED:文件超出" + picMax
								+ "M大小！");
						JsonUtil.responseOutWithJson(response,
								jsonOut.toString());
						return;
					}
				} else // 非图片文件，最大50M
				{
					if (mfile.getSize() > vidMax * 1024 * 1024) {
						jsonOut.put("ResponseCode", "201");
						jsonOut.put("ResponseMsg", "FAILED:文件超出" + picMax
								+ "M大小！");
						JsonUtil.responseOutWithJson(response,
								jsonOut.toString());
						return;
					}
				}

				UUID uuid = UUID.randomUUID();
				fileName = uuid.toString() + extension; // 随机生成的唯一文件名 压缩后的
				oriName = uuid.toString() +"_orign"+ extension; // 随机生成的唯一文件名 原始
				String absoluteURL =uploadPath+relativePath + fileName; // 文件存放的绝对路径
				// logger.info("name:" + name);

				md5 = new Md5EncryptFile().getMD5(mfile);
				boolean isExsit = false;

				SvrFileInfo query = new SvrFileInfo();
				query.setFile_md5(md5);
				 efile = fileService.getFileInfo(query);

				if (efile != null)
					isExsit = true;

				// 计算md5,查看本地文件库中是否有相同的文件
				if (!isExsit) {
					// 没有，则上传
					// 上传文件（服务器指定地址）
					File uploaderFile = new File(absoluteURL);
					
					
					
					logger.info("文件存放的绝对路径:" + absoluteURL);
					// 保存文件
					//mfile.transferTo(uploaderFile);
					
					//压缩文件 zj 180107
					String orginFile =uploadPath+relativePath + uuid.toString()+"_orign" + extension;; // 文件存放原始图片
					File orginFileData = new File(orginFile);
				
					try {
				
						if(extension.contains("png")||
								extension.contains("jpeg")||
								extension.contains("jpg")||
								extension.contains("bmp"))
						{
							//保持源文件
							mfile.transferTo(orginFileData);
						//使用压缩后的图片
						ImageUtil.resize(orginFileData, uploaderFile);
						}
						//end
						else
						{
							//保持源文件
							mfile.transferTo(uploaderFile);
							/*
							 * //压缩异常 -普通非图片文件-附件管理 FileUtils.copyFile(orginFileData, uploaderFile);
							 */
						}
						
				
					} catch (Exception e) {
						
					}
					
					
					

					logger.info("uploaderFile值:  " + uploaderFile);

					relativeURL = relativePath + fileName; // 文件相对路径
					httpURL = http_path + relativeURL;
					httpURL2=http_path + relativePath+oriName;

					downURL = "/FileSvr/downFile.action?m5=" + md5;
					httpDownURL = http_path + downURL;

					// 存储信息
					SvrFileInfo finfo = new SvrFileInfo();
					finfo.setOld_name(value);
					finfo.setSave_name(fileName);
					finfo.setFull_path(absoluteURL);
					finfo.setHttp_relative_path(relativeURL);
					finfo.setHttp_relative_path2(relativePath+oriName);
					finfo.setHttp_down_url(downURL);
					finfo.setFile_md5(md5);
					finfo.setFile_size(mfile.getSize());
					finfo.setDown_nums(0);

					fileService.SaveFileInfo(finfo);
					efile=finfo;
				} else {

					absoluteURL = efile.getFull_path();// 之前上传的文件路径
					logger.info("md5已存在，使用原有文件存放的绝对路径:" + absoluteURL);

					relativeURL = efile.getHttp_relative_path();// curDir + "/"
																// + fileName;
																// // 文件相对路径
					httpURL = http_path + efile.getHttp_relative_path();// curDir
																		// + "/"
																		// +
																		// fileName;
					
					

					String orpath=efile.getHttp_relative_path2();
					if(orpath==null||orpath.equals(""))
						orpath=relativeURL.substring(relativeURL.lastIndexOf("."))+"_orign"+ extension;
					httpURL2=http_path+orpath;
					
					
					downURL = efile.getHttp_down_url();
					httpDownURL = http_path + downURL;
					
				}

				break;// 只处理一个文件一张图片

			}

		} catch (Exception e) {
			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "FAILED:" + e.getMessage());
				e.printStackTrace();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (relativeURL != null && !relativeURL.equals("")) {
			try {
				jsonOut.put("ResponseCode", "200");
				jsonOut.put("ResponseMsg", "OK");
				jsonOut.put("FileUrl", httpURL);
				jsonOut.put("FileUrl2", httpURL2);
				
				jsonOut.put("relativeURL", relativeURL);
				jsonOut.put("downURL", downURL);
				jsonOut.put("httpDownURL", httpDownURL);
				jsonOut.put("md5", md5);
				jsonOut.put("newname", fileName);
				jsonOut.put("oldname", efile.getOld_name());
				jsonOut.put("fileid", efile.getId());


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				jsonOut.put("ResponseCode", "201");
				jsonOut.put("ResponseMsg", "上传失败!");
				jsonOut.put("FileUrl", "");
				jsonOut.put("relativeURL", "");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		JsonUtil.responseOutWithJson(response, jsonOut.toString());

	}
}
