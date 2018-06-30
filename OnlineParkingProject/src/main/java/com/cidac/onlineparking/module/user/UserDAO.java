package com.cidac.onlineparking.module.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cidac.onlineparking.utilty.CommonUtil;
import com.cidac.onlineparking.utilty.DBConnection;
import com.cidac.onlineparking.utilty.UserQuery;

public class UserDAO {

	public List<CityVO> selectCity() {
		List<CityVO> cityVOs = new ArrayList<CityVO>();

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(UserQuery.SELECT_USER_CITY);
			// execute the query
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				CityVO cityVO = new CityVO();
				cityVO.setCityId(rs.getInt("city_id"));
				cityVO.setCityName(rs.getString("city_name"));
				cityVOs.add(cityVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityVOs;
	}

	public List<CityAreaVO> getArea(Integer cityId) {
		List<CityAreaVO> areaVOs = new ArrayList<CityAreaVO>();
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(UserQuery.SLECT_CITY_AREA);
			// set input param
			psmt.setInt(1, cityId);
			// execute the query
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				CityAreaVO areaVO = new CityAreaVO();
				areaVO.setAreaId(rs.getInt("area_id"));
				areaVO.setAreaName(rs.getString("area_name"));
				areaVOs.add(areaVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaVOs;

	}

	public List<UserBookSlotVO> getAreaSlot(Integer areaId) {
		List<UserBookSlotVO> bookSlotVOs = new ArrayList<UserBookSlotVO>();
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(UserQuery.SELECT_AREASLOT);
			// set input param
			psmt.setInt(1, areaId);
			// execute the query
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				UserBookSlotVO vo = new UserBookSlotVO();
				vo.setfSlotId(rs.getInt("id"));
				vo.setSlotNumber(rs.getInt("slot_number"));
				vo.setIsReserved(rs.getInt("is_reserved"));
				vo.setfSlotId(rs.getInt("fslot_id"));
				bookSlotVOs.add(vo);

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookSlotVOs;

	}

	public int registerUser(RegisterVO vo) {
		int status = 0;
		int count = 0;
		int wolletStatus = 0;
		try {
			Connection con = DBConnection.getConnection();
			// PreparedStatement psmt=con.prepareStatement(UserQuery.REGISTER_USER);
			PreparedStatement psmt = con.prepareStatement(UserQuery.REGISTER_USER, Statement.RETURN_GENERATED_KEYS);
			// set input param
			psmt.setString(1, vo.getfName());
			psmt.setString(2, vo.getlName());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getMobileNumber());
			psmt.setString(5, vo.getCurrentTime());
			psmt.setString(6, vo.getDob());
			psmt.setInt(7, vo.getCityId());
			psmt.setString(8, vo.getPass());
			psmt.setString(9, vo.getGender());
			psmt.setInt(10, 1);
			// execute the query
			status = psmt.executeUpdate();
			ResultSet rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				count = rs.getInt(1);

			}
			if (count != 0) {
				wolletStatus = this.createUserWolet(count);
			}
			if (wolletStatus == 0) {
				// con.rollback();
				count = 0;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	private int createUserWolet(int count) {
		int statusForWolet = 0;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(UserQuery.CREATE_USER_WOLET);
			// set input param
			psmt.setInt(1, 10000);
			psmt.setInt(2, count);

			// execute the query
			statusForWolet = psmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusForWolet;

	}

	public RegisterVO login(RegisterVO registerVO) {
		RegisterVO vo = new RegisterVO();
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(UserQuery.LOGIN_USSER);
			// set input param
			psmt.setString(1, registerVO.getEmail());
			psmt.setString(2, registerVO.getPass());

			// execute the query
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {

				vo.setId(rs.getInt("id"));
				vo.setfName(rs.getString("fname"));
				vo.setlName(rs.getString("lname"));
				vo.setEmail(rs.getString("email"));
				vo.setRoll(rs.getInt("roll"));
				vo.setGender(rs.getString("gender"));
				vo.setMobileNumber(rs.getString("mobileNumber"));
				vo.setTotalAmount(rs.getDouble("totalamount"));

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;

	}

	public int slotBookUsingWolet(WolletBookVO bookVO) throws SQLException {
		int statusForWolet = 0;
		boolean paymentFlag = this.userWoletDeduction(bookVO);
		System.out.println("paymentFlag comming " + paymentFlag);
		if (paymentFlag) {
			System.out.println("after uf");
			boolean statusRecord = this.recordOwnerWoller(bookVO);
			System.out.println("statusRecord comming  " + statusRecord);
			if (statusRecord) {

				boolean flag = this.slotBook(bookVO.getBookedSlots(), bookVO.getAreaId());
				System.out.println("original slot booking comming..." + flag);

				if (flag) {
					try {
						Connection con = DBConnection.getConnection();
						PreparedStatement psmt = con.prepareStatement(UserQuery.USER_BOOK_HISTORY);

						// set input param
						psmt.setInt(1, bookVO.getUserId());
						psmt.setString(2, bookVO.getBookedSlots());
						psmt.setInt(3, bookVO.getAreaId());
						psmt.setDouble(4, bookVO.getwBill());
						psmt.setString(5, bookVO.getTimeTaken());
						psmt.setString(6, new Date() + "");

						// execute the query
						statusForWolet = psmt.executeUpdate();

					} catch (SQLException se) {
						se.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return statusForWolet;

	}

	/*
	 * private void recordOwnerWoller(WolletBookVO bookVO) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */

	private boolean recordOwnerWoller(WolletBookVO bookVO) {
		System.out.println("recordOwnerWoller method enter...");
		WolletStatusVO statusVO = this.creditOwnerWollet(bookVO);
		System.out.println("recordOwnerWoller  " + statusVO);
		boolean statusRecord = false;
		if (statusVO.isSuccess()) {
			try {
				Connection con = DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(UserQuery.OWNER_RECORD);

				// set input param
				psmt.setInt(1, bookVO.getUserId());
				psmt.setString(2, new Date() + "");
				psmt.setDouble(3, bookVO.getwBill());
				psmt.setInt(4, statusVO.getOwnerId());
				psmt.setInt(5, 0);
				// execute the query
				int ret = psmt.executeUpdate();
				if (ret == 1) {
					statusRecord = true;

				}

			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return statusRecord;

	}

	private WolletStatusVO creditOwnerWollet(WolletBookVO bookVO) {
		System.out.println("creditOwnerWollet  enter");
		WolletStatusVO statusVO = this.getOwnerWolletAmount(bookVO);
		System.out.println(" credit vo " + statusVO);
		statusVO.setSuccess(false);
		if (statusVO.getOwnerId() != 0) {
			try {
				Connection con = DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(UserQuery.CREDIT_OWNER_WOLLET);

				// set input param
				psmt.setDouble(1, (statusVO.getAmount() + bookVO.getwBill()));
				psmt.setInt(2, statusVO.getOwnerId());

				// execute the query
				int ret = psmt.executeUpdate();
				if (ret == 1) {
					statusVO.setSuccess(true);

				}

			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return statusVO;

	}

	private WolletStatusVO getOwnerWolletAmount(WolletBookVO bookVO) {
		System.out.println("getOwnerWolletAmount ");
		WolletStatusVO vo = new WolletStatusVO();
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(UserQuery.OWNER_WOLET_AMOUNT);
			// this.getOwnerWolletAmount(bookVO);
			// set input param
			psmt.setDouble(1, bookVO.getAreaId());

			// execute the query
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {

				vo.setOwnerId(rs.getInt("ownerId"));
				vo.setAmount(rs.getDouble("amount"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("return vo " + vo);
		return vo;

	}

	private boolean userWoletDeduction(WolletBookVO bookVO) {
		boolean flag = true;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(UserQuery.USER_WOLLET_DEDUCTION);

			// set input param
			psmt.setDouble(1, (bookVO.getwTotal() - bookVO.getwBill()));
			psmt.setInt(2, bookVO.getUserId());

			// execute the query
			int ret = psmt.executeUpdate();
			if (ret == 0) {
				flag = false;

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	private boolean slotBook(String bookedSlots, Integer areaId) throws SQLException {
		List<Integer> ids = CommonUtil.csvIds(bookedSlots);
		System.out.println("ids   " + ids);
		boolean flag = true;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			try {

				for (Integer id : ids) {
					PreparedStatement psmt = con.prepareStatement(UserQuery.BOOK_SLOTS);
					// con.setAutoCommit(false);
					System.out.println("for loop..." + id);
					// set input param
					psmt.setInt(1, areaId);
					psmt.setInt(2, id);
					System.out.println("areaId  " + areaId + "   id  " + id);

					// execute the query
					int ret = psmt.executeUpdate();
					System.out.println("ret--->   " + ret);

				}

			} catch (SQLException se) {
				flag = false;
				se.printStackTrace();
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			}

		} catch (SQLException se) {
			flag = false;
			se.printStackTrace();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;

	}
}
