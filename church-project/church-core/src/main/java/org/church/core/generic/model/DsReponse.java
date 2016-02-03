package org.church.core.generic.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Default Response class.
 * 
 *  
 * @author charles.ma
 *
 */
public class DsReponse {

	/** The result code. */
	private Integer resultCode = 200;

	/** The result message. */
	private String resultMessage = "ok";

	/** The response id. */
	private final UUID responseId = UUID.randomUUID();

	/** The request id. */
	private String requestId;

	/** The remains. */
	private Integer remains;

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getRemains() {
		return remains;
	}

	public void setRemains(Integer remains) {
		this.remains = remains;
	}

	public UUID getResponseId() {
		return responseId;
	}
	
	/**
	 * Mount Json String to return.
	 * 
	 * @param obj - This object
	 * @return String json of object
	 * @throws Exception
	 */
	private String makeJson(Object obj) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_NULL);
		
		String jsonString = mapper.writeValueAsString(obj);

		return jsonString;

	}
	
	@Override
	public String toString() {

		String jsonString = null;

		try {

			jsonString = this.makeJson(this);

		} catch (Exception e) {

			jsonString = null;

		}

		return jsonString;
	}
	
	
	
}
