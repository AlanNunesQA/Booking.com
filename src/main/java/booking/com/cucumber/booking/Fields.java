package booking.com.cucumber.booking;

public enum Fields {

	//Fields creation and constants
	
	VIEW_CITY_NAME("ss"),
	VIEW_CLASS_DATE("xp__dates-inner"),
	VIEW_CLASS_NEXT_MONTH("c2-button c2-button-further"), 
	VIEW_ID_SELECT_DATE("1537228800000"), 
	VIEW_CLASS_BUTTON_SUBMIT("sb-searchbox__button  "), 
	VIEW_CLASS_LABEL_FILTERS("filter_label"), 
	VIEW_CLASS_NAME_HOTEL_FILTERS("sr-hotel__name"), 
	VIEW_APPLIED_FILTER("Filters have been applied");

	private String name;

	private Fields(String name) {
		this.name = name;
	}

	public String getXPathFromCityName() {
		return "//*[contains(@name, '" + name + "')]";
	}

	public String getXPathFromClassDate() {
		return "//*[contains(@class, '" + name + "')]";
	}

	public String getXPathFromNextMonth() {
		return "//*[contains(@class, '" + name + "')]";
	}

	public String getXPathFromSelectDate() {
		return "//*[contains(@data-id, '" + name + "')]";
	}

	public String getXPathFromSubmit() {
		return "//*[contains(@class, '" + name + "')]";
	}

	public String getXPathFromLabelFilter(String filter) {
		return "//*[contains(@class, '" + name + "') and contains(text(), '" + filter + "')]";
	}

	public String getXPathFromAppliedFilter() {
		return "//*[contains(text(), '" + name + "')]";
	}

	public String getXPathFromHotelName(String hotelname) {
		return "//*[contains(@class, '" + name + "') and contains(text(), '" + hotelname + "')]";
	}

}
