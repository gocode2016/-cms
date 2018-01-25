package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.qianyue.bean.Page;
import org.qianyue.bean.User;
import org.qianyue.dao.InformationDao;
import org.qianyue.dao.UserDao;
import org.qianyue.dto.DirectoryDto;
import org.qianyue.dto.InformationDto;
import org.qianyue.dto.PictureDto;
import org.qianyue.dto.VideoDto;
import org.qianyue.service.DirectoryService;
import org.qianyue.service.InformationService;
import org.qianyue.service.PictureService;
import org.qianyue.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext.xml" })
public class ServiceTest {

	@Value("${directoryImage.savePath}")
	private String directoryImageSavePath;

	@Value("${directoryImage.url}")
	private String directoryImageUrl;

	@Value("${directoryCategory.Image}")
	private String directoryCategoryImage;

	@Autowired
	InformationService informationService;

	@Autowired
	InformationDao informationDao;

	@Autowired
	DirectoryService directoryService;

	@Autowired
	PictureService PictureService;

	@Autowired
	VideoService videoService;

	@Autowired
	UserDao userDao;

	@Test
	public void testDirectory() {
		List<DirectoryDto> list = directoryService.selectAll();
		for (DirectoryDto dir : list) {
			System.out.println(dir);
		}
	}

	@Test
	public void testDeleteAllChild() {
		directoryService.deleteAllChild(4L);
	}

	@Test
	public void testword() {
		String str = "abc.def";
		String prefix = str.substring(str.lastIndexOf(".") + 1);
		System.out.println(prefix);
		String a = "a";
		String b = "a";
		System.out.println((a.equals(b)));
	}

	@Test
	public void testpath() {
		String path = directoryService.allPath(57L);
		System.out.println(path);
	}

	@Test
	public void testproperties() {
		System.out.println(directoryImageUrl);
		System.out.println(directoryCategoryImage);

	}

	@Test
	public void testDate() {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.print(dateFormater.format(date));
	}

	@Test
	public void selectPicVidDirectory() {
		List<DirectoryDto> listDir = directoryService.selectChildsByParentId(1L);
		System.out.println("!!!!!!!" + listDir.get(0).getFolderName());
		List<VideoDto> listVid = videoService.selectVidByFoldId(73L);
		System.out.println("!!!!!!!" + listVid.get(0).getVideoName());
		List<PictureDto> listPic = PictureService.selectPicByFoldId(73L);
		System.out.println("!!!!!!!" + listPic.get(0).getPicName());
	}

	@Test
	public void admin() {
		User user = new User();
		user.setName("admin");
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		List<User> list = userDao.select(user);
		System.out.println(list.get(0).getName());
	}

}
