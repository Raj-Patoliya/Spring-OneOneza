package net.javaguides.springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.Model.Notice;
import net.javaguides.springboot.Repository.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeRepository noticeRepository;
	@Override
	public void noticeSave(Notice notice) {
			this.noticeRepository.save(notice);
	}
	@Override
	public List<Notice> getAllNotice() {
		return noticeRepository.findAll();
	}
	@Override
	public Notice getNoticeByNoticeId(long noticeId) {
		Optional<Notice> optional=  noticeRepository.findById(noticeId);
		Notice notice = null;
		if(optional.isPresent())
		{
			notice = optional.get();
		}
		else
		{
			throw new RuntimeException("Person not found by id no : "+noticeId);
		}
		return notice;
	
	}
	@Override
	public void deleteNoticeByNoticeId(long noticeId) {
		this.noticeRepository.deleteById(noticeId);
		
	}

}
