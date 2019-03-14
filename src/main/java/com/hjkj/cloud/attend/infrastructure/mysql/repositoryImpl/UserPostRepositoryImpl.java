package com.hjkj.cloud.attend.infrastructure.mysql.repositoryImpl;

import com.hjkj.cloud.attend.domain.model.UserPost;
import com.hjkj.cloud.attend.domain.repository.IUserPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserPostRepositoryImpl implements IUserPostRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUserPostBatch(List<UserPost> userPosts) {
        String sql = "insert into user_post(user_id,post_id) values(?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, userPosts.get(i).getUserId());
                ps.setString(2, userPosts.get(i).getPostId());
            }

            @Override
            public int getBatchSize() {
                return userPosts.size();
            }
        });
        logger.info(sql);
    }
}
