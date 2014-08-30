<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QeePHP 敏捷开发框架 - test</title>
<style type="text/css">
/*<![CDATA[*/
body {
	font-family: Verdana, Geneva, sans-serif;
	background-color: #222C57;
	font-size: 14px;
	line-height: 160%;
}

#container {
    width: 64em;
	background-color: White;
	margin: 2em auto;
    padding: 0;
	border: 1px solid #333;
    background-color: #eee;
}

#contents {
	padding: 1em 2em;
    margin: 0;
    background-color: #fff;
    width: 37em;
    float: left;
}

#sidebar {
    width: 24em;
    margin-left: 0.4em;
    padding: 1em 0.5em 1em 1em;
    float: left;
    font-size: 12px;
    line-height: 120%;
}

#sidebar p {
	text-indent: 2em;
	margin-top: 0.5em;
	margin-bottom: 1em;
}

#sidebar ul {
	margin-left: 1.5em;
	margin-top: 0.5em;
	padding-left: 0;
}

#sidebar li a {
	font-size: 14px;
	line-height: 160%;
}

.no-float {
    float: none;
    clear: both;
}

h1, h2, h3 {
    margin-bottom: 0;
    padding-bottom: 0.1em;
    padding-top: 1em;
}

h1 {
	font-size: 1.6em;
}

h2 {
	font-size: 1.4em;
}

h3 {
	font-size: 1.2em;
}

pre {
	background-color: #333;
	padding: 1em;
}
code {
	color: White;
	font-family: Consolas, "Courier New", Courier, monospace;
}
.footnote {
	margin-top :5em;
	font-size: 0.85em;
	text-align: center;
}

a {
	color: #069;
	text-decoration: none;
}

a:hover {
	color: #000;
	text-decoration: underline;
}

th {
	text-align: left;
	background-color: #333;
	color: #fff;
	padding-left: 4px;
}

td {
	border-bottom: 1px solid #999;
	padding-left: 4px;
}

tr.odd {
	background-color: #fff;
}

tr.even {
	background-color: #ffc;
}
/*]]>*/
</style>
</head>
<body>
<div id="container">

  <div id="contents">
<?php

//var_dump(changePwdToken('ali18626258389','123456'));
var_dump(delUserToken('ali18626258389'));
//var_dump(registerToken('ali18626258389','123456'));


//授权注册模式 POST /{org_name}/{app_name}/users
function registerToken($nikename,$pwd)
{
	$formgettoken="https://a1.easemob.com/easemob-playground/test1/users";
	$body=array(
		"username"=>$nikename,
		"password"=>$pwd,
	);
	$patoken=json_encode($body);
	$header = array(_get_token());
	$res = _curl_request($formgettoken,$patoken,$header);

	$arrayResult =  json_decode($res, true);	
	return $arrayResult ;
}
//重置用户密码 PUT /{org_name}/{app_name}/users/{username}/password
function changePwdToken($nikename,$newpwd)
{
	$formgettoken="https://a1.easemob.com/easemob-playground/test1X/users/".$nikename."/password";
	$body=array(
		"newpassword"=>$newpwd,
	);
	$patoken=json_encode($body);
	$header = array(_get_token());
	$method = "PUT";
	$res = _curl_request($formgettoken,$patoken,$header,$method);
	$arrayResult =  json_decode($res, true);	
	return $arrayResult ;
}
//删除 DELETE /{org_name}/{app_name}/users/{username}
function delUserToken($nikename)
{
	$formgettoken="https://a1.easemob.com/easemob-playground/test1/users/".$nikename;
	$body=array();
	$patoken=json_encode($body);
	$header = array(_get_token());
	$method = "DELETE";
	$res = _curl_request($formgettoken,$patoken,$header,$method);
	$arrayResult =  json_decode($res, true);	
	return $arrayResult ;
}


//先获取app管理员token POST /{org_name}/{app_name}/token
function _get_token()
{
	$formgettoken="https://a1.easemob.com/easemob-playground/test1/token";
	$body=array(
	"grant_type"=>"client_credentials",
	"client_id"=>"YXA6wDs-MARqEeSO0VcBzaqg5A",
	"client_secret"=>"YXA6JOMWlLap_YbI_ucz77j-4-mI0JA"
	);
	$patoken=json_encode($body);
	$res = _curl_request($formgettoken,$patoken);
	$tokenResult = array();
	
	$tokenResult =  json_decode($res, true);
	//var_dump($tokenResult);
	return "Authorization: Bearer ". $tokenResult["access_token"];	
}

function _curl_request($url, $body, $header = array(), $method = "POST")
{
	array_push($header, 'Accept:application/json');
	array_push($header, 'Content-Type:application/json');

	$ch = curl_init();
	curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 60);
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
	//curl_setopt($ch, $method, 1);
	
	switch ($method){ 
		case "GET" : 
			curl_setopt($ch, CURLOPT_HTTPGET, true);
		break; 
		case "POST": 
			curl_setopt($ch, CURLOPT_POST,true); 
		break; 
		case "PUT" : 
			curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT"); 
		break; 
		case "DELETE":
			curl_setopt ($ch, CURLOPT_CUSTOMREQUEST, "DELETE"); 
		break; 
	}
	
	curl_setopt($ch, CURLOPT_USERAGENT, 'SSTS Browser/1.0');
	curl_setopt($ch, CURLOPT_ENCODING, 'gzip');
	curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
	curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 1);
	if (isset($body{3}) > 0) {
		curl_setopt($ch, CURLOPT_POSTFIELDS, $body);
	}
	if (count($header) > 0) {
		curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
	}

	$ret = curl_exec($ch);
	$err = curl_error($ch);

	curl_close($ch);
	//clear_object($ch);
	//clear_object($body);
	//clear_object($header);

	if ($err) {
		return $err;
	}

	return $ret;
}

?>

  </div>

  <div class="no-float"></div>

</div>

</body>
</html>