package org.qianyue.service.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.qianyue.bean.Directory;
import org.qianyue.dao.DirectoryDao;
import org.qianyue.dto.DirectoryDto;
import org.qianyue.service.DirectoryService;
import org.qianyue.service.PictureService;
import org.qianyue.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceImpl implements DirectoryService {

	@Value("${directoryImage.savePath}")
	private String directoryImageSavePath;

	@Value("${directoryVideo.savePath}")
	private String directoryVideoSavePath;
	// 首图保存
	@Value("${directoryHeadImage.savePath}")
	private String directoryHeadImageSavePath;
	// 首图访问
	@Value("${directoryHeadImage.url}")
	private String directoryHeadImageUrl;

	@Autowired
	DirectoryDao DirectoryDao;

	@Autowired
	PictureService pictureService;

	@Autowired
	VideoService videoService;

	public final static String haveHeadImage = "有首图";
	public final static String haveNotHeadImage = "无首图";

	// 初始化查询所有节点对象
	public List<DirectoryDto> selectAll() {
		List<Directory> list = DirectoryDao.selectAll();
		List<DirectoryDto> result = new ArrayList<DirectoryDto>();
		for (Directory dir : list) {
			DirectoryDto dirDto = new DirectoryDto();
			BeanUtils.copyProperties(dir, dirDto);
			result.add(dirDto);
			if (dir.getIsParent() == 1) {
				dirDto.setParent(true);
			} else {
				dirDto.setParent(false);
			}
			dirDto.setOpen(true);
		}
		return result;
	}

	// 重命名节点
	public boolean update(DirectoryDto DirectoryDto) {
		Directory directory = DirectoryDao.selectById(DirectoryDto.getId());
		directory.setFolderName(DirectoryDto.getFolderName());
		if (DirectoryDao.update(directory) == 1) {
			return true;
		}
		return false;
	}

	// 更新节点信息:使节点为父节点
	public boolean updateIsParent(Long isParent) {
		Directory directory = DirectoryDao.selectById(isParent);
		directory.setIsParent(1L);
		if (DirectoryDao.updateIsParentOrNot(directory) == 1) {
			return true;
		} else {
			return false;
		}

	}

	// 添加子节点
	public int addChild(DirectoryDto DirectoryDto) {
		Directory directory = new Directory();
		BeanUtils.copyProperties(DirectoryDto, directory);
		directory.setIsParent(0L);
		if (DirectoryDao.addChild(directory) == 1) {
			// 获取子节点id
			int maxId = DirectoryDao.selectMaxId();
			return maxId;
		} else {
			return -1;
		}

	}

	public int selectMaxId() {
		int maxId = DirectoryDao.selectMaxId();
		return maxId;
	}

	// 删除文件夹及所有子文件夹
	public boolean deleteAllChild(Long id) {
		try {
			Long con = id;
			if (DirectoryDao.judgeIdExists(id) == 1) {
				while (DirectoryDao.selectIdByParentId(id).size() > 0) {
					id = DirectoryDao.selectIdByParentId(id).get(0).longValue();
				}
				pictureService.findAndDeleteIdByFolderId(id);
				videoService.findAndDeleteIdByFolderId(id);
				DirectoryDao.delete(id);
				deleteAllChild(con);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 判断自身是否为父节点
	public boolean judgeIsParent(Long id) {
		if (DirectoryDao.judgeIsParent(id) == 1) {
			return true;
		} else {
			return false;
		}

	}

	// 根据id查询出完整路径
	public String allPath(Long id) {
		String path = null;
		Directory directory = DirectoryDao.selectById(id);
		path = directory.getFolderName();
		while (directory.getParentId() != 0) {
			directory = DirectoryDao.selectById(directory.getParentId());
			path = directory.getFolderName() + "/" + path;
		}
		return path;
	}

	// 根据id查出子id集合
	public List<DirectoryDto> selectChildsByParentId(Long id) {
		List<Directory> list = DirectoryDao.returnChildFolders(id);
		List<DirectoryDto> result = new ArrayList<DirectoryDto>();
		for (Directory dir : list) {
			DirectoryDto dirDto = new DirectoryDto();
			BeanUtils.copyProperties(dir, dirDto);
			dirDto.setHeadImageUrl(directoryHeadImageUrl + dir.getHeadImageName());
			result.add(dirDto);
			if (dir.getIsParent() == 1) {
				dirDto.setParent(true);
			} else {
				dirDto.setParent(false);
			}
			dirDto.setOpen(true);
		}
		return result;
	}

	public DirectoryDto selectById(Long id) {
		Directory directory = DirectoryDao.selectById(id);
		DirectoryDto directoryDto = new DirectoryDto();
		BeanUtils.copyProperties(directory, directoryDto);
		directoryDto.setOpen(true);
		if (directory.getIsParent() == 1) {
			directoryDto.setParent(true);
		} else {
			directoryDto.setParent(false);
		}
		return directoryDto;
	}

	public boolean updateIsNotParent(Long isParent) {
		Directory directory = DirectoryDao.selectById(isParent);
		directory.setIsParent(0L);
		if (DirectoryDao.updateIsParentOrNot(directory) == 1) {
			return true;
		} else {
			return false;
		}
	}

	// 添加首图
	public boolean addHeadImage(DirectoryDto directoryDto) {
		Directory directory = new Directory();
		directory.setId(directoryDto.getId());
		System.out.println(directoryHeadImageSavePath);
		if (directoryDto.getHeadImage() != null && directoryDto.getHeadImage().getSize() > 0) {
			String fileName = System.currentTimeMillis() + directoryDto.getHeadImage().getOriginalFilename();
			System.out.println(fileName);
			File file = new File(directoryHeadImageSavePath + fileName);
			File fileFolder = new File(directoryHeadImageSavePath);
			if (!fileFolder.exists()) {
				fileFolder.mkdirs();
			}

			try {
				directoryDto.getHeadImage().transferTo(file);
				directory.setHeadImageName(fileName);
				DirectoryDao.headImage(directory);
				return true;
			} catch (Exception e) {
				// TODO需要添加日志
				return false;
			}

		}

		return false;
	}

	// 显示目录列表查询目录的列表信息
	public List<DirectoryDto> selectAllDirForList(DirectoryDto directoryDto) {
		List<Directory> list = DirectoryDao.selectAllByPage(directoryDto);
		List<DirectoryDto> result = new ArrayList<DirectoryDto>();
		for (Directory dir : list) {
			DirectoryDto dirDto = new DirectoryDto();
			BeanUtils.copyProperties(dir, dirDto);
			dirDto.setPath(allPath(dir.getId()));
			if (dir.getHeadImageName() != null) {
				dirDto.setHeadImageUrl(directoryHeadImageUrl + dir.getHeadImageName());
				dirDto.setHeadImageHaveOrnot(haveHeadImage);
			} else {
				dirDto.setHeadImageHaveOrnot(haveNotHeadImage);
			}
			result.add(dirDto);

		}
		return result;
	}

}
