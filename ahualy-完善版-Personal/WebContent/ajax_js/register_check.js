
var message1=1;
var message2=1;
var message3=1;
var message4=1;
var message5=1;
function check_register_loginname() {
		//先用js代码做校验,如果校验通过再通过ajax向服务器提交数据
       
		var loginname=document.getElementById("loginname").value;
			 $.ajax({
	             url:"check_Register_loginname",
	             type:"post",
	             data:{"loginname":loginname},
	             dataType:"text",
	             beforeSend:function(){
	             },
	             success:function(message){
	            	 message1=message;
	            	 if(message!=""){
	            		 document.getElementById("loginname").focus();
	            		 document.getElementById("loginname").style.border="1px solid rgb(229,58,49)";
	            	
	            	 }else{
	            		 $('#loginname')[0].style.border="1px solid #ccc";
	            	 }
	            	 
	            	document.getElementById("loginname_message").value = message;
	             }
	          });
}

function check_register_username() {
	//先用js代码做校验,如果校验通过再通过ajax向服务器提交数据
   
	var username=document.getElementById("username").value;
		 $.ajax({
             url:"check_Register_username",
             type:"post",
             data:{"username":username},
             dataType:"text",
             beforeSend:function(){
             },
             success:function(message){
            	 message2 = message;
            	 if(message!=""){
            		 document.getElementById("username").focus();
            		 document.getElementById("username").style.border="1px solid rgb(229,58,49)";
            	 }else{
            		 $('#username')[0].style.border="1px solid #ccc";
            	 }
            	document.getElementById("username_message").value = message;
             }
          });
}

function check_register_email() {
	//先用js代码做校验,如果校验通过再通过ajax向服务器提交数据

	var email=document.getElementById("email").value;
		 $.ajax({
             url:"check_Register_email",
             type:"post",
             data:{"email":email},
             dataType:"text",
             beforeSend:function(){
             },
             success:function(message){
            	 message3=message;
            	 if(message!=""){
            		 document.getElementById("email").focus();
            		 document.getElementById("email").style.border="1px solid rgb(229,58,49)";
            	 }else{
            		 $('#email')[0].style.border="1px solid #ccc";
            	 }
            	document.getElementById("email_message").value = message;
             }
          });
}

function check_register_password() {
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
            	document.getElementById("password_message").value = message;
             }
          });
}


function check_register_repassword() {
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

function checkMessage() {
	var password=document.getElementById("password").value;
	var username=document.getElementById("username").value;
	var loginname=document.getElementById("loginname").value;
	var repassword=document.getElementById("repassword").value;
	var email=document.getElementById("email").value;
	var params = {
            "password":password,
            "username":username,
            "loginname":loginname,
            "repassword":repassword,
            "email":email
            
        }
	  $.ajax({
         url:"checkMessage",
         type:"post",
         data:params,
         dataType:"text",
         beforeSend:function(){
         },
         success:function(message){
             	document.getElementById("register_check_message").value = message;
             	if(message==""&&message1==""&&message2==""&&message3==""&&message4==""&&message5==""){
             		 $("form").submit();
             	}
             	if(message!=""){
             		if(message=="用户名和密码不能为空,请重新输入"){
             			document.getElementById("loginname").focus();
             			document.getElementById("loginname").style.border="1px solid rgb(229,58,49)";
             			document.getElementById("username").style.border="1px solid rgb(229,58,49)";
             			document.getElementById("email").style.border="1px solid rgb(229,58,49)";
             			document.getElementById("password").style.border="1px solid rgb(229,58,49)";
             			document.getElementById("repassword").style.border="1px solid rgb(229,58,49)";
                   		document.getElementById("register_check_message").value = "请输入你的个人信息";
             		}
             	}
             	}
        
      });
	
}











