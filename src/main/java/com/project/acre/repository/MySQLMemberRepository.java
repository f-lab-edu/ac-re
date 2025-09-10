package com.project.acre.repository;

import com.project.acre.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MySQLMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySQLMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member join(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("rownum");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", member.getId());
        parameters.put("password", member.getPassword());
        parameters.put("name", member.getName());
        parameters.put("nickname", member.getNickname());
        parameters.put("email", member.getEmail());
        parameters.put("phone", member.getPhone());

        Number key = jdbcInsert.executeAndReturnKey(parameters);
        member.setKey(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findMember(String id, String password) {
        return null;
    }
}
