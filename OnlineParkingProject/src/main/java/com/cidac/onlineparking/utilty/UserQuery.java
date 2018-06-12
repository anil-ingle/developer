package com.cidac.onlineparking.utilty;

public class UserQuery {
	public static final String SELECT_USER_CITY="SELECT city_id,city_name FROM cities";
	public static final String SELECT_LOGIN_QUERY="SELECT * FROM login_table where user_name=? and password=?";
	public static final String SELECT_USER_CITY1="SELECT city_id,city_name FROM cities";
	
	public static final String SLECT_CITY_AREA="select area_id,area_name from cityarea where city_id=?";
	
	public static final String SELECT_AREASLOT="select id,slot_number,is_reserved,fslot_id from onlineparking.tab_slot_datail where fslot_id=(select slot_id from tab_slot where fcity_id=?)";
	
	public static final String REGISTER_USER="insert into user_info (fname,lname,email,mobileNumber,createdDate,dob,cityId,password,gender) values(?,?,?,?,?,?,?,?,?)";
	
	public static final String LOGIN_USSER="select id,fname,lname,email,mobileNumber,password,gender from user_info where email=? and password=?";

}