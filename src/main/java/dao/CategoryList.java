package dao;

import java.util.List;

public class CategoryList {
    List<String> categoryList;
    int selected;

    public CategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
