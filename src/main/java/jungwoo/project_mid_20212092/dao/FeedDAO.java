package jungwoo.project_mid_20212092.dao;

import jungwoo.project_mid_20212092.domain.FeedVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FeedDAO {

    public String getTime(){

        String now = null;

        try(Connection connection = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select now()");
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            resultSet.next();

            now = resultSet.getString(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return now;
    }


    public List<FeedVO> selectAll()throws Exception  {

        String sql = "select * from feed";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();


        List<FeedVO> list = new ArrayList<>();

        while(resultSet.next()) {
            FeedVO vo = FeedVO.builder()
                    .feedId(resultSet.getLong("feedId"))
                    .content(resultSet.getString("content"))
                    .modifiedDate(resultSet.getTimestamp("modifiedDate").toLocalDateTime())
                    .build();

            list.add(vo);
        }

        return list;
    }

    public void insert(FeedVO vo) throws Exception {
        String sql = "insert into feed (content, modifiedDate) values (?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, vo.getContent());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(vo.getModifiedDate()));

        preparedStatement.executeUpdate();
    }

    public FeedVO selectOne(Long feedId) throws Exception {
        String sql = "select * from feed where feedId = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, feedId);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        FeedVO vo = FeedVO.builder()
                .feedId(resultSet.getLong("feedId"))
                .content(resultSet.getString("content"))
                .modifiedDate(resultSet.getTimestamp("modifiedDate").toLocalDateTime())
                .build();

        return vo;
    }

    public void deleteOne(Long feedId) throws Exception {
        String sql = "delete from feed where feedId = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, feedId);

        preparedStatement.executeUpdate();
    }

    public void updateOne(FeedVO feedVO) throws Exception {
        String sql = "update feed set content = ?, modifiedDate = ? where feedId = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, feedVO.getContent());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(feedVO.getModifiedDate()));
        preparedStatement.setLong(3, feedVO.getFeedId());

        preparedStatement.executeUpdate();
    }

}