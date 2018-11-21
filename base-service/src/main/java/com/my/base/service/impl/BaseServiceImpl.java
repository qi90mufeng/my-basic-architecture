package com.my.base.service.impl;

import java.util.List;

import com.my.base.dto.PageDTO;
import com.my.base.service.BaseService;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private Mapper<T> mapper;

    public BaseServiceImpl() {
    }

    public BaseServiceImpl(Mapper<T> mapper) {
        this.mapper = mapper;
    }

    public Mapper<T> getMapper() {
        return this.mapper;
    }

    @Override
    public PageDTO<T> page(Example example, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageDTO<T>(getMapper().selectByExample(example));
    }

    @Override
    public List<T> list(Example example) {
        return getMapper().selectByExample(example);
    }

    @Override
    public T getById(Long id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public T getById(String id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public T selectOne(T entity) {
        return getMapper().selectOne(entity);
    }

    @Override
    public List<T> select(T entity) {
        return getMapper().select(entity);
    }

    @Override
    public int insert(T entity) {
        return getMapper().insert(entity);
    }

    @Override
    public int insertSelective(T entity) {
        return getMapper().insertSelective(entity);
    }

    @Override
    public int update(T entity) {
        return getMapper().updateByPrimaryKey(entity);
    }

    @Override
    public int updateSelective(T entity) {
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByExample(T entity, Example example) {
        return getMapper().updateByExample(entity, example);
    }

    @Override
    public int updateByExampleSelective(T entity, Example example) {
        return getMapper().updateByExampleSelective(entity, example);
    }

}
