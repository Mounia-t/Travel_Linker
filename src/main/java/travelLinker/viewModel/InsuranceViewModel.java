package travelLinker.viewModel;

import java.util.Date;

public class InsuranceViewModel {
	
	public String type;
	public Date startDate;
	public Date endDate;
	public String description;
	public float price;
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsuranceViewModel [type=");
		builder.append(type);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		builder.append(price);
		builder.append(price);
		return builder.toString();
	}

	public String getType() {
		return type;
	}
	
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
