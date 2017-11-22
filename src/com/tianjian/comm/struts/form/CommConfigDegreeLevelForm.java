package com.tianjian.comm.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tianjian.comm.bean.CommConfigDegreeLevel;
/**
 * Created by TemplateActionForm
 * @author atEcd
 * @since 2009-4-1 17:55:44
 */
public class CommConfigDegreeLevelForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private String itemCode = "";
	private String itemName = "";
	private String inputCode = "";
	private String levelFlag = "";
	private String parentItemCode = "";
	private String comments = "";
	private String seqNo = "";
	
	private List<CommConfigDegreeLevel> parentItemList;

	private String[] itemCodeList;
	private String[] itemNameList;
	private String[] inputCodeList;
	private String[] levelFlagList;
	private String[] parentItemCodeList;
	private String[] parentItemCodeNameList;
	private String[] commentsList;
	private String[] seqNoList;

	private String parentItemCodeName = "";

	private String[] parentItemCodes;
	private String[] parentItemCodeNames;

	private String verbId = "";
	private String message = "";
	private String orderNo = "";
	private String asc = "";
	private String idHidden = "";

	public void setItemCode(String itemCode){
		this.itemCode=itemCode;
	}
	public String getItemCode(){
		return this.itemCode;
	}
	public void setItemCodeList(String[] itemCodeList){
		this.itemCodeList=itemCodeList;
	}
	public String[] getItemCodeList(){
		return this.itemCodeList;
	}
	public void setItemName(String itemName){
		this.itemName=itemName;
	}
	public String getItemName(){
		return this.itemName;
	}
	public void setItemNameList(String[] itemNameList){
		this.itemNameList=itemNameList;
	}
	public String[] getItemNameList(){
		return this.itemNameList;
	}
	public void setInputCode(String inputCode){
		this.inputCode=inputCode;
	}
	public String getInputCode(){
		return this.inputCode;
	}
	public void setInputCodeList(String[] inputCodeList){
		this.inputCodeList=inputCodeList;
	}
	public String[] getInputCodeList(){
		return this.inputCodeList;
	}
	public void setLevelFlag(String levelFlag){
		this.levelFlag=levelFlag;
	}
	public String getLevelFlag(){
		return this.levelFlag;
	}
	public void setLevelFlagList(String[] levelFlagList){
		this.levelFlagList=levelFlagList;
	}
	public String[] getLevelFlagList(){
		return this.levelFlagList;
	}
	public void setParentItemCode(String parentItemCode){
		this.parentItemCode=parentItemCode;
	}
	public String getParentItemCode(){
		return this.parentItemCode;
	}
	public void setParentItemCodeList(String[] parentItemCodeList){
		this.parentItemCodeList=parentItemCodeList;
	}
	public String[] getParentItemCodeList(){
		return this.parentItemCodeList;
	}
	public void setParentItemCodeName(String parentItemCodeName){
		this.parentItemCodeName=parentItemCodeName;
	}
	public String getParentItemCodeName(){
		return this.parentItemCodeName;
	}
	public void setParentItemCodes(String[] parentItemCodes){
		this.parentItemCodes=parentItemCodes;
	}
	public String[] getParentItemCodes(){
		return this.parentItemCodes;
	}
	public void setParentItemCodeNames(String[] parentItemCodeNames){
		this.parentItemCodeNames=parentItemCodeNames;
	}
	public String[] getParentItemCodeNames(){
		return this.parentItemCodeNames;
	}
	public void setComments(String comments){
		this.comments=comments;
	}
	public String getComments(){
		return this.comments;
	}
	public void setCommentsList(String[] commentsList){
		this.commentsList=commentsList;
	}
	public String[] getCommentsList(){
		return this.commentsList;
	}
	public void setSeqNo(String seqNo){
		this.seqNo=seqNo;
	}
	public String getSeqNo(){
		return this.seqNo;
	}
	public void setSeqNoList(String[] seqNoList){
		this.seqNoList=seqNoList;
	}
	public String[] getSeqNoList(){
		return this.seqNoList;
	}
	public void setVerbId(String verbId){
		this.verbId=verbId;
	}
	public String getVerbId(){
		return this.verbId;
	}
	public void setMessage(String message){
		this.message=message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	public String getOrderNo(){
		return this.orderNo;
	}
	public void setAsc(String asc){
		this.asc=asc;
	}
	public String getAsc(){
		return this.asc;
	}
	public void setIdHidden(String idHidden){
		this.idHidden=idHidden;
	}
	public String getIdHidden(){
		return this.idHidden;
	}
	public String[] getParentItemCodeNameList() {
		return parentItemCodeNameList;
	}
	public void setParentItemCodeNameList(String[] parentItemCodeNameList) {
		this.parentItemCodeNameList = parentItemCodeNameList;
	}
	public List<CommConfigDegreeLevel> getParentItemList() {
		return parentItemList;
	}
	public void setParentItemList(List<CommConfigDegreeLevel> parentItemList) {
		this.parentItemList = parentItemList;
	}
}
