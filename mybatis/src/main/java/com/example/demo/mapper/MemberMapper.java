package com.example.demo.mapper;

import com.example.demo.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<Member> selectAll();
    Member selectById(@Param("id") Long id);
    Member selectByEmail(@Param("email") String email);
    int insert(@Param("member") Member member);
    int update(@Param("id") Long id, @Param("member") Member member);
    int delete(@Param("id") Long id);
}
