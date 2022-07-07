package net.javaguides.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{

}
