package com.tuling.crud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuling.zhouyuspring.spring.Component;
import org.apache.ibatis.annotations.Mapper;

import java.awt.print.Book;

@Mapper

public interface BookDao extends BaseMapper<Book> {
}