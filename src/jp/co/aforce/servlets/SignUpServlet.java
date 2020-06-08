package jp.co.aforce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.SignUpBean;
import jp.co.aforce.models.SignUpModel;

public class SignUpServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        // GETリクエストはあり得ないので、無条件でログイン画面に飛ばす
        RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/signup.jsp");
        rDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        // 文字のエンコードを UTF-8 とする。これがないと文字化け。
        request.setCharacterEncoding("UTF-8");

        // ユーザによって入力された情報を取り出す
        SignUpBean signUpBean = new SignUpBean();
        String name = request.getParameter("name");
        String ageString = request.getParameter("age");
        String birthyearString= request.getParameter("birth_year");
        String birthmonthString = request.getParameter("birth_month");
        String birthdayString = request.getParameter("birth_day");
        //nameの文字列の長さを取得;
        int namelength = name.length();
        try {
            int age = Integer.parseInt(ageString);
            int birthyear = Integer.parseInt(birthyearString);
            int birthmonth = Integer.parseInt(birthmonthString);
            int birthday = Integer.parseInt(birthdayString);
        //数字をintへ返還

            if ((namelength <= 0)||(namelength >= 21)||(age <= 0)||(age >=150)) {

            	// 書き漏れ、間違いのあるときの処理
            	signUpBean.setEmsg("入力されていない項目があります。");
            	request.setAttribute("signUpBean", signUpBean);

            	// 書き漏れのないときの処理
            } else {
            	// 取り出した情報を loginBean に格納する
            	signUpBean.setName(name);
            	signUpBean.setAge(age);
            	signUpBean.setBirthyear(birthyear);
            	signUpBean.setBirthmonth(birthmonth);
            	signUpBean.setBirthday(birthday);

            	// モデルをインスタンス化する
            	SignUpModel signUpModel = new SignUpModel();
            	//会員情報の登録
            	if (signUpModel.setUserDate(name,age,birthyear,birthmonth,birthday)) {
            		signUpBean.setSuccess("会員登録に成功しました。");
            		request.setAttribute("signUpBean", signUpBean);
            	}else {
            		signUpBean.setEmsg("会員登録に失敗しました。");
            		request.setAttribute("signUpBean", signUpBean);
            	}
            }
        }catch (Exception e) {
        	// 書き漏れ、間違いのあるときの処理
        	signUpBean.setEmsg("入力されていない項目があります。");
        	request.setAttribute("signUpBean", signUpBean);

        }finally {
        	// forwaed_jsp に設定されているJSPへディスパッチ
        	RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/signup.jsp");
        	rDispatcher.forward(request, response);
        }
}
}