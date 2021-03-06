package com.ibm.ro.tm.apprenticeship.poll.metter.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Answer implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5720254597469710394L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
    
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="poll_id", referencedColumnName = "id")
	private Poll poll;
    
    @Column
    @ApiModelProperty(value = "description", name = "notificationExpiryDate",
            dataType = "String", example = "2022-01-16T08:42:37.484Z")
    private Timestamp vottingDate;

    @Column(nullable = false)
    private int vottingDetails;
    
    @Column
    private String comment;


    protected Answer(){}

    public Answer(int vottingDetails, String comment){
        this.vottingDetails = vottingDetails;
        this.comment = comment;
    }
    
    public Answer(String content){
    	this.comment = content;
    }

    //getters
    
    public Long getId(){
        return id;
    }

    public String getComment(){
        return comment;
    }

    public int getVottingDetails(){
        return vottingDetails;
    }
    
    public User getUser() {
		return user;
	}
    
    public Poll getPoll() {
    	return poll;
    }
    
    //setters
    
    public void setId(Long newId) {
    	this.id = newId;
    }
    
    public void setVottingDetails(int newVottingDetails) {
    	this.vottingDetails = newVottingDetails;
    }
    
    public void setComment(String newComment) {
    	this.comment = newComment;
    }
    
    public void setPoll(Poll newPoll) {
    	this.poll = newPoll;
    }
    
    public void setUser(User newUser) {
    	this.user = newUser;
    }

	public void assignAnswersToPoll(Poll poll2) {
		// TODO Auto-generated method stub
		this.poll = poll2;
	}
}
