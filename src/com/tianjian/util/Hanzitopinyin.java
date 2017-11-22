package com.tianjian.util;

import java.util.ArrayList;
import java.util.List;

import com.tianjian.comm.dao.ICommonDAO;

public class Hanzitopinyin {
	private  ICommonDAO comm;
	public  String zhuanhuan(String name){
		String nameString="";
		if(name!=null&&name.trim().length()>0){
			List<String> nameList=new ArrayList<String>();
			for(int i=0;i<name.trim().length();i++){
				if(i+1<=name.trim().length()){
					String namePart=name.trim().substring(i, i+1);
					nameList.add(namePart);
				}
			}
			if(nameList.size()>0){
				for(int i=0;i<nameList.size();i++){
					if(i==0){
						nameString += PinyinUtil.hanziToPinyin(nameList.get(i));
					}else{
						char[] c1= nameList.get(i).toCharArray();
						char[] c=PinyinUtil.getHeadByChar(c1[0], false);
						nameString += c[0];
					}
				}
			}
			String hql="from SecurityStaffBaseinfo a where a.staffCode = ?";
			List<?> list=comm.findListByHql(hql,new String[]{nameString});
			if(list.size()==0){
				return nameString;
			}else{
				String hql1="from SecurityStaffBaseinfo a where ( a.staffCode like '"+nameString+"0%' or a.staffCode like '"+nameString+"1%' or " +
						"a.staffCode like '"+nameString+"2%' or a.staffCode like '"+nameString+"3%' or a.staffCode like '"+nameString+"4%' or " +
								"a.staffCode like '"+nameString+"5%' or a.staffCode like '"+nameString+"6%' or a.staffCode like '"+nameString+"7%' or " +
										"a.staffCode like '"+nameString+"8%' or a.staffCode like '"+nameString+"9%' )";
				List<?> list1= comm.findListByHql(hql1);
				if(list1.size()<9){
					nameString =nameString +"00" + String.valueOf(list1.size()+1);
				}else if(list1.size()>=9&&list1.size()<99){
					nameString =nameString +"0" + String.valueOf(list1.size()+1);
				}else{
					nameString =nameString + String.valueOf(list1.size()+1); 
				}
				return nameString;
			}
		}else{
			return nameString;
		}
	}
	public ICommonDAO getComm() {
		return comm;
	}
	public void setComm(ICommonDAO comm) {
		this.comm = comm;
	}
}
