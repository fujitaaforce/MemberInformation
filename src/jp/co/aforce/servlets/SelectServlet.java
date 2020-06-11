package jp.co.aforce.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.MemberNoBean;
import jp.co.aforce.models.SelectModel;

public class SelectServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// GETリクエストはあり得ないので、無条件でメニュー画面に飛ばす
		RequestDispatcher rDispatcher = request.getRequestDispatcher("./index.html");
		rDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// 文字のエンコードを UTF-8 とする。これがないと文字化け。
		request.setCharacterEncoding("UTF-8");

		// ユーザによって入力された情報を取り出す
		String memberno = request.getParameter("member_no");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");

		//遷移元URLの取得
		String url = request.getHeader("REFERER");
		String forward_jsp = url.substring(39);
		if((forward_jsp.length() == 7)&&(update != null)){
			forward_jsp = "/views/update.jsp";
		}else if ((forward_jsp.length() == 7) && (delete != null)){
			forward_jsp = "/views/delete.jsp";
		}else {
		}

		//情報の照会
		SelectModel selectModel = new SelectModel();
		List<MemberNoBean> memberInfo = selectModel.selectUserDate(memberno);
		request.setAttribute("memberInfo", memberInfo);
		if (memberInfo == null) {
			MemberNoBean memberNoBean = new MemberNoBean();
			memberNoBean.setEmsg("該当する会員情報が見つかりません。");
			request.setAttribute("memberNoBean", memberNoBean);
		}else {
		}

		// forwaed_jsp に設定されているJSPへディスパッチ
		RequestDispatcher rDispatcher = request.getRequestDispatcher(forward_jsp);
		rDispatcher.forward(request, response);



	}
}