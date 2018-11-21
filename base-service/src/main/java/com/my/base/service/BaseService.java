package com.my.base.service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

import com.my.base.dto.PageDTO;

public interface BaseService<T> {
    public PageDTO<T> page(Example example, int pageNum, int pageSize);

    public List<T> list(Example example);

    public T getById(Long id);

    public T getById(String id);

    public T selectOne(T entity);

    public List<T> select(T entity);

    public int insert(T entity);

    public int insertSelective(T entity);

    public int update(T entity);

    public int updateSelective(T entity);

    public int updateByExample(T entity, Example example);

    public int updateByExampleSelective(T entity, Example example);
}
