package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.Submission;

public interface SubmissionService {
	void saveSumission(Submission submission);
	List<Submission> getSubmissionByAssId(long assId);
	Submission getSubmissionBySubmissionId(long submissionId);
	void deleteSubmissionById(long submissionId);
}
