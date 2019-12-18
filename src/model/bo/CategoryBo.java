package model.bo;

import java.util.ArrayList;

import model.bean.CategoryBean;
import model.dao.CategoryDao;

public class CategoryBo {
	private CategoryDao categoryDao = new CategoryDao();
	
	public ArrayList<CategoryBean> getCategories() throws Exception{
		return categoryDao.getCategories();
	}
	
	public CategoryBean getCategoryByID(String id) throws Exception{
        return categoryDao.getCategoryByID(id);
    }
	
	public int addCategory(String idCategory, String name) throws Exception {
		return categoryDao.addCategory(idCategory, name);
	}
	
	public int updateCategory(String idCategory, String name) throws Exception {
		return categoryDao.updateCategory(idCategory, name);
	}
	
	public int deleteCategory(String idCategory) throws Exception {
		return categoryDao.delelteCategory(idCategory);
	}
}
