package com.cidac.onlineparking.utilty;

public class UserQuery {
	public static final String SELECT_USER_CITY="SELECT city_id,city_name FROM cities";
	
	public static final String SLECT_CITY_AREA="select area_id,area_name from cityarea where city_id=?";

}
