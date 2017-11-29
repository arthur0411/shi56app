package com.flf.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	static String filePath = "";

	/**
	 * 图片文件上传
	 * 
	 * @param file
	 * @param subPath
	 *            分类保存，不同类型的文件放在不同类型目录下
	 * @param request
	 * @return 返回该文件的网络地址
	 * @throws IOException
	 * @Author SevenWong
	 */
	public static String uploadFile(MultipartFile file, String subPath, HttpServletRequest request) throws IOException {
		String fileName = file.getOriginalFilename();
		filePath = File.separator + "opt" + File.separator + "shop" + File.separator + subPath;
		System.out.println("com.flf.util.FileUpload.uploadFile()文件目录: " + filePath);
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		fileName = UUID.randomUUID().toString() + suffix;
		File tempFile = new File(filePath, fileName);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		file.transferTo(tempFile);
		return request.getScheme() + "://" + request.getServerName() + ":88" + request.getContextPath()
				+ File.separator + subPath + tempFile.getName();
	}

	/**
	 * excel文件上传
	 * 
	 * @param file
	 * @param subPath
	 *            分类保存，不同类型的文件放在不同类型目录下
	 * @param request
	 * @return 返回该文件的磁盘路径
	 * @throws IOException
	 * @Author SevenWong
	 */
	public static String uploadExcel(MultipartFile file, String subPath, HttpServletRequest request) throws IOException {
		String fileName = file.getOriginalFilename();
		filePath = File.separator + "opt" + File.separator + "shop" + File.separator + subPath;
		System.out.println("com.flf.util.FileUpload.uploadExcel()文件目录: " + filePath);
		File tempFile = new File(filePath, fileName);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		file.transferTo(tempFile);
		filePath = filePath + tempFile.getName();
		return filePath;
	}

	/**
	 * 上传文件到工程目录下（windows）
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 * @Author SevenWong
	 */
	public static String uploadFile4Win(MultipartFile file, HttpServletRequest request) throws IOException {
		filePath = request.getSession().getServletContext().getRealPath("upload");

		String fileName = file.getOriginalFilename();
		// System.out.println("文件目录: " + filePath);

		File tempFile = new File(filePath, String.valueOf(fileName));
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		file.transferTo(tempFile);

		filePath = request.getContextPath() + "/upload/" + fileName;

		return filePath;
	}

	public static File getFile(String fileName) {
		return new File(filePath, fileName);
	}
}
