package com.cidac.onlineparking.module.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cidac.onlineparking.utilty.DBConnection;
import com.cidac.onlineparking.utilty.UserQuery;

public class UserDAO{

	
	public List<CityVO> selectCity() {
		List<CityVO> cityVOs=new ArrayList();

		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement psmt=con.prepareStatement(UserQuery.SELECT_USER_CITY);
			//execute the query
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				CityVO cityVO=new CityVO();
				cityVO.setCityId(rs.getInt("city_id"));
				cityVO.setCityName(rs.getString("city_name"));
				cityVOs.add(cityVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cityVOs;
	}
	
	public List<CityAreaVO> getArea(Integer cityId){
		List<CityAreaVO> areaVOs=new ArrayList();
		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement psmt=con.prepareStatement(UserQuery.SLECT_CITY_AREA);
			//set input param 
			psmt.setInt(1, cityId);
			//execute the query
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				CityAreaVO areaVO=new CityAreaVO();
				areaVO.setAreaId(rs.getInt("area_id"));
				areaVO.setAreaName(rs.getString("area_name"));
				areaVOs.add(areaVO);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return areaVOs;
		
	}

	public List<UserBookSlotVO> getAreaSlot(Integer areaId){
		List<UserBookSlotVO> bookSlotVOs=new ArrayList();
		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement psmt=con.prepareStatement(UserQuery.SELECT_AREASLOT);
			//set input param 
			psmt.setInt(1, areaId);
			//execute the query
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				UserBookSlotVO vo=new UserBookSlotVO();
				vo.setfSlotId(rs.getInt("id"));
				vo.setSlotNumber(rs.getInt("slot_number"));
				vo.setIsReserved(rs.getInt("is_reserved"));
				vo.setfSlotId(rs.getInt("fslot_id"));
				bookSlotVOs.add(vo);
				
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bookSlotVOs;
		
	}
	public int registerUser(RegisterVO vo){
		int status=0;
		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement psmt=con.prepareStatement(UserQuery.REGISTER_USER);
			//set input param 
			psmt.setString(1, vo.getfName());
			psmt.setString(2, vo.getlName());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getMobileNumber());
			psmt.setString(5, vo.getCurrentTime());
			psmt.setString(6, vo.getDob());
			psmt.setInt(7, vo.getCityId());
			psmt.setString(8, vo.getPass());
			psmt.setString(9, vo.getGender());
			//execute the query
			 status=psmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	public RegisterVO login(RegisterVO registerVO){
		int status=0;
		RegisterVO vo=new RegisterVO();
		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement psmt=con.prepareStatement(UserQuery.LOGIN_USSER);
			//set input param 
			psmt.setString(1, registerVO.getEmail());
			psmt.setString(2, registerVO.getPass());
			
			//execute the query
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				
				vo.setId(rs.getInt("id"));
				vo.setfName(rs.getString("fname"));
				vo.setlName(rs.getString("lname"));
				vo.setEmail(rs.getString("email"));
				vo.setPass(rs.getString("password"));
				vo.setGender(rs.getString("gender"));
				vo.setMobileNumber(rs.getString("mobileNumber"));
			
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
		
	}
}
