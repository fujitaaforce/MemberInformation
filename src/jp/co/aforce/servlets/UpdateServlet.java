package jp.co.aforce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.UpdateBean;
import jp.co.aforce.models.UpdateModel;

public class UpdateServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// GETリクエストはあり得ないので、無条件でログイン画面に飛ばす
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/update.jsp");
		rDispatcher.forward(request, response);
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// 文字のエンコードを UTF-8 とする。これがないと文字化け。
		request.setCharacterEncoding("UTF-8");

		// ユーザによって入力された情報を取り出す
		UpdateBean updateBean = new UpdateBean();
		String memberno = request.getParameter("member_no");
		String nameString = request.getParameter("name");
		String ageString = request.getParameter("age");
		String birthyearString= request.getParameter("birth_year");
		String birthmonthString = request.getParameter("birth_month");
		String birthdayString = request.getParameter("birth_day");
		//nameの文字列の長さを取得;
		String name = nameString.strip();
		int namelength = name.length();
		try {
			int birthyear = Integer.parseInt(birthyearString);
			int birthmonth = Integer.parseInt(birthmonthString);
			int birthday = Integer.parseInt(birthdayString);
			//数字をintへ返還

			if ((namelength <= 0)||(namelength >= 21)) {

				// 書き漏れ、間違いのあるときの処理
				updateBean.setEmsg("入力されていない項目があります。");
				request.setAttribute("updateBean", updateBean);

				// 書き漏れのないときの処理
			} else {

				// モデルをインスタンス化する
				UpdateModel updateModel = new UpdateModel();

				//会員情報の登録
				if ((updateModel.updateUserDate(memberno,name,ageString,birthyear,birthmonth,birthday))&&(memberno != "" )) {
					updateBean.setSuccess("変更に成功しました。");
					request.setAttribute("updateBean", updateBean);
				}else {
					updateBean.setEmsg("変更に失敗しました。");
					request.setAttribute("updateBean", updateBean);
				}
			}
		}catch (Exception e) {
			// 書き漏れ、間違いのあるときの処理
			updateBean.setEmsg("入力されていない項目があります。");
			request.setAttribute("updateBean", updateBean);

		}finally {
			// forwaed_jsp に設定されているJSPへディスパッチ
			RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/update.jsp");
			rDispatcher.forward(request, response);
		}
	}
}