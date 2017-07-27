package sttnf.app.nfkita.models;

import java.util.ArrayList;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class CategoryModel {

    private int count;
    private ArrayList<Categories> categories;

    public static class Categories {
        private int id;
        private String slug;
        private String title;
        private int post_count;

        public String getSlug() {
            return slug;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getPost_count() {
            return post_count;
        }
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }
}
