/**
 * 
 */
package dto;

/**
 * @author vlads
 *
 */
public class AnswerDto {
	private Long id;
	private Long userId;
	
	private Long pollId;
	
	private String content;
	
	private int vottingDetail;
	
	public AnswerDto() {
		
	}
	
	/**
	 * @return the comment
	 */
	public Long getId(){
		return id;
	}
	public String getContent() {
		return content;
	}

	/**
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the vottingDetail
	 */
	public int getVottingDetail() {
		return vottingDetail;
	}

	/**
	 * @param vottingDetail the vottingDetail to set
	 */
	public void setVottingDetail(int vottingDetail) {
		this.vottingDetail = vottingDetail;
	}

	

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param userId the userId to set
	 */


	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the pollId
	 */
	public Long getPollId() {
		return pollId;
	}

	/**
	 * @param pollId the pollId to set
	 */
	public void setPollId(Long pollId) {
		this.pollId = pollId;
	}
	
	
	
	
}
