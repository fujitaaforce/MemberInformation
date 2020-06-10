package jp.co.aforce.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.util.DBUtil;

public class DeleteModel {

    public boolean updateUserDate(String memberno, String name, int age, int birthyear, int birthmonth, int birthday) {
        // 実行結果を格納する変数
    	ResultSet rs = null;

        try {
            // DBに接続するための手続
            DBUtil.makeConnection();
            DBUtil.makeStatement();

            //membernoに格納されている値がデータべースにあるか確認
            List<String> memberNoList = new ArrayList<String>();
            String sql = "SELECT `member_no` FROM `members`";
            rs  = DBUtil.execute(sql);
            memberNoList.add(rs.getString("member_no"));
            while (rs.next()) {
				memberNoList.add(rs.getString("member_no"));
			}
            if (memberNoList.contains(memberno)) {

            	// SQLを実行
            	sql = "DELETE FROM `members` WHERE `member_no` = '" + memberno + "' AND `name` ='" + name + "' AND `age` ='" + age +"' AND `birth_year` ='" + birthyear +"' AND `birth_month` ='" + birthmonth + "' AND `birth_day` ='" + birthday + "'" ;
            	DBUtil.execute(sql);
            	return true;

            }else {
            	return false;
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	DBUtil.closeConnection();
        }
        return rs != null;
    }
}