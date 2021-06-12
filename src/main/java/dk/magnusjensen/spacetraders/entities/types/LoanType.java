package dk.magnusjensen.spacetraders_sdk.entities.types;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.spacetraders_sdk.api.ApiCaller;
import dk.magnusjensen.spacetraders_sdk.entities.AccountEntity;
import dk.magnusjensen.spacetraders_sdk.entities.LoanEntity;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.ArrayList;

public class LoanType {
	private int amount;
	private boolean collateralRequired;
	private int rate;
	private int termInDays;
	private String type;

	public LoanType() {
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isCollateralRequired() {
		return collateralRequired;
	}

	public void setCollateralRequired(boolean collateralRequired) {
		this.collateralRequired = collateralRequired;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getTermInDays() {
		return termInDays;
	}

	public void setTermInDays(int termInDays) {
		this.termInDays = termInDays;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LoanEntity take(String token) throws Exception {
		JsonNode response = ApiCaller.POST("my/loans", token, null, RequestBody.create("{\"type\": \"" + this.getType() +"\"}", MediaType.parse("application/json")));
		return SpaceTraders.getMapper().treeToValue(response.get("loan"), LoanEntity.class);
	}

	public static ArrayList<LoanType> getLoans(String token) throws Exception {
		ArrayList<LoanType> loans = new ArrayList<>();

		JsonNode loansNode = ApiCaller.GET("types/loans", token, null).get("loans");

		if (loansNode.isArray()) {
			for (JsonNode loanNode : loansNode) {
				loans.add(SpaceTraders.getMapper().treeToValue(loanNode, LoanType.class));
			}
		}

		return loans;
	}

	@Override
	public String toString() {
		return this.getType();
	}
}
