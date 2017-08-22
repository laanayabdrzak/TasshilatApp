package m2t.com.tashilatappprototype.Common.POJO;

public class Invoice {

	String ref, amount, status, date;
	int icon;

	public Invoice(String ref, String amount, String status, String date, int icon) {
		this.ref = ref;
		this.amount = amount;
		this.status = status;
		this.date = date;
		this.icon = icon;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
}
