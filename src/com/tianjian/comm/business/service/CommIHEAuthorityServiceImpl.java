package com.tianjian.comm.business.service;



import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tianjian.comm.bean.CommIHEAuthority;
import com.tianjian.comm.business.ICommIHEAuthorityService;
import com.tianjian.comm.dao.ICommIHEAuthorityDAO;
import com.tianjian.comm.struts.form.CommIHEAuthorityForm;

public class CommIHEAuthorityServiceImpl implements ICommIHEAuthorityService {
	private static final Logger log = LogManager.getLogger(CommIHEAuthorityServiceImpl.class);
	private ICommIHEAuthorityDAO commIHEAuthorityDAO;
	//------------------------DAO-- 澹版槑缁撴潫---------------------------------------------------
	
	public ICommIHEAuthorityDAO getCommIHEAuthorityDAO() {
		return commIHEAuthorityDAO;
	}

	public void setCommIHEAuthorityDAO(ICommIHEAuthorityDAO commIHEAuthorityDAO) {
		this.commIHEAuthorityDAO = commIHEAuthorityDAO;
	}

	public void addInit(CommIHEAuthorityForm form){
		init(form);
		form.setSeqNo(String.valueOf(this.commIHEAuthorityDAO.seqNoMaker("CommIHEAuthority")));
	}
	
