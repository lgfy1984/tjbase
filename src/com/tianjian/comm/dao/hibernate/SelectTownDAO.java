package com.tianjian.comm.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.dao.ISelectTownDAO;
import com.tianjian.util.db.DBTools;


public class SelectTownDAO  implements ISelectTownDAO {
	public String findTownId(String staffid)  {
	 String comm_clt_id="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		String sql = null;
		String sql1 = null;
		sql1 = "SELECT VR.SDOT_ID,VR.SDO_VALUE FROM SECURITY.SECURITY_DATA_OBJECT_VS_ROLES VR WHERE VR.SECURITY_STAFF_BASEINFO_ID='"+ staffid + "'";
		try {
			conn = DBTools.getConnection();
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				sql = "SELECT A.COMM_CLT_ID FROM COMM.COMM_CONFIG_LOCATION_VILLAGE A WHERE   A.ID='"+rs1.getString(2) + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					comm_clt_id=rs.getString(1); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBTools.close(rs);
			DBTools.close(pstmt);
			DBTools.close(conn);
		}
		return comm_clt_id;
	}
 

	 
	public List<?> findListByHql(String itemCode,String staffid)  {
		if ("".equals(itemCode)) {
			return null;
		}
		List town = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		String sql = null;
		String sql1 = null;
		boolean flag = false;
		String townid=findTownId(staffid);
		CommConfigLocationTown commconfigtown = new CommConfigLocationTown();
		sql1 = "SELECT VR.SDOT_ID,VR.SDO_VALUE FROM SECURITY.SECURITY_DATA_OBJECT_VS_ROLES VR WHERE VR.SECURITY_STAFF_BASEINFO_ID='"
				+ staffid + "'";
		try {
			conn = DBTools.getConnection();
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			commconfigtown.setId("");
			commconfigtown.setItemName("");
			town.add(commconfigtown);
			while (rs1.next()) {
				if ("4".equals(rs1.getString(1))) {
					sql = "SELECT ID,ITEM_NAME FROM COMM.COMM_CONFIG_LOCATION_TOWN WHERE COMM_CONFIG_LOCATION_ID = '"
							+ itemCode
							+ "' AND ID='"
							+ rs1.getString(2)+ "' ORDER BY ID";
					 
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						commconfigtown = new CommConfigLocationTown();
						commconfigtown.setId(rs.getString("ID"));
						commconfigtown.setItemName(rs.getString("ITEM_NAME"));
						town.add(commconfigtown);
					}
				}else 	if ("5".equals(rs1.getString(1))) {
					sql = "SELECT ID,ITEM_NAME FROM COMM.COMM_CONFIG_LOCATION_TOWN WHERE ID = '" + townid + "'  ORDER BY ID";
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					commconfigtown = new CommConfigLocationTown();
					commconfigtown.setId(rs.getString("ID"));
					commconfigtown.setItemName(rs.getString("ITEM_NAME"));
					town.add(commconfigtown);
				}
			} else {
					flag = true;
					sql = "SELECT ID,ITEM_NAME FROM COMM.COMM_CONFIG_LOCATION_TOWN WHERE COMM_CONFIG_LOCATION_ID = '"
							+ itemCode + "'  ORDER BY ID";
					
				}
			}
			if (flag) {
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					commconfigtown = new CommConfigLocationTown();
					commconfigtown.setId(rs.getString("ID"));
					commconfigtown.setItemName(rs.getString("ITEM_NAME"));
					 
					town.add(commconfigtown);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBTools.close(rs);
			DBTools.close(pstmt);
			DBTools.close(conn);
		}
		return town;
	}
 
	public List<?> findVillageListByHql(String itemCode,String staffid)  {
		if("".equals(itemCode)) {
			return null;
		}
		List village = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		String sql = null;
		String sql1 = null;
		CommConfigLocationVillage commconfigvillage = new CommConfigLocationVillage();
		commconfigvillage.setItemCode("");
		commconfigvillage.setItemName("");
		village.add(commconfigvillage);
		sql1 = "SELECT VR.SDOT_ID,VR.SDO_VALUE FROM SECURITY.SECURITY_DATA_OBJECT_VS_ROLES VR WHERE VR.SECURITY_STAFF_BASEINFO_ID='" + staffid + "'";
		
		try {
			conn = DBTools.getConnection();
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				if ("5".equals(rs1.getString(1))) {
					 sql = "SELECT V.ITEM_CODE,V.ITEM_NAME FROM COMM.COMM_CONFIG_LOCATION_VILLAGE V WHERE V.COMM_CLT_ID = '"+itemCode+"' AND V.ID='"+rs1.getString(2)+"' ORDER BY V.ID";
				}else{
					sql = "SELECT V.ITEM_CODE,V.ITEM_NAME FROM COMM.COMM_CONFIG_LOCATION_VILLAGE V WHERE V.COMM_CLT_ID = '"+itemCode+"'  ORDER BY V.ID";
				}
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				 
				while(rs.next()) {
					commconfigvillage = new CommConfigLocationVillage();
					commconfigvillage.setItemCode(rs.getString("ITEM_CODE"));
					commconfigvillage.setItemName(rs.getString("ITEM_NAME"));
					village.add(commconfigvillage);
				}
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBTools.close(rs);
			DBTools.close(pstmt);
			DBTools.close(conn);
		}
		return village;
	}
	
	 
	 
	
}
