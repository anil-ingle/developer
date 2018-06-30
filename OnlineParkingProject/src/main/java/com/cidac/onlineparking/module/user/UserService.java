package com.cidac.onlineparking.module.user;

import java.sql.SQLException;
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
	
	public RegisterVO login(RegisterVO registerVO){
		return dao.login(registerVO);
	}
	public boolean slotBookUsingWolet(WolletBookVO bookVO){
		int status=0;
		try {
			status = dao.slotBookUsingWolet(bookVO);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(status==1) {
			return true;
		}else {
			return false;
		}
	}
}
