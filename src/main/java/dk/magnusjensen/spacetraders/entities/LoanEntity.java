package dk.magnusjensen.spacetraders.entities;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.spacetraders.SpaceTraders;
import dk.magnusjensen.spacetraders.api.ApiCaller;
import dk.magnusjensen.spacetraders.util.TimeUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoanEntity {
	private String due;
	private String id;
	private int repaymentAmount;
	private String status;
	private String type;

	public LoanEntity() {
	}

	public ZonedDateTime getDue() {
		return TimeUtil.parseDateTimeString(this.due);
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(int repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static ArrayList<LoanEntity> getLoans(String token) throws Exception {
		ArrayList<LoanEntity> loans = new ArrayList<>();

		JsonNode loansNode = ApiCaller.GET("my/loans", token, null).get("loans");

		if (loansNode.isArray()) {
			for (JsonNode loanNode : loansNode) {
				loans.add(SpaceTraders.getMapper().treeToValue(loanNode, LoanEntity.class));
			}
		}

		return loans;
	}

	public LoanEntity payOff(String token) throws Exception {
		ApiCaller.PUT("my/loans/:loanId/", token, new ArrayList<>(List.of(this.getId())), RequestBody.create("{}", MediaType.parse("application/json")));
		return this;
	}

	@Override
	public String toString() {
		return this.getType();
	}
}
