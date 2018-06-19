package booking.com.cucumber.booking;

public enum Fields {

	VIEW_CITY_NAME("ss"),
	VIEW_CLASS_DATE("xp__dates-inner"),
	VIEW_CLASS_NEXT_MONTH("c2-button c2-button-further"),
	VIEW_ID_SELECT_DATE("1537228800000"),
	VIEW_CLASS_BUTTON_SUBMIT("sb-searchbox__button  "),
	VIEW_CLASS_LABEL_FILTERS("filter_label"),
	VIEW_CLASS_NAME_HOTEL_FILTERS("sr-hotel__name");
	
	private String name;
	
	private Fields(String name) {
		this.name = name;
	}
	
	public String getXPathFromField() {		
		return "//*[contains(@name, '"+name+"')]";		
	}
	
}