	public void serchInit(CommIHEAuthorityForm form){
		searchInit(form);
	}
	/**鑾峰彇璁板綍瀵硅薄*/
	public CommIHEAuthority checkData(String id){
		CommIHEAuthority data = commIHEAuthorityDAO.findById(id);
		return data;
	}
	/**鎻愪氦淇濆瓨*/
	public void save(CommIHEAuthorityForm form){
		CommIHEAuthority data = new CommIHEAuthority();
		this.setData(form, data);
		commIHEAuthorityDAO.save(data);
	}
	/**淇敼涔嬪墠鍒濆鍖�*/
	public void updateInit(CommIHEAuthorityForm form){
		CommIHEAuthority data = commIHEAuthorityDAO.findById(form.getIdHidden());
		this.setForm(form, data);
		init(form);
	}
	/**鎻愪氦淇敼*/
	public void update(CommIHEAuthorityForm form){
		CommIHEAuthority data = commIHEAuthorityDAO.findById(form.getIdHidden());
		this.setData(form, data);
		commIHEAuthorityDAO.update(data);
	}
	/**鎻愪氦瀵熺湅璇︾粏*/
	public void showInit(CommIHEAuthorityForm form){
		CommIHEAuthority data = commIHEAuthorityDAO.findById(form.getIdHidden());
		form = new CommIHEAuthorityForm();
		this.setForm(form, data);
		init(form);
	}
	/**鎻愪氦鍒犻櫎*/
	public void delete(CommIHEAuthorityForm form){
		CommIHEAuthority data = commIHEAuthorityDAO.findById(form.getIdHidden());
		commIHEAuthorityDAO.delete(data);
	}
	/**鑾峰彇鎬昏褰曟暟*/
	public int getCount(String universalId, String universalIdType, String namespaceId, String seqNo){
		return commIHEAuthorityDAO.getCount(universalId, universalIdType, namespaceId, seqNo);
	}
	/**鏍规嵁鏌ヨ鏉′欢鑾峰彇褰撳墠鏄剧ず椤电殑鏁版嵁*/
	public void getSearch(CommIHEAuthorityForm form, int curCount, int pageSize){
		try{
			 
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.universalId";
		} else if(form.getOrderNo().equals("2")){
			order = " a.universalIdType";
		} else if(form.getOrderNo().equals("3")){
			order = " a.namespaceId";
		} else if(form.getOrderNo().equals("4")){
			order = " a.seqNo";
		} else {
			order = " a.seqNo";
		}
		
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
		
		List<?> list = commIHEAuthorityDAO.getData(form.getUniversalId()
								, form.getUniversalIdType()
								, form.getNamespaceId()
								, form.getSeqNo()
								, order
								, curCount
								, pageSize);
		 
		if(list != null && list.size() > 0){
			String[] ids = new String[list.size()];
			String[] universalIds = new String[list.size()];
			String[] universalIdTypes = new String[list.size()];
			String[] namespaceIds = new String[list.size()];
			String[] dateCreateds = new String[list.size()];
			String[] creatorIds = new String[list.size()];
			String[] seqNos = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				CommIHEAuthority data = (CommIHEAuthority)list.get(i);
				ids[i] = this.transNullToString(data.getId());
				universalIds[i] = this.transNullToString(data.getUniversalId());
				universalIdTypes[i] = this.transNullToString(data.getUniversalIdType());
				namespaceIds[i] = this.transNullToString(data.getNamespaceId());
				Date date = data.getDateCreated();
				String dateString = "";
				if(date != null){
					dateString = DateFormatUtils.format(date, "yyyy-MM-dd");
				}
				dateCreateds[i] = this.transNullToString(dateString);
				creatorIds[i] = this.transNullToString(data.getCreatorId());
				seqNos[i] = this.transNullToString(String.valueOf(data.getSeqNo()));
//				ids[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
//				universalIds[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
//				universalIdTypes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
//				namespaceIds[i] = String.valueOf(transNullToString(((Object[])list.get(i))[3]));		
//				dateCreateds[i] = String.valueOf(transNullToString(((Object[])list.get(i))[4]));
//				dateCreateds[i] = String.valueOf(transNullToString(((Object[])list.get(i))[4]));
//				creatorIds[i] = String.valueOf(transNullToString(((Object[])list.get(i))[5]));		
//				seqNos[i] = ((Object[])list.get(i))[6] == null ? "0" : String.valueOf(((Object[])list.get(i))[6]);
				 
			}
			form.setIdList(ids);
			form.setUniversalIdList(universalIds);
			form.setUniversalIdTypeList(universalIdTypes);
			form.setNamespaceIdList(namespaceIds);
			form.setDateCreatedList(dateCreateds);
			form.setCreatorIdList(creatorIds);
			form.setSeqNoList(seqNos);	 
		}
		 } catch (Exception e) { 
			 log.error("getSearch fail!",e);
			 e.printStackTrace();
	} 
	}
	/**鏋勯�燿ata*/
	private void setData(CommIHEAuthorityForm form, CommIHEAuthority data){
		try{
//				data.setId(this.transNullToString(form.getId()));
				data.setUniversalId(transNullToString(form.getUniversalId()));
				data.setUniversalIdType(transNullToString(form.getUniversalIdType()));
				data.setNamespaceId(transNullToString(form.getNamespaceId()));
				data.setDateCreated(new Date(System.currentTimeMillis()));
				data.setCreatorId(transNullToString(form.getCreatorId()));
				data.setSeqNo(Long.valueOf((form.getSeqNo() == null || form.getSeqNo().trim() == "") ? "0" : form.getSeqNo()));
			 } catch (Exception e) { 
				 log.error("setData fail!",e);
				 e.printStackTrace();
		} 
	}
	/**
	 * 鍘绘帀瀛椾覆涓殑绌烘牸
	 * */
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	
	public String transNullToZero(Object obj){
		String temp = "0";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	/**鍦ㄤ紶閫掓暟鎹埌椤甸潰涔嬪墠鏋勯�爁orm*/
	private void setForm(CommIHEAuthorityForm form, CommIHEAuthority data){
		try{
			
			Date date = data.getDateCreated();
			String dateString = "";
			if(date != null){
				dateString = DateFormatUtils.format(date, "yyyy-MM-dd");
			}
			form.setId(transNullToString(data.getId()));
			form.setUniversalId(transNullToString(data.getUniversalId()));
			form.setUniversalIdType(transNullToString(data.getUniversalIdType()));
			form.setNamespaceId(transNullToString(data.getNamespaceId()));
			form.setDateCreated(transNullToString(dateString)); 
			form.setCreatorId(transNullToString(data.getCreatorId())); 
			form.setSeqNo(data.getSeqNo() == 0 ? "0" : String.valueOf(data.getSeqNo()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
	/**鍦ㄥ垵濮嬪寲闃舵灏嗗瓧鍏稿簱浼犲叆form涓�*/
	private void init(CommIHEAuthorityForm form){
		
	}
	/***/
	public void getDetail(CommIHEAuthorityForm form){
	
	}
	/**鑾峰彇鏌ヨ缁撴灉涔嬪墠鍒濆鍖�*/
	/**鍦ㄨ繘琛屾煡鎵句箣鍓嶅皢瀛楀吀搴撲紶鍏orm涓�*/
	private void searchInit(CommIHEAuthorityForm form){
	}


	
}
