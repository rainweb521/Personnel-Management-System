
var message1=1;
var message2=1;
var message4=1;
var message5=1;


function checkLoginname() {
		//先用js代码做校验,如果校验通过再ajax向服务器提交数据
		var loginname=document.getElementById("loginname").value;
			 $.ajax({
	             url:"checkLoginName",
	             type:"post",
	             data:{"loginname":loginname},
	             dataType:"text",
	             beforeSend:function(){
	             },
	             success:function(message){
	            	 message1 = message;
	            	 if(message!=""){
	            		 document.getElementById("loginname").focus();
	            		 $('#loginname')[0].style.border="1px solid rgb(229,58,49)";
	            	 }else{
	            		 $('#loginname')[0].style.border="1px solid #ccc";
	            	 }
	            	document.getElementById("loginname_message").value = message;
	             }
	     });
}


function checkUsername() {
	//先用js代码做校验,如果校验通过再进行ajax想服务器发送数据
	//其实可以通过用户名去查询手机号码，如果相等，就返回空消息，否则就提示用户用户名和密码不匹配
	var username=document.getElementById("username").value;
	var loginname=document.getElementById("loginname").value;
		 $.ajax({
             url:"checkUsername",
             type:"post",
             data:{"username":username,"loginname":loginname},
             dataType:"text",
             beforeSend:function(){
             },
             success:function(message){
            	 message2 = message;
            	 if(message!=""){
            		 document.getElementById("username").focus();
            		 $('#username')[0].style.border="1px solid rgb(229,58,49)";
            	 }else{
            		 $('#username')[0].style.border="1px solid #ccc";
            	 }
            	document.getElementById("username_message").value = message;
             }
     });
}




function check_register_password1() {
	//先用js代码做校验,如果校验通过再通过ajax向服务器提交数据

	var password=document.getElementById("password").value;
		 $.ajax({
             url:"check_Register_password",
             type:"post",
             data:{"password":password},
             dataType:"text",
             beforeSend:function(){
             },
             success:function(message){
            	 message4 = message;
            	 if(message!=""){
            		 document.getElementById("password").focus();
            		 document.getElementById("password").style.border="1px solid rgb(229,58,49)";
            	 }else{
            		 $('#password')[0].style.border="1px solid #ccc";
            	 }
            	document.getElementById("psd_message").value = message;
             }
          });
}


function check_register_repassword1() {
	//先用js代码做校验,如果校验通过再通过ajax向服务器提交数据

	var repassword=document.getElementById("repassword").value;
	var password=document.getElementById("password").value;
	var params = {
			 "password":password,
             "repassword":repassword
        }
		 $.ajax({
             url:"check_Register_repassword",
             type:"post",
             data:params,
             dataType:"text",
             beforeSend:function(){
             },
             success:function(message){
            	 message5 = message;
            	 if(message!=""){
            		 document.getElementById("repassword").focus();
            		 document.getElementById("repassword").style.border="1px solid rgb(229,58,49)";
            	 }else{
            		 $('#repassword')[0].style.border="1px solid #ccc";
            	 }
            	document.getElementById("repassword_message").value = message;
             }
 });
}



function tofind() {
	var message3=document.getElementById("user_input_verifyCode").value;
	if(message1==""&& message2==""&& message3!=""){
		 $("form").submit();
	}else if(message1!=""&& message2!="" && message3==""){
		  document.getElementById("loginname").focus();
		  $('#loginname')[0].style.border="1px solid rgb(229,58,49)";
		  $('#username')[0].style.border="1px solid rgb(229,58,49)";
		  $('#user_input_verifyCode')[0].style.border="1px solid rgb(229,58,49)";
		document.getElementById("find_message").value = "请输入您的相关信息";
	}else if(message1==""&& message2!="" && message3!=""){
		  document.getElementById("username").focus();
		  $('#username')[0].style.border="1px solid rgb(229,58,49)";
		  $('#user_input_verifyCode')[0].style.border="1px solid rgb(229,58,49)";
		  document.getElementById("find_message").value = "请输入您的用户名";
	}else if(message1==""&& message2=="" && message3==""){
		  document.getElementById("user_input_verifyCode").focus();
		  $('#user_input_verifyCode')[0].style.border="1px solid rgb(229,58,49)";
		  document.getElementById("find_message").value = "请输入校验码";
	}
}


function toUpdata(){
	var password=document.getElementById("password").value;
	var repassword=document.getElementById("repassword").value;
	 if(message4==""&&message5==""){
		 $("form").submit();
	}else{
		 document.getElementById("password").focus();
		 $('#password')[0].style.border="1px solid rgb(229,58,49)";
		 $('#repassword')[0].style.border="1px solid rgb(229,58,49)";
		 document.getElementById("updata_message").value = "请重新输入您的新密码";
	}
}

