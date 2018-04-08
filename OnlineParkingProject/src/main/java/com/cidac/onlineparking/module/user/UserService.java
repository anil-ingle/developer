package com.cidac.onlineparking.module.user;

import java.util.List;

public class UserServiceImpl implements UserService{

	@Override
	public List<CityVO> selectCity() {
		return new UserDAOImpl().selectCity();
	}

}
