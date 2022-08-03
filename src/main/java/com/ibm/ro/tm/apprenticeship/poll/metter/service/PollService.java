package com.ibm.ro.tm.apprenticeship.poll.metter.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ibm.ro.tm.apprenticeship.poll.metter.entity.Answer;
import com.ibm.ro.tm.apprenticeship.poll.metter.entity.Poll;
import com.ibm.ro.tm.apprenticeship.poll.metter.exception.PollNotFoundException;
import com.ibm.ro.tm.apprenticeship.poll.metter.repository.AnswerRepository;
import com.ibm.ro.tm.apprenticeship.poll.metter.repository.PollRepository;

@Service
public class PollService {

	private final PollRepository pollRepository;
	
	private AnswerRepository answerRepository;
	
	
	public PollService(PollRepository pollRepository, AnswerRepository answerRepository) {
		this.pollRepository = pollRepository;
		this.answerRepository = answerRepository;
	}

	public List<Answer> findAllAnswers(Long id){
		 Poll poll = pollRepository.findById(id).orElseThrow(() -> new PollNotFoundException("poll id "+id+" not found"));
		 List<Answer> answers = answerRepository.getAnswersByPoll(poll);
		 return answers;		
	}
	
	
//	/**
//	 * @return the pollRepository
//	 */
//	public PollRepository getPollRepository() {
//		return pollRepository;
//	}
//	
	public Poll add(Poll poll) {
		if(poll.getStartingDate()!=null && poll.getClosingDate() != null && poll.getId() != 0 ) {
		Poll newPoll = null;		
		newPoll = pollRepository.save(poll);		
		return newPoll;
		} else {
			throw new PollNotFoundException("Invalid data introduced!");
		}
	}
	
	public List<Poll> findAll(){
		return pollRepository.findAll();
	}
	
	public Poll updatePoll(Poll poll) {
		
		if(pollRepository.findById(poll.getId()).isPresent()) {			
			Poll existingPoll = pollRepository.findById(poll.getId()).get();
			existingPoll.setTopic(poll.getTopic());
			existingPoll.setStartingDate(poll.getStartingDate());
			existingPoll.setClosingDate(poll.getClosingDate());
			
			Poll updatePoll = pollRepository.save(existingPoll);
			
			return updatePoll;
		} else {
		return pollRepository.save(poll);
		}
	}
	
	public Poll findById(Long id) {
		Poll poll = null;
		if(pollRepository.findById(id).isPresent()) {
			poll = pollRepository.getById(id);
			return poll;
		} else {
			throw new PollNotFoundException("Poll id "+id+" doesn`t exist!");
		}
				
	}
	
//	public List<Poll> findByTopic(String topic) {
//		return pollRepository.findByTopic(topic);
//	}
//	
	public void delete(Long id) {
		if(pollRepository.findById(id).isPresent()) {
			pollRepository.deleteById(id);
			} else {
				throw new PollNotFoundException("Poll id "+id+" doesn`t exist!");
			}
		
	}
//	
		
}
