package jp.co.aforce.models;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.aforce.util.DBUtil;

public class SignUpModel {

	/**
	 * 入力されたデータがDBに上に存在するかどうかを調べる。
	 *
	 * @param user_id ユーザID
	 * @param password パスワード
	 * @return ログイン成功=true, 失敗=false
	 */
	public boolean setUserDate(String name, String ageString, int birthyear, int birthmonth, int birthday) {
		// 実行結果を格納する変数
		ResultSet rs = null;

		try {
			// DBに接続するための手続
			DBUtil.makeConnection();
			DBUtil.makeStatement();

			//現在時刻の取得
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddhhmmss");
			String strDate = dateFormat.format(date);
			String memberno ="A"+strDate;

			//ageのint変換
			int age = Integer.parseInt(ageString);

			//name文字列の空白削除と空白の場合のreturn
			String insertName = name.strip();
			if((insertName.length() <= 0)||(insertName.length() >= 21)||(age <= 0)||(age >=150)) {
				return false;
			}else {
				// SQLを実行
				String sql = "INSERT INTO `members`(`member_no`, `name`, `age`, `birth_year`, `birth_month`, `birth_day`)"
						+ "VALUES ('" + memberno +"','" + insertName +"','" +age+"','" + birthyear +"','" + birthmonth + "','" + birthday + "')";
				DBUtil.execute(sql);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return rs != null;
	}
}