package travelLinker.viewModel;

import java.util.Date;

import travelLinker.entity.SubscriptionPack;


public class SubscriptionViewModel {
	private int price;
    private Date startDate;
    private Date endDate;
    private SubscriptionPack type;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public SubscriptionPack getType() {
		return type;
	}
	public void setType(SubscriptionPack type) {
		this.type = type;
	}
    
    

}
