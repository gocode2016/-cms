package org.qianyue.ForApiDto;

import java.util.List;

import org.qianyue.bean.Page;
import org.qianyue.dto.PictureDto;

public class PictureForApiDto {
	// ͼƬ����
	private List<PictureDto> listPic;

	// ͼƬ���Ϸ�ҳ��Ϣ
	private Page pageForlistPic;

	public PictureForApiDto(List<PictureDto> listPic, Page pageForlistPic) {

		this.listPic = listPic;
		this.pageForlistPic = pageForlistPic;
	}

	public List<PictureDto> getListPic() {
		return listPic;
	}

	public void setListPic(List<PictureDto> listPic) {
		this.listPic = listPic;
	}

	public Page getPageForlistPic() {
		return pageForlistPic;
	}

	public void setPageForlistPic(Page pageForlistPic) {
		this.pageForlistPic = pageForlistPic;
	}

}
