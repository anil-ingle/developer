package com.cidac.onlineparking.module.user;

import java.util.List;

public class UserService{

	
	public List<CityVO> selectCity() {
		return new UserDAO().selectCity();
	}
	public List<CityAreaVO> getArea(Integer cityId){
		return new UserDAO().getArea(cityId);
	}

}
