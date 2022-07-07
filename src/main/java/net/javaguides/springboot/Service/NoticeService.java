package net.javaguides.springboot.Service;

import java.util.List;
import net.javaguides.springboot.Model.Notice;

public interface NoticeService {

	List<Notice> getAllNotice();
	void noticeSave(Notice notice);
	Notice getNoticeByNoticeId(long noticeId);
	void deleteNoticeByNoticeId(long noticeId);

}
