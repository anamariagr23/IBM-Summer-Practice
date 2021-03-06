/**
 * 
 */
package com.ibm.ro.tm.apprenticeship.poll.metter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.ro.tm.apprenticeship.poll.metter.entity.Answer;
import com.ibm.ro.tm.apprenticeship.poll.metter.entity.Poll;
import com.ibm.ro.tm.apprenticeship.poll.metter.entity.User;
import com.ibm.ro.tm.apprenticeship.poll.metter.exception.AnswerNotFoundException;
import com.ibm.ro.tm.apprenticeship.poll.metter.exception.PollNotFoundException;
import com.ibm.ro.tm.apprenticeship.poll.metter.exception.UserNotFoundException;
import com.ibm.ro.tm.apprenticeship.poll.metter.repository.AnswerRepository;
import com.ibm.ro.tm.apprenticeship.poll.metter.repository.PollRepository;
import com.ibm.ro.tm.apprenticeship.poll.metter.repository.UserRepository;

import dto.AnswerDto;

/**
 * @author vlads
 *
 */
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;
	
	@Autowired
	PollRepository pollRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	public AnswerService(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}

	//add answer
	
	public Answer add(AnswerDto answerDto) {
		Answer newAnswer = null;
		newAnswer = new Answer(answerDto.getContent());
		User user = userRepository.findById(answerDto.getUserId()).orElseThrow(() -> new UserNotFoundException("user id not found "+answerDto.getUserId()));
		 Poll poll = pollRepository.findById(answerDto.getPollId()).orElseThrow(() -> new PollNotFoundException("poll id not found "+answerDto.getPollId()));
		 newAnswer.setUser(user);
		newAnswer.setPoll(poll);
		newAnswer.setVottingDetails(answerDto.getVottingDetail());
		answerRepository.save(newAnswer);
		return newAnswer;
		
	}
	
	//findAll	
	
	public List<AnswerDto> findAllAnswers(){
		List<Answer> repositoryAnswers = answerRepository.findAll();
		List<AnswerDto> answerDtoList = new ArrayList<AnswerDto>();
		for (Answer repositoryAnswer: repositoryAnswers) {
			AnswerDto answerDtoToAdd = new AnswerDto();
			answerDtoToAdd.setPollId(repositoryAnswer.getPoll().getId());
			answerDtoToAdd.setUserId(repositoryAnswer.getUser().getId());
			answerDtoToAdd.setContent(repositoryAnswer.getComment());
			answerDtoToAdd.setVottingDetail(repositoryAnswer.getVottingDetails());
			answerDtoToAdd.setId(repositoryAnswer.getId());
			answerDtoList.add(answerDtoToAdd);

		}
		return answerDtoList;

	}
	
	//findById
	
	public AnswerDto findById(Long id) {
		Answer isAnswer = answerRepository.findById(id).orElseThrow(()-> new AnswerNotFoundException("Answer not found"));
		isAnswer = answerRepository.getById(id);
		AnswerDto isAnswerDto = new AnswerDto();
		isAnswerDto.setPollId(isAnswer.getPoll().getId());
		isAnswerDto.setUserId(isAnswer.getUser().getId());
		isAnswerDto.setVottingDetail(isAnswer.getVottingDetails());
		isAnswerDto.setContent(isAnswer.getComment());
		isAnswerDto.setId(isAnswer.getId());
		return isAnswerDto;
	}
	
	//update
	
	public Answer update(AnswerDto answerDto) {
		
		if(userRepository.findById(answerDto.getUserId()).isPresent() && pollRepository.findById(answerDto.getPollId()).isPresent()) {
//			Answer updateAnswer = null;
//			updateAnswer = new Answer(answerDto.getContent());
//			User user = userRepository.findById(answerDto.getUserId()).get();
//			Poll poll = pollRepository.findById(answerDto.getPollId()).get();
//			updateAnswer.setUser(user);
//			updateAnswer.setPoll(poll);
//			updateAnswer.setVottingDetails(answerDto.getVottingDetail());
//			updateAnswer = answerRepository.save(updateAnswer);
			Answer dbAnswer = answerRepository.findById(answerDto.getId()).get();
			dbAnswer.setComment(answerDto.getContent());
			dbAnswer.setVottingDetails(answerDto.getVottingDetail());
			answerRepository.save(dbAnswer);
			return dbAnswer;
		} else {
			throw new AnswerNotFoundException("No answer existent");
		}

	}
	
	//delete
	
	public void delete(Long id) {
		if(answerRepository.findById(id).isPresent()) {
		answerRepository.deleteById(id);
		} else {
			throw new AnswerNotFoundException("Answer id "+id+" doesn`t exist!");
		}
	}
	
	//findAnswersByPoll

	 public List<Answer> findAnswersByPoll (Long pollId){
		 Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new PollNotFoundException("poll id not found"+pollId));
		 List<Answer> answers = answerRepository.getAnswersByPoll(poll);
		 return answers;		 
	 }	

	 //findAnswersByUser

	public List<Answer> findAnswersByUser(Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user id not found"+userId));
		List<Answer> answers = answerRepository.getAnswersByUser(user);
		return answers;
		
	}
	public AnswerDto findAnswerByUserIdAndPollId(Long pollId,Long userId){
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user id not found"+userId));
		Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new PollNotFoundException("poll id not found"+pollId));

		Answer dbAnswer = answerRepository.findAnswerByUserIdAndPollId(poll,user);

		AnswerDto dtoToReturn = new AnswerDto();
		dtoToReturn.setContent(dbAnswer.getComment());
		dtoToReturn.setPollId(dbAnswer.getPoll().getId());
		dtoToReturn.setVottingDetail(dbAnswer.getVottingDetails());
		dtoToReturn.setUserId(dbAnswer.getUser().getId());
		dtoToReturn.setId(dbAnswer.getId());

		return dtoToReturn;
	}
	
}
