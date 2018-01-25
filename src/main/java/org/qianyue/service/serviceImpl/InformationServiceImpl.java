package org.qianyue.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.qianyue.Util.CommonUtil;
import org.qianyue.bean.Information;
import org.qianyue.dao.InformationDao;
import org.qianyue.dto.InformationDto;
import org.qianyue.service.InformationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl implements InformationService {

	@Value("${informationImage.savePath}")
	private String informationImagesavePath;

	@Value("${informationImage.url}")
	private String informationImageurl;

	@Autowired
	InformationDao informationDao;

	public List<InformationDto> select(InformationDto informationDto) {
		Information information = new Information();
		BeanUtils.copyProperties(informationDto, information);
		List<Information> list = informationDao.selectByPage(information);
		List<InformationDto> result = new ArrayList<InformationDto>();
		for (Information In : list) {
			InformationDto informationDtos = new InformationDto();
			BeanUtils.copyProperties(In, informationDtos);
			informationDtos.setTime(CommonUtil.DateTran(In.getCreateTime()));
			result.add(informationDtos);
		}
		return result;
	}

	public boolean add(InformationDto informationDto, String fileNameUrl) {
		Information information = new Information();
		BeanUtils.copyProperties(informationDto, information);
		information.setImage(fileNameUrl);
		Date createTime = new Date();
		information.setCreateTime(createTime);
		if (informationDao.add(information) == 1) {
			return true;
		} else {
			return false;
		}

	}

	public int selectMaxId() {
		int maxId = informationDao.selectMaxId();
		return maxId;
	}

	public boolean update(Information Information) {
		if (informationDao.update(Information) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delete(Long id) {
		if (informationDao.delete(id) == 1) {

			return true;

		} else {
			return false;
		}

	}

	public InformationDto selectById(Long id) {
		Information Information = informationDao.selectById(id);
		InformationDto informationDto = new InformationDto();
		BeanUtils.copyProperties(Information, informationDto);
		informationDto.setTime(CommonUtil.DateTran(Information.getCreateTime()));
		return informationDto;
	}

}
