<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>视频播放</title>
</head>
<body>

<div id="a1"></div>
<script type="text/javascript" src="${ctx }/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
	var flashvars={
		p:0,
		e:1,
		i:'${ctx }/upload/suoluetu.png'
		};
	var video=['${ctx }/upload/${requestScope.filename}->video/mp4'];
	//Personal/WebContent/WEB-INF/upload/01mybatis.mp4
	var support=['all'];
	CKobject.embedHTML5('a1','ckplayer_a1',600,400,video,flashvars,support);
</script>
</body>
</html>