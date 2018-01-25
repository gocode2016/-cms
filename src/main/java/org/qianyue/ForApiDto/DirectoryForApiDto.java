package org.qianyue.ForApiDto;

import java.util.List;

import org.qianyue.dto.DirectoryDto;

//前台返回json格式
public class DirectoryForApiDto extends DirectoryDto {

	// 当前文件夹的文件名
	private String folderName;
	// 判断内容是文件夹还是图片视频
	private boolean isFolder;
	// 当下子文件夹集合
	private List<DirectoryDto> listDir;
	// 图片集合包分页实体
	private PictureForApiDto pictureForApiDto;
	// 视频
	private VideoForApiDto videoForApiDto;

	// 初始化 构造器
	public DirectoryForApiDto(String folderName, boolean isFolder, List<DirectoryDto> listDir,
			PictureForApiDto pictureForApiDto, VideoForApiDto videoForApiDto) {

		this.folderName = folderName;
		this.isFolder = isFolder;
		this.listDir = listDir;
		this.pictureForApiDto = pictureForApiDto;
		this.videoForApiDto = videoForApiDto;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public List<DirectoryDto> getListDir() {
		return listDir;
	}

	public void setListDir(List<DirectoryDto> listDir) {
		this.listDir = listDir;
	}

	public PictureForApiDto getPictureForApiDto() {
		return pictureForApiDto;
	}

	public void setPictureForApiDto(PictureForApiDto pictureForApiDto) {
		this.pictureForApiDto = pictureForApiDto;
	}

	public VideoForApiDto getVideoForApiDto() {
		return videoForApiDto;
	}

	public void setVideoForApiDto(VideoForApiDto videoForApiDto) {
		this.videoForApiDto = videoForApiDto;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

}
