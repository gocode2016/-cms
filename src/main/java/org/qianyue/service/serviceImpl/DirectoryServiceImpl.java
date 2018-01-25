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
	// ��ͼ����
	@Value("${directoryHeadImage.savePath}")
	private String directoryHeadImageSavePath;
	// ��ͼ����
	@Value("${directoryHeadImage.url}")
	private String directoryHeadImageUrl;

	@Autowired
	DirectoryDao DirectoryDao;

	@Autowired
	PictureService pictureService;

	@Autowired
	VideoService videoService;

	public final static String haveHeadImage = "����ͼ";
	public final static String haveNotHeadImage = "����ͼ";

	// ��ʼ����ѯ���нڵ����
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

	// �������ڵ�
	public boolean update(DirectoryDto DirectoryDto) {
		Directory directory = DirectoryDao.selectById(DirectoryDto.getId());
		directory.setFolderName(DirectoryDto.getFolderName());
		if (DirectoryDao.update(directory) == 1) {
			return true;
		}
		return false;
	}

	// ���½ڵ���Ϣ:ʹ�ڵ�Ϊ���ڵ�
	public boolean updateIsParent(Long isParent) {
		Directory directory = DirectoryDao.selectById(isParent);
		directory.setIsParent(1L);
		if (DirectoryDao.updateIsParentOrNot(directory) == 1) {
			return true;
		} else {
			return false;
		}

	}

	// ����ӽڵ�
	public int addChild(DirectoryDto DirectoryDto) {
		Directory directory = new Directory();
		BeanUtils.copyProperties(DirectoryDto, directory);
		directory.setIsParent(0L);
		if (DirectoryDao.addChild(directory) == 1) {
			// ��ȡ�ӽڵ�id
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

	// ɾ���ļ��м��������ļ���
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

	// �ж������Ƿ�Ϊ���ڵ�
	public boolean judgeIsParent(Long id) {
		if (DirectoryDao.judgeIsParent(id) == 1) {
			return true;
		} else {
			return false;
		}

	}

	// ����id��ѯ������·��
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

	// ����id�����id����
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

	// �����ͼ
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
				// TODO��Ҫ�����־
				return false;
			}

		}

		return false;
	}

	// ��ʾĿ¼�б��ѯĿ¼���б���Ϣ
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
