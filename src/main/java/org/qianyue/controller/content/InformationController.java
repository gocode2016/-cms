package org.qianyue.controller.content;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.qianyue.Util.MediaUtils;
import org.qianyue.bean.Information;
import org.qianyue.bean.Page;
import org.qianyue.constant.ImageSuffix;
import org.qianyue.constant.PageCodeEnum;
import org.qianyue.constant.Suffix;
import org.qianyue.dto.InformationDto;
import org.qianyue.dto.WangEditorDto;
import org.qianyue.dto.kindEditorResult;
import org.qianyue.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/information")
public class InformationController {

	// ȫ��title
	String holdTitle;
	// ȫ��id
	Long holdId;

	// ͼƬ�ļ�����·��
	@Value("${informationImage.savePath}")
	private String informationImagesavePath;

	// ͼƬ�ļ�����·��
	@Value("${informationImage.url}")
	private String informationImageurl;

	@Autowired
	InformationService informationService;

	// ��ʼ��ҳ��
	@RequestMapping
	public String init(Model model) {
		InformationDto informationDto = new InformationDto();
		Page page = new Page();
		informationDto.setPage(page);
		List<InformationDto> list = informationService.select(informationDto);
		model.addAttribute("list", list);
		model.addAttribute("informationDto", informationDto);
		return "/content/informationList";
	}

	// �ύ׫д������
	@RequestMapping("/addInformation")
	public String addAndIntoWirtePage(InformationDto informationDto, Model model) {

		String picName = informationDto.getImageFile().getOriginalFilename();
		String suffix = picName.substring(picName.lastIndexOf(".") + 1);
		System.out.println(suffix);
		if (suffix.equals(Suffix.jpg) || suffix.equals(Suffix.bmp) || suffix.equals(Suffix.jpeg)
				|| suffix.equals(Suffix.gif)) {
			try {
				String fileName = System.currentTimeMillis() + "_" + picName;
				String fileNameUrl = informationImageurl + fileName;
				File filesave = new File(informationImagesavePath + fileName);
				File fileFolder = new File(informationImagesavePath);
				if (!fileFolder.exists()) {
					fileFolder.mkdirs();
				}
				MediaUtils.saveImage(informationDto.getImageFile(), 230, 500, informationImagesavePath + fileName);
				informationService.add(informationDto, fileNameUrl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/information";
		} else {
			model.addAttribute("informationDto", informationDto);
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.IMAGE_FORMAT_WRONG);
			return "/content/informationAdd";
		}

	}

	// ��ת����׫дҳ��
	@RequestMapping("/addInformationPage")
	public String initAddInformation(Model model) {
		return "/content/informationAdd";
	}

	// ɾ������
	@RequestMapping("/removeInformation")
	@ResponseBody
	public Map<String, String> removeInformation(@RequestParam("id") Long id) {
		Map<String, String> map = new HashMap<String, String>();
		if (informationService.delete(id)) {
			map.put(PageCodeEnum.KEY, "ɾ�����³ɹ�");
			return map;
		} else {
			map.put(PageCodeEnum.KEY, "ɾ������ʧ��");
			return map;
		}
	}

	// �޸����� ����id���������޸�ҳ��
	@RequestMapping("/modifyInformation/{id}")
	public String modifyInformation(@PathVariable("id") Long id, Model model) {
		InformationDto informationDto = informationService.selectById(id);
		model.addAttribute("informationDto", informationDto);
		return "/content/informationAdd";
	}

	// ģ����ѯ�Լ���ҳ����
	@RequestMapping("/search")
	public String search(InformationDto informationDto, Model model) {
		System.out.println(informationDto);
		List<InformationDto> list = informationService.select(informationDto);
		model.addAttribute("list", list);
		model.addAttribute("informationDto", informationDto);
		return "/content/informationList";

	}

	// �ϴ�ͼƬ�ӿ�
	@RequestMapping("/upload")
	@ResponseBody
	public Map<String, Object> uploadImage(HttpServletRequest request,
			@RequestParam("imgFile") MultipartFile[] imgFile) {
		for (MultipartFile file : imgFile) {
			String picName = file.getOriginalFilename();
			String suffix = picName.substring(picName.lastIndexOf(".") + 1);
			if (suffix.equals(ImageSuffix.jpg) || suffix.equals(ImageSuffix.bmp) || suffix.equals(ImageSuffix.gif)
					|| suffix.equals(ImageSuffix.png)) {
				String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
				File filesave = new File(informationImagesavePath + fileName);
				File fileFolder = new File(informationImagesavePath);
				if (!fileFolder.exists()) {
					fileFolder.mkdirs();
				}
				try {
					MediaUtils.saveImage(file, 230, 500, informationImagesavePath + fileName);
					return kindEditorResult.ok(informationImageurl + fileName);
				} catch (Exception e) {
					e.printStackTrace();

					return kindEditorResult.error("�ϴ�ʧ��");
				}

			} else {
				return kindEditorResult.error("�ϴ�ʧ��");
			}
		}

		return null;
	}

	// �޸����·����б�ҳ
	@RequestMapping("/updateInformation/{id}")
	public String updateInformation(@PathVariable("id") Long id, InformationDto informationDto, Model model)
			throws IOException {
		Information information = new Information();
		if (informationDto.getImageFile() != null && informationDto.getImageFile().getSize() > 0) {
			String picName = informationDto.getImageFile().getOriginalFilename();
			String suffix = picName.substring(picName.lastIndexOf(".") + 1);
			System.out.println(suffix);
			if (suffix.equals(Suffix.jpg) || suffix.equals(Suffix.bmp) || suffix.equals(Suffix.jpeg)
					|| suffix.equals(Suffix.gif)) {
				String fileName = System.currentTimeMillis() + "_" + picName;
				String fileNameUrl = informationImageurl + fileName;
				File filesave = new File(informationImagesavePath + fileName);
				MediaUtils.saveImage(informationDto.getImageFile(), 230, 500, informationImagesavePath + fileName);
				information.setImage(fileNameUrl);
			} else {
				model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.IMAGE_FORMAT_WRONG);
				return "/content/informationAdd";
			}
		}
		information.setId(id);
		information.setTitle(informationDto.getTitle());
		information.setContent(informationDto.getContent());
		informationService.update(information);
		return "redirect:/information";
	}

	// �ϴ�ͼƬ�ӿ�forWangeditor
	@RequestMapping(value = "/uploadForWang", method = RequestMethod.POST)
	@ResponseBody
	public WangEditorDto uploadImageForWang(HttpServletRequest request,
			@RequestParam("imgFile") MultipartFile[] imgFile) {
		String[] data = new String[5];
		int i = 0;
		for (MultipartFile file : imgFile) {
			String picName = file.getOriginalFilename();
			String suffix = picName.substring(picName.lastIndexOf(".") + 1);
			if (suffix.equals(ImageSuffix.jpg) || suffix.equals(ImageSuffix.bmp) || suffix.equals(ImageSuffix.gif)
					|| suffix.equals(ImageSuffix.png)) {
				String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
				File filesave = new File(informationImagesavePath + fileName);
				File fileFolder = new File(informationImagesavePath);
				if (!fileFolder.exists()) {
					fileFolder.mkdirs();
				}
				try {
					MediaUtils.saveImage(file, 230, 500, informationImagesavePath + fileName);
					data[i] = informationImageurl + fileName;
					i++;

				} catch (Exception e) {
					e.printStackTrace();

					return new WangEditorDto(1, null);
				}

			} else {
				return new WangEditorDto(1, null);
			}
		}
		return new WangEditorDto(0, data);

	}
}
