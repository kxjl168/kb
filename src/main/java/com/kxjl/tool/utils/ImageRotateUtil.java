package com.kxjl.tool.utils;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
/*
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifImageDirectory;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.iptc.IptcReader;*/

public class ImageRotateUtil {

	public static void main(String[] args) {

		//getPictureByName("F:\\kxjl\\pic\\", "1.jpg");
		
		rotateImg(new File("F:\\kxjl\\pic\\11.jpg"),new File("F:\\kxjl\\pic\\11.jpg"),"jpg");

	}
	
	/**
	 * 读取图片exif信息，旋转图片还原显示
	 * @param file
	 * @param extension   jpg/png等
	 * @return
	 * @author zj
	 * @date 2018年12月6日
	 */
	public static boolean  rotateImg(File file,File fileout,String extension) {

		boolean isok=false;
		try {
			
			BufferedImage src =  ImageIO.read(file);
			// name为前端请求图片名，如 a.jpg
			//BufferedImage src = getPicture(filePath + name);
			BufferedImage bi = null;

			// 图片存在
			if (src != null) {
				// 获取图片旋转角度
				int angel = getRotateAngleForPhoto(file);

				if (angel == 0) { // 图片正常，不处理图片
					bi = src;
				} else { // 图片被翻转，调整图片
					int src_width = src.getWidth(null);
					int src_height = src.getHeight(null);
					Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);

					bi = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2 = bi.createGraphics();

					g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
					g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

					g2.drawImage(src, null, null);
				}

				//int index = name.lastIndexOf(".");
				//String formate = name.substring(index + 1);
				if(extension.startsWith("."))
					extension=extension.substring(1,extension.length());
				isok= ImageIO.write(bi, extension, fileout);
			} else {
				// 图片不存在
				System.out.println("图片不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isok;
	}
	
	

	// 处理ios图片旋转的问题
	public static void getPictureByName(String filePath, String name) {

		try {
			// name为前端请求图片名，如 a.jpg
			BufferedImage src = getPicture(filePath + name);
			BufferedImage bi = null;

			// 图片存在
			if (src != null) {
				// 获取图片旋转角度
				int angel = getRotateAngleForPhoto(filePath + name);

				if (angel == 0) { // 图片正常，不处理图片
					bi = src;
				} else { // 图片被翻转，调整图片
					int src_width = src.getWidth(null);
					int src_height = src.getHeight(null);
					Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);

					bi = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2 = bi.createGraphics();

					g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
					g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

					g2.drawImage(src, null, null);
				}

				int index = name.lastIndexOf(".");
				String formate = name.substring(index + 1);
				 ImageIO.write(bi, formate, new File(filePath+"new_"+name));
			} else {
				// 图片不存在
				System.out.println("图片不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取指定图片
	 */
	public static BufferedImage getPicture(String path) {
		BufferedImage bi = null;
		try {
			File file = new File(path);
			if (!file.exists()) {
				return null;
			}
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bi;
	}

	public static int getRotateAngleForPhoto(File file) {
		int angel = 0;
		/*Metadata metadata;

		try {

			metadata = JpegMetadataReader.readMetadata(file);
			// Directory directory = (ExifImageDirectory.class);

			
			 * for (Directory directory : metadata.getDirectories()) {
			 * 
			 * // // Each Directory stores values in Tag objects // for (Tag tag :
			 * directory.getTags()) { System.out.println(tag); } }
			 

			for (Directory directory : metadata.getDirectoriesOfType(ExifIFD0Directory.class)) {
				if (directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION)) {
					int orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
					System.out.println(orientation);

					if (directory.containsTag(ExifImageDirectory.TAG_ORIENTATION)) { // Exif信息中方向

						// https://www.impulseadventure.com/photo/exif-orientation.html
						// 原图片的方向信息 需要顺时针旋转
						if (6 == orientation) { // 6旋转90
							angel = 90;
						} else if (3 == orientation) { // 3旋转180
							angel = 180;
						} else if (8 == orientation) { // 8旋转90
							angel = 270;
						}

					}
				}

				break;

			}

		} catch (

		Exception e) {
			e.printStackTrace();
		} */
		System.out.println("图片旋转角度：" + angel);
		return angel;
	}
	/**
	 * 图片翻转时，计算图片翻转到正常显示需旋转角度
	 */
	public static int getRotateAngleForPhoto(String fileName) {

		File file = new File(fileName);

		return getRotateAngleForPhoto(file);
	}

	/**
	 * 计算旋转参数
	 */
	public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
		// if angel is greater than 90 degree,we need to do some conversion.
		if (angel > 90) {
			if (angel / 9 % 2 == 1) {
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel % 90;
		}

		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
		double angel_dalta_width = Math.atan((double) src.height / src.width);
		double angel_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}
}
