package com.tianjian.util.db;

import java.sql.*;


public class creattable {

	/**
	 * 向数据库中插入一张新的表
	 * @param tablename
	 * @return
	 */
	public int insertdb(String tablename) {
		
		
		/*GetConnection ccn = new GetConnection();
		Connection conn = null;
		Connection conntwo = null;*/
		/*
		if(conntwo != null){
			System.out.println("conntwo-------------not null---");
		}else{
			System.out.println("conntwo-------------null---");
		}
		if(conn != null){
			System.out.println("conntwo-------------not null---");
		}else{
			System.out.println("conntwo-------------null---");
		}*/
		try {
			/*ResourceBundle bundle = ResourceBundle.getBundle("/conf/comm/creattable");
			String url = bundle.getString("url");
			String port = bundle.getString("port");
			String sid = bundle.getString("sid");
			String user = bundle.getString("user");
			String passwd = bundle.getString("passwd");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@"+url+":"+port+":"+sid,user,passwd);*/
			
			String lab_master_tablename = "T0001"+tablename;
			String lab_report_tablename = "T0002"+tablename;
			String lab_detail_tablename = "T0003"+tablename;
			String exam_report_tablename = "T0004"+tablename;
			String exam_master_tablename = "T0005"+tablename;
			String exam_detail_tablename = "T0006"+tablename;
			
			boolean lmflag = this.creat_lab_master_table(lab_master_tablename);    //判断表是否创建成功，如果成功创建同义词
			if(lmflag == false){
				return 1;
			}
			
			boolean lmrlag = this.creat_lab_report_table(lab_report_tablename);
			if(lmrlag == false){
				return 2;
			}
			
			boolean lmdlag = this.creat_lab_detail_table(lab_detail_tablename);
			if(lmdlag == false){
				return 3;
			}
			
			boolean erlag = this.creat_exam_report_table(exam_report_tablename);
			if(erlag == false){
				return 4;
			}
			
			boolean emflag = this.creat_exam_master_table(exam_master_tablename);
			if(emflag == false){
				return 5;
			}
			
			boolean edflag = this.creat_exam_detail_table(exam_detail_tablename);
			if(edflag == false){
				return 6;
			}		
		
			
			/* url = bundle.getString("url");
			 port = bundle.getString("port");
			 sid = bundle.getString("sid");
			 user = "system";
			 passwd = "manager";
			Connection conntwo = DriverManager.getConnection("jdbc:oracle:thin:@"+url+":"+port+":"+sid,user,passwd);*/
			//DBConnect dbc = new DBConnect(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//Connection conntwo = dbc.getConnection();
			
			
			
			boolean samewordflag = this.creatsynonym(lab_master_tablename); 
			if(samewordflag == false){
				return 0;
			}
			samewordflag = this.creatsynonym(lab_report_tablename); 
			if(samewordflag == false){
				return 0;
			}
			samewordflag = this.creatsynonym(lab_detail_tablename); 
			if(samewordflag == false){
				return 0;
			}
			samewordflag = this.creatsynonym(exam_report_tablename); 
			if(samewordflag == false){
				return 0;
			}
			samewordflag = this.creatsynonym(exam_master_tablename); 
			if(samewordflag == false){
				return 0;
			}
			samewordflag = this.creatsynonym(exam_detail_tablename); 
			if(samewordflag == false){
				return 0;
			}
			
			return 100;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 判断表是否存在
	 * @param conn
	 * @param testTable
	 * @return
	 */
	private boolean istableexist(String tablename) {
		Connection conn = null;
		Statement ss = null;
		try {
			conn = DBTools.getConnection();
			ss = conn.createStatement();
			ss.execute("select count(*) from " + tablename);
		} catch (SQLException e) {
			return false;
		} finally{
			DBTools.close(ss);
			DBTools.close(conn);
		}
		return true;
	}

	/**
	 * 这个方法没有使用
	 * 创建表
	 * @param conn
	 * @param tablename
	 * @return
	 */
	/**
	private boolean creattable(Connection conn, String tablename) {
		 
		String creattabelstr = "create table "+tablename+"( "
				+ "userid varchar2(8), "
				+ "username varchar2(16), "
				+ "constraint pk_"+tablename+" primary key ( "
				+ " userid " + " ) " 
				+ " using index " + " pctfree 20 "
				+ " storage  (  initial 32K  next 16K "
				+ " minextents 1  maxextents unlimited  pctincrease 0 "
				+ " )  tablespace TSP_HEALTH  ) "
				+ " pctfree 5 "
				+ " pctused 90  storage  ( initial 256K "
				+ " next 128K  minextents 1  maxextents unlimited "
				+ " pctincrease 0  )";
		
		String grantsql = " grant select,insert,update,delete "
				+ " on "+tablename
				+ " to role_pid," 
				+ " role_nurse, "
				+ " role_doctor";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(creattabelstr);             //
			stmt.executeUpdate(grantsql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	*/
	
	/**
	 * 创建同义词
	 * @param conn
	 * @param tablename
	 * @return
	 */
	private boolean creatsynonym(String tablename) {
		/*创建同义词的sql语句*/
		System.out.println("table------------------------" + tablename);
		Connection conn = null;
		Statement ss = null;
		
		String creatsynonymstr = "create public synonym " + tablename + " for archives."+tablename;
		try {
			conn = DBTools.getConnection();
			ss = conn.createStatement();
			ss.execute(creatsynonymstr);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			DBTools.close(ss);
			DBTools.close(conn);
		}
		return true;
	}
	
	/**
	 * 创建表 lab_sheet_master_records(检验项目主记录表)
	 * @param conn
	 * @param tablename
	 * @return
	 * @throws SQLException 
	 */
	private boolean creat_lab_master_table(String tablename) throws SQLException {
		
		System.out.println("table============" + tablename);
		
		Connection conn = null;
		Statement ss = null;
		
		/*创建表的sql语句*/
		String creattabelstr = "create table "+tablename
		+ " ( "
		+ " seq_no					varchar2(32) not null, "
		+ " user_id					varchar2(40) not null, "
		+ " human_from				varchar2(20), "
		+ " hospital_code				varchar2(40), "
		+ " hospital_seq_no				varchar2(32), "
		+ " lab_class				varchar2(20), "
		+ " lab_sun_class				varchar2(20), "
		+ " do_dept_code				varchar2(20), "
		+ " diag					varchar2(1000), "
		+ " item_code				varchar2(20), "
		+ " lab_time				date, "
		+ " lab_doctor				varchar2(20), "
		+ " lab_machine				varchar2(60), "
		+ " specimen				varchar2(40), "
		+ " notes_of_specimen			varchar2(60), "
		+ " report_doctor				varchar2(20), "
		+ " report_time				date, "
		+ " comments				varchar2(40), "
		+ " constraint pk_"+tablename
		+ " primary key "
		+ " 	( "
		+ " 	seq_no "
		+ " 	) "
		+ " using index "
		+ " 	pctfree		5 "
		+ " 	storage "
		+ " 	( "
		+ " 	initial		4K "
		+ " 	next			2K "
		+ " 	minextents		1 "
		+ " 	maxextents		unlimited "
		+ " 	pctincrease		0 "
		+ " 	) "
		+ " 	tablespace tsp_archives "
		+ " ) "
		+ " pctfree				5 "
		+ " pctused				90 "
		+ " storage "
		+ " ( "
		+ " initial				4K "
		+ " next					2K "
		+ " minextents				1 "
		+ " maxextents				unlimited "
		+ " pctincrease				0 "
		+ " ) ";
		/*创建表后授权限的sql语句*/
		String grantsql = " grant select,insert,update,delete "
							+ " on "+tablename
							+ " to public ";
		try {
			conn = DBTools.getConnection();
			ss = conn.createStatement();
			ss.execute(creattabelstr);
			ss.execute(grantsql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			DBTools.close(ss);
			DBTools.close(conn);
		}
		return true;
	}
	
	/**
	 * 创建表 Lab_sheet_report_records(检验报告记录表)
	 * @param conn
	 * @param tablename
	 * @return
	 * @throws SQLException 
	 */
	private boolean creat_lab_report_table(String tablename) throws SQLException {
		System.out.println("table============" + tablename);
		Connection conn = null;
		Statement ss = null;
		/*创建表的sql语句*/
		String creattabelstr = "create table "+tablename
			+ " ("
			+ " seq_no					varchar2(32) not null, "
			+ " parent_seq_no				varchar2(32), "
			+ " sub_content_seq				varchar2(3), "
			+ " sub_content				varchar2(40), "
			+ " sub_content_detail			varchar2(1000), "
			+ " comments				varchar2(40), "
			+ " constraint pk_"+tablename
			+ " primary key "
			+ "	( "
			+ "	seq_no "
			+ "	) "
			+ " using index "
			+ " pctfree		5 "
			+ " storage "
			+ " ( "
			+ " initial		4K "
			+ " next			2K "
			+ " minextents		1 "
			+ " maxextents		unlimited "
			+ " pctincrease		0 "
			+ " ) "
			+ " tablespace tsp_archives "
			+ " ) "
			+ " pctfree				5 "
			+ " pctused				90 "
			+ " storage "
			+ " ( "
			+ " initial				4K "
			+ " next					2K "
			+ " minextents				1 "
			+ " maxextents				unlimited "
			+ " pctincrease				0 "
			+ " ) ";
		/*创建表后授权限的sql语句*/
		String grantsql = " grant select,insert,update,delete "
			+ " on "+tablename
			+ " to	public ";
		try {
			conn = DBTools.getConnection();
			ss = conn.createStatement();
			ss.execute(creattabelstr);
			ss.execute(grantsql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			DBTools.close(ss);
			DBTools.close(conn);
		}
		return true;
	}
	
	/**
	 * 创建表 Lab_sheet_detail_records(检验项目记录表)
	 * @param conn
	 * @param tablename
	 * @return
	 * @throws SQLException 
	 */
	private boolean creat_lab_detail_table(String tablename) throws SQLException {
		System.out.println("table============" + tablename);
		Connection conn = null;
		Statement ss = null;
		/*创建表的sql语句*/
		String creattabelstr = "create table "+tablename
			+ " ( "
			+ " seq_no					varchar2(32) not null, "
			+ " parent_seq_no				varchar2(32), "
			+ " sub_item_code				varchar2(20), "
			+ " print_seq				number(5), "
			+ " result_type				number(1), "
			+ " value					varchar2(500), "
			+ " value_units				varchar2(20), "
			+ " abnormal_flag				varchar2(10), "
			+ " print_context				varchar2(50), "
			+ " comments				varchar2(8), "
			+ " constraint pk_"+tablename
			+ " primary key "
			+ "	( "
			+ " seq_no "
			+ " ) "
			+ " using index "
			+ "	pctfree		5 "
			+ "	storage "
			+ " ( "
			+ " initial		4K "
			+ " next			2K "
			+ " minextents		1 "
			+ " maxextents		unlimited "
			+ " pctincrease		0 "
			+ " ) "
			+ " tablespace tsp_archives "
			+ " ) "
			+ " pctfree				5 "
			+ " pctused				90 "
			+ " storage "
			+ " ( "
			+ " initial				4K "
			+ " next					2K "
			+ " minextents				1 "
			+ " maxextents				unlimited "
			+ " pctincrease				0 "
			+ " ) ";
		/*创建表后授权限的sql语句*/
		String grantsql = "grant select,insert,update,delete "
						+ " on "+tablename
						+ " to	public ";
		try {
			conn = DBTools.getConnection();
			ss = conn.createStatement();
			ss.execute(creattabelstr);
			ss.execute(grantsql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBTools.close(ss);
			DBTools.close(conn);
		}
		return true;
	}
	
	/**
	 * 创建表 Exam_sheet_report_records(检查报告记录表)
	 * @param conn
	 * @param tablename
	 * @return
	 * @throws SQLException 
	 */
	private boolean creat_exam_report_table(String tablename) throws SQLException {
		System.out.println("table============" + tablename);
		Connection conn = null;
		Statement ss = null;
		/*创建表的sql语句*/
		String creattabelstr = "create table "+tablename
			+ " ("
			+ " seq_no					varchar2(32) not null, "
			+ " parent_seq_no				varchar2(32), "
			+ " sub_content_seq				varchar2(3), "
			+ " sub_content				varchar2(40), "
			+ " sub_content_detail			varchar2(1000), "
			+ " comments				varchar2(40), "
			+ " constraint pk_"+tablename
			+ "	primary key "
			+ "	( "
			+ "	seq_no "
			+ "	) "
			+ " using index "
			+ "	pctfree		5 "
			+ " storage "
			+ " ( "
			+ " initial		4K "
			+ " next			2K "
			+ " minextents		1 "
			+ " maxextents		unlimited "
			+ " pctincrease		0 "
			+ " ) "
			+ " tablespace tsp_archives "
			+ " ) "
			+ " pctfree				5 "
			+ " pctused				90 "
			+ " storage "
			+ " ( "
			+ " initial				4K "
			+ " next					2K "
			+ " minextents				1 "
			+ " maxextents				unlimited "
			+ " pctincrease				0 "
			+ " ) ";
		/*创建表后授权限的sql语句*/
		String grantsql = " grant select,insert,update,delete "
			           + " on "+tablename
			           + " to	public ";
		try {
			conn = DBTools.getConnection();
			ss = conn.createStatement();
			ss.execute(creattabelstr);
			ss.execute(grantsql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			DBTools.close(ss);
			DBTools.close(conn);
		}
		return true;
	}
	
	/**
	 * 创建表 exam_sheet_detail_records(检查项目记录表)
	 * @param conn
	 * @param tablename
	 * @return
	 * @throws SQLException 
	 */
	private boolean creat_exam_detail_table(String tablename) throws SQLException {
		System.out.println("table============" + tablename);
		Connection conn = null;
		Statement ss = null;
		/*创建表的sql语句*/
		String creattabelstr = "create table "+tablename
			+ " ( "
			+ " seq_no					varchar2(32) not null, "
			+ " parent_seq_no				varchar2(32), "
			+ " sub_item_code				varchar2(20), "
			+ " print_seq				number(5), "
			+ " result_type				number(1), "
			+ " value					varchar2(500), "
			+ " value_units				varchar2(20), "
			+ " abnormal_flag				varchar2(10), "
			+ " print_context				varchar2(50), "
			+ " comments				varchar2(8), "
			+ " constraint pk_"+tablename
			+ " primary key "
			+ "	( "
			+ "	seq_no "
			+ "	) "
			+ " using index "
			+ " pctfree		5 "
			+ " storage "
			+ " ( "
			+ " initial		4K "
			+ " next			2K "
			+ " minextents		1 "
			+ " maxextents		unlimited "
			+ " pctincrease		0 "
			+ " ) "
			+ " tablespace tsp_archives "
			+ " ) "
			+ "	pctfree				5 "
			+ " pctused				90 "
			+ " storage "
			+ " ( "
			+ " initial				4K "
			+ " next					2K "
			+ " minextents				1 "
			+ " maxextents				unlimited "
			+ " pctincrease				0 "
			+ " ) ";
		/*创建表后授权限的sql语句*/
		String grantsql = " grant select,insert,update,delete "
						+ " on "+tablename
						+ " to	public ";
		try {
			conn = DBTools.getConnection();
			ss = conn.createStatement();
			ss.execute(creattabelstr);
			ss.execute(grantsql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			DBTools.close(ss);
			DBTools.close(conn);
		}
		return true;
	}
	
	/**
	 * 创建表 exam_sheet_master_records(检查结果主记录表)
	 * @param conn
	 * @param tablename
	 * @return
	 * @throws SQLException 
	 */
	private boolean creat_exam_master_table(String tablename) throws SQLException {
		System.out.println("table============" + tablename);
		Connection conn = null;
		Statement ss = null;
		/*创建表的sql语句*/
		String creattabelstr = "create table "+tablename
			+ " ( "
			+ " seq_no					varchar2(32) not null, "
			+ " user_id					varchar2(40) not null, "
			+ " human_from				varchar2(20), "
			+ " hospital_code				varchar2(40), "
			+ " hospital_seq_no				varchar2(32), "
			+ " item_code				varchar2(20), "
			+ " exam_class				varchar2(20), "
			+ " exam_sun_class				varchar2(20), "
			+ " do_dept_code				varchar2(20), "
			+ " clin_diag				varchar2(1000), "
			+ " exam_time				date, "
			+ " exam_doctor				varchar2(20), "
			+ " exam_machine				varchar2(40), "
			+ " exam_position				varchar2(40), "
			+ " image_no				varchar2(40), "
			+ " report_doctor				varchar2(20), "
			+ " report_time				date, "
			+ " abnormal_flag				number(1), "
			+ " comments				varchar2(40), "
			+ " constraint pk_"+tablename
			+ "	primary key "
			+ "	( "
			+ "	seq_no "
			+ "	) "
			+ " using index "
			+ "	pctfree		5 "
			+ "	storage "
			+ "	( "
			+ "	initial		4K "
			+ "	next			2K "
			+ "	minextents		1 "
			+ "	maxextents		unlimited "
			+ "	pctincrease		0 "
			+ "	) "
			+ " tablespace tsp_archives "
			+ "	) "
			+ " pctfree				5 "
			+ " pctused				90 "
			+ " storage "
			+ " ( "
			+ " initial				4K "
			+ " next					2K "
			+ " minextents				1 "
			+ " maxextents				unlimited "
			+ " pctincrease				0 "
			+ " ) ";
		/*创建表后授权限的sql语句*/
		String grantsql = " grant select,insert,update,delete "
						+ " on "+tablename
						+ " to	public ";
		try {
			conn = DBTools.getConnection();
			ss = conn.createStatement();
			ss.execute(creattabelstr);
			ss.execute(grantsql);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			DBTools.close(ss);
			DBTools.close(conn);
		}
		return true;
	}
	
	public int droptables(String tablename) throws SQLException{
		
		/*GetConnection ccn = new GetConnection();
		Connection conn = null;
		PreparedStatement ps = null;*/
		try {
			/*ResourceBundle bundle = ResourceBundle.getBundle("/conf/comm/creattable");
			String url = bundle.getString("url");
			String port = bundle.getString("port");
			String sid = bundle.getString("sid");
			String user = bundle.getString("user");
			String passwd = bundle.getString("passwd");
			
		    conn = DriverManager.getConnection("jdbc:oracle:thin:@"+url+":"+port+":"+sid,user,passwd);*/
			
			String lab_master_tablename = "T0001"+tablename;
			String lab_detail_tablename = "T0002"+tablename;
			String lab_report_tablename = "T0003"+tablename;
			String exam_master_tablename = "T0004"+tablename;
			String exam_detail_tablename = "T0005"+tablename;
			String exam_report_tablename = "T0006"+tablename;
			
			boolean reflag = this.istableexist(lab_master_tablename);
			if(reflag == true){
				this.droptablebytablename(lab_master_tablename);
			}
			reflag = this.istableexist(lab_report_tablename);
			if(reflag == true){
				this.droptablebytablename(lab_report_tablename);
			}
			reflag = this.istableexist(lab_detail_tablename);
			if(reflag == true){
				this.droptablebytablename(lab_detail_tablename);
			}
			reflag = this.istableexist(exam_report_tablename);
			if(reflag == true){
				this.droptablebytablename(exam_report_tablename);
			}
			reflag = this.istableexist(exam_master_tablename);
			if(reflag == true){
				this.droptablebytablename(exam_master_tablename);
			}
			reflag = this.istableexist(exam_detail_tablename);
			if(reflag == true){
				this.droptablebytablename(exam_detail_tablename);
			}
			return 100;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int droptablebytablename(String tablename) throws SQLException{
		
		Connection conn = null;
		Statement ss = null;
		String droptablesql = "drop table " + tablename;
		try {
			
			conn = DBTools.getConnection();
			ss = conn.createStatement();
			ss.execute(droptablesql);
			/*Statement stmt = conn.createStatement();
			stmt.executeUpdate(droptablesql);
			stmt.close();
			conn.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			DBTools.close(ss);
			DBTools.close(conn);
		}
		return 1;
	}
	
}
