function userLogin(){
    		 //测试绑定事件  alert("堵哪了");
    		 var name=$("#count").val().trim();
    		 var password = $("#password").val().trim();
    		 
    		// alert(name+":"+password);
    		var ok=true;
    		//格式检查
    		if(name==""){
    			$("#count_span").html("用户名不能为空");
    			ok=false;
    		}
    		if(password==""){
    			$("#password_span").html("密码不能为空");
    			ok=false;
    		}
    		
    		//发送请求
    		if(ok){
    			$.ajax({
    				url:path+"/user/login.do",
    				type:"post",
    				data:{"name":name,"password":password},
    				dataType:"json",
    				success:function(result){
    					//result是服务器返回的JSON结果
    					if(result.status==0){
    						var userId = result.data.cn_user_id;
    						addCookie("userId",userId,2);
    						window.location.href="edit.html";
    					}else if(result.status==1){
    						$("count_span").html(result.msg);
    					}else if(result.status==2){
    						$("password_span").html(result.msg);
    					}
    				},
    				error:function(){
    					alert(name+":"+password+" 登录失败！");
    				}
    			});
    			 
    		}
    		 
    	 }