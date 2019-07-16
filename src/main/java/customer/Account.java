package customer;

public class Account {
	private int primaryUserId;
	private String status;
	private float balance;
	private int jointUserId;
	
	public Account() {}

	public int getPrimaryUserId() {
		return primaryUserId;
	}

	public void setPrimaryUserId(int primaryUserId) {
		this.primaryUserId = primaryUserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getJointUserId() {
		return jointUserId;
	}

	public void setJointUserId(int jointUserId) {
		this.jointUserId = jointUserId;
	};
}
