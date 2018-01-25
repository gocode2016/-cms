package org.qianyue.service.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.qianyue.ForApiDto.VideoForApiDto;
import org.qianyue.Util.CommonUtil;
import org.qianyue.bean.Page;
import org.qianyue.bean.Video;
import org.qianyue.dao.VideoDao;
import org.qianyue.dto.VideoDto;
import org.qianyue.service.DirectoryService;
import org.qianyue.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoDao videoDao;

	@Autowired
	DirectoryService directoryService;

	@Value("${directoryVideo.savePath}")
	private String directoryVideoSavePath;

	@Value("${directoryCategory.Video}")
	private String directoryCategoryVideo;

	@Value("${directoryVideo.url}")
	private String directoryVideourl;

	public boolean addVideo(VideoDto videoDto) {
		Video video = new Video();
		video.setFolderId(videoDto.getFolderId());
		video.setTitle(videoDto.getTitle());
		Date createTime = new Date();
		video.setCreateTime(createTime);
		if (videoDto.getVideoFile() != null && videoDto.getVideoFile().getSize() > 0) {
			String fileName = UUID.randomUUID().toString();
			System.out.println(fileName);
			File file = new File(directoryVideoSavePath + fileName);
			File fileFolder = new File(directoryVideoSavePath);
			if (!fileFolder.exists()) {
				fileFolder.mkdirs();
			}

			try {
				videoDto.getVideoFile().transferTo(file);
				video.setVideoName(fileName);
				videoDao.add(video);
				return true;
			} catch (Exception e) {
				// TODO需要添加日志
				return false;
			}

		}

		return false;
	}

	// 后台用的接口
	public List<VideoDto> selectAllOrFolder(VideoDto viDto) {
		Video videos = new Video();
		BeanUtils.copyProperties(viDto, videos);
		List<Video> list = videoDao.selectByPage(videos);
		List<VideoDto> result = new ArrayList<VideoDto>();
		for (Video video : list) {
			VideoDto videoDto = new VideoDto();
			BeanUtils.copyProperties(video, videoDto);
			videoDto.setCategory(directoryCategoryVideo);
			// 存储路径
			videoDto.setPath(directoryService.allPath(video.getFolderId()));
			videoDto.setTime(CommonUtil.DateTran(video.getCreateTime()));
			result.add(videoDto);
		}
		return result;
	}

	public boolean deleteOneVideo(Long id) {
		if (videoDao.deleteOneVideo(id) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void findAndDeleteIdByFolderId(Long id) {
		List<Video> ListVid = videoDao.findVidByFolderId(id);
		if (ListVid.size() > 0) {
			for (Video vid : ListVid) {
				videoDao.deleteOneVideo(vid.getId());
				File fileVid = new File(directoryVideoSavePath + vid.getVideoName());
				if (fileVid.exists()) {
					fileVid.delete();
				}
			}
		}

	}

	// 前台用的接口
	public VideoForApiDto selectVidByFoldId(Long id) {
		Video videos = new Video();
		Page page = new Page();
		videos.setFolderId(id);
		videos.setPage(page);
		List<Video> listVid = videoDao.selectByPage(videos);
		// List<Video> listVid = videoDao.findVidByFolderId(id);
		List<VideoDto> result = new ArrayList<VideoDto>();
		for (Video video : listVid) {
			VideoDto videoDto = new VideoDto();
			BeanUtils.copyProperties(video, videoDto);
			videoDto.setTime(CommonUtil.DateTran(video.getCreateTime()));
			videoDto.setCategory(directoryCategoryVideo);
			videoDto.setPath(directoryService.allPath(video.getFolderId()));
			videoDto.setVideo(directoryVideourl + video.getVideoName());
			result.add(videoDto);
		}
		return new VideoForApiDto(result, videos.getPage());
	}

	// 查找文件夹下视频 选取某一页
	public VideoForApiDto selectVidByFoldIdByPage(Long id, int currentPage, int pageNumber) {
		Video videos = new Video();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageNumber(pageNumber);
		videos.setFolderId(id);
		videos.setPage(page);
		List<Video> listVid = videoDao.selectByPage(videos);
		List<VideoDto> result = new ArrayList<VideoDto>();
		for (Video video : listVid) {
			VideoDto videoDto = new VideoDto();
			BeanUtils.copyProperties(video, videoDto);
			videoDto.setTime(CommonUtil.DateTran(video.getCreateTime()));
			videoDto.setCategory(directoryCategoryVideo);
			videoDto.setPath(directoryService.allPath(video.getFolderId()));
			videoDto.setVideo(directoryVideourl + video.getVideoName());
			result.add(videoDto);
		}
		return new VideoForApiDto(result, videos.getPage());
	}

}
