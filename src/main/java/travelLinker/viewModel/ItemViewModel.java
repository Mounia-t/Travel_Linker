package travelLinker.viewModel;

import java.util.Date;

import travelLinker.entity.Item;

public class ItemViewModel {

	private Long id;
	private Long journeyId;
	private Long accomodationId;
	private Long transportId;
	private Long restaurantId;
	private Long subscriptionId;
	private int quantity;
	private Date startDate;
	private Date endDate;
	private float price;
	private Item.ItemType itemType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public Item.ItemType getItemType() {
		return itemType;
	}

	public void setItemType(Item.ItemType itemType) {
		this.itemType = itemType;
	}

	public Long getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(Long journeyId) {
		this.journeyId = journeyId;
	}

	public Long getAccomodationId() {
		return accomodationId;
	}

	public void setAccomodationId(Long accomodationId) {
		this.accomodationId = accomodationId;
	}

	public Long getTransportId() {
		return transportId;
	}

	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemViewModel [id=");
		builder.append(id);
		builder.append(", journeyId=");
		builder.append(journeyId);
		builder.append(", accomodationId=");
		builder.append(accomodationId);
		builder.append(", transportId=");
		builder.append(transportId);
		builder.append(", restaurantId=");
		builder.append(restaurantId);
		builder.append(", subscriptionId=");
		builder.append(subscriptionId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", price=");
		builder.append(price);
		builder.append(", itemType=");
		builder.append(itemType);
		builder.append("]");
		return builder.toString();
	}

}
