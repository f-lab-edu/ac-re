package com.project.acre.repository;

import com.project.acre.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void join(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("rownum");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", member.getId());
        parameters.put("password", member.getPassword());
        parameters.put("name", member.getName());
        parameters.put("nickname", member.getNickname());
        parameters.put("birth", member.getBirth());
        parameters.put("email", member.getEmail());
        parameters.put("phone", member.getPhone());
        parameters.put("classify", member.getClassify());

        Number key = jdbcInsert.executeAndReturnKey(parameters);
        member.setKey(key.longValue());
    }

    @Override
    public Optional<Member> findMember(String id, String password) {
        String sql = "select * from member where id = ? and password = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    sql,
                    new RowMapper<Member>() {
                        @Override
                        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Member member = new Member();
                            member.setId(rs.getString("id"));
                            member.setPassword(rs.getString("password"));
                            member.setName(rs.getString("name"));
                            member.setNickname(rs.getString("nickname"));
                            member.setBirth(rs.getString("birth"));
                            member.setEmail(rs.getString("email"));
                            member.setPhone(rs.getString("phone"));
                            member.setClassify(rs.getString("classify"));

                            return member;
                        }
                    },
                    id,
                    password
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
