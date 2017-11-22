package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.dao.IStaffBatchCreateDAO;
import com.tianjian.security.struts.form.StaffBatchCreateForm;
import com.tianjian.util.Converter;

public class StaffBatchCreateDAO extends HibernateDaoSupport implements IStaffBatchCreateDAO{
	
	private String createQuerySql(StaffBatchCreateForm sbcForm){
		StringBuilder sb = new StringBuilder();
		sb.append("select h.id, h.item_code, h.item_name, count(s.id), nvl(u.id, ''), nvl(i.id, '') from hsp.hsp_config_baseinfo h left join security.security_staff_baseinfo s on h.id = s.hsp_config_baseinfo_id left join (select distinct(h.id) as id from hsp.hsp_config_baseinfo h, security.security_staff_baseinfo s where h.item_code = s.staff_code) u on h.id=u.id left join (select distinct(h.id) as id from hsp.hsp_config_baseinfo h, security.security_staff_baseinfo s，security.security_user_vs_roles uvr, security.security_config_roles r where h.item_code=s.staff_code and h.id = s.hsp_config_baseinfo_id and s.id = uvr.security_staff_baseinfo_id and uvr.security_config_roles_id = r.id and r.role_code='9') i on h.id=i.id group by h.id, h.item_code, h.item_name, nvl(u.id, ''), nvl(i.id, '')");
		if("filter".equals(sbcForm.getFilter())){
			sb.append(" having count(s.id)=0 ");
		}
		return sb.toString();
	}

	@Override
	public List<?> list(StaffBatchCreateForm sbcForm) {
		StringBuilder sql = new StringBuilder(this.createQuerySql(sbcForm));
		sql.append(" order by ");
		if("2".equals(sbcForm.getOrderNo())){
			sql.append(" h.item_name ");
		}else if("3".equals(sbcForm.getOrderNo())){
			sql.append(" count(s.id) ");
		}else if("4".equals(sbcForm.getOrderNo())){
			sql.append(" nvl(u.id, '') ");
		}else if("5".equals(sbcForm.getOrderNo())){
			sql.append(" nvl(i.id, '') ");
		}else{
			sql.append(" h.item_code ");
		}
		if("1".equals(sbcForm.getAsc())){
			sql.append(" desc ");
		}
		Query q = this.getSession().createSQLQuery(sql.toString());
		List<?> list = q.list();
		return list;
	}
	
	@Override
	public int getCount(StaffBatchCreateForm sbcForm){
		String sql = "select count(*) from (" + this.createQuerySql(sbcForm) + ")";
		Query q = this.getSession().createSQLQuery(sql);
		List<?> list = q.list();
		if(list != null && list.size() > 0){
			int count = Converter.toInteger(list.get(0));
			return count;
		}
		return 0;
	}

