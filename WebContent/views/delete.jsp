<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href = "../css/menustyle.css" rel = "stylesheet">
<link href = "./css/menustyle.css" rel = "stylesheet">
<title>会員情報削除</title>
</head>
<body>
<div class = page_center>
会員情報削除画面
<!-- 入力フォーム -->
  <form action="/MemberInformation/Delete" method="post">
<p>
        <span class = font>　　　会員番号　　　</span><input type="text" class = textbox name="member_no" size="30" value =${requestScope.memberInfo[0].memberno}>
        <input type="submit" value="表示" name = delete formaction = "/MemberInformation/Select"class = button_sign>
  </p>
    <p>
  		<span class = emsg>${requestScope.memberNoBean.emsg}</span>
  </p>
  <!-- 入力項目 -->
  <p>
  		<span class = emsg>${requestScope.updateBean.emsg}</span><span class = smsg>${requestScope.updateBean.success}</span>
  </p>
  <p>
        <span class = font>　　　名前　　　</span><input type="text" class = textbox name="name" size="30" readonly value =${requestScope.memberInfo[0].name}>
  </p>
  <p>
			<span>　　　年齢　　　</span> <input type="text" class = textbox name="age" size="30" readonly value =${requestScope.memberInfo[0].age}>
  </p>
  <p>
			<span>　　　生年月日　　</span>  <select class = selectbox name="birth_year">
				<option value=${requestScope.memberInfo[0].birthyear}>${requestScope.memberInfo[0].birthyear}</option>
			</select> <select class = selectbox name="birth_month">
				<option value=${requestScope.memberInfo[0].birthmonth}>${requestScope.memberInfo[0].birthmonth}</option>
			</select> <select class = selectbox name="birth_day">
				<option value=${requestScope.memberInfo[0].birthday}>${requestScope.memberInfo[0].birthday}</option>
			</select>
  </p>

  <!-- 登録ボタン -->
		<a href ="./index.html"><input type = "button" value = "戻る" class = button_sign ></a>　　　<input type="submit" value="変更" class = button_sign>
</form>
</div>
</body>
</html>