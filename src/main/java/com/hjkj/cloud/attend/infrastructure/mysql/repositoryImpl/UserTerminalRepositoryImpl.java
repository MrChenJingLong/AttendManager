package com.hjkj.cloud.attend.infrastructure.mysql.repositoryImpl;

import com.hjkj.cloud.attend.domain.model.UserTerminal;
import com.hjkj.cloud.attend.domain.repository.IUserTerminalRepository;
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
public class UserTerminalRepositoryImpl implements IUserTerminalRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUserTerminalBatch(List<UserTerminal> userTerminals) {
        String sql = "insert into user_terminal(user_id,terminal_id) values(?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, userTerminals.get(i).getUserId());
                ps.setString(2, userTerminals.get(i).getTerminalId());
            }

            @Override
            public int getBatchSize() {
                return userTerminals.size();
            }
        });
        logger.info(sql);
    }
}
