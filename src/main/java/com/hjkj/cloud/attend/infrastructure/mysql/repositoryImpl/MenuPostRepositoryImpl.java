package com.hjkj.cloud.attend.infrastructure.mysql.repositoryImpl;

import com.hjkj.cloud.attend.domain.model.MenuPost;
import com.hjkj.cloud.attend.domain.repository.IMenuPostRepository;
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
public class MenuPostRepositoryImpl implements IMenuPostRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertMenuPostBatch(List<MenuPost> menuPosts) {
        String sql = "insert into menu_post(menu_id,post_id) values(?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, menuPosts.get(i).getMenuId());
                ps.setString(2, menuPosts.get(i).getPostId());
            }

            @Override
            public int getBatchSize() {
                return menuPosts.size();
            }
        });
        logger.info(sql);
    }
}
