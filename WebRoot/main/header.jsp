<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script language="javascript">
function cmdView(flag,id,url){
		document.form.publicClassId.value = id; 
	    document.form.verbId.value = "login";
	    document.form.target="_parent";
		document.form.submit(); 
	}

</script>
    <div id="header">
				<img src="main/include/images/login_03.jpg" width="536" height="85" />
                <ul class="nav">
                	<%
						if(stauts != null && !stauts.equals("1")){ 
					%>
					<li class="user">欢迎您！<%=sessionForm.getStaffName()%></li>
					<%
						}else{
				 	%>
				 	<li class="user">欢迎您！</li>
				 	<%
					 	 }
				 	%>
					<li><a href="<%=request.getContextPath()%>/security/security_login.do?verbId=reLogin">返回首页</a></li>
					<li><a href="<%=request.getContextPath()%>/index.jsp?agree=true">退出系统</a></li>
				</ul>
				
				<%
					if(stauts != null && !stauts.equals("1")){ 
				%>
                <div id="topNav">
                  <div id="ca-container" class="ca-container">
                        <div class="ca-wrapper">
                        	<% 
								if(sessionForm.getPublicClassIds()!=null && sessionForm.getPublicClassIds().length>0){ 
									for(int i = 0;i < sessionForm.getPublicClassIds().length; i++){
							%>	
							
							 <div class="ca-item">
                                <div class="ca-item-main">
                                    <div class="ca-icon"><a href="javascript:void(0);" onclick="cmdView('<%=sessionForm.getPublicClassSysFlags()[i]%>','<%=sessionForm.getPublicClassIds()[i] %>','<%=sessionForm.getPublicClassRedirectUrls()[i]%>');" title="<%=sessionForm.getPublicClassNames()[i]%>"> </a> </div>
                                    <h3><a href="javascript:void(0);" onclick="cmdView('<%=sessionForm.getPublicClassSysFlags()[i]%>','<%=sessionForm.getPublicClassIds()[i] %>','<%=sessionForm.getPublicClassRedirectUrls()[i]%>');" title="<%=sessionForm.getPublicClassNames()[i]%>"><%=sessionForm.getPublicClassNames()[i]%> </a> </h3>
                                </div>
                            </div>								
										
							<%
									}
								}
							 %>
                        </div>	
                           
                	</div>
				</div>
				
				<%
					}
				 %>
		</div>