	private StringBuilder createSelectedHspIdSql(String[] selectedHspIds){
		StringBuilder sb = new StringBuilder(" and (id in(");
		for(int i = 0 ; i < selectedHspIds.length; i++){
			if ((i+1) % 1000 == 0) {//in里面最多只能有1000个
				sb.delete(sb.length() - 1, sb.length());
				sb.append(") or id in (");
			}
			sb.append("'").append(selectedHspIds[i]).append("',");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("))");
		return sb;
	}
	@Override
	public int createBySelectedHspIds(String[] selectedHspIds, String defaultPassword) {
		if(selectedHspIds == null){
			return 0;
		}
		int count = 0;
		try{
			StringBuilder selectedHspIdSql = this.createSelectedHspIdSql(selectedHspIds);
			//插入操作员表
			StringBuilder staff_sql = new StringBuilder("insert into security.security_staff_baseinfo (id, staff_code, hsp_config_baseinfo_id, name, comm_config_sex_id, date_of_birth, comm_config_stafftype_id, islocation, create_date)");
			staff_sql.append("(select sys_guid(), item_code, id, '卫生人员维护', '9', sysdate, '9', '1', sysdate from hsp.hsp_config_baseinfo where 1=1 ");
			staff_sql.append(selectedHspIdSql);
			staff_sql.append(" and item_code not in (select nvl(staff_code, '') from security.security_staff_baseinfo))");
			count = this.getSession().createSQLQuery(staff_sql.toString()).executeUpdate();
			
			//插入操作人员许可证表
			StringBuilder license_sql = new StringBuilder("insert into security.security_license(security_staff_baseinfo_id, regist_code, reg_time,start_time)");
			license_sql.append("(select id, substr(sys_guid(), 0, 25), sysdate, sysdate from security.security_staff_baseinfo ");
			license_sql.append(" where (staff_code, hsp_config_baseinfo_id) in (select item_code, id from hsp.hsp_config_baseinfo where 1=1 ").append(selectedHspIdSql).append(")");
			license_sql.append(" and id not in (select nvl(security_staff_baseinfo_id,'') from security.security_license))");
			this.getSession().createSQLQuery(license_sql.toString()).executeUpdate();
			//插入操作人员密码表
			StringBuilder system_users_sql = new StringBuilder("insert into security.security_system_users(security_staff_baseinfo_id, passwd, license_tag, create_date)");
			system_users_sql.append("(select id, '").append(defaultPassword).append("', '1', sysdate from security.security_staff_baseinfo ");
			system_users_sql.append(" where (staff_code, hsp_config_baseinfo_id) in (select item_code, id from hsp.hsp_config_baseinfo where 1=1 ").append(selectedHspIdSql).append(")");
			system_users_sql.append(" and id not in (select nvl(security_staff_baseinfo_id,'') from security.security_system_users))");
			this.getSession().createSQLQuery(system_users_sql.toString()).executeUpdate();
			//插入用户角色对照表
			StringBuilder user_vs_roles_sql = new StringBuilder("insert into security.security_user_vs_roles(id, security_staff_baseinfo_id, security_config_roles_id)");
			user_vs_roles_sql.append("(select sys_guid(), s.id, r.id from security.security_staff_baseinfo s, (select id from security.security_config_roles where role_code='9') r where (staff_code, hsp_config_baseinfo_id) in (select item_code, id from hsp.hsp_config_baseinfo where 1=1 ").append(selectedHspIdSql).append(")");
			user_vs_roles_sql.append(" and s.id not in (select nvl(security_staff_baseinfo_id,'') from security.security_user_vs_roles))");
			this.getSession().createSQLQuery(user_vs_roles_sql.toString()).executeUpdate();
		}catch(RuntimeException e){
			count = 0;
			throw e;
		}
		return count;
	}

	@Override
	public int createAll(StaffBatchCreateForm sbcForm, String defaultPassword) {
		int count = 0;
		try{
			//插入操作员表
			StringBuilder staff_sql = new StringBuilder("insert into security.security_staff_baseinfo (id, staff_code, hsp_config_baseinfo_id, name, comm_config_sex_id, date_of_birth, comm_config_stafftype_id, islocation, create_date)");
			staff_sql.append("(select sys_guid(), item_code, id, '卫生人员维护', '9', sysdate, '9', '1', sysdate from hsp.hsp_config_baseinfo where 1=1 ");
			if("filter".equals(sbcForm.getFilter())){
				staff_sql.append(" and id in(select h.id from hsp.hsp_config_baseinfo h left join security.security_staff_baseinfo s on h.id = s.hsp_config_baseinfo_id group by h.id having count(s.id)=0) ");
			}
			staff_sql.append(" and item_code not in (select nvl(staff_code, '') from security.security_staff_baseinfo))");
			count = this.getSession().createSQLQuery(staff_sql.toString()).executeUpdate();
			
			//插入操作人员许可证表
			StringBuilder license_sql = new StringBuilder("insert into security.security_license(security_staff_baseinfo_id, regist_code, reg_time,start_time)");
			license_sql.append("(select id, substr(sys_guid(), 0, 25), sysdate, sysdate from security.security_staff_baseinfo where (staff_code, hsp_config_baseinfo_id) in (select item_code, id from hsp.hsp_config_baseinfo)");
			license_sql.append(" and id not in (select nvl(security_staff_baseinfo_id,'') from security.security_license))");
			this.getSession().createSQLQuery(license_sql.toString()).executeUpdate();
			//插入操作人员密码表
			StringBuilder system_users_sql = new StringBuilder("insert into security.security_system_users(security_staff_baseinfo_id, passwd, license_tag, create_date)");
			system_users_sql.append("(select id, '").append(defaultPassword).append("', '1', sysdate from security.security_staff_baseinfo where (staff_code, hsp_config_baseinfo_id) in (select item_code, id from hsp.hsp_config_baseinfo)");
			system_users_sql.append(" and id not in (select nvl(security_staff_baseinfo_id,'') from security.security_system_users))");
			this.getSession().createSQLQuery(system_users_sql.toString()).executeUpdate();
			//插入用户角色对照表
			StringBuilder user_vs_roles_sql = new StringBuilder("insert into security.security_user_vs_roles(id, security_staff_baseinfo_id, security_config_roles_id)");
			user_vs_roles_sql.append("(select sys_guid(), s.id, r.id from security.security_staff_baseinfo s, (select id from security.security_config_roles where role_code='9') r where (staff_code, hsp_config_baseinfo_id) in (select item_code, id from hsp.hsp_config_baseinfo) ");
			user_vs_roles_sql.append(" and s.id not in (select nvl(security_staff_baseinfo_id,'') from security.security_user_vs_roles))");
			this.getSession().createSQLQuery(user_vs_roles_sql.toString()).executeUpdate();
		}catch(RuntimeException e){
			count = 0;
			throw e;
		}
		return count;
	}

}
