package sttnf.app.nfkita.models;

import java.util.ArrayList;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class NewsModel {

    private ArrayList<Post> posts;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public static class Post {
        private int id;
        private String title;
        private String content;
        private String slug;
        private String date;
        private String url;
        private Author author;
        private ArrayList<Attachment> attachments;
        private int comment_count;
        private String thumbnail;
        private ArrayList<CategoryModel.Categories> categories;
        private ArrayList<Comments> comments;

        public static class Comments {
            private String name;
            private String content;
            private String date;

            public String getName() {
                return name;
            }

            public String getContent() {
                return content;
            }

            public String getDate() {
                return date;
            }
        }

        public static class Author {
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }

        public static class Attachment {
            private int id;
            private String url;
            private Images images;

            public int getId() {
                return id;
            }

            public String getUrl() {
                return url;
            }

            public Images getImages() {
                return images;
            }

            public static class Images {
                private Thumbnail thumbnail;

                public Thumbnail getThumbnail() {
                    return thumbnail;
                }

                public static class Thumbnail {
                    private String url;

                    public String getUrl() {
                        return url;
                    }
                }
            }
        }

        public int getId() {
            return id;
        }

        public ArrayList<Comments> getComments() {
            return comments;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getSlug() {
            return slug;
        }

        public String getDate() {
            return date;
        }

        public String getUrl() {
            return url;
        }

        public Author getAuthor() {
            return author;
        }

        public ArrayList<Attachment> getAttachments() {
            return attachments;
        }

        public int getCommentCount() {
            return comment_count;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public ArrayList<CategoryModel.Categories> getCategories() {
            return categories;
        }
    }
}
