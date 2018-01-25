package org.qianyue.ForApiDto;

import java.util.List;

import org.qianyue.dto.DirectoryDto;

//ǰ̨����json��ʽ
public class DirectoryForApiDto extends DirectoryDto {

	// ��ǰ�ļ��е��ļ���
	private String folderName;
	// �ж��������ļ��л���ͼƬ��Ƶ
	private boolean isFolder;
	// �������ļ��м���
	private List<DirectoryDto> listDir;
	// ͼƬ���ϰ���ҳʵ��
	private PictureForApiDto pictureForApiDto;
	// ��Ƶ
	private VideoForApiDto videoForApiDto;

	// ��ʼ�� ������
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
