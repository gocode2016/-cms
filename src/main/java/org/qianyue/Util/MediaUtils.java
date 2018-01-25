package org.qianyue.Util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class MediaUtils {

	public static void saveImage(MultipartFile multipartFile, int width, int height, String saveFile)
			throws IOException {
		File saveFiles = new File(saveFile);
		if (width > 0 && height > 0) {
			BufferedImage image = null;
			image = Thumbnails.of(multipartFile.getInputStream()).width(width).asBufferedImage();
			Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height).width(width).outputFormat("jpg")
					.toFile(saveFiles);
		} else {
			if (width == 0 && height == 0) {
				multipartFile.transferTo(saveFiles);
			} else {
				if (width > 0) {
					Thumbnails.of(multipartFile.getInputStream()).width(width).keepAspectRatio(true).outputFormat("jpg")
							.toFile(saveFiles);
				}
				if (height > 0) {
					Thumbnails.of(multipartFile.getInputStream()).height(height).keepAspectRatio(true)
							.outputFormat("jpg").toFile(saveFiles);
				}
			}
		}

	}

}
