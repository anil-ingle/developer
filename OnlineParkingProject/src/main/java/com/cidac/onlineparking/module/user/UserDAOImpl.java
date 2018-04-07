package com.cidac.onlineparking.module.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cidac.onlineparking.utilty.DBConnection;
import com.cidac.onlineparking.utilty.UserQuery;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<CityVO> selectCity() {
		List<CityVO> cityVOs=new ArrayList();

		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement psmt=con.prepareStatement(UserQuery.SELECT_USER_CITY);
			//execute the query
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				CityVO cityVO=new CityVO();
				cityVO.setCityId(rs.getInt("id"));
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

}
