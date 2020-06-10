package jp.co.aforce.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.MemberNoBean;
import jp.co.aforce.util.DBUtil;

public class SelectModel {

    /**
     * 入力されたデータがDBに上に存在するかどうかを調べる。
     *
     * @param user_id ユーザID
     * @param password パスワード
     * @return ログイン成功=true, 失敗=false
     */
	public List<MemberNoBean> selectUserDate(String memberno){

        try {
            // DBに接続するための手続
            DBUtil.makeConnection();
            DBUtil.makeStatement();
            // SQLを実行
            String sql = "SELECT * FROM `members` WHERE member_no = '" + memberno + "'";
            ResultSet rs = DBUtil.execute(sql);

            List<MemberNoBean> MemberNoBeanList = new ArrayList<MemberNoBean>();
            MemberNoBean memberNoBean = new MemberNoBean();
            memberNoBean.setMemberno(memberno);
            memberNoBean.setName(rs.getString("name"));
            memberNoBean.setAge(rs.getInt("age"));
            memberNoBean.setBirthyear(rs.getInt("birth_year"));
            memberNoBean.setBirthmonth(rs.getInt("birth_month"));
            memberNoBean.setBirthday(rs.getInt("birth_day"));
            MemberNoBeanList.add(memberNoBean);

            return MemberNoBeanList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
		return null;
	}
}