package com.cidac.onlineparking.module.user;

import java.util.List;

public class UserService{
	UserDAO dao=new UserDAO();
	
	public List<CityVO> selectCity() {
		return dao.selectCity();
	}
	public List<CityAreaVO> getArea(Integer cityId){
		return dao.getArea(cityId);
	}
	public List<UserBookSlotVO> getAreaSlot(Integer areaId){
		return dao.getAreaSlot(areaId);
	}
	public boolean registerUser(RegisterVO vo){
		int status=dao.registerUser(vo);
		if(status==0) {
			return false;
		}else {
			return true;
		}
		
	}
}
