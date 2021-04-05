package com.hyb.travel.service.impl;

import com.hyb.travel.dao.CategoryDao;
import com.hyb.travel.dao.impl.CategoryDaoImpl;
import com.hyb.travel.domain.Category;
import com.hyb.travel.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